<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
//delete
function deleteNoti() {
	var deleteCheck = confirm("알림을 삭제하시겠습니까?")
	
	return deleteCheck;
}
function getAuctionInfo(notiId){
	var reqUrl = "../rest/auction/" + notiId;
	
	$.ajax({
		type: "get",
		url: reqUrl,
		processData: false,
		success: function(responseJson){	// object parsed from JSON text	
			$("#detail").html("<ul></ul>");
			$("#detail > ul").append("<li>경매 제목: " + responseJson.title + "</li>");
			$("#detail > ul").append("<li>내용: " + responseJson.content+ "</li>");
			$("#detail > ul").append("<li>마감일: " +  new Date(responseJson.endDate) + "</li>");
			$("#detail > ul").append("<li>낙찰가: " + responseJson.maxPrice + "원</li>");
		},
		error: function(){
			alert("ERROR", arguments);
		}
	});
};
function getGroupBuyInfo(notiId){
	var reqUrl = "../rest/groupBuy/" + notiId;
	
	$.ajax({
		type: "get",
		url: reqUrl,
		processData: false,
		success: function(responseJson){	// object parsed from JSON text	
			$("#detail").html("<ul></ul>");
			$("#detail > ul").append("<li>공동구매 제목: " + responseJson.title + "</li>");
			$("#detail > ul").append("<li>내용: " + responseJson.content+ "</li>");
			$("#detail > ul").append("<li>마감일: " +  new Date(responseJson.endDate) + "</li>");
			$("#detail > ul").append("<li>개당 가격: " + responseJson.price + "</li>");
			$("#detail > ul").append("<li>현재인원 / 목표인원: " + responseJson.participants + " / " + responseJson.minNo + "</li>");
		},
		error: function(){
			alert("ERROR", arguments);
		}
	});
	
};
</script>

<style>
.btn-submit {
	position: relative;
	top: 0;
	right: 5px;
	bottom: 0;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 8px 30px;
	background: #3498db;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
.btn-danger {
	position: relative;
	top: 0;
	right: 5px;
	bottom: 0;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 8px 30px;
	background: #bb404c;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
</style>

<body>

 <%@ include file="../header.jsp" %> 
    
	<!-- ======= Portfolio Section ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

	    <!-- start groupBuy detail -->
	      <div class="d-flex justify-content-between align-items-center">
	       		<h2>
		         	<c:if test="${type eq 1}">
						<a  href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${id}"/>
									</c:url>">${noti.title}</a>
					</c:if>
					<c:if test="${type eq 2}">
						<a  href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${id}"/>
									</c:url>">${noti.title}</a>				
					</c:if>
				</h2>  
				<ol>
			        <li><a href="#" onclick="history.back()">알림함</a></li>
			        <li>알림 상세보기</li>
			    </ol>
	       </div>
	       <p> 수신일 : <fmt:formatDate value="${noti.notiDate}" pattern="yyyy-MM-dd HH:mm" /></p>
	  	
	  		<br>
	  		<div>
	          <p><b>${content}</b></p>
	        </div>
	        
	        <br>
		
			<c:if test="${type eq 1}">
				<a href="javascript:void(0);" onClick="getAuctionInfo(${noti.notiId}); return false;">경매 세부정보 보기</a>
			</c:if>
			<c:if test="${type eq 2}">
				<a href="javascript:void(0);" onClick="getGroupBuyInfo(${noti.notiId}); return false;">공동구매 세부정보 보기</a>
			</c:if>
			
			<div id="detail"> <!-- REST Service 정보 보여주기 -->
			</div>
			
			<br/>
			
			<c:if test="${type eq 1}">
				<a  href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${id}"/>
							</c:url>">해당 경매 바로가기 >></a>
			</c:if>
			<c:if test="${type eq 2}">
				<a  href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${id}"/>
							</c:url>">해당 공동구매 바로가기 >></a>				
			</c:if>
			
			<br> <br>
			<div class="form-group" align="center">
				<a class="btn-danger" href="<c:url value='/noti/delete.do'>
	   				<c:param name="notiId" value="${noti.notiId}"/>
	   				<c:param name="type" value="${type}"/>
	   				</c:url>" 
			   		onClick="return deleteNoti();">삭제</a>
		   		<a class="btn-submit" href="<c:url value='/noti/list.do'>
					</c:url>">확인</a>
     		</div>	
	   </div>
	</section>	  	
    
	<%@ include file="../includeBottom.jsp" %>
