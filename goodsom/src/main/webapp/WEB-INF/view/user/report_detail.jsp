<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<body>

 <%@ include file="../header.jsp" %>

	<main id="main">
		<!-- ======= Portfolio Section ======= -->
		<section id="portfolio" class="portfolio">
			<div class="container">

				<div class="section-title">
					<h2>신고 현황</h2>
					<p>신고 현황 및 경고 횟수</p>
				</div>

				<div class="site-section">
					<section class="ftco-section ftco-car-details">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-12">
									<div class="car-details">
										<div class="text text-left">
											<h3>
												<b>신고현황</b>
											</h3>
										</div>
										<br>
										<div class="text text-left">
											<table class="table table-striped">
												<tbody>
													<tr>
														<th scope="row">욕설 및 비방</th>
														<td>${reportForm.abuse} 회</td>
													</tr>
													<tr>
														<th scope="row">거래파기</th>
														<td>${reportForm.destroy} 회</td>
													</tr>
												</tbody>
											</table>
										</div>
										
										<br><br>
										
										<div class="text text-left">
											<h3>
												<b>경고횟수</b>
											</h3>
										</div>
										<br>
										<div class="text text-left">
											<table class="table table-striped">
												<tbody>
													<tr>
														<th scope="row">${userSession.user.warning} 회</th>
													</tr>
												</tbody>
											</table>
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
										