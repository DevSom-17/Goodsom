package com.example.goodsom.domain;

public class Image_a {
	int auctionId;
	int fileNo;
	String url;
	
	public Image_a(int auctionId, int fileNo, String url) {
		this.auctionId = auctionId;
		this.fileNo = fileNo;
		this.url = url;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Image_a [auctionId=" + auctionId + ", fileNo=" + fileNo + ", url=" + url + "]";
	}
	
	
}
