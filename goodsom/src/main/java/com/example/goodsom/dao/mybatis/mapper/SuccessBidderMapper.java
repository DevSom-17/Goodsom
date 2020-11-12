package com.example.goodsom.dao.mybatis.mapper;

import com.example.goodsom.domain.SuccessBidder;

/**
 * @author Seonmi Hwang
 * @since 2020.06.27
 */

public interface SuccessBidderMapper {
	
	int insertSuccessBidder(SuccessBidder successBidder);
	
	SuccessBidder getSuccessBidderByAuctionId(int auctionId);
}
