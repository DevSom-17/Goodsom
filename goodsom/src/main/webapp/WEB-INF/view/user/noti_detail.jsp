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

 <%@ include file="../header.jsp" %> 
    
    <div class="site-section-cover">
      <div class="container">
        <div class="row align-items-center text-center justify-content-center">
          <div class="col-lg-6">
            <h1 class="text-white mb-4">Noti Page</h1>
            <p class="lead">알림을 통해 참여한 경매/공동구매 등의 낙찰/마감 여부를 확인할 수 있습니다.</p>
          </div>
        </div>
      </div>
    </div>

    <!-- start groupBuy detail -->
	<br/>    
    <div align="center">
    	<br/><h2 class="text-primary mb-5 font-weight-bold">알림함</h2><br/>
    </div>
         	
     <div class="container">
       <div>
          	<div align="right"><p>&nbsp; &nbsp; ${nickname}님의 알림함 <br/></p></div>
          
       		<h2 align="center">
	         	<c:if test="${type eq 1}">
					<a  href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${id}"/>
								</c:url>">제목: ${noti.title}</a>
				</c:if>
				<c:if test="${type eq 2}">
					<a  href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${id}"/>
								</c:url>">제목: ${noti.title}</a>				
				</c:if>
			</h2>  
         	
         	<br/>
         	
          	<div align="right">
         	수신일 : &nbsp; &nbsp; <fmt:formatDate value="${noti.notiDate}" pattern="yyyy-MM-dd HH:mm" />
		  	</div>
		  	
			<h5>내용</h5>
			<h6>${content}</h6>
			
       </div> 
       
       <br/><br/>
		
		<c:if test="${type eq 1}">
			<a href="javascript:void(0);" onClick="getAuctionInfo(${noti.notiId}); return false;">경매 세부정보 보기</a>
		</c:if>
		<c:if test="${type eq 2}">
			<a href="javascript:void(0);" onClick="getGroupBuyInfo(${noti.notiId}); return false;">공동구매 세부정보 보기</a>
		</c:if>
		
		<div id="detail"> <!-- REST Service 정보 보여주기 -->
		</div>
		
		<br/><br/>
		
		<c:if test="${type eq 1}">
			<a  href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${id}"/>
						</c:url>">해당 경매 바로가기 >></a>
		</c:if>
		<c:if test="${type eq 2}">
			<a  href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${id}"/>
						</c:url>">해당 공동구매 바로가기 >></a>				
		</c:if>
		
	   <div class="form-group" align="right">
	   		<a class="btn btn-primary py-3 px-5" href="<c:url value='/noti/list.do'>
				</c:url>">확인</a>
   			<a class="btn btn-primary py-3 px-5" href="<c:url value='/noti/delete.do'>
   				<c:param name="notiId" value="${noti.notiId}"/>
   				<c:param name="type" value="${type}"/>
   				</c:url>" 
		   		onClick="return deleteNoti();">삭제</a>	
   		</div>	
	    					
   </div>
    
	<%@ include file="../includeBottom.jsp" %>
