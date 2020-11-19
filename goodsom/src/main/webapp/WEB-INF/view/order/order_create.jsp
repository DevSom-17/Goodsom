<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>

<%@ include file="../header.jsp" %> 

		<div class="site-section-cover">
			<div class="container">
				<div
					class="row align-items-center text-center justify-content-center">
					<div class="col-lg-6">
						<h1 class="text-white mb-4">Proceed Order</h1>
						<p class="lead">결제를 진행하는 화면</p>

					</div>
				</div>
			</div>
		</div>

		<div class="site-section bg-left-half">
			<div class="container">
			
				<h2>주문 내역 확인</h2>	<br />
				
				<c:choose>
					<c:when test="${not empty orderForm.order.lineGroupBuys}">  
						<div class="col-lg-4 col-md-6 mb-4">
								<div class="post-entry-1 h-100" style="width:1000px;">
									<div class="post-entry-1-contents" style="width:1000px;">
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
									</div>
								</div>
								<br />
								<div class="d-flex">
									<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
								</div>
						</div>
						
					</c:when>
					<c:otherwise> <!-- auction이 담긴 orderForm이 넘어옴 -->
							<div class="col-lg-4 col-md-6 mb-4">
								<div class="post-entry-1 h-100" style="width:1000px;">
									<div class="post-entry-1-contents" style="width:1000px;">
										<h3>
											<a href="<c:url value='../auction/detail.do'>
															<c:param name="auctionId" value="${orderForm.order.auction.auctionId}" />
													 </c:url>"> ${orderForm.order.auction.title}</a>
										</h3>
		
										<span class="meta d-inline-block mb-3">
											<span class="mx-2"> 금액 : ${orderForm.order.totalPrice}원</span> <br>
										</span>
									</div>
								</div><br />
								<div class="d-flex">
									<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
								</div>
							</div>
					</c:otherwise>
				</c:choose>

				<div class="row" >
					<div class="col-lg-8 mb-5">
						<h2>결제 정보 입력</h2>
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
								<input type="submit" value="Order" class="btn btn-primary py-3 px-5"> &nbsp;
								<a class="btn btn-primary py-3 px-5" href="javascript:history.back()">Cancel</a> 
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
		<!-- END .site-section -->


		<div class="footer site-section bg-light">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<div class="site-logo-footer">
							<a href="index.html"></a>
						</div>
					</div>
					<div class="col-md-8 ml-auto">
						<div class="row">
							<div class="col-md-4 ml-auto">
								<ul class="list-unstyled links">
									<!-- <li><a href="#">Contact Us</a></li>
									<li><a href="#">hello@mydomain.com</a></li>
									<li><a href="#">+1 829 2293 382</a></li>
									<li><a href="#">Support</a></li> -->
								</ul>
							</div>
							<div class="col-md-4">
								<ul class="list-unstyled links">
									<!-- <li><a href="#">Home</a></li>
									<li><a href="#">Blog</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">About Us</a></li> -->
								</ul>
							</div>
							<div class="col-md-4">
								<ul class="list-unstyled links">
									<!-- <li><a href="#">Home</a></li>
									<li><a href="#">Blog</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">About Us</a></li> -->
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section bg-light">
			<div class="container">
				<div class="row mb-4 text-center">
					<div class="col">
						<a href="#"><span class="m-2 icon-facebook"></span></a> <a
							href="#"><span class="m-2 icon-twitter"></span></a> <a href="#"><span
							class="m-2 icon-linkedin"></span></a> <a href="#"><span
							class="m-2 icon-instagram"></span></a> <a href="#"><span
							class="m-2 icon-skype"></span></a>
					</div>
				</div>
				<div class="row mt-5 justify-content-center">
					<div class="col-md-7 text-center">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved | This template is made with <i
								class="icon-heart text-danger" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>
			</div>
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
            strings: ["Business"," Startups"," Organization", " Company"],
            typeSpeed: 80,
            backSpeed: 80,
            backDelay: 4000,
            startDelay: 1000,
            loop: true,
            showCursor: true
            });
            </script>


	<script src="js/main.js"></script>

</body>

</html>