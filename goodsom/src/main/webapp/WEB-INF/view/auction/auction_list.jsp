<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../IncludeTop.jsp" %> 

<%@ include file="../IncludeBanner.jsp" %> 
		
		<div class="site-section-cover">
			<div class="container">
				<div
					class="row align-items-center text-center justify-content-center">
					<div class="col-lg-6">
						<h1 class="text-white mb-4">Auction</h1>
						<p class="lead">솜솜이들이 갖고 있던 학교 굿즈를 경매하는 공간</p>

					</div>
				</div>
			</div>
		</div>
		
		
		<div class="site-section">
			<div class="container">
			<a class="btn btn-primary py-3 px-5" href="<c:url value='/auction/form.do'></c:url>">경매 등록</a> <br/> <br/>
				<div class="row">
					<c:forEach var="auction" items="${auctionList}" varStatus="status">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="post-entry-1 h-100">
								
								<a href="<c:url value='/auction/detail.do'><c:param name="auctionId" value="${auction.auctionId}"/></c:url>">
									<img src="${auction.img}" alt="Image" class="img-fluid">
								</a>
									
								<div class="post-entry-1-contents">
									<div class="price-wrap d-flex" style="color:blue;">
									
										<c:if test="${auction.state eq 'proceeding'}">
											<h5>Proceeding</h5> 
										</c:if>
										
										<c:if test="${auction.state eq 'closed'}">
											<h5>Closed</h5>
										</c:if>
										
			    						<span class="meta d-inline-block mb-3">
											&nbsp; ~ <fmt:formatDate value="${auction.endDate}" pattern="yyyy-MM-dd" />
										</span>
			    					</div>
										
									<h2><a  href="<c:url value='/auction/detail.do'>
											<c:param name="auctionId" value="${auction.auctionId}"/>
											</c:url>">${auction.title}</a>
									</h2>
										
									<span class="meta d-inline-block mb-3">
										<span>현재 최고 금액</span> &nbsp;
										<a href="#"><fmt:formatNumber value="${auction.maxPrice}" pattern="#,###원"/></a>
										
									</span>
									<p>
							 			<c:out value="${auction.content}" ></c:out>
									</p>
										
									
								</div>
							</div>
						</div>
					</c:forEach>


				</div>
			</div>
		</div>

<%@ include file="../IncludeBottom.jsp" %>