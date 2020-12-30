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
	if (isNewAuction) {
		document.auctionForm.action="create.do";
	} else {
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
.btn-submit {
	position: relative;
	top: 0;
	right: 5px;
	bottom: 0;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 8px 30px;
	background: #3498db;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
.btn-danger {
	position: relative;
	top: 0;
	right: 5px;
	bottom: 0;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 8px 30px;
	background: #bb404c;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
</style>

<body>
	<!-- ======= Contact Section ======= -->
	<section id="contact" class="contact section-bg">
		<div class="container">

			<div class="section-title">
				<c:choose>
					<c:when test="${auctionForm.newAuction}">
						<h2>경매 등록</h2>
					</c:when>
					<c:otherwise>
						<h2>경매 수정</h2>
					</c:otherwise>
				</c:choose>
				<p>해당 경매에 대한 필수 정보들을 입력해주세요!</p>
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
								이미지 <span style="font-size: small; color: #898a8b;">(5MB이하)</span>&nbsp;&nbsp;&nbsp;
								<form:errors path="auction.report" cssClass="error" />
								<br />
								<label for="auction.report">
									<img src="/assets/img/photo_add.png" id="addImg" style="width:100px; height:100px; cursor: pointer;">
								</label>
								<form:input type="file" path="auction.report" onchange="previewImage(this, 'View_area')"
											style="display: none;" multiple="multiple" accept=".jpg, .png, .jpeg, .gif"/>
								<span id="View_area" style="position: relative; color: black; border: 0px solid black;">
								</span>
								<c:choose>
									<c:when test="${auctionForm.newAuction eq false}">
										<div style="padding-left: 20px;">
											<input type="checkbox" name="checkExistingImage" id="checkExistingImage" onchange="previewExistingImage()"/> 기존 이미지 사용
											<br/>
											<span id="ExistingImg_View_area" style="position: relative; color: black; border: 0px solid black; display:none;">
												<c:forEach var="img" items="${auctionForm.auction.imgs_a}" varStatus="status">
													<span id="existing_img_id_${status.index}" style="width: 100px; height: 100px;">
														<img class="existingImg" src="${img.url}" style="width: inherit; height: inherit;">
													</span>
												</c:forEach>
											</span>
											<input type="hidden" name="useExistingImage" id="useExistingImage" value="no" />
										</div>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="useExistingImage" id="useExistingImage" value="no"/>
									</c:otherwise>
								</c:choose>
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
											<form:input type="date" path="auction.endDate" id="datepicker"
												class="form-control" placeholder="yyyy-MM-dd" />
										</c:when>
										<c:otherwise>
											<fmt:formatDate value='${auctionForm.auction.endDate}'
												pattern='yyyy-MM-dd' var="dateFormat" />
											<form:input type="date" path="auction.endDate" id="datepicker"
												class="form-control" value="${dateFormat}" />
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="form-group">
							<form:radiobuttons items="${amPm}" id="amPm" name="amPm"
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

						<div class="form-group" align="center">
							<a class="btn-danger"
								href="<c:url value='/auction/list.do'></c:url>">취소</a>
							&nbsp; <input type="button" value="완료"
								onClick="auctionSubmit(${auctionForm.newAuction})"
								class="btn-submit">
						</div>
					</form:form>
				</div>

			</div>

		</div>
	</section>
	<!-- End Contact Section -->
</body>

<%@ include file="../includeBottom.jsp"%>

