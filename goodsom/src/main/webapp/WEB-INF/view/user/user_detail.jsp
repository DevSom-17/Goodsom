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

<body>

 <%@ include file="../header.jsp" %>

	<main id="main">
		<!-- ======= Portfolio Section ======= -->
		<section id="portfolio" class="portfolio">
			<div class="container">

				<div class="section-title">
					<h2>마이페이지</h2>
					<p>회원 정보 조회 및 수정, 공동구매 및 경매에 등록한 목록이나 결제한 목록, 알림 등을 볼 수 있는 페이지</p>
				</div>

				<div class="site-section">
					<section class="ftco-section ftco-car-details">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-12">
									<div class="car-details">
										<div class="text text-left">
											<span class="subheading">회원 정보</span>
											<h1>
												<b>${userForm.user.userName}</b>
											</h1>
										</div>
										<br>

										<div class="text text-left">
											<table class="table table-striped">
												<tbody>
													<tr>
														<th scope="row">Email</th>
														<td>${userForm.user.email}</td>
													</tr>
													<tr>
														<th scope="row">Nickname</th>
														<td>${userForm.user.nickname}</td>
													</tr>
													<tr>
														<th scope="row">Phone</th>
														<td>${userForm.user.phone}</td>
													</tr>
													<tr>
														<th scope="row">Address</th>
														<td>${userForm.user.address1}-
															${userForm.user.address2} - ${userForm.user.address3}</td>
													</tr>
													<tr>
														<th scope="row">Account</th>
														<td>${userForm.user.refundBank}&nbsp;
															${userForm.user.refundAccount}</td>
													</tr>
												</tbody>
											</table>
										</div>
										<br>
										<br>

										<div class="container">
											<a class="btn btn-primary py-3 px-5"
												href="<c:url value='/mypage/list.do'></c:url>">목록 보기</a>
											&nbsp; <a class="btn btn-primary py-3 px-5"
												href="<c:url value='/user/update.do'></c:url>">회원 정보 수정</a>
											&nbsp; <a class="btn btn-primary py-3 px-5"
												href="<c:url value='/noti/list.do'></c:url>">알림 목록 보기</a>
											&nbsp; <a class="btn btn-primary py-3 px-5"
												href="javascript:logoutCheck()">로그아웃</a> &nbsp; <a
												class="btn btn-primary py-3 px-5"
												href="javascript:removeCheck()">회원 탈퇴</a>
										</div>

									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</section>
	</main>

	<%@ include file="../includeBottom.jsp" %>
