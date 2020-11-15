<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script>
	
	function home(targetUri) {
		form.action = targetUri;
		form.submit();
	}

</script>
<style>
	.error {
		color: #ff0000;
		/* font-weight: bold; */
	}
</style>

<body bgcolor="black">
	<div class="container" style="padding: 100px">
		<div class="row block-9 justify-content-center mb-5">
			<div class="col-md-8 mb-md-5">

				<h2 class="text-center">Register</h2><br/>

				<form:form modelAttribute="userForm" method="POST" action="register.do" class="bg-light p-5 contact-form">

					<div class="form-group">
						<label for="name">Email</label> 
						<form:input path="user.email" class="form-control" placeholder="Email ex) 20170000@dongduk.ac.kr" />
						<form:errors path="user.email" cssClass="error" />
						<form:errors cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">Password</label> 
						<form:input path="user.passwd" type="password" class="form-control" placeholder="Password" />
						<form:errors path="user.passwd" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">Check Password</label> 
						<form:input path="repeatedPassword" type="password" class="form-control" placeholder="Password" />
						<form:errors path="repeatedPassword" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">Name</label> 
						<form:input path="user.userName" class="form-control" placeholder="Name" />
						<form:errors path="user.userName" cssClass="error" />
					</div>
					
					<div class="form-group">
						<label for="name">Nickname</label>
						<form:input path="user.nickname" class="form-control" placeholder="Nickname" />
						<form:errors path="user.nickname" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">Phone</label> 
						<form:input path="user.phone" class="form-control" placeholder="Phone ex) 010-0000-0000" />
						<form:errors path="user.phone" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">Address</label> <br/> 
						<form:input path="user.address1" style="width:70px;" />&nbsp;-&nbsp; 
						<form:input path="user.address2" style="width:70px;" /> &nbsp;&nbsp; 
						<form:input path="user.address3" style="width:70px;" />
						<!-- <input id="zonecode" name="addr3" type="text" value="" style="width: 50px;" readonly /> &nbsp; 
						<input type="button" onClick="openDaumZipAddress();" value="find address" /> <br/> 
						<input type="text" name="addr4" id="address" value="" style="width: 240px;" readonly /> 
						<input type="text" name="addr5" id="address_etc" value="" style="width: 200px;" />  -->
					</div>

					<div class="form-group">
						<label for="name">Account</label>
						<div class="d-flex">
							<form:select path="user.refundBank" >
								<option value="" selected>은행</option>
								<form:options items="${cardBanks}" />
							</form:select>
							<form:input path="user.refundAccount" class="form-control" placeholder="Account ex) 123-1234-1234" /> 
						</div>
					</div>
					<br/>

					<div class="form-group" align="center">
						<input type="submit" value="Register" class="btn btn-primary py-3 px-5"> 
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>