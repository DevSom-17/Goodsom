<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script>

	function logoutCheck() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href= "logout.do";
		}
	}
	
	function removeCheck() {
		if (confirm("정말 탈퇴하시겠습니까?")) {
			location.href= "delete.do";
		}
	}

</script>

<c:choose>
	<c:when test="${deleteComplete == -1}">
		<body onLoad="alert('작성한 공동구매/경매 중 마감되지 않은 게시글이 있어서 \n회원탈퇴가 불가합니다.')">
	</c:when>
	<c:when test="${deleteComplete == 0}">
		<body onLoad="alert('예상치 못한 문제로 회원탈퇴에 실패했습니다.')">
	</c:when>
	<c:when test="${updateComplete == 0}">
		<body onLoad="alert('예상치 못한 문제로 회원정보 수정에 실패했습니다.')">
	</c:when>
	<c:otherwise>
		<body>
	</c:otherwise>
</c:choose>

<style>
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

 <%@ include file="../header.jsp" %>

	<main id="main">
		<!-- ======= Portfolio Section ======= -->
		<section id="portfolio" class="portfolio">
			<div class="container">

				<div class="section-title">
					<h2>회원 정보</h2>
				</div>

				<div class="site-section">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-12">
									<div class="car-details">
										<div class="text text-left">
											<table class="table table-striped">
												<tbody>
													<tr>
														<th scope="row">이름</th>
														<td>${userForm.user.userName}</td>
													</tr>
													<tr>
														<th scope="row">이메일</th>
														<td>${userForm.user.email}</td>
													</tr>
													<tr>
														<th scope="row">닉네임</th>
														<td>${userForm.user.nickname}</td>
													</tr>
													<tr>
														<th scope="row">전화번호</th>
														<td>${userForm.user.phone}</td>
													</tr>
													<tr>
														<th scope="row">주소</th>
														<td>(${userForm.user.postcode}) &nbsp; ${userForm.user.address} &nbsp; ${userForm.user.detailAddress} &nbsp; ${userForm.user.extraAddress}</td>
													</tr>
													<tr>
														<th scope="row">계좌번호</th>
														<td>${userForm.user.refundBank}&nbsp;
															${userForm.user.refundAccount}</td>
													</tr>
													<tr>
														<th scope="row">경고횟수</th>
														<td><a href="../mypage/report/detail.do">${userForm.user.warning}</a> / 3</td>
													</tr>
												</tbody>
											</table>
										</div>
										<br>
										<div class="container" style="text-align:center">
										 	<a class="btn-danger"
												href="javascript:removeCheck()">회원탈퇴</a> 
												&nbsp;
											<a class="btn-submit"
												href="<c:url value='/user/update.do'></c:url>">수정하기</a>
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
