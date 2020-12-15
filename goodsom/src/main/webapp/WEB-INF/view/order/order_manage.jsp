<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>

<style>
	.error {
		color: #ff0000;
		/* font-weight: bold; */
	}
	
	table tr {
		text-align : center;
	}
</style>



<body>

<%@ include file="../header.jsp" %> 

	<main id="main">
		<!-- ======= Portfolio Section ======= -->
		<section id="portfolio" class="portfolio">
			<div class="container">

				<div class="section-title">
					<h2>참여 현황</h2>
				</div>

				<div class="site-section">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-12">
									<div class="car-details">
										<div class="text text-left">
											<h2><b>
												<c:if test="${!empty groupBuy}">
													<a href="<c:url value='../../groupBuy/detail.do'>
																	<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
								 								</c:url>">${groupBuy.title}</a>
												</c:if>
												<c:if test="${!empty auction}">
													<a href="<c:url value='../../auction/detail.do'>
																	<c:param name="auctionId" value="${auction.auctionId}"/>
								 								</c:url>">${auction.title}</a>
												</c:if>
											</b></h2>
										</div>
										<br>

										<div class="text text-left">
											<table class="table table-striped">
												<tbody>
													<tr>
														<th>이름</th>
														<th>전화번호</th>
														<th>주소</th>
														<th>계좌번호</th>
														<th>입금시간</th>
														<th>금액</th>
														<th>상태</th>
														<th>발송</th>
													</tr>
														<c:forEach items="${orderList}" var="order">
													<tr>
														<td>${order.name}</td>
														<td>${order.phone}</td>
														<td>(${order.postcode}) &nbsp; ${order.address} &nbsp; ${order.detailAddress} &nbsp; ${order.extraAddress}</td>
														<td>${order.bank} &nbsp; ${order.account}</td>
														<td>${order.depositTime}</td>
														<td>${order.totalPrice}</td>
														<td>
															<c:if test="${order.depositCheck eq 'F'}">
																<input type="checkbox" id="depositCheck" value="T" />
															</c:if>
															<c:if test="${order.depositCheck eq 'T'}">
																<input type="text" value="입금완료" />
															</c:if>
														</td>
														<td>
															<c:if test="${order.isDelivered eq 'F'}">
																<input type="checkbox" id="isDelivered" value="T" />
															</c:if>
															<c:if test="${order.isDelivered eq 'T'}">
																<input type="text" value="배송완료" />
															</c:if>
														</td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</main>

	<%@ include file="../includeBottom.jsp" %>
