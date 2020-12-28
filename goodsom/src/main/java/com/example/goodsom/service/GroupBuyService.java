package com.example.goodsom.service;

import java.util.List;

import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_g;

/**
 * @author Seonmi Hwang | HK			| YeJin Lee
 * @since 2020.05.06	| 2020.06.26	| 2020.12.06
 */

public interface GroupBuyService {
	
	public GroupBuy getGroupBuy(int groupBuyId);
	
	public int createGroupBuy(GroupBuy groupBuy, List<Image_g> groupBuyImgs);
	
	public int updateGroupBuy(GroupBuy groupBuy, List<Image_g> groupBuyImgs);
	
	public void deleteOptions(int groupBuyId);
	
	public void deleteGroupBuy(int groupBuyId);

	public void createOptions(GroupBuy groupBuy);
	
	public void updateOptions(GroupBuy groupBuy);
	
	public List<GroupBuy> getGroupBuyList(int userId);
	
	public List<GroupBuy> getGroupBuyListByUserId(int userId);
	
	public void increaseCount(GroupBuy groupBuy);
	
	public void deadLineScheduler();
	
	public List<GroupBuy> getBestGroupBuyList(int userId);
	
	List<GroupBuy> getLikedGroupBuyListByUserId(int userId);

}
