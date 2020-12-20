<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script>

</script>
 <%@ include file="../header.jsp" %>

<main id="main">
	<!-- ======= Portfolio Section ======= -->
	<section id="portfolio" class="portfolio">
		<div class="container">

			<div class="section-title">
				<h2>알림함</h2>
			</div>


			<div class="site-section">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-12">
							<div class="car-details">
								<div class="text text-left">
									<h1>
										<b>${nickName}</b>
									</h1>
								</div>
								<br>

								<h4><b>공동구매 알림</b></h4>
									<table class="table table-striped">
										<tbody>
											<tr>
												<th scope="row">제목</th>
												<th scope="row">날짜</th>
												<th scope="row">내용</th>
											</tr>
											<c:forEach var="noti_g" items="${groupBuyNotiList}"
												varStatus="status">
												<tr>
													<td><a
														href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_g.notiId}"/>
															<c:param name="type" value="2"/>
															<c:param name="content" value="${noti_g.content}"/>
														</c:url>">${noti_g.title}</a>
													</td>
													<td><fmt:formatDate value="${noti_g.notiDate}"
															pattern="yyyy-MM-dd" /></td>
													<td><a
														href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_g.notiId}"/>
															<c:param name="type" value="2"/>
															<c:param name="content" value="${noti_g.content}"/>
														</c:url>">${noti_g.content}</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								<br>

								<h4><b>경매 알림</b></h4>
								<div class="text text-left">
									<table class="table table-striped">
										<tbody>
											<tr>
												<th scope="row">제목</th>
												<th scope="row">날짜</th>
												<th scope="row">내용</th>
											</tr>
											<c:forEach var="noti_a" items="${bidNotiList}"
												varStatus="status">
												<tr>
													<td><a
														href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_a.notiId}"/>
															<c:param name="type" value="1"/>
															<c:param name="content" value="${noti_a.content}"/>
														</c:url>">${noti_a.title}</a>
													</td>
													<td><fmt:formatDate value="${noti_a.notiDate}"
															pattern="yyyy-MM-dd" /></td>
													<td><a
														href="<c:url value='/noti/detail.do'>
															<c:param name="notiId" value="${noti_a.notiId}"/>
															<c:param name="type" value="1"/>
															<c:param name="content" value="${noti_a.content}"/>
														</c:url>">${noti_a.content}</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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

