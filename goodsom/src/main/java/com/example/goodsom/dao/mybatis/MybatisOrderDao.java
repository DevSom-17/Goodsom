package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.OrderDao;
import com.example.goodsom.dao.mybatis.mapper.BidMapper;
import com.example.goodsom.dao.mybatis.mapper.GroupBuyMapper;
import com.example.goodsom.dao.mybatis.mapper.LineGroupBuyMapper;
import com.example.goodsom.dao.mybatis.mapper.OrderMapper;
import com.example.goodsom.dao.mybatis.mapper.SuccessBidderMapper;
import com.example.goodsom.domain.LineGroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.SuccessBidder;

/**
 * @author Seonmi Hwang
 * @since 2020.06.12
 */

@Repository
public class MybatisOrderDao implements OrderDao {
	
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected LineGroupBuyMapper lineGroupBuyMapper;
	@Autowired
	protected GroupBuyMapper groupBuyMapper;
	@Autowired
	protected SuccessBidderMapper successBidderMapper;
	
	@Override
	public Order getOrder(int orderId) throws DataAccessException {
	    Order order = orderMapper.getOrder(orderId);
	    return order;
	}

	@Override	
	public Order getOrderWithLineGroupBuys(int orderId) throws DataAccessException {
		return orderMapper.getOrderWithLineGroupBuys(orderId);
	}

	@Override
	@Transactional
	public void createOrder(Order order) throws DataAccessException {
		// ORDERS 테이블에 order 삽입
		orderMapper.createOrder(order);
		
		// GroupBuy를 결제하는 경우
		List<LineGroupBuy> lineGroupBuys = order.getLineGroupBuys();
		if (lineGroupBuys != null) {
			// LINEGROUPBUYS 테이블에 lineGroupBuy들 삽입
			for (LineGroupBuy lineGroupBuy : lineGroupBuys) {
				lineGroupBuy.setOrderId(order.getOrderId());
				lineGroupBuyMapper.insertLineGroupBuy(lineGroupBuy);
			}
			// GROUPBUYS 테이블에 참여자, 달성률, 상태 update
			groupBuyMapper.updateParticipants(order.getGroupBuy());
		}
		
		// Auction을 결제하는 경우
		SuccessBidder successBidder = order.getSuccessBidder();
		
		System.out.println("[SuccessBidder] : " + successBidder);
		
		if (successBidder != null) {
			successBidder.setOrderId(order.getOrderId());
			successBidderMapper.insertSuccessBidder(successBidder);
		}
		
	}
	
	@Override
	public int getGroupBuyId(int orderId) throws DataAccessException { // From LineGroupBuys
		return orderMapper.getGroupBuyId(orderId);
	}
	
	@Override
	public int getAuctionId(int orderId) throws DataAccessException { // From SuccessBidders
		return orderMapper.getAuctionId(orderId);
	}
}
