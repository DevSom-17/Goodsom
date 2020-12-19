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
					<h2>신고접수</h2>
				</div>

				<div class="site-section">
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
								 </select> &nbsp;&nbsp;
								 <input type="submit"  value="완료">
							</form:form>
						</div>
					</div>
				</section>
		</main>

	<%@ include file="../includeBottom.jsp" %>
