package com.example.goodsom.dao.mybatis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.mybatis.mapper.GroupBuyMapper;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;


/**
 * @author hk
 * @since 2020.06.13
 */

@Repository
public class MybatisGroupBuyDao implements GroupBuyDao {
	
	@Autowired
	private GroupBuyMapper groupBuyMapper;
	
	public GroupBuy getGroupBuy(int groupBuyId) throws DataAccessException{
		GroupBuy groupBuy = groupBuyMapper.getGroupBuy(groupBuyId);
		System.out.println(groupBuy.toString());
		for (Image_g img : groupBuy.getImgs_g()) {
			System.out.println("Auction의 Img["+img.getFileNo() + "]: " + img.getUrl());
		}
		return groupBuy;
	}

	public int createGroupBuy(GroupBuy groupBuy) throws DataAccessException{
		groupBuyMapper.createGroupBuy(groupBuy);
		return groupBuy.getGroupBuyId();
	}

	public int updateGroupBuy(GroupBuy groupBuy) throws DataAccessException{
		groupBuyMapper.updateGroupBuy(groupBuy);
		System.out.println("groupBuyId in mapper: " + groupBuy.getGroupBuyId());
		return groupBuy.getGroupBuyId();
	}
	
	public void deleteOptions(int groupBuyId) throws DataAccessException{
		groupBuyMapper.deleteOptions(groupBuyId);
	}
	
	public void deleteGroupBuy(int groupBuyId) throws DataAccessException{
		groupBuyMapper.deleteGroupBuy(groupBuyId);
	}
	
	public void createOptions(GroupBuy groupBuy) throws DataAccessException{
		groupBuyMapper.createOptions(groupBuy);
	}
	
	public List<GroupBuy> getGroupBuyList() throws DataAccessException{
		return groupBuyMapper.getGroupBuyList();
	}
	
	public List<GroupBuy> getGroupBuyListByUserId(int userId) throws DataAccessException {
		return groupBuyMapper.getGroupBuyListByUserId(userId);
	}

	public void increaseCount(GroupBuy groupBuy) throws DataAccessException{
		groupBuyMapper.increaseCount(groupBuy);
	}

	public int updateState(GroupBuy groupBuy) throws DataAccessException{
		return groupBuyMapper.updateState(groupBuy);
	}
	
	public void closeEvent() {
		groupBuyMapper.closeEvent();
	}
	
	public void achieveEvent() {
		groupBuyMapper.achieveEvent();
	}
	
	public void updateAchieveNoti(int groupBuyId) throws DataAccessException{
		groupBuyMapper.updateAchieveNoti(groupBuyId);
	}
	
	public void updateCloseNoti(int groupBuyId) throws DataAccessException{
		groupBuyMapper.updateCloseNoti(groupBuyId);
	}
	
	public int[] getGroupBuyIdForAchieveNoti() throws DataAccessException{
		return groupBuyMapper.getGroupBuyIdForAchieveNoti();
	}
	
	public int[] getGroupBuyIdForCloseNoti() throws DataAccessException{
		return groupBuyMapper.getGroupBuyIdForCloseNoti();
	}
	
	public List<GroupBuy> getRecentGroupBuyList(){
		return groupBuyMapper.getRecentGroupBuyList();
	}
	
	public List<GroupBuy> groupBuyListByKeyword(String keyword) {
		return groupBuyMapper.groupBuyListByKeyword(keyword);
	}

	
}
