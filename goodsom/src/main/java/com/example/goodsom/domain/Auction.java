package com.example.goodsom.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class Auction implements Serializable {
	private static final String PROCEEDING = "proceeding";
	private static final int MENUID_AUCTION = 1;
	
	int auctionId;
	@NotEmpty
	String title;
	MultipartFile report;
	@NotEmpty
	String content;
	String img;
	@NotNull @Positive
	int startPrice;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date uploadDate;
	@NotNull
//	@FutureOrPresent
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date endDate;
	int count;
	int maxPrice;
	String state;
	int receive;
	int menuId;
	int userId;

	@NotEmpty
	String isAmPm;
	int hour;
	int minute;
	
	List<Bid> bids = new ArrayList<Bid>();
	
	public int getUserId() {
		return userId;
	}

		public void setUserId(int userId) {
			this.userId = userId;
	}
	
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public Auction() {
	}
	
	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getReport() {
		return report;
	}

	public void setReport(MultipartFile report) {
		this.report = report;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int max) {
		this.maxPrice = max;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public int getReceive() {
		return receive;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}

	public String getIsAmPm() {
		return isAmPm;
	}

	public void setIsAmPm(String isAmPm) {
		this.isAmPm = isAmPm;
	}
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public void initAuction(User user) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        System.out.println(date);
        
        uploadDate = date;
        userId = user.getUserId();
        state = PROCEEDING;
        count = 0;
        menuId = MENUID_AUCTION;
	}
	
	public void timeSet() {
		 SimpleDateFormat KSTFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat tmpFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHour = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        // date 형식 초기화
        Date tmpDate;
        String newDate = null;
		try {
			tmpDate = KSTFormat.parse(getEndDate().toString());
			System.out.println(tmpDate);
			newDate = tmpFormat.format(tmpDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        // 마감시간 세팅
        if(isAmPm.equals("pm")){
        	int tmpHour = getHour()+12;
        	if(tmpHour == 24) {
        		setHour(00);
        	}else {
        		setHour(tmpHour);
        	}
        }
        if (getHour() == 12) {
        	setHour(00);
        }
        try {
        	String dateFormat = newDate + " " + String.valueOf(getHour()) + ":" + String.valueOf(getMinute());
            System.out.println("dateFormat: " + dateFormat);
			Date resultDate = sdfHour.parse(dateFormat);
			setEndDate(resultDate);	// 마감일 세팅
			System.out.println(resultDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
//	기본 이미지 지정하는 메서드
	public void initImg(String contextPath) {
		img = contextPath + "/resources/images/somsom.jpg";
	}

	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", title=" + title + ", report=" + report + ", content=" + content
				+ ", img=" + img + ", startPrice=" + startPrice + ", uploadDate=" + uploadDate + ", endDate=" + endDate
				+ ", count=" + count + ", maxPrice=" + maxPrice + ", state=" + state + ", menuId=" + menuId
				+ ", userId=" + userId + ", isAmPm=" + isAmPm + ", hour=" + hour + ", minute=" + minute + ", bids="
				+ bids + "]";
	}

	
	
	
}
