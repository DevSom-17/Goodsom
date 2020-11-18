package com.example.goodsom.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.GroupBuy;

/**
 * @author hk
 * @since 2020.06.13
 */
public interface GroupBuyMapper { // GroupBuyMapper.xml에서 구현한 method 이름과 일치해야함!
	
	GroupBuy getGroupBuy(int groupBuyId) throws DataAccessException;

	void createGroupBuy(GroupBuy groupBuy) throws DataAccessException;

	int updateGroupBuy(GroupBuy groupBuy) throws DataAccessException;
	
	void deleteOptions(int groupBuyId) throws DataAccessException;
	
	void deleteGroupBuy(int groupBuyId) throws DataAccessException;
	
	int deleteGroupBuyByUserId(int userId) throws DataAccessException;
	
	void createOptions(GroupBuy groupBuy) throws DataAccessException;
	
	List<GroupBuy> getGroupBuyList() throws DataAccessException;
	
	void increaseCount(GroupBuy groupBuy) throws DataAccessException;
	
	int updateState(GroupBuy groupBuy) throws DataAccessException;
	
	void closeEvent(Date curTime) throws DataAccessException;
	
	void achieveEvent() throws DataAccessException;
	
	void updateGroupBuyNoti(int groupBuyId) throws DataAccessException;
	
	int[] getGroupBuyIdForNoti() throws DataAccessException;
	
	List<GroupBuy> getRecentGroupBuyList() throws DataAccessException;
	
//	keyword로 검색
	List<GroupBuy> groupBuyListByKeyword(String keyword);
}
