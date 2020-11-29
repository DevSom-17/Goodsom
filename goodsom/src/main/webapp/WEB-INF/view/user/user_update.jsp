<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<body>

 <%@ include file="../header.jsp" %> 

  <main id="main">
		
	<!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">
		<div class="section-title">
			<h2>회원 정보 수정</h2>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row block-9 justify-content-center mb-5">
					<div class="col-md-8 mb-md-5">
							<form:form modelAttribute="userForm" method="POST" action="update.do" class="bg-light p-5 contact-form">
								
								<div class="form-group">
									<label for="email">이메일</label> &emsp;
									<form:input path="user.email" class="form-control" readonly="true"/><br/>
								</div>
								
						        <div class="form-group">
									<label for="password">비밀번호</label> &emsp;
									<form:input path="user.passwd" type="password" class="form-control" />
									<form:errors path="user.passwd" cssClass="error" /><br/>
								</div>
								
								<div class="form-group">	
									<label for="password">비밀번호 확인</label> &emsp;
									<form:input path="repeatedPassword" type="password" class="form-control" />
									<form:errors path="repeatedPassword" cssClass="error" /><br/>
								</div>
								
								<div class="form-group">	
									<label for="userName">이름</label> &emsp;
									<form:input path="user.userName" class="form-control" readonly="true"/><br/>
								</div>
								
								<div class="form-group">	
									<label for="nickname">닉네임</label> &emsp;
									<form:input path="user.nickname" class="form-control" />
									<form:errors path="user.nickname" cssClass="error" /><br/>
								</div>
								
								<div class="form-group">
									<label for="phone">전화번호</label> &emsp;
									<form:input path="user.phone" class="form-control" />
									<form:errors path="user.phone" cssClass="error" /><br/>
								</div>
								
								<div class="form-group">	
									<label>주소</label> <br />
									<form:input path="user.address1" style="width:70px;"/>&nbsp;-&nbsp; 
									<form:input path="user.address2" style="width:70px;"/> &nbsp;&nbsp; 
									<form:input path="user.address3" style="width:70px;"/><br/>
								</div>
								
								<div class="form-group">
									<label>계좌번호</label> &emsp;
									<div class="d-flex">
										<form:select path="user.refundBank" >
											<option value="">은행</option>
											<form:options items="${cardBanks}" />
										</form:select>
										<form:input path="user.refundAccount" class="form-control" />
										<form:errors path="user.refundAccount" cssClass="error" />
									</div><br/>
								</div>
		
								<div class="form-group" align="center">
									<a class="btn btn-primary py-3 px-5" href="<c:url value='/user/detail.do'></c:url>">취소</a> &nbsp;
									<input type="submit" value="저장" class="btn btn-primary py-3 px-5">
								</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	</main>


<%@ include file="../includeBottom.jsp" %>
