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

<c:if test="${deleteComplete == 1}">
	<body onLoad="alert('회원탈퇴가 완료되었습니다.')">
</c:if>

<body bgcolor="black">
	<div class="container" style="padding: 100px">
		<div class="row block-9 justify-content-center mb-5">
			<div class="col-md-8 mb-md-5">

				<h2 class="text-center">GOODSOM</h2>
				<br/>
				
				<form:form modelAttribute="loginForm" method="POST" action="login.do" class="bg-light p-5 contact-form">
					<form:errors cssClass="error" /> <br />
					<div class="form-group">
						<label for="email">이메일</label> 
						<form:input path="email" class="form-control" placeholder="ex) 20170000@dongduk.ac.kr" />
						<form:errors path="email" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="password">비밀번호</label> 
						<form:input path="password" type="password" class="form-control" placeholder="비밀번호를 입력하세요" />
						<form:errors path="password" cssClass="error" />
					</div>
					<br />

					<div class="form-group" align="center">
						<input type="submit" value="로그인" class="btn-submit"> &nbsp;
					</div>
					
					<a style="float:right" href="<c:url value='/user/register.do'></c:url>">계정이 없으신가요?</a>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>