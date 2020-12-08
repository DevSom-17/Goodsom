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
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class GroupBuy implements Serializable {
	private static final String PROCEEDING = "proceeding";
	private static final String ACHIEVED = "achieved";
	private static final int MENUID_GROUPBUY = 2;
	
	int groupBuyId;
	@NotEmpty
	String title;
	List<MultipartFile> report;
	@NotEmpty
	String content;
	
	@Positive
	int minNo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date uploadDate;

	Date resultDate;
	
	@NotNull
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	Date endDate;
	
	int count;
	String state;
	int rate;
	int participants;
	
	@Positive
	int catId;
	
	int menuId;
	int userId;
	
	@Positive
	int price;
	
	@NotEmpty
	String[] optionList;
	
	@NotEmpty
	String isAmPm;
	
	@Positive
	int hour;
	@PositiveOrZero
	int minute;
	
	int notiId;
	int sendNoti;
	
	int receive;
	
	List<Option> options = new ArrayList<Option>();
	List<User> groupBuyUsers = new ArrayList<User>();
	List<Question> questions = new ArrayList<Question>();
	List<Image_g> imgs_g = new ArrayList<Image_g>();
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public List<User> getGroupBuyUsers() {
		return groupBuyUsers;
	}

	public void setGroupBuyUsers(List<User> groupBuyUsers) {
		this.groupBuyUsers = groupBuyUsers;
	}

	public int getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
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

	public int getMinNo() {
		return minNo;
	}

	public void setMinNo(int minNo) {
		this.minNo = minNo;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getReceive() {
		return receive;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}
	
	public String[] getOptionList() {
		return optionList;
	}

	public void setOptionList(String[] optionList) {
		this.optionList = optionList;
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
	
	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	
	public int getNotiId() {
		return notiId;
	}

	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}
	
	public int getSendNoti() {
		return sendNoti;
	}

	public void setSendNoti(int sendNoti) {
		this.sendNoti = sendNoti;
	}
	
	public List<Image_g> getImgs_g() {
		return imgs_g;
	}

	public void setImgs_g(List<Image_g> imgs_g) {
		this.imgs_g = imgs_g;
	}

	public GroupBuy() {
	}
	
	public void initGroupBuy(User user) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
  
        uploadDate = date;
        userId = user.getUserId();
        count = 0;					// 조회수
        state = PROCEEDING;			// 게시물 상태
        rate = 0;					// 참여 달성률
        participants = 0;			// 참여자 수
        menuId = MENUID_GROUPBUY;	// 메뉴
        receive = 0;				// 수령 여부
        sendNoti = 0; 				// 알림 안보냄
        
        System.out.println("[initGroupBuy] uploadDate: " + uploadDate + ", userId: " + userId
        		 + ", count: " + count  + ", state: " + state  + ", rate: " + rate  
        		 + ", participants: " + participants  + ", menuId: " + menuId );
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
        
        if(getHour() == 12) {
        	setHour(00);
        }
        
        try {
        	String dateFormat = newDate + " " + String.valueOf(getHour()) + ":" + String.valueOf(getMinute());
            System.out.println("dateFormat: " + dateFormat);
			resultDate = sdfHour.parse(dateFormat);
			setEndDate(resultDate);	// 마감일 세팅
			System.out.println(resultDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void optionSetting(int groupBuyId) {
		
		for(int i = 0; i < optionList.length; i++) {
			Option op = new Option();
			op.setName(optionList[i]);
			op.setGroupBuyId(groupBuyId);
			options.add(i, op);
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
	
	public void orderSet(int quentity) {
		float total = getParticipants() + quentity;
		float tmpRate = (total / (float)getMinNo()) * 100;
		
		setParticipants((int)total);
		setRate((int) tmpRate);
		
		if(tmpRate >= 100) {
			setState(ACHIEVED);
		}
		System.out.println("quentity: " + quentity + "minNo: " + getMinNo());
		System.out.println("total: " + total + "rate: " + getRate() + "state: " + getState());
		
	}
	
	public String toString() {
		String str = "groupBuyId: " + groupBuyId + ", title: " + title + ", content: " + content + ", minNo: " + minNo 
				+ ", uploadDate: " + uploadDate + ", endDate: " + endDate + ", state: " + state + ", catId: " + catId;
		str += "\noptions --> ";
		for(Option op:options) {
			str += op.toString() + "\n";
		}
		
		return str;
	}

}
