<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../IncludeTop.jsp"%>

<%@ include file="../IncludeBanner.jsp" %> 

<div class="site-section-cover">
	<div class="container">
		<div class="row align-items-center text-center justify-content-center">
			<div class="col-lg-6">
				<h1 class="text-white mb-4">My Page</h1>
				<p class="lead">회원 정보 조회 및 수정, 공동구매 및 경매에 등록한 목록이나 결제한 목록, 알림 등을 볼 수 있는 페이지</p>
			</div>
		</div>
	</div>
</div>


<div class="site-section">
	<section class="ftco-section ftco-car-details">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="car-details">
						<div class="text text-left">
							<span class="subheading" style="color:red;">&nbsp; ${message}</span><br />
							<h1>
								<b>세부 결제 내역</b>
							</h1>
							<br />
						<c:choose>
							<c:when test="${not empty order.groupBuy}">	
								<h3>
									<button disabled>공동구매</button>
									&nbsp; ${order.groupBuy.title}
								</h3>
							</c:when>
							<c:when test="${not empty order.auction}">
								<h3>
									<button disabled>경매</button>
									&nbsp; ${order.auction.title}
								</h3>
							</c:when>
						</c:choose>
						</div>
						<br>

						<c:choose>
							<c:when test="${not empty order.groupBuy}">	
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="post-entry-1 h-100" style="width:1000px;">
									<div class="post-entry-1-contents" style="width:1000px;">
										<h4>
											<a href="<c:url value='../../groupBuy/detail.do'>
																<c:param name="groupBuyId" value="${order.groupBuy.groupBuyId}" />
													 </c:url>"> ${order.groupBuy.title}</a>
										</h4>
										<c:forEach var="lineGroupBuy" items="${order.lineGroupBuys}" varStatus="status"><br />
										<span class="meta d-inline-block mb-3"> 
											<span class="mx-2"> 옵션 : ${lineGroupBuy.selectOption}</span> &nbsp;&nbsp; 
											<span class="mx-2"> 수량 : ${lineGroupBuy.quantity}개</span> &nbsp;&nbsp;
											<span class="mx-2"> 금액 : ${lineGroupBuy.unitPrice}원</span>
										</span>
										</c:forEach>
									</div>
								</div>
								<br />
								<div class="d-flex">
									<h4>TotalPrice : ${order.totalPrice}원</h4>
									&nbsp;
								</div>
							</div>
							</c:when>
							<c:when test="${not empty order.auction}">	
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="post-entry-1 h-100" style="width:1000px;">
									<div class="post-entry-1-contents" style="width:1000px;">
										<h4>
											<a href="<c:url value='../../auction/detail.do'>
																<c:param name="auctionId" value="${order.auction.auctionId}" />
													 </c:url>"> ${order.auction.title}</a>
										</h4>
	
										<span class="meta d-inline-block mb-3"> 
											<span class="mx-2"> 금액 : ${order.totalPrice}원</span> &nbsp;&nbsp;<br>
										</span>
									</div>
								</div>
								<br />
								<div class="d-flex">
									<h4>TotalPrice : ${order.totalPrice}원</h4>
									&nbsp;
								</div>
							</div>
							</c:when>
						</c:choose>
						<br>

						<div class="text text-left">
							<table class="table table-striped">
								<tbody>
									<tr>
										<th scope="row">cardInfo</th>
										<td>${order.cardBank} &nbsp; ${order.cardNo}</td>
									</tr>
									<tr>
										<th scope="row">validDate</th>
										<td>${order.validDate}</td>
									</tr>
									<tr>
										<th scope="row">CVC</th>
										<td>${order.cvc}</td>
									</tr>
									<tr>
										<th scope="row">Address</th>
										<td>${order.address1}-${order.address2} -
											${order.address3}</td>
									</tr>
									<tr>
										<th scope="row">Phone</th>
										<td>${order.phone}</td>
									</tr>
									<tr>
										<th scope="row">RefundAccount</th>
										<td>${order.refundBank} &nbsp; ${order.refundAccount}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<br> <br>

						<div class="container">
							<a class="btn btn-primary py-3 px-5" href="<c:url value='/mypage/list.do'>
																			<c:param name="menuId" value ="0" />
																	   </c:url>">목록</a>
							&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<!-- 구현 끝 -->

<div class="site-section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="service-29191">
					<span class="wrap-icon mb-4 d-block"> <span
						class="icon-desktop_windows"></span>
					</span>
					<h3 class="mb-3">Interface Design</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Deleniti voluptatem reiciendis minus, a dolores.</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="service-29191">
					<span class="wrap-icon mb-4 d-block"> <span
						class="icon-explore"></span>
					</span>
					<h3 class="mb-3">Product Design</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Deleniti voluptatem reiciendis minus, a dolores.</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="service-29191">
					<span class="wrap-icon mb-4 d-block"> <span
						class="icon-layers"></span>
					</span>
					<h3 class="mb-3">Quality Results</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Deleniti voluptatem reiciendis minus, a dolores.</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="site-section bg-primary">
	<div class="container mb-5">
		<div class="row">
			<div class="col-md-7">
				<h2 class="text-white font-weight-bold">Dongduk Women's
					University</h2>
				<p class="text-white lead">Computer Science 17th grade</p>
				<p class="text-white lead">Software System Development Final
					Project Goodsom</p>
			</div>
		</div>
	</div>
	<div class="container-fluid"></div>
</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-migrate-3.0.0.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.animateNumber.min.js"></script>
	<script src="js/jquery.fancybox.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/aos.js"></script>


	<script src="js/typed.js"></script>
	<script>
		var typed = new Typed('.typed-words', {
			strings : [ "Business", " Startups", " Organization", " Company" ],
			typeSpeed : 80,
			backSpeed : 80,
			backDelay : 4000,
			startDelay : 1000,
			loop : true,
			showCursor : true
		});
	</script>


	<script src="js/main.js"></script>

	</body>
	</html>