package com.example.goodsom.domain;

public class Image_g {
	int groupBuyId;
	int fileNo;
	String url;
	
	public Image_g(int groupBuyId, int fileNo, String url) {
		this.groupBuyId = groupBuyId;
		this.fileNo = fileNo;
		this.url = url;
	}

	public int getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
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
		return "Image_g [groupBuyId=" + groupBuyId + ", fileNo=" + fileNo + ", url=" + url + "]";
	}
	
}
