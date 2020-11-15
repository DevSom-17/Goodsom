<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../IncludeTop.jsp" %> 

<script>

</script>
<%@ include file="../IncludeBanner.jsp" %> 

		<div class="site-section-cover">
			<div class="container">
				<div
					class="row align-items-center text-center justify-content-center">
					<div class="col-lg-6">
						<h1 class="text-white mb-4">Noti Page</h1>
						<p class="lead">알림을 통해 참여한 경매/공동구매 등의 낙찰/마감 여부를 확인할 수 있습니다.</p>
					</div>
				</div>
			</div>
		</div>


		<div class="site-section">
			<section class="ftco-section ftco-car-details">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-12">
							<div class="car-details">
								<div class="text text-left">
									<span class="subheading">${nickname}님의</span>
									<h1>
										<b>알림함</b>
									</h1>
								</div>
								<br>
								
								<h5>경매 알림</h5>
								<div class="text text-left">
									<table class="table table-striped">
										<tbody>
											<tr>
												<th scope="row">제목</th>
												<th scope="row">날짜</th>
												<th scope="row">내용</th>
											</tr>
											<c:forEach var="noti_a" items="${bidNotiList}" varStatus="status">
												<tr>
													<td><a  href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_a.notiId}"/>
															<c:param name="type" value="1"/>
															<c:param name="content" value="${noti_a.content}"/>
														</c:url>">${noti_a.title}</a>
													</td>
													<td><fmt:formatDate value="${noti_a.notiDate}" pattern="yyyy-MM-dd" />
													</td>
													<td><a  href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_a.notiId}"/>
															<c:param name="type" value="1"/>
															<c:param name="content" value="${noti_a.content}"/>
														</c:url>">${noti_a.content}</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<h5>공동구매 알림</h5>
									<table class="table table-striped">
										<tbody>
											<tr>
												<th scope="row">제목</th>
												<th scope="row">날짜</th>
												<th scope="row">내용</th>
											</tr>
											<c:forEach var="noti_g" items="${groupBuyNotiList}" varStatus="status">
												<tr>
													<td><a  href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_g.notiId}"/>
															<c:param name="type" value="2"/>
															<c:param name="content" value="${noti_g.content}"/>
														</c:url>">${noti_g.title}</a>
													</td>
													<td><fmt:formatDate value="${noti_g.notiDate}" pattern="yyyy-MM-dd" />
													</td>
													<td><a  href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_g.notiId}"/>
															<c:param name="type" value="2"/>
															<c:param name="content" value="${noti_g.content}"/>
														</c:url>">${noti_g.content}</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div><br><br>
								
								<div class="container">
									<a class="btn btn-primary py-3 px-5" href="<c:url value='/mypage/list.do'></c:url>">목록 보기</a> &nbsp;
									
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		
<%@ include file="../IncludeBottom.jsp" %>

