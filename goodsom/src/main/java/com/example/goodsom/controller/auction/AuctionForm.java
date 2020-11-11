package com.example.goodsom.controller.auction;

import java.io.Serializable;

import javax.validation.Valid;

import com.example.goodsom.domain.Auction;

/**
 * @author Hyekyung Kim | Yejin Lee
 * @since 2020.05.08	| 2020.06.13
 */

@SuppressWarnings("serial")
public class AuctionForm implements Serializable{
	
	@Valid
	private Auction auction;

	private boolean newAuction;
	
	public AuctionForm() {
		this.auction = new Auction();
		this.newAuction = true;
	}
	
	public AuctionForm(Auction auction) {
		this.auction = auction;
		this.newAuction = false;
	}
	
	public Auction getAuction() {
		return auction;
	}

	public boolean isNewAuction() {
		return newAuction;
	}

	@Override
	public String toString() {
		return "AuctionForm [auction=" + auction + ", newAuction=" + newAuction + "]";
	}

}
