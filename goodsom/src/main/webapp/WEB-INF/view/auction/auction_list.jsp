<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script src="<c:url value="/assets/js/clickLikeInList.js"/>"></script>

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
</style>
<body>

 <%@ include file="../header.jsp" %> 

  <main id="main">

    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="section-title">
          <h2>경매</h2>
        </div>

        <div class="form-group" align="left" style="margin-bottom:50px;">
          <a class="btn-submit" href="<c:url value='/auction/create.do'></c:url>">경매 등록</a>
        </div>
	
        <div class="row portfolio-container">
        <c:if test="${empty auctionList}">
			<c:choose>
				<c:when test="${requestScope['javax.servlet.forward.request_uri'] eq '/list/search.do'}">
					해당하는 경매 게시글이 없습니다.
				</c:when>
				<c:otherwise>
					현재 경매 게시글이 없습니다.
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:forEach var="auction" items="${auctionList}" varStatus="status">
          <div class="col-lg-4 col-md-6 portfolio-item filter-app wow fadeInUp">
            <div class="portfolio-wrap">
              <figure style="background: white; text-align: center;">
              	<a href="<c:url value='/auction/detail.do'>
					<c:param name="auctionId" value="${auction.auctionId}"/></c:url>">
					<img src="${auction.imgs_a[0].url}" class="img-fluid" alt="" style="height: 100%;">
				</a>
				<c:forEach var="img" items="${auction.imgs_a}" varStatus="status">
					<a href="${img.url}" data-gall="portfolioGallery" class="link-preview venobox" title="Preview"><i class="bx bx-images"></i></a>
				</c:forEach>
                <a href="javascript:void(0);" onclick="changeHeartAuction(${auction.auctionId}, ${loginUserId}); return false;" class="link-details" title="좋아요">
              		<c:choose>
              			<c:when test="${auction.liked eq 0}">
		              		<i id="i_like_${auction.auctionId}" class="bx bx-heart"></i>
              			</c:when>
              			<c:otherwise>
							<i id="i_like_${auction.auctionId}" class="bx bxs-heart"></i>             			
              			</c:otherwise>
              		</c:choose>
              	</a>
              </figure>
              

              <div class="portfolio-info" style="height: 105px;">
				<div>
	                <h4><a href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${auction.auctionId}"/>
								 </c:url>">${auction.title}</a></h4>
						<div style="float:left; color: #898a8c;">
							<strong>시작가</strong>: <fmt:formatNumber value="${auction.startPrice}" pattern="#,###원"/>
						</div>
						<div style="float:right; color: #898a8c;">
							<strong>최고가</strong>:
							<span style="color: #fa981f; font-weight: 600;"><fmt:formatNumber value="${auction.maxPrice}" pattern="#,###"/></span> 원
						</div>
						<br/>
						<div style="float:left; color: #898a8c;">
							<strong>마감일</strong>: <fmt:formatDate value="${auction.endDate}" pattern="yyyy-MM-dd" />
						</div>
						<c:if test="${auction.state eq 'proceeding'}">
							<span style ="color: #2f94d8; float:right; font-weight: bold;">진행 중</span>
						</c:if>
						<c:if test="${auction.state eq 'closed'}">
							<span style ="color: #ff5757; float:right; font-weight: bold;">마감</span>
						</c:if>
	              </div>
	            </div>
	          </div>
         	</div>
         </c:forEach>
      	 </div>
      </div>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->
<script>

</script>
  <%@ include file="../includeBottom.jsp" %> 