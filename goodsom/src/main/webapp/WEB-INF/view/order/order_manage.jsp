j<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
														<th>입금확인</th>
														<th>발송</th>
													</tr>
														<form:form modelAttribute="orderListForm" name="form" method="post">
														<c:if test="${!empty groupBuy}">
															<form:hidden path="groupBuyId" value="${groupBuy.groupBuyId}"/>
														</c:if>
														<c:if test="${!empty auction}">
															<form:hidden path="auctionId" value="${auction.auctionId}"/>
														</c:if>
														<c:forEach items="${orderListForm.orderList}" var="order" varStatus="status">
														<form:hidden path="orderList[${status.index}].orderId" value="${order.orderId}" />
													<tr>
														<td>${order.name}</td>
														<form:hidden path="orderList[${status.index}].name" value="${order.name}" />
														<td>${order.phone}</td>
														<form:hidden path="orderList[${status.index}].phone" value="${order.phone}" />
														<td>(${order.postcode}) &nbsp; ${order.address} &nbsp; ${order.detailAddress} &nbsp; ${order.extraAddress}</td>
														<form:hidden path="orderList[${status.index}].postcode" value="${order.postcode}" />
														<form:hidden path="orderList[${status.index}].address" value="${order.address}" />
														<form:hidden path="orderList[${status.index}].detailAddress" value="${order.detailAddress}" />
														<form:hidden path="orderList[${status.index}].extraAddress" value="${order.extraAddress}" />
														<td>${order.bank} &nbsp; ${order.account}</td>
														<form:hidden path="orderList[${status.index}].bank" value="${order.bank}" />
														<form:hidden path="orderList[${status.index}].account" value="${order.account}" />
														<td>${order.depositTime}</td>
														<form:hidden path="orderList[${status.index}].depositTime" value="${order.depositTime}" />
														<td>${order.totalPrice}</td>
														<form:hidden path="orderList[${status.index}].totalPrice" value="${order.totalPrice}" />
														<td>
															<c:choose>
															<c:when test="${!order.depositCheck}">
																<form:checkbox path="orderList[${status.index}].depositCheck" value="true" />
															</c:when>
															<c:otherwise>
																입금완료
																<form:hidden path="orderList[${status.index}].depositCheck" value="${order.depositCheck}" />
															</c:otherwise>
															</c:choose>
														</td>
														<td>
															<c:choose>
															<c:when test="${!order.isDelivered}">
																<form:checkbox path="orderList[${status.index}].isDelivered" value="true" />
															</c:when>
															<c:otherwise>
																발송완료
																<form:hidden path="orderList[${status.index}].isDelivered" value="${order.isDelivered}" />
															</c:otherwise>
															</c:choose>
														</td>
													</tr>
													</c:forEach>
													</form:form>
												</tbody>
											</table>
										</div>
										<div class="text text-right">
											<c:if test="${!empty groupBuy}">
												<input type="button" class="btn btn-primary py-3 px-5" onclick="button_click()" value="수정완료">
												<a class="btn btn-primary py-3 px-5" href="<c:url value='../../groupBuy/detail.do'>
																							<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
									 					 								   </c:url>">뒤로가기</a>
									 		</c:if>
									 		<c:if test="${!empty auction}">
									 			<input type="button" class="btn btn-primary py-3 px-5" onclick="button_click()" value="수정완료">
												<a class="btn btn-primary py-3 px-5" href="<c:url value='../../auction/detail.do'>
																							<c:param name="auctionId" value="${auction.auctionId}"/>
																	 					   </c:url>">뒤로가기</a>
									 		</c:if>
									 		
											<script>
												function button_click() {
													if (confirm("체크사항들을 적용하시겠습니까? (이 선택은 되돌릴 수 없습니다.)")) {
														form.submit();
													}
												}
											</script>		
	
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
