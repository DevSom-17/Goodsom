<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>
<%@ include file="../header.jsp"%>
<script src="<c:url value="/assets/js/imagePreview.js"/>"></script>

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

<body>
	<!-- ======= Contact Section ======= -->
	<section id="contact" class="contact section-bg">
		<div class="container">

			<div class="section-title">
				<h2>경매 등록</h2>
				<p>경매에 등록하고자 하는 물품에 대한 정보들을 입력해주세요!</p>
			</div>

			<div class="row mt-5 justify-content-center">
				<div class="col-lg-10">
					<div class="info-wrap">
						<div class="row">
							<div class="col-lg-4 info">
								<i class="icofont-google-map"></i>
								<h4>Location:</h4>
								<p>
									A108 Adam Street<br>New York, NY 535022
								</p>
							</div>

							<div class="col-lg-4 info mt-4 mt-lg-0">
								<i class="icofont-envelope"></i>
								<h4>Email:</h4>
								<p>
									info@example.com<br>contact@example.com
								</p>
							</div>

							<div class="col-lg-4 info mt-4 mt-lg-0">
								<i class="icofont-phone"></i>
								<h4>Call:</h4>
								<p>
									+1 5589 55488 51<br>+1 5589 22475 14
								</p>
							</div>
						</div>
					</div>

				</div>

			</div>

			<div class="row mt-5 justify-content-center">
				<div class="col-lg-10">
					<form:form modelAttribute="auctionForm" name="auctionForm"
						method="post" enctype="multipart/form-data">

						<div class="form-row">
							<div class="col-md-6 form-group">
								<label for="auction.title">제목</label> &nbsp;&nbsp;&nbsp;
								<form:errors path="auction.title" cssClass="error" />
								<form:input path="auction.title" class="form-control"
									placeholder="제목을 입력해주세요." />
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 form-group" style="display: inline;">
								<label for="auction.report">대표 이미지</label>&nbsp;&nbsp;&nbsp;
								<form:errors path="auction.report" cssClass="error" />
								<br />
								<label for="auction.report">
									<img src="/assets/img/photo_add.png"  style="width:100px; height:100px; cursor: pointer;">
								</label>
								<form:input type="file" path="auction.report" onchange="previewImage(this, 'View_area')"
											style="display: none;" multiple="multiple"/>
								<span id="View_area" style="position: relative; color: black; border: 0px solid black;">
								</span>

							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 form-group">
								<label for="auction.content">상세 설명</label> &nbsp;&nbsp;&nbsp;
								<form:errors path="auction.content" cssClass="error" />
								<c:choose>
									<c:when test="${auctionForm.newAuction}">
										<form:textarea path="auction.content" class="form-control"
											placeholder="상세 설명을 적어주세요." cols="30" rows="10" />
									</c:when>
									<c:otherwise>
										<form:textarea path="auction.content" class="form-control"
											cols="30" rows="10" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 form-group">
								<label for="auction.startPrice">최소 입찰 금액</label>
								&nbsp;&nbsp;&nbsp;
								<form:errors path="auction.startPrice" cssClass="error" />
								<div class="d-flex">
									<div class="form-group mr-2">
										<c:choose>
											<c:when test="${auctionForm.newAuction}">
												<form:input type="number" path="auction.startPrice"
													class="form-control" placeholder="10000" />
											</c:when>
											<c:otherwise>
												<form:input type="number" path="auction.startPrice"
													class="form-control"
													value="${auctionForm.auction.startPrice}" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="endDate">마감일</label> &nbsp;&nbsp;&nbsp;
							<form:errors path="auction.endDate" cssClass="error" />
							<div class="d-flex">
								<div class="form-group mr-2">
									<c:choose>
										<c:when test="${auctionForm.newAuction}">
											<form:input type="date" path="auction.endDate"
												class="form-control" placeholder="yyyy-MM-dd" />
										</c:when>
										<c:otherwise>
											<fmt:formatDate value='${auctionForm.auction.endDate}'
												pattern='yyyy-MM-dd' var="dateFormat" />
											<form:input type="date" path="auction.endDate"
												class="form-control" value="${dateFormat}" />
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="form-group">
							<form:radiobuttons items="${amPm}" id="amPm"
								path="auction.isAmPm" />
							&nbsp;&nbsp;&nbsp;
							<form:errors path="auction.isAmPm" cssClass="error" />
							&nbsp;&nbsp;&nbsp;
						</div>
						<div class="form-group">
							<form:select path="auction.hour">
								<form:options path="auction.hour" items="${hourData}"
									itemLabel="label" itemValue="code" />
							</form:select>
							&nbsp;&nbsp;&nbsp;
							<form:select path="auction.minute">
								<form:options path="auction.minute" items="${minuteData}"
									itemLabel="label" itemValue="code" />
							</form:select>
						</div>


						<div class="form-group" align="right">
							<a class="btn btn-primary py-3 px-5"
								href="<c:url value='/auction/list.do'></c:url>">Cancel</a>
							&nbsp; <input type="button" value="Save"
								onClick="auctionSubmit(${auctionForm.newAuction})"
								class="btn btn-primary py-3 px-5">
						</div>
					</form:form>
				</div>

			</div>

		</div>
	</section>
	<!-- End Contact Section -->
</body>

<%@ include file="../includeBottom.jsp"%>

