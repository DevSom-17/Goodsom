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
</style>

<body>

<%@ include file="../header.jsp" %> 

<main id="main">
    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="section-title">
          <h2>결제 진행</h2>
        </div>

		<div class="site-section">
			<div class="container">
				<c:choose>
					<c:when test="${not empty orderForm.order.lineGroupBuys}">
						<div style="width:100%">
						<h3>주문 내역 확인</h3>  
						<div class="portfolio-wrap" style="padding : 35px">
								<div style="width:100%">
									<div class="portfolio-info">
										<h4>
											<a href="<c:url value='../../groupBuy/detail.do'>
															<c:param name="groupBuyId" value="${orderForm.order.groupBuyId}" />
													 </c:url>"> ${orderForm.order.groupBuy.title}</a>
										</h4>
										<c:forEach var="lineGroupBuy" items="${orderForm.order.lineGroupBuys}" varStatus="status"><br />
										<span class="meta d-inline-block mb-3">
											<span class="mx-2"> 옵션 : ${lineGroupBuy.selectOption}</span> &nbsp;&nbsp; 
											<span class="mx-2"> 수량 : ${lineGroupBuy.quantity}개</span> &nbsp;&nbsp;
											<span class="mx-2"> 금액 : ${lineGroupBuy.unitPrice}원</span>
										</span>
										</c:forEach>
										
										<div class="d-flex" style="float:right;padding:10px">
											<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
										</div>	
									</div>
								</div>
							</div>
							</div>
								<br />
					</c:when>
					<c:otherwise> <!-- auction이 담긴 orderForm이 넘어옴 -->
							<div style="width:100%">
							<h3>주문 내역 확인</h3>
							<div class="portfolio-wrap" style="padding : 35px">
							<div style="width:100%">
									<div class="portfolio-info">
										<h4>
											<a href="<c:url value='../auction/detail.do'>
															<c:param name="auctionId" value="${orderForm.order.auction.auctionId}" />
													 </c:url>"> ${orderForm.order.auction.title}</a>
										</h4>
		
										<span class="meta d-inline-block mb-3">
											<span class="mx-2"> 금액 : ${orderForm.order.totalPrice}원</span> <br>
										</span>
									</div>
									<div class="d-flex">
										<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
									</div>
								</div><br />
							</div>
							</div>
					</c:otherwise>
				</c:choose>

					<div style="width:100%">
						<h3>결제 정보 입력</h3>
						<div style="width:100%">
						<form:form modelAttribute="orderForm" class="bg-light p-5 contact-form">
							<form:input type="hidden" path="order.orderId" value="${order.userId}"/>
							<div class="form-group">
								<label for="account">카드정보</label> 
								<div class="d-flex">
								<form:select path="order.cardBank" items="${cardBanks}" />
								<form:errors path="order.cardBank" cssClass="error" />
								<form:input path="order.cardNo" type="text"
									class="form-control" placeholder="카드번호를 입력하세요 ex) 1234-1234-1234-1234" /> 
								</div>
								<form:errors path="order.cardNo" cssClass="error" />
							</div>
							
							<div class="form-group">
								<label for="validDate">유효기간</label> 
								<form:input path="order.validDate" type="text"
									class="form-control" placeholder="MM/YY ex) 12/34" />
								<form:errors path="order.validDate" cssClass="error" />	
							</div>

							<div class="form-group">
								<label for="cvc">CVC</label> 
								<form:input path="order.cvc" type="text" 
									class="form-control" placeholder="cvc ex) 123" />
								<form:errors path="order.cvc" cssClass="error" />	
							</div>

							<div class="form-group">
								<label for="phone">전화번호</label> 
								<form:input path="order.phone" type="text"
									class="form-control" placeholder="phone ex) 010-1234-5678" />
								<form:errors path="order.phone" cssClass="error" />	
							</div>

							<div class="form-group">
								<label for="name">Address</label> <br /> 
								<form:input path="order.address1" type="text"
									style="width: 70px;" />&nbsp;-&nbsp;
								<form:input path="order.address2" type="text"
									style="width: 70px;" /> &nbsp;&nbsp; 
								<form:input path="order.address3" type="text" 
								value="" style="width: 70px;" />
								<form:errors path="order.address1" cssClass="error" />	
								<form:errors path="order.address2" cssClass="error" />	
								<form:errors path="order.address3" cssClass="error" />	
							</div>

							<div class="form-group">
								<label for="refundAccount">환불계좌</label> 
								<div class="d-flex">
									<form:select path="order.refundBank" >
										<option value="" disabled>은행</option>
										<form:options items="${cardBanks}" />
									</form:select>
									<form:errors path="order.refundBank" cssClass="error" />	
									<form:input path="order.refundAccount" type="text"
										class="form-control" placeholder="환불계좌를 입력하세요 ex) 123-1234-1234" />
								</div>
								<form:errors path="order.refundAccount" cssClass="error" />	 
							</div>
							
							<br />

							<div class="form-group" align="center">
								<a class="btn btn-primary py-3 px-5" href="javascript:history.back()">취소</a> &nbsp; 
								<input type="submit" value="결제" class="btn btn-primary py-3 px-5"> 
							</div>
						</form:form>
					</div>
				</div>
			</div>
			</div>
		</div> 
	</section> <!-- END Portfolio Section -->
	</main>
	
	<%@ include file="../includeBottom.jsp" %>