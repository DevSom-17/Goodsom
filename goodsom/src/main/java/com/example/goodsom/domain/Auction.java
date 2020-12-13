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
	List<MultipartFile> report;
	@NotEmpty
	String content;
//	String img;
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
	int sendNoti;
	
	List<Bid> bids = new ArrayList<Bid>();
	List<Image_a> imgs_a = new ArrayList<Image_a>();
	int likeCount;
	
	public Auction() {
	}

	public int getUserId() {
		return userId;
	}

		public void setUserId(int userId) {
			this.userId = userId;
	}
	
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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

	public List<MultipartFile> getReport() {
		return report;
	}

	public void setReport(List<MultipartFile> report) {
		this.report = report;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	public int getSendNoti() {
		return sendNoti;
	}
	
	public void setSendNoti(int sendNoti) {
		this.sendNoti = sendNoti;
	}
	
	public List<Bid> getBids() {
		return bids;
	}
	
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public List<Image_a> getImgs_a() {
		return imgs_a;
	}

	public void setImgs_a(List<Image_a> imgs_a) {
		this.imgs_a = imgs_a;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public void initAuction(User user) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        System.out.println(date);
        
        uploadDate = date;			// 게시물 업로드 일자
        userId = user.getUserId();	// 게시물 작성자
        state = PROCEEDING;			// 경매 상태 (PROCEEDING: 진행 중, CLOSED: 마감)
        count = 0;					// 조회수
        menuId = MENUID_AUCTION;	// 메뉴 (1: auction, 2: groupbuy, 3: post)
        receive = 0;				// 수령 여부
        sendNoti = 0;				// 알림 안 보냄
        likeCount = 0;				// 좋아요 수
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
	
////	기본 이미지 지정하는 메서드
//	public void initImg(String contextPath) {
//		img = contextPath + "/resources/images/somsom.jpg";
//	}

	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", title=" + title + ", report=" + report + ", content=" + content
				+ "startPrice=" + startPrice + ", uploadDate=" + uploadDate + ", endDate=" + endDate
				+ ", count=" + count + ", maxPrice=" + maxPrice + ", state=" + state + ", receive=" + receive
				+ ", menuId=" + menuId + ", userId=" + userId + ", isAmPm=" + isAmPm + ", hour=" + hour + ", minute="
				+ minute + ", sendNoti=" + sendNoti + ", bids의 크기=" + bids.size() + ", imgs_a의 크기=" + imgs_a.size() + "]";
	}
	
	
}
