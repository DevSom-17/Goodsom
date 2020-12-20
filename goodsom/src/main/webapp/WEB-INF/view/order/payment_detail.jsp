<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>

<%@ include file="../header.jsp"%>

<main id="main">
	<!-- ======= Portfolio Section ======= -->
	<section id="portfolio" class="portfolio">
		<div class="container">

			<div class="section-title">
				<h2>세부 구매 내역</h2>
			</div>

			<div class="site-section">
				<div class="container">
					<div class="text text-left">
						<span class="subheading" style="color: red;">${message}</span><br />
					</div>
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
						<div class="portfolio-wrap" style="padding: 35px">
							<div style="width: 100%">
								<div class="portfolio-info">
									<h4>
										<a
											href="<c:url value='../../groupBuy/detail.do'>
																<c:param name="groupBuyId" value="${order.groupBuy.groupBuyId}" />
													 </c:url>">
											${order.groupBuy.title}</a>
									</h4>
									<c:forEach var="lineGroupBuy" items="${order.lineGroupBuys}"
										varStatus="status">
										<br />
										<span class="meta d-inline-block mb-3"> <span
											class="mx-2"> 옵션 : ${lineGroupBuy.selectOption}</span>
											&nbsp;&nbsp; <span class="mx-2"> 수량 :
												${lineGroupBuy.quantity}개</span> &nbsp;&nbsp; <span class="mx-2">
												금액 : ${lineGroupBuy.unitPrice}원</span>
										</span>
									</c:forEach>
									<div class="d-flex">
										<h4>TotalPrice : ${order.totalPrice}원</h4>
										&nbsp;
									</div>
								</div>
							</div>
						</div>
						<br />

					</c:when>
					<c:when test="${not empty order.auction}">
						<div class="portfolio-wrap" style="padding: 35px">
							<div style="width: 100%">
								<div class="portfolio-info">
									<h4>
										<a
											href="<c:url value='../../auction/detail.do'>
																<c:param name="auctionId" value="${order.auction.auctionId}" />
													 </c:url>">
											${order.auction.title}</a>
									</h4>

									<span class="meta d-inline-block mb-3"> <span
										class="mx-2"> 금액 : ${order.totalPrice}원</span> &nbsp;&nbsp;<br>
									</span>
								</div>
								<div class="d-flex">
									<h4>TotalPrice : ${order.totalPrice}원</h4>
									&nbsp;
								</div>
							</div>
						</div>
						<br />

					</c:when>
				</c:choose>
				<br>

				<div class="text text-left">
					<table class="table table-striped">
						<tbody>
							<tr>
								<th scope="row">입금자명</th>
								<td>${order.name}</td>
							</tr>
							<tr>
								<th scope="row">전화번호</th>
								<td>${order.phone}</td>
							</tr>
							<tr>
								<th scope="row">계좌번호</th>
								<td>${order.bank}&nbsp;${order.account}</td>
							</tr>
							<tr>
								<th scope="row">주소</th>
								<td>(${order.postcode}) &nbsp; ${order.address} &nbsp;
									${order.detailAddress} &nbsp; ${order.extraAddress}</td>
							</tr>
							<tr>
								<th scope="row">입금시간</th>
								<td>${order.depositTime}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<br> <br>

				<div class="container">
					<a class="btn btn-primary py-3 px-5"
						href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  				<c:param name="listType" value ="2" />
	                  		  </c:url>">목록</a>
					&nbsp;
				</div>
			</div>
		</div>
	</section>
</main>

<%@ include file="../includeBottom.jsp" %>