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

import com.example.goodsom.validator.PresentOrFuture;

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
	@Positive
	@NotNull
	Integer startPrice;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date uploadDate;
	@NotNull
	@PresentOrFuture
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date endDate;

	int count;
	int maxPrice;
	String state;
	int receive;
	int menuId;
	int userId;

	int hour;
	int minute;
	int sendNoti;
	
	List<Bid> bids = new ArrayList<Bid>();
	List<Image_a> imgs_a = new ArrayList<Image_a>();
	int likeCount;
	int liked;
	
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

	public Integer getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Integer startPrice) {
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
	
	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
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
        liked = 0;					// 로그인 한 사용자가 좋아요를 눌렀는지 (0: x  1: o)
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
			setEndDate(tmpDate);
			newDate = tmpFormat.format(tmpDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		String hourStr = String.valueOf(getHour());
		if (hourStr == "0") {
			System.out.println("timeSet()에서 hour가 0일 때 00으로 해줬음! hour값: " + getHour());
			hourStr = "00";
		}
		
        try {
        	String dateFormat = newDate + " " + String.valueOf(getHour()) + ":" + String.valueOf(getMinute());
			Date resultDate = sdfHour.parse(dateFormat);
			setEndDate(resultDate);	// 마감일 세팅
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getDday(long end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
		
		long timeLength = getEndDate().getTime() - now.getTime();
		long dHour = (timeLength % ( 24*60*60*1000 )) / ( 60*60*1000 ); 
		long dDay = timeLength / ( 24*60*60*1000 ); 
		Math.abs(dDay);
		Math.abs(dHour);
		String str = dDay + "일 " + dHour + "시간  \n(" + sdf.format(getEndDate()) + "까지)";
		System.out.println(str);
		return str;
	}

//	경매생성에서 isAmPm과 hour/minute를 설정해주기
	
	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", title=" + title + ", report=" + report + ", content=" + content
				+ "startPrice=" + startPrice + ", uploadDate=" + uploadDate + ", endDate=" + endDate
				+ ", count=" + count + ", maxPrice=" + maxPrice + ", state=" + state + ", receive=" + receive
				+ ", menuId=" + menuId + ", userId=" + userId + ", hour=" + hour + ", minute="
				+ minute + ", sendNoti=" + sendNoti + ", bids의 크기=" + bids.size() + ", imgs_a의 크기=" + imgs_a.size()
				+ "liked: " + liked + "]";
	}
	
	
}
