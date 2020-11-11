package com.example.goodsom.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  com.example.goodsom.controller.groupBuy.LineGroupBuyForm;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06
 */

public class Order {
	int orderId;
	String cardBank;
	String cardNo;
	String validDate;
	String cvc;
	String address1;
	String address2;
	String address3;
	String phone;
	String refundBank;
	String refundAccount;
	int totalPrice;
	Date orderDate;
	int userId;
	
	List<LineGroupBuy> lineGroupBuys;
	GroupBuy groupBuy;
	SuccessBidder successBidder;
	Auction auction;
	
	int menuId; // auction과 groupBuy를 구분하기 위함.
	int groupBuyId;
	int auctionId;
	int totalQuantity; // groupBuy의 state를 update하기 위함

	
	public int getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public SuccessBidder getSuccessBidder() {
		return successBidder;
	}

	public void setSuccessBidder(SuccessBidder successBidder) {
		this.successBidder = successBidder;
	}

	public List<LineGroupBuy> getLineGroupBuys() {
		return lineGroupBuys;
	}

	public void setLineGroupBuys(List<LineGroupBuy> lineGroupBuys) {
		this.lineGroupBuys = lineGroupBuys;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCardBank() {
		return cardBank;
	}

	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRefundBank() {
		return refundBank;
	}

	public void setRefundBank(String refundBank) {
		this.refundBank = refundBank;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public void initOrder(User user, LineGroupBuyForm lineGroupBuyForm, Auction auction) {
		userId = user.getUserId();
		
		// 주소 불러오기
		address1 = user.getAddress1();
		address2 = user.getAddress2();
		address3 = user.getAddress3();
		
		// 전화번호 불러오기
		phone = user.getPhone();
		
		// 환불계좌 정보 불러오기
		refundBank = user.getRefundBank();
		refundAccount = user.getRefundAccount();
		
		totalPrice = 0;
		totalQuantity = 0;
		
		// GroupBuy를 결제하는 경우
		if (lineGroupBuyForm != null) {
			lineGroupBuys = new ArrayList<LineGroupBuy>();
			
			groupBuyId = lineGroupBuyForm.getGroupBuyId();
			groupBuy = lineGroupBuyForm.getGroupBuy();
			
			List<String> options = lineGroupBuyForm.getOptions();
			List<Integer> quantities = lineGroupBuyForm.getQuantities();
			List<Integer> unitPrices = new ArrayList<Integer>();
			
			for (int i = 0; i < quantities.size(); i++) { // (옵션, 수량)쌍의 갯수 동안
				int unitPrice = lineGroupBuyForm.getQuantities().get(i) * groupBuy.getPrice();
				unitPrices.add(unitPrice);
				
				LineGroupBuy lineGroupBuy = new LineGroupBuy();
				lineGroupBuy.setSelectOption(options.get(i));
				lineGroupBuy.setQuantity(quantities.get(i));
				lineGroupBuy.setUnitPrice(unitPrice);
				lineGroupBuy.setGroupBuyId(groupBuyId);
				lineGroupBuy.setGroupBuy(groupBuy);
				
				lineGroupBuys.add(lineGroupBuy);
				
				totalPrice += unitPrice;
				totalQuantity += quantities.get(i);
			}
			
			lineGroupBuyForm.setUnitPrices(unitPrices);
		}
		
		// Auction을 결제하는 경우
		if (auction != null) {
			List<Bid> bids = auction.getBids();
			totalPrice = auction.getMaxPrice();
			auctionId = auction.getAuctionId();
			this.auction = auction;
			
			successBidder = new SuccessBidder();
			successBidder.setAuctionId(auctionId);
			successBidder.setUserId(user.getUserId());
			
			for (Bid bid : bids) {
				if (bid.getBidPrice() == totalPrice) {
					successBidder.setBidId(bid.getBidId());
					break;
				}
			}
		}

	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", cardBank=" + cardBank + ", cardNo=" + cardNo + ", validDate="
				+ validDate + ", cvc=" + cvc + ", address1=" + address1 + ", address2=" + address2 + ", address3="
				+ address3 + ", phone=" + phone + ", refundBank=" + refundBank + ", refundAccount=" + refundAccount
				+ ", userId=" + userId + ", lineGroupBuys=" + lineGroupBuys + ", groupBuy=" + groupBuy
				+ ", successBidder=" + successBidder + ", auction=" + auction + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", menuId=" + menuId + ", groupBuyId="
				+ groupBuyId + ", auctionId=" + auctionId + "]";
	}

}
