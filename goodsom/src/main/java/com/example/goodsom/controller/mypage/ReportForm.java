package com.example.goodsom.controller.mypage;

public class ReportForm {
	private int abuse; // 욕설 및 비방 횟수
	private int destroy; // 거래 파기 횟수
	
	public int getAbuse() {
		return abuse;
	}
	
	public void setAbuse(int abuse) {
		this.abuse = abuse;
	}
	
	public int getDestroy() {
		return destroy;
	}
	
	public void setDestroy(int destroy) {
		this.destroy = destroy;
	}

	@Override
	public String toString() {
		return "ReportForm [abuse=" + abuse + ", destroy=" + destroy + "]";
	}
	
}
