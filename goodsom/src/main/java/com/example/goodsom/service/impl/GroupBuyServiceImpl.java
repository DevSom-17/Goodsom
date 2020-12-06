package com.example.goodsom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.FileDao;
import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;
import com.example.goodsom.domain.Notification;
import com.example.goodsom.service.GroupBuyService;

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
		fileDao.deleteGroupBuyImgs(groupBuyId);
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
	
	public List<GroupBuy> getGroupBuyList() {
		return groupBuyDao.getGroupBuyList();
	}

	public void increaseCount(GroupBuy groupBuy) {
		groupBuyDao.increaseCount(groupBuy);
	}
	
	public List<GroupBuy> getRecentGroupBuyList() {
		return groupBuyDao.getRecentGroupBuyList();
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
			
			if(userId == null || groupBuy == null) break; // 성사공구나 참여한 유저가 없을때

			System.out.println("스케줄러 내의 state: " +achieveId[i]);
			
			Notification noti = new Notification();
			noti.setTitle(groupBuy.getTitle());
			noti.setGroupBuyId(achieveId[i]);
			noti.setState(ACHIEVED);
			
			for(int j = 0; j < userId.length; j++) {
				noti.setUserId(userId[j]);
				
				// 알림 생성: 유저가 알림 페이지에 들어갈 때 DB에서 읽어서 즉시 생성
				notiDao.createNoti_g(noti);
			}
			// 달성 알림을 보냈다는 표시
			groupBuyDao.updateAchieveNoti(achieveId[i]);
		}
		
		// 동시에 여러 공동구매가 마감될 경우
		for(int i = 0; i < closeId.length; i++) { 
			int[] userId = notiDao.getUserIdByGroupBuyId(closeId[i]); // 해당 공동구매에 참여한 유저
			GroupBuy groupBuy = groupBuyDao.getGroupBuy(closeId[i]);
			
			if(userId == null || groupBuy == null) break; // 마감공구나 참여한 유저가 없을때

			System.out.println("스케줄러 내의 state: " +closeId[i]);
			
			Notification noti = new Notification();
			noti.setTitle(groupBuy.getTitle());
			noti.setGroupBuyId(closeId[i]);
			noti.setState(CLOSED);
			for(int j = 0; j < userId.length; j++) {
				noti.setUserId(userId[j]);
				
				// 알림 생성: 유저가 알림 페이지에 들어갈 때 DB에서 읽어서 즉시 생성
				notiDao.createNoti_g(noti);
				
			}
			// 마감 알림을 보냈다는 표시
			groupBuyDao.updateCloseNoti(closeId[i]);
		}
	}
}
