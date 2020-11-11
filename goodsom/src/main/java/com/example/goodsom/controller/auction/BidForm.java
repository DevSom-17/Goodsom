package com.example.goodsom.controller.auction;

import javax.validation.Valid;

import com.example.goodsom.domain.Bid;

/**
 * @author Hyekyung Kim | kimdahyee		| Yejin Lee
 * @since  2020.05.08   | 2020.06.25	| 2020.06.29
 */

public class BidForm {
	@Valid
	private Bid bid;
	private boolean newBid;
	
	public BidForm() { // create
		this.bid = new Bid();
		this.newBid = true;
	}
	
	public BidForm(Bid bid) { //update
		this.bid = bid;
		this.newBid = false;
	}
	
	public void setBid(Bid bid) {
		this.bid = bid;
	}
	
	public Bid getBid() {
		return bid;
	}
	
	public boolean isNewBid() {
		return newBid;
	}
	
}
