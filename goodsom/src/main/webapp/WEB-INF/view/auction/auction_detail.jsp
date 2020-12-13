<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>
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
						<h3>
							<fmt:formatDate value="${auction.endDate}"
								pattern="yyyy-MM-dd HH:mm" />
							까지
						</h3>
						<ul>
							<li><strong>조회수</strong>: ${auction.count}</li>
							<li><strong>작성자</strong>: ${writer}</li>
							<li><strong>시작 금액</strong>: ${auction.startPrice}</li>
							<li><strong>작성일</strong>: <fmt:formatDate
									value="${auction.uploadDate}" pattern="yyyy-MM-dd" /></li>
							<li><strong>현재 최고 금액</strong>: <fmt:formatNumber
									value="${auction.maxPrice}" pattern="#,###원" /></li>
							<li><strong>최고 금액 입찰자</strong>: <fmt:formatDate
									value="${date_maxBid}" pattern="yyyy-MM-dd" /> <br />
								${user_maxBid}</li>
						</ul>
					</div>
					<!-- betting -->
					<c:if test="${isWriter eq false}">
						<div class="d-flex">
							<form:form modelAttribute="bidForm" method="post" action="bidCreate.do">
								<h5>베팅 금액</h5>
								<div class="d-flex">
								<form:input type="hidden" path="bid.auctionId" value="${auction.auctionId}"/>
									<c:if test="${auction.state eq 'proceeding'}">
										<form:input type="number" path="bid.bidPrice" class="form-control"/>
										<input type="button" value="신청하기" onClick="bid()" > 
									</c:if>
										
									<c:if test="${auction.state eq 'closed'}">
										<form:input type="number" path="bid.bidPrice" class="form-control" readonly="true"/>
										<input type="button" value="신청하기" onClick="bid()" disabled> 
										
										<c:if test="${completeOrder ne 1 && successBidderUserId eq userSession.user.userId}">
											&nbsp;&nbsp; <!-- 아래 버튼은 낙찰자만 볼 수 있도록 -->
											<input type="button" value="결제하기" onClick="orderAuction()" > 
										</c:if>
									</c:if>
								</div>
								<form:errors path="bid.bidPrice" cssClass="error"/>
							</form:form>
						</div>
					</c:if>
					

				</div>

				<div class="portfolio-description">
					<c:if test="${auction.state eq 'proceeding'}">
						<h2>진행 중</h2>
					</c:if>
					<c:if test="${auction.state eq 'closed'}">
						<h2>마감</h2>
					</c:if>
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
				<dd id="likeCount" style="margin-left:5px;">${auction.likeCount}</dd>
			</dl>
		</div>

		<div class="form-group" align="center">
				<%-- <c:if test="${(isWriter eq true) and (empty bids) and (auction.state eq 'proceeding')}"> --%>
				<c:if test="${(isWriter eq true) and (empty auction.bids)}">
					<a class="btn btn-primary py-3 px-5" href="javascript:updateAuction()" >수정</a>
					<a class="btn btn-primary py-3 px-5" href="javascript:deleteAuction()" >삭제</a>
				</c:if>
					<a class="btn btn-primary py-3 px-5" href="<c:url value='/auction/list.do'></c:url>">목록</a>
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