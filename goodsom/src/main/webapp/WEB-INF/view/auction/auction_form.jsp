<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../IncludeTop.jsp" %> 

<script>
function auctionSubmit(isNewAuction) {

	if(isNewAuction){
		alert("경매를 등록합니다.");
		document.auctionForm.action="create.do";
	}else{
		alert("경매를 수정합니다.");
		document.auctionForm.action="update.do";
	}
	document.auctionForm.submit();
}
</script>
<style>
.error {
	color: #ff0000;
	/* font-weight: bold; */
}
</style>

<%@ include file="../IncludeBanner.jsp" %> 

		<div class="site-section-cover">
			<div class="container">
				<div
					class="row align-items-center text-center justify-content-center">
					<div class="col-lg-6">
						<h1 class="text-white mb-4">Add Auction</h1>
						<p class="lead">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit maxime nemo placeat dolor.</p>

					</div>
				</div>
			</div>
		</div>

		<div class="site-section bg-left-half">
			<div class="container">
				<div class="row" >
					<div class="col-lg-8 mb-5">
						<form:form modelAttribute="auctionForm" name="auctionForm" method="post" enctype="multipart/form-data">
							
							<div class="form-group row">
								<div class="col-md-12">
									<label for="auction.title">제목</label> &nbsp;&nbsp;&nbsp; <form:errors path="auction.title" cssClass="error"/> 
										<form:input path="auction.title" class="form-control" placeholder="Title"/>
										
									<%-- <c:choose>
										<c:when test="${auctionForm.newAuction}">
											<form:input path="auction.title" class="form-control" placeholder="Title" />
										</c:when>
										<c:otherwise>
											<form:input path="auction.title" class="form-control" value="${auctionForm.auction.title}" />
										</c:otherwise>
									</c:choose> --%>
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-md-12">
									<label for="auction.report">대표 이미지</label>&nbsp;&nbsp;&nbsp;<form:errors path="auction.report" cssClass="error"/><br/>
                					<form:input type="file" path="auction.report" />
                					
              					</div>
              				</div>
							
							<div class="form-group row">
								<div class="col-md-12">
									<label for="auction.content">상세 설명</label> &nbsp;&nbsp;&nbsp;<form:errors path="auction.content" cssClass="error"/>
									<c:choose>
										<c:when test="${auctionForm.newAuction}">
											<form:textarea path="auction.content" class="form-control"
											placeholder="Write description." cols="30" rows="10"/>
										</c:when>
										<c:otherwise>
											<form:textarea path="auction.content" class="form-control" cols="30" rows="10"/>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-md-12">
									<label for="auction.startPrice">최소 입찰 금액</label> &nbsp;&nbsp;&nbsp; <form:errors path="auction.startPrice" cssClass="error"/>
									<div class="d-flex">
										<div class="form-group mr-2">
										<c:choose>
											<c:when test="${auctionForm.newAuction}">
												<form:input type="number" path="auction.startPrice" class="form-control" placeholder="10000"/>
											</c:when>
											<c:otherwise>
												<form:input type="number" path="auction.startPrice" class="form-control" value="${auctionForm.auction.startPrice}"/>
											</c:otherwise>
										</c:choose>
										</div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
			              	<label for="auction.endDate">마감일</label> &nbsp;&nbsp;&nbsp; 
			              		<form:errors path="auction.endDate" cssClass="error"/>
				                <div class="d-flex">
					    		  <div class="form-group mr-2">
					    		  	<c:choose>
										<c:when test="${auctionForm.newAuction}">
							                <form:input type="date" path="auction.endDate" class="form-control" placeholder="yyyy-MM-dd"/>
										</c:when>
										<c:otherwise>
											<fmt:formatDate value='${groupBuyForm.groupBuy.endDate}' pattern='yyyy-MM-dd' var="dateFormat"/>
											<form:input type="date" path="auction.endDate" class="form-control"	value="${dateFormat}"/>
										</c:otherwise>
									</c:choose>
					             </div>
			              		</div>
			              	</div>
			              		
			              	<div class="form-group">
			              		<form:radiobuttons items="${amPm}" id="amPm" path="auction.isAmPm"/> &nbsp;&nbsp;&nbsp;
					            <form:errors path="auction.isAmPm" cssClass="error"/>  &nbsp;&nbsp;&nbsp;
					        </div>
					        <div class="form-group"> 
								<form:select path="auction.hour">
									<form:options path="auction.hour" items="${hourData}" itemLabel="label" itemValue="code"/>
								</form:select>
								&nbsp;&nbsp;&nbsp;
								<form:select path="auction.minute">
									<form:options path="auction.minute" items="${minuteData}" itemLabel="label" itemValue="code"/>
								</form:select>
			              	</div>

							
							<div class="form-group" align="right">
								<a class="btn btn-primary py-3 px-5" href="<c:url value='/auction/list.do'></c:url>">Cancel</a> &nbsp;
								<input type="button" value="Save" onClick="auctionSubmit(${auctionForm.newAuction})" class="btn btn-primary py-3 px-5">
							</div>
						</form:form>
					</div>
					
				</div>
				

			</div>
			
		</div>


<%@ include file="../IncludeBottom.jsp" %>

