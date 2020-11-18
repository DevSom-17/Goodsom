package com.example.goodsom.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Option implements Serializable {
	int optionId;
	int groupBuyId;
	String name;
	
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroupBuyId() {
		return groupBuyId;
	}
	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}
	
	public String toString() {
		return "[optionId: " + optionId + "groupBuyId: " + groupBuyId + "name: " + name + "]";
	}
	
}
