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
									<form:input path="user.phone" class="form-control" readonly="true"/>
									<form:errors path="user.phone" cssClass="error" /><br/>
								</div>
								
								<div class="form-group">
									<label for="name">주소</label> <br/> 
									<form:input path="user.postcode" class="form-control" placeholder="우편번호" readonly="true" style="width:30%;float:left;margin-bottom:5px"/> &nbsp;
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
									<form:input path="user.address" class="form-control" placeholder="주소" style="margin-bottom:5px" readonly="true" />
									<form:input path="user.detailAddress" class="form-control" placeholder="상세주소" style="width:50%;float:left"/> &nbsp; 
									<form:input path="user.extraAddress" class="form-control" placeholder="참고항목" readonly="true" style="width:49%;float:right"/>
								</div>
											
								<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
									<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
								</div>
			
								<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
								<script>
								    // 우편번호 찾기 찾기 화면을 넣을 element
								    var element_wrap = document.getElementById('wrap');
								
								    function foldDaumPostcode() {
								        // iframe을 넣은 element를 안보이게 한다.
								        element_wrap.style.display = 'none';
								    }
								
								    function execDaumPostcode() {
								        // 현재 scroll 위치를 저장해놓는다.
								        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
								        new daum.Postcode({
								            oncomplete: function(data) {
								                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
								
								                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
								                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
								                var addr = ''; // 주소 변수
								                var extraAddr = ''; // 참고항목 변수
								
								                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
								                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								                    addr = data.roadAddress;
								                } else { // 사용자가 지번 주소를 선택했을 경우(J)
								                    addr = data.jibunAddress;
								                }
								
								                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
								                if(data.userSelectedType === 'R'){
								                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
								                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
								                        extraAddr += data.bname;
								                    }
								                    // 건물명이 있고, 공동주택일 경우 추가한다.
								                    if(data.buildingName !== '' && data.apartment === 'Y'){
								                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
								                    }
								                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								                    if(extraAddr !== ''){
								                        extraAddr = ' (' + extraAddr + ')';
								                    }
								                    // 조합된 참고항목을 해당 필드에 넣는다.
								                    document.getElementById("user.extraAddress").value = extraAddr;
								                
								                } else {
								                    document.getElementById("user.extraAddress").value = '';
								                }
								
								                // 우편번호와 주소 정보를 해당 필드에 넣는다.
								                document.getElementById('user.postcode').value = data.zonecode;
								                document.getElementById("user.address").value = addr;
								                // 커서를 상세주소 필드로 이동한다.
								                document.getElementById("user.detailAddress").focus();
								
								                // iframe을 넣은 element를 안보이게 한다.
								                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
								                element_wrap.style.display = 'none';
								
								                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
								                document.body.scrollTop = currentScroll;
								            },
								            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
								            onresize : function(size) {
								                element_wrap.style.height = size.height+'px';
								            },
								            width : '100%',
								            height : '100%'
								        }).embed(element_wrap);
								
								        // iframe을 넣은 element를 보이게 한다.
								        element_wrap.style.display = 'block';
								    }
								</script>
								
								<br>
								
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
									<a class="btn-submit" href="<c:url value='/user/detail.do'></c:url>">취소</a> &nbsp;
									<input type="submit" value="저장" class="btn-submit">
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
