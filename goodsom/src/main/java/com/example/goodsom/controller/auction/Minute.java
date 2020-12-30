package com.example.goodsom.controller.auction;

public class Minute {
	
	private int code;
	private int label;
	
	public Minute(int code, int label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	
}
