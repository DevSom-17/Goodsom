package com.example.goodsom.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Option;
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
public class GroupBuyServiceImpl implements GroupBuyService {
	
	@Autowired
	private GroupBuyDao groupBuyDao;
	
	@Autowired
	private NotificationDao notiDao;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
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

	public void deadLineScheduler(Date endDate, final int groupBuyId) {
		Runnable updateTableRunner = new Runnable() {	
			// anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
				Date curTime = new Date();
				// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경 
				groupBuyDao.closeEvent(curTime);	// EVENTS 테이블의 레코드 갱신	
				System.out.println("updateTableRunner is executed at " + curTime);
				
				String state = groupBuyDao.getGroupBuy(groupBuyId).getState();
				
				if(state.equals("closed")) {
					GroupBuy groupBuy = groupBuyDao.getGroupBuy(groupBuyId);
					notiDao.createNoti_g(groupBuy);
					notiDao.notiUserUpdate(groupBuyId);
					System.out.println("****closed groupBuy and create noti ");
				}
			}
		};
		
		// 스케줄 생성: closingTime에 updateTableRunner.run() 메소드 실행
		scheduler.schedule(updateTableRunner, endDate);  
		
		System.out.println("updateTableRunner has been scheduled to execute at " + endDate);

	}

}
