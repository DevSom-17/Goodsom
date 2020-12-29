<%  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Pragma","no-cache");  
response.setDateHeader("Expires",0);  
if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="includeTop.jsp" %> 

<script src="<c:url value="/assets/js/clickLikeInList.js"/>"></script>

<body>

  <%@ include file="header.jsp" %> 

  <main id="main">

    <!-- ======= What We Do Section ======= -->
    <section id="what-we-do" class="what-we-do">
      <div class="container">

        <div class="section-title">
          <h2>What We Do</h2>
          <p>Goodsom이 대표적으로 제공하는 기능에 대해 소개합니다.</p>
        </div>

        <div class="row">
          <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
            <div class="icon-box">
              <div class="icon"><i class="bx bxl-dribbble"></i></div>
              <h4><a href="<%=request.getContextPath()%>/groupBuy/list.do">공동구매</a></h4>
              <p>동덕여대 솜솜이들이 굿즈를 직접 기획하고 제작하여 공동구매를 진행할 수 있는 기능을 지원합니다</p>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-md-0">
            <div class="icon-box">
              <div class="icon"><i class="bx bx-file"></i></div>
              <h4><a href="<%=request.getContextPath()%>/auction/list.do">경매</a></h4>
              <p>더 이상 판매되지 않는 동덕여대 굿즈가 활발히 교류될 수 있게 경매 기능을 지원합니다</p>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-lg-0">
            <div class="icon-box">
              <div class="icon"><i class="bx bx-tachometer"></i></div>
              <h4><a href="<%=request.getContextPath()%>/user/detail.do">마이페이지</a></h4>
              <p>회원정보 변경 및 알림 기능과 참여하거나 좋아요를 누른 공동구매/경매의 목록들을 볼 수 있는 기능을 지원합니다</p>
            </div>
          </div>

        </div>

      </div>
    </section><!-- End What We Do Section -->

    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="section-title">
          <h2>Best Item</h2>
          <p>현재 가장 높은 조회수를 기록하고 있는 상품들입니다 <br/> ALL(전체보기)의 경우 경매, 공동구매 순으로 정렬됩니다</p>
        </div>

        <div class="row">
          <div class="col-lg-12">
            <ul id="portfolio-flters">
              <li data-filter="*" class="filter-active">All</li>
              <li data-filter=".filter-app">경매</li>
              <li data-filter=".filter-card">공동구매</li>
            </ul>
          </div>
        </div>

       <div class="row portfolio-container">

		<c:forEach var="auction" items="${recentAuction}" varStatus="status">
          <div class="col-lg-4 col-md-6 portfolio-item filter-app wow fadeInUp">
            <div class="portfolio-wrap">
              <figure style="background: white; text-align: center;">
              	<a href="<c:url value='/auction/detail.do'>
					<c:param name="auctionId" value="${auction.auctionId}"/></c:url>">
					<img src="${auction.imgs_a[0].url}" class="img-fluid" alt="" style="height: 100%;">
				</a>
                <c:forEach var="img" items="${auction.imgs_a}" varStatus="status">
					<a href="${img.url}" data-gall="portfolioGallery" class="link-preview venobox" title="Preview"><i class="bx bx-plus"></i></a>
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
							<strong>최고가</strong>: <fmt:formatNumber value="${auction.maxPrice}" pattern="#,###원"/>
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

		<c:forEach var="groupBuy" items="${recentGroupBuy}" varStatus="status">
          <div class="col-lg-4 col-md-6 portfolio-item filter-card wow fadeInUp">
            <div class="portfolio-wrap">
              <figure style="background: white; text-align: center;">
					<img src="${groupBuy.imgs_g[0].url}" class="img-fluid" alt="" style="height: 100%;">
                <c:forEach var="img" items="${groupBuy.imgs_g}" varStatus="status">
					<a href="${img.url}" data-gall="portfolioGallery" class="link-preview venobox" title="Preview"><i class="bx bx-plus"></i></a>
				</c:forEach>
                <a href="javascript:void(0);" onclick="changeHeartGroupBuy(${groupBuy.groupBuyId}, ${loginUserId}); return false;" class="link-details" title="좋아요">
              		<c:choose>
              			<c:when test="${groupBuy.liked eq 0}">
		              		<i id="i_like_${groupBuy.groupBuyId}" class="bx bx-heart"></i>
              			</c:when>
              			<c:otherwise>
							<i id="i_like_${groupBuy.groupBuyId}" class="bx bxs-heart"></i>             			
              			</c:otherwise>
              		</c:choose>
              	</a>
              </figure>
              
              <div class="portfolio-info" style="height: 105px;">
              	<div>
              		<h4><a href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
							</c:url>">${groupBuy.title}</a></h4>
					<div style="float:left; color: #898a8c;">
						<strong>금액</strong>: <fmt:formatNumber value="${groupBuy.price}" pattern="#,###원"/>
					</div>
					<div style="float:right; color: #898a8c;">
						<strong>달성률</strong>: <fmt:formatNumber value="${groupBuy.rate}" />% 달성
					</div>
					<br/>
					<div style="float:left; color: #898a8c;">
						<strong>마감일</strong>: <fmt:formatDate value="${groupBuy.endDate}" pattern="yyyy-MM-dd" />
					</div>
					 
               		<c:if test="${groupBuy.state eq 'proceeding'}">
						<span style ="color: #2f94d8; float:right; font-weight: bold;">진행 중</span>
					</c:if>
					<c:if test="${groupBuy.state eq 'achieved'}">
						<span style ="color: #2f94d8; float:right; font-weight: bold;">달성</span>
					</c:if>
					<c:if test="${groupBuy.state eq 'closed'}">
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

    <!-- ======= Team Section ======= -->
    <section id="team" class="team">
      <div class="container">

        <div class="section-title">
          <h2>Team</h2>
          <p>GOODSOM의 개발자들을 소개합니다</p>
        </div>

        <div class="row">
          <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
            <div class="member">
              <img src="<%=request.getContextPath()%>/assets/img/team/bambi.PNG" alt="">
              <h4>yejineer</h4>
              <span>Auction & Image</span>
              <p>
                 	경매와 배팅, 다중 이미지 업로드, 이미지 미리보기, 회원가입시 사용자의 휴대폰 인증 기능을 구현하였습니다.
                 	이 외에도 경매/공동구매 및 전체적인 디자인 적용과  검색 기능, 좋아요 기능을 담당하였습니다.
              </p>
              <div class="social">
                <a href=""><i class="icofont-twitter"></i></a>
                <a href=""><i class="icofont-facebook"></i></a>
                <a href=""><i class="icofont-instagram"></i></a>
                <a href=""><i class="icofont-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
            <div class="member">
              <img src="<%=request.getContextPath()%>/assets/img/team/ddungii.PNG" alt="">
              <h4>Seonmi-Hwang</h4>
              <span>User & Order</span>
              <p>
                	로그인/회원가입과 사용자의 회원 정보를 포함한 마이페이지, 구매 진행 및 업로더 관리 화면 기능, 주소 검색 API를 적용하였습니다.
                	이 외에도 공동구매와 경매의 신고 기능, DB 관리를 주로 담당하였습니다.
              </p>
              <div class="social">
                <a href="https://github.com/Seonmi-Hwang"><i class="icofont-github"></i></a>
                <a href="https://www.instagram.com/willow.s__/"><i class="icofont-instagram"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
            <div class="member">
              <img src="<%=request.getContextPath()%>/assets/img/team/parrotbill.PNG" alt="">
              <h4>hyekyung-kim</h4>
              <span>Groupbuy & Scheduling</span>
              <p>
                	공동구매와 스케줄러 및 알림, 회원가입시 사용자의 이메일 인증 기능을 구현하였습니다.
                	이 외에도 메인 페이지의 정렬과 REST API 기능을 담당하였습니다.
              </p>
              <div class="social">
                <a href=""><i class="icofont-twitter"></i></a>
                <a href=""><i class="icofont-facebook"></i></a>
                <a href=""><i class="icofont-instagram"></i></a>
                <a href=""><i class="icofont-linkedin"></i></a>
              </div>
            </div>
          </div>

        </div>

      </div>
    </section><!-- End Team Section -->
  </main><!-- End #main -->
<%@ include file="includeBottom.jsp" %> 