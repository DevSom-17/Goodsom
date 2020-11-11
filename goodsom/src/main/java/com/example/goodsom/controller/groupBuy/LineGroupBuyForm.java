package com.example.goodsom.controller.groupBuy;

import java.util.List;

import com.example.goodsom.domain.GroupBuy;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06
 */

public class LineGroupBuyForm {

	int groupBuyId;
	GroupBuy groupBuy = new GroupBuy();

	List<Integer> quantities;
	List<String> options;
	List<Integer> unitPrices;

	public int getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}
	
	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}

	public List<Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<Integer> getUnitPrices() {
		return unitPrices;
	}

	public void setUnitPrices(List<Integer> unitPrices) {
		this.unitPrices = unitPrices;
	}

	@Override
	public String toString() {
		return "LineGroupBuyForm [groupBuyId=" + groupBuyId + ", groupBuy=" + groupBuy + ", quantities=" + quantities
				+ ", options=" + options + "]";
	}

}
