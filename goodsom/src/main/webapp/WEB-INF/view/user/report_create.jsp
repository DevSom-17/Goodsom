<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

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
</style>

<body>

 <%@ include file="../header.jsp" %>

	<main id="main">
		<!-- ======= Portfolio Section ======= -->
		<section id="portfolio" class="portfolio">
			<div class="container">

				<div class="section-title">
					<h2>신고접수</h2>
				</div>

				<div class="site-section" align="center">
							<form:form modelAttribute="reportForm" action="create.do" method="POST">
							     <input type="hidden" name="type" value='<c:url value="${type}"/>' />
								 <input type="hidden" name="id" value="${reportForm.id}" />
								 <input type="hidden" name="reporterId" value="${reportForm.reporterId}" />
								 <input type="hidden" name="userId" value="${reportForm.userId}" />
								 사유 : 
								 <select name="content">
									<c:forEach var="content" items="${contents}">
										<option value="${content}">${content}</option>
									</c:forEach>
								 </select> 
								 <br><br>
								 <input type="submit" class="btn-submit" value="제출">
							</form:form>
						</div>
					</div>
				</section>
		</main>

	<%@ include file="../includeBottom.jsp" %>
