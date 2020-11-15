<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<style>
	.error {
		color: #ff0000;
		/* font-weight: bold; */
	}
</style>

<c:if test="${deleteComplete == 1}">
	<body onLoad="alert('회원탈퇴가 완료되었습니다.')">
</c:if>

<body bgcolor="black">
	<div class="container" style="padding: 100px">
		<div class="row block-9 justify-content-center mb-5">
			<div class="col-md-8 mb-md-5">

				<h2 class="text-center">Login</h2>
				<br/>
				
				<form:form modelAttribute="loginForm" method="POST" action="login.do" class="bg-light p-5 contact-form">
					<form:errors cssClass="error" /> <br />
					<div class="form-group">
						<label for="email">Email</label> 
						<form:input path="email" class="form-control" placeholder="Email" />
						<form:errors path="email" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="password">Password</label> 
						<form:input path="password" type="password" class="form-control" placeholder="Password" />
						<form:errors path="password" cssClass="error" />
					</div>
					<br />

					<div class="form-group" align="center">
						<input type="submit" value="Login" class="btn btn-primary py-3 px-5"> &nbsp;
				        <a class="btn btn-primary py-3 px-5" href="<c:url value='/user/register.do'></c:url>">Register</a>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>