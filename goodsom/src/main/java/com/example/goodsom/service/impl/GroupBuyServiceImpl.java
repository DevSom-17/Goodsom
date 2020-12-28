package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.FileDao;
import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.LikeDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_g;
import com.example.goodsom.domain.Notification;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.NotiMailService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06
 */

/**
 * @author hk			| YeJin Lee
 * @since 2020.06.14	| 2020.12.07
 */

@Service
@Transactional
@Component
public class GroupBuyServiceImpl implements GroupBuyService {
	private static final String CLOSED = "closed";
	private static final String ACHIEVED = "achieved";
	@Autowired
	private GroupBuyDao groupBuyDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private NotificationDao notiDao;
	
	@Autowired
	private NotiMailService notiMailService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LikeDao likeDao;
	
	public GroupBuy getGroupBuy(int groupBuyId) {
		return groupBuyDao.getGroupBuy(groupBuyId);
	}
	
	@Transactional
	public int createGroupBuy(GroupBuy groupBuy, List<Image_g> groupBuyImgs) {
		int groupBuyId = groupBuyDao.createGroupBuy(groupBuy);
		System.out.println("경매 생성 후 바로 받아온 auctionId: " + groupBuyId);
		for (Image_g groupBuyImg : groupBuyImgs) {
			groupBuyImg.setGroupBuyId(groupBuyId);
			System.out.println("사진 저장 전 auctionId=" + groupBuyImg.getGroupBuyId() + "의 fileNo: " + groupBuyImg.getFileNo() + " url:" + groupBuyImg.getUrl());
		}
		groupBuy.setImgs_g(groupBuyImgs);
		fileDao.saveGroupBuyImgs(groupBuyImgs);
		return groupBuyId;
	}
	
	@Transactional
	public int updateGroupBuy(GroupBuy groupBuy, List<Image_g> groupBuyImgs) {
		fileDao.deleteGroupBuyImgs(groupBuy.getGroupBuyId());
		int groupBuyId = groupBuyDao.updateGroupBuy(groupBuy);
		for (Image_g groupBuyImg : groupBuyImgs) {
			groupBuyImg.setGroupBuyId(groupBuyId);
			System.out.println("사진 저장 전 groupBuyId=" + groupBuyImg.getGroupBuyId() + "의 fileNo: " + groupBuyImg.getFileNo() + " url:" + groupBuyImg.getUrl());
		}
		groupBuy.setImgs_g(groupBuyImgs);
		fileDao.saveGroupBuyImgs(groupBuyImgs);
		return groupBuyId;
	}
	
	public void deleteOptions(int groupBuyId) {
		groupBuyDao.deleteOptions(groupBuyId);
	}
	
	@Transactional
	public void deleteGroupBuy(int groupBuyId) {
		groupBuyDao.deleteGroupBuy(groupBuyId);
	}
	
	public void createOptions(GroupBuy groupBuy) {
		groupBuy.optionSetting(groupBuy.getGroupBuyId());
		groupBuyDao.createOptions(groupBuy);
	}
	
	public void updateOptions(GroupBuy groupBuy) {
		groupBuyDao.deleteOptions(groupBuy.getGroupBuyId());
		groupBuy.optionSetting(groupBuy.getGroupBuyId());
		groupBuyDao.createOptions(groupBuy);
	}
	
	public List<GroupBuy> getGroupBuyList(int userId) {
		List<GroupBuy> groupBuys = groupBuyDao.getGroupBuyList();
		List<Integer> likedGroupBuyIds = likeDao.getLikedGroupBuyIdList(userId);
		for (GroupBuy groupBuy : groupBuys) {
			int id = groupBuy.getGroupBuyId();
			groupBuy.setLiked(0);
			for (int likedGroupBuyId : likedGroupBuyIds) {
				if (id == likedGroupBuyId) {
					groupBuy.setLiked(1);
					break;
				}
			}
		}
		return groupBuys;
	}

	public List<GroupBuy> getGroupBuyListByUserId(int userId) {
		return groupBuyDao.getGroupBuyListByUserId(userId);
	}

	public void increaseCount(GroupBuy groupBuy) {
		groupBuyDao.increaseCount(groupBuy);
	}
	
	public List<GroupBuy> getBestGroupBuyList() {
		return groupBuyDao.getBestGroupBuyList();
	}
	
	@Scheduled(fixedDelay = 1000)	// 1초마다
	public void deadLineScheduler() {	
		groupBuyDao.closeEvent();	// 마감 시간 확인 -> close로 업데이트
		groupBuyDao.achieveEvent();	// 달성 여부 확인 -> achieve로 업데이트
		
		int[] achieveId = groupBuyDao.getGroupBuyIdForAchieveNoti(); // 성사되었지만 알림을 보내지 않은 공구id
		int[] closeId = groupBuyDao.getGroupBuyIdForCloseNoti();	 // 마감되었지만 알림을 보내지 않은 공구id
		
		// 동시에 여러 공동구매가 달성될 경우
		for(int i = 0; i < achieveId.length; i++) { 
			int[] userId = notiDao.getUserIdByGroupBuyId(achieveId[i]); // 해당 공동구매에 참여한 유저
			GroupBuy groupBuy = groupBuyDao.getGroupBuy(achieveId[i]);
			
			if(groupBuy == null) break; // 달성공구가 없을때

			System.out.println("스케줄러 내의 state: " +achieveId[i]);
			
			// 공구 작성자
			try {
				User writer = userDao.getUserByUserId(groupBuy.getUserId());
				notiMailService.sendGroupBuyWriterAchieveMessage(writer.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Notification noti = new Notification();
			noti.setTitle(groupBuy.getTitle());
			noti.setGroupBuyId(achieveId[i]);
			noti.setState(ACHIEVED);
			
			// 공구에 참여한 모든 유저에게
			if(userId == null) break;
			for(int j = 0; j < userId.length; j++) {
				noti.setUserId(userId[j]);
				
				// 알림 생성: 유저가 알림 페이지에 들어갈 때 DB에서 읽어서 즉시 생성
				notiDao.createNoti_g(noti);
				
				// 메일 보내기 - 성사됐고 알림을 보내지 않았을 때
				try {
					// 공구 참여자
					User user = userDao.getUserByUserId(userId[j]);
					notiMailService.sendGroupBuyAchieveMessage(user.getEmail());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// 달성 알림을 보냈다는 표시
			groupBuyDao.updateAchieveNoti(achieveId[i]);
		}
		
		// 동시에 여러 공동구매가 마감될 경우
		for(int i = 0; i < closeId.length; i++) { 
			int[] userId = notiDao.getUserIdByGroupBuyId(closeId[i]); // 해당 공동구매에 참여한 유저
			GroupBuy groupBuy = groupBuyDao.getGroupBuy(closeId[i]);
			
			if(groupBuy == null) break; // 마감공구가 없을때

			System.out.println("스케줄러 내의 state: " +closeId[i]);
			
			// 공구 작성자
			try {
				User writer = userDao.getUserByUserId(groupBuy.getUserId());
				notiMailService.sendGroupBuyWriterCloseMessage(writer.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Notification noti = new Notification();
			noti.setTitle(groupBuy.getTitle());
			noti.setGroupBuyId(closeId[i]);
			noti.setState(CLOSED);
			
			// 공구에 참여한 모든 유저에게
			if(userId == null) break;
			for(int j = 0; j < userId.length; j++) {
				noti.setUserId(userId[j]);
				
				// 알림 생성: 유저가 알림 페이지에 들어갈 때 DB에서 읽어서 즉시 생성
				notiDao.createNoti_g(noti);
				
				// 메일 보내기 - 마감됐고 알림을 보내지 않았을 때
				try {
					// 공구 참여자
					User user = userDao.getUserByUserId(userId[j]);
					notiMailService.sendGroupBuyCloseMessage(user.getEmail());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// 마감 알림을 보냈다는 표시
			groupBuyDao.updateCloseNoti(closeId[i]);
		}
	}

	@Override
	public List<GroupBuy> getLikedGroupBuyListByUserId(int userId) {
		return groupBuyDao.getLikedGroupBuyListByUserId(userId);
	}

}
