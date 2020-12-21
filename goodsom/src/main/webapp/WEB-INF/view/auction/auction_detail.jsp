<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>
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
<script type="text/javascript">

function deleteAuction() {
	if (confirm("경매를 삭제하시겠습니까?")) {
		location.href= "delete.do?auctionId=${auction.auctionId}";
	}
}

function updateAuction() {
	if (confirm("경매를 수정하시겠습니까?")) {
		location.href= "form.do?auctionId=${auction.auctionId}";
	}
}

function bid() {
	if (confirm("배팅 하시겠습니까?")) {
		bidForm.submit();
	}
	
}

function orderAuction() {
	location.href= "../order/auction/create.do?auctionId=${auction.auctionId}";
}

</script>

<style>
.error {
	color: #ff0000;
	/* font-weight: bold; */
}
</style>
<body>

	<%@ include file="../header.jsp"%>
	<main id="main">

		<!-- ======= Breadcrumbs ======= -->
		<section id="breadcrumbs" class="breadcrumbs">
			<div class="container">

				<div class="d-flex justify-content-between align-items-center">
					<h2>${auction.title}</h2>
					<ol>
						<li><a href="/index.do">Home</a></li>
						<li>${auction.title}</li>
					</ol>
				</div>
				<p style="float:left;">작성자 : &nbsp; &nbsp; ${writer} &nbsp;
				<a href="<c:url value='../report/create.do'>
						<c:param name="auctionId" value="${auction.auctionId}"/>
						<c:param name="writerId" value="${auction.userId}"/>
					</c:url>">신고하기</a> 
				<br/>
				작성일 : &nbsp; &nbsp; <fmt:formatDate value="${auction.uploadDate}" pattern="yyyy-MM-dd" />
				<p style="text-align: end;">조회수 : &nbsp; &nbsp; ${auction.count} &nbsp; </p>
			</div>
		</section>
		<!-- End Breadcrumbs -->

		<!-- ======= Portfolio Details Section ======= -->
		<section id="portfolio-details" class="portfolio-details">
			<div class="container">

				<div class="portfolio-details-container">

					<div class="owl-carousel portfolio-details-carousel">
						<c:forEach items="${auction.imgs_a}" var="img">
							<img src="${img.url}" class="img-fluid" alt="">
						</c:forEach>
					</div>

					<div class="portfolio-info">
						<c:if test="${auction.state eq 'proceeding'}">
							<h3>진행 중</h3>
						</c:if>
						<c:if test="${auction.state eq 'closed'}">
							<h3>마감</h3>
						</c:if>
						<ul>
							<li><strong>조회수</strong>: ${auction.count}</li>
							<li><strong>남은 시간</strong>: 
								<c:if test="${auction.state eq 'closed'}" > 마감되었습니다. </c:if>
								<c:if test="${auction.state eq 'proceeding'}" > ${dDay} </c:if>	
							</li>
							<li><strong>시작 금액</strong>: <fmt:formatNumber
									value="${auction.startPrice}" pattern="#,###원" /></li>
							<li><strong>현재 최고 금액</strong>: <fmt:formatNumber
									value="${auction.maxPrice}" pattern="#,###원" /></li>
							<li><strong>현재 최고 금액 입찰자</strong>: ${user_maxBid} <fmt:formatDate
									value="${date_maxBid}" pattern="(yyyy-MM-dd)" />
							</li>
							<c:if test="${isWriter eq false}">
								<li><strong>베팅 금액</strong>
									<form:form modelAttribute="bidForm" method="post" action="bidCreate.do" style="margin-top: 5px;">
										<div class="d-flex" style="margin-block: 5px;">
										<form:input type="hidden" path="bid.auctionId" value="${auction.auctionId}"/>
											<c:if test="${auction.state eq 'proceeding'}">
												<form:input type="number" path="bid.bidPrice" class="form-control" value="" style="width: 50%;"/>
												<input type="button" value="참여하기" onClick="bid()" style="margin-inline-start: auto;"> 
											</c:if>
												
											<c:if test="${auction.state eq 'closed'}">
												<form:input type="number" path="bid.bidPrice" class="form-control" value="" style="width: 50%;" readonly="true"/>
												<input type="button" value="참여하기" onClick="bid()" style="margin-inline-start: auto;" disabled> 
												
												<c:if test="${completeOrder ne 1 && successBidderUserId eq userSession.user.userId}">
													&nbsp;&nbsp; <!-- 아래 버튼은 낙찰자만 볼 수 있도록 -->
													<input type="button" value="결제하기" onClick="orderAuction()" style="margin-inline-start: auto;"> 
												</c:if>
											</c:if>
										</div>
										<form:errors path="bid.bidPrice" cssClass="error"/>
									</form:form>
								</li>
							</c:if>
						</ul>
					</div>
					<!-- betting -->
					
					

				</div>

				<div class="portfolio-description">
					<h2>상세정보</h2>
					<p style="white-space:pre;"><c:out value="${auction.content}" escapeXml="false"></c:out></p>
				</div>

			</div>
		</section>
		<!-- End Portfolio Details Section -->
		<div class="form-group" align="center">
			<dl>
				<c:choose>
					<c:when test="${like eq true}">
						<img id="btn_like" src="/assets/img/liked(bright_red).png" class="img-fluid" style="cursor:pointer;">
					</c:when>
					<c:otherwise>
						<img id="btn_like" src="/assets/img/unliked.png" class="img-fluid" style="cursor:pointer;">
					</c:otherwise>
				</c:choose>	
				<dd id="likeCount" style="margin-left:1px;">${auction.likeCount}</dd>
			</dl>
		</div>

		<div class="form-group" align="center">
				<%-- <c:if test="${(isWriter eq true) and (empty bids) and (auction.state eq 'proceeding')}"> --%>
				<c:if test="${isWriter eq true}">
					<a class="btn-submit" href="<c:url value='../order/auction/manage.do'>
																<c:param name="auctionId" value="${auction.auctionId}" />
														 	  </c:url>">낙찰자 현황</a>
				</c:if>	
				<c:if test="${(isWriter eq true) and (auction.maxPrice == 0)}">
					<a class="btn-submit" href="javascript:updateAuction()" >수정</a>
					<a class="btn-danger" href="javascript:deleteAuction()" >삭제</a>
				</c:if>
					<a class="btn-submit" href="<c:url value='/auction/list.do'></c:url>">목록</a>
		</div>
		
	</main>
	<!-- End #main -->
	<script>
/* JSP SCRIPT */
var auctionId = ${auction.auctionId};
var userId = ${loginUserId};
 
var btn_like = document.getElementById("btn_like");
 btn_like.onclick = function(){ changeHeart(); }
 
/* 좋아요 버튼 눌렀을떄 */
function changeHeart(){ 
     $.ajax({
            type : "POST",  
            url : "/clickLikeAuction.do",       
            dataType : "json",   
            data : "auctionId="+auctionId+"&userId="+userId,
			error : function(){
                alert("통신 에러","error","확인",function(){});
            },
            success : function(jdata) {
                if(jdata.resultCode == -1){
                    alert("좋아요 오류","error","확인",function(){});
                }
                else{
                    if(jdata.likeCheck == 1){
                        $("#btn_like").attr("src","/assets/img/liked(bright_red).png");
                        $("#likeCount").empty();
                        $("#likeCount").append(jdata.likeCount);
                    }
                    else if (jdata.likeCheck == 0){
                        $("#btn_like").attr("src","/assets/img/unliked.png");
                        $("#likeCount").empty();
                        $("#likeCount").append(jdata.likeCount);
                    }
                }
            }
        });
 }
</script>
	<%@ include file="../includeBottom.jsp"%>