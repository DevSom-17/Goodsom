package com.example.goodsom.domain;
/**
 * @author Seonmi Hwang
 * @since 2020.06.27
 */

public class LineGroupBuy {
	int lineId;
	int orderId;
	int groupBuyId;
	int quantity;
	String selectOption;
	int unitPrice;
	GroupBuy groupBuy = new GroupBuy();
	
	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}
	
	public LineGroupBuy() {
	}
	
	public int getLineId() {
		return lineId;
	}
	
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getGroupBuyId() {
		return groupBuyId;
	}
	
	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getSelectOption() {
		return selectOption;
	}
	
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	
	public int getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "LineGroupBuy [lineId=" + lineId + ", orderId=" + orderId + ", groupBuyId=" + groupBuyId + ", quantity="
				+ quantity + ", selectOption=" + selectOption + ", unitPrice=" + unitPrice + ", groupBuy=" + groupBuy + "]";
	}
}
