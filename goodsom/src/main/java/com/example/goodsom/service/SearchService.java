package com.example.goodsom.service;

import java.util.List;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;

/**
 * @author Hyekyung Kim
 * @since 2020.05.05
 */

public interface SearchService {

	List<GroupBuy> getGroupBuyListByKeyword(String keyword);
	
	List<Auction> getAuctionListByKeyword(String keyword);

}
