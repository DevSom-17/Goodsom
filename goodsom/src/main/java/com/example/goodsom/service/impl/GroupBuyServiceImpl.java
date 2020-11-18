package com.example.goodsom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Notification;
import com.example.goodsom.service.GroupBuyService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06
 */

/**
 * @author hk
 * @since 2020.06.14
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
	private NotificationDao notiDao;
	
	public GroupBuy getGroupBuy(int groupBuyId) {
		return groupBuyDao.getGroupBuy(groupBuyId);
	}
	
	public void createGroupBuy(GroupBuy groupBuy) {
		groupBuyDao.createGroupBuy(groupBuy);
	}
	
	public int updateGroupBuy(GroupBuy groupBuy) {
		groupBuyDao.updateGroupBuy(groupBuy);
		return groupBuy.getGroupBuyId();
	}
	
	public void deleteOptions(int groupBuyId) {
		groupBuyDao.deleteOptions(groupBuyId);
	}
	
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
		Date curTime = new Date();
		groupBuyDao.closeEvent(curTime);	// 마감 시간 확인
		groupBuyDao.achieveEvent();			// 달성 여부 확인
		
		int[] groupBuyId = groupBuyDao.getGroupBuyIdForNoti();
		
		for(int i = 0; i < groupBuyId.length; i++) { // 동시에 여러 공동구매가 마감될 경우
			int[] userId = notiDao.getUserIdByGroupBuyId(groupBuyId[i]); // 해당 공동구매에 참여한 유저
			GroupBuy groupBuy = groupBuyDao.getGroupBuy(groupBuyId[i]);
			String state = groupBuy.getState();
			
			for(int j = 0; j < userId.length; j++) {
				Notification noti = new Notification();
				noti.setTitle(groupBuy.getTitle());
				noti.setUserId(userId[j]);
				noti.setGroupBuyId(groupBuyId[i]);
				noti.setState(state);
				
				// 알림 생성: 유저가 알림 페이지에 들어갈 때 DB에서 읽어서 즉시 생성
				notiDao.createNoti_g(noti);
				groupBuyDao.updateGroupBuyNoti(groupBuyId[i]);
			}
			
		}
	}
}
