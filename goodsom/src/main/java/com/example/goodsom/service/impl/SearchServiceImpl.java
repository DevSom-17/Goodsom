package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.SearchService;
import com.example.goodsom.dao.AuctionDao;

/**
 * @author Hyekyung Kim
 * @since 2020.05.05
 */

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private GroupBuyDao groupBuy;
	@Autowired
	private AuctionDao auction;	
	
	@Override
	public List<GroupBuy> getGroupBuyListByKeyword(String keyword) {
		return groupBuy.getGroupBuyListByKeyword(keyword);
	}

	@Override
	public List<Auction> getAuctionListByKeyword(String keyword) {
		return auction.getAuctionListByKeyword(keyword);
	}
	
}
