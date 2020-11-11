package com.example.goodsom.controller.groupBuy;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.goodsom.domain.GroupBuy;

/**
 * @author Seonmi Hwang | HK
 * @since 2020.05.06	| 2020.06.26
 */

@SuppressWarnings("serial")
public class GroupBuyForm implements Serializable {
	
	@Valid
	private GroupBuy groupBuy;

	private boolean newGroupBuy;
	
	public GroupBuyForm() {
		
		this.groupBuy = new GroupBuy();
		System.out.println("GroupBuyForm 내 setReport: " + groupBuy.getReport());
		this.setNewGroupBuy(true);
	}
	
	public GroupBuyForm(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
		this.setNewGroupBuy(false);
	}
	
	public GroupBuy getGroupBuy() {
		return groupBuy;
	}

	public boolean getNewGroupBuy() {
		return newGroupBuy;
	}

	public void setNewGroupBuy(boolean newGroupBuy) {
		this.newGroupBuy = newGroupBuy;
	}
}
