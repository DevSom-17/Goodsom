package com.example.goodsom.domain;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  com.example.goodsom.controller.groupBuy.LineGroupBuyForm;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06 | 2020.12.12(update)
 */

public class Order {
	private int orderId;
	private int userId;
	private String name;
	private String phone;
	private String bank; 
	private String account;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private int postcode;
	private String depositTime;
	private String depositCheck;
	private String isDelivered;
	private int totalPrice;
	private Date orderDate;
	
	List<LineGroupBuy> lineGroupBuys;
	GroupBuy groupBuy;
	SuccessBidder successBidder;
	Auction auction;
	
	int menuId; // auction과 groupBuy를 구분하기 위함.
	int groupBuyId;
	int auctionId;
	int totalQuantity; // groupBuy의 state를 update하기 위함

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getDepositTime() {
		return depositTime;
	}

	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}

	public String getDepositCheck() {
		return depositCheck;
	}

	public void setDepositCheck(String depositCheck) {
		this.depositCheck = depositCheck;
	}

	public String getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(String isDelivered) {
		this.isDelivered = isDelivered;
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

	public List<LineGroupBuy> getLineGroupBuys() {
		return lineGroupBuys;
	}

	public void setLineGroupBuys(List<LineGroupBuy> lineGroupBuys) {
		this.lineGroupBuys = lineGroupBuys;
	}

	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}

	public SuccessBidder getSuccessBidder() {
		return successBidder;
	}

	public void setSuccessBidder(SuccessBidder successBidder) {
		this.successBidder = successBidder;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

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

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public void initOrder(User user, LineGroupBuyForm lineGroupBuyForm, Auction auction) {
		userId = user.getUserId();
		
		// user 정보 불러오기
		name = user.getUserName();
		phone = user.getPhone();
		
		// 환불계좌 정보 불러오기
		bank = user.getRefundBank();
		account = user.getRefundAccount();
		
		// 주소 불러오기
		address = user.getAddress();
		detailAddress = user.getDetailAddress();
		extraAddress = user.getExtraAddress();
		postcode = user.getPostcode();
		
		// 입금 체크 초기화
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		String nowTime = format.format(time);
		depositTime = nowTime; // 현재 시간으로 초기화
		depositCheck = "F"; // false 초기화
		isDelivered = "F"; // false 초기화
		
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

			System.out.println("경매 결제 시작입니다.");
			System.out.println(bids);
			
			for (Bid bid : bids) {
				System.out.println("Bid for문 시작");
				if (bid.getUserId() == user.getUserId()) {
					System.out.println("bid Id set 성공~!");
					successBidder.setBidId(bid.getBidId());
					break;
				}
			}
		}

	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", name=" + name + ", phone=" + phone + ", bank="
				+ bank + ", account=" + account + ", address=" + address + ", detailAddress=" + detailAddress
				+ ", extraAddress=" + extraAddress + ", postcode=" + postcode + ", depositTime=" + depositTime
				+ ", depositCheck=" + depositCheck + ", isDelivered=" + isDelivered + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", lineGroupBuys=" + lineGroupBuys + ", groupBuy=" + groupBuy
				+ ", successBidder=" + successBidder + ", auction=" + auction + ", menuId=" + menuId + ", groupBuyId="
				+ groupBuyId + ", auctionId=" + auctionId + ", totalQuantity=" + totalQuantity + "]";
	}

}
