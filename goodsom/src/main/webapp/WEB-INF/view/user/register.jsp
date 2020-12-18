<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>

</script>
<script>
	
	function home(targetUri) {
		form.action = targetUri;
		form.submit();
	}

	// submit
	function emailSubmit(){
		var emailId = document.getElementById('user.email').value;
		var reqUrl = "/email/send";
		
		$.ajax({
			type: 'post',
			url: reqUrl,
			processData: false,
			contentType: 'application/json',
			data: JSON.stringify(emailId),
			success: function(){	// object parsed from JSON text	
				var codeBtn = document.getElementById('codeVerify');
				codeBtn.disabled=false;
			},
			error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	};

	function codeSubmit(){
		var reqUrl = "/email/verifyCode";

		var code = document.getElementById('user.code').value;

		$.ajax({
			type: 'post',
			url: reqUrl,
			processData: false,
			contentType: 'application/json',
			data: JSON.stringify(code),
			success: function(codeMatch){	// object parsed from JSON text	
				if(codeMatch){
					var checkBtn = document.getElementById('emailVerify');
					checkBtn.value="이메일 인증 완료";
					checkBtn.disabled=true;

					var codeBtn = document.getElementById('codeVerify');
					codeBtn.disabled=true;
					
				}else{
					alert("잘못된 인증번호입니다!");
				}
			},
			error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	};

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
						<label for="name">이메일</label> 
						<form:input path="user.email" class="form-control" placeholder="ex) 20170000@dongduk.ac.kr" />
						<form:errors path="user.email" cssClass="error" />
						<form:errors cssClass="error" />
					</div>
					
					<div class="form-group">
						<input type="button" id="emailVerify" value="이메일 인증" onClick="emailSubmit()" />
					</div>
					
					<div class="form-group">
						<label for="name">인증번호</label> 
						<form:input path="user.code" class="form-control" placeholder="ex) 20170000@dongduk.ac.kr" />
						<form:errors path="user.code" cssClass="error" />
						<form:errors cssClass="error" />
					</div>
					
					<div class="form-group">
						<input type="button" id="codeVerify" value="확인" onClick="codeSubmit()" disabled/>
					</div>

					<div class="form-group">
						<label for="name">비밀번호</label> 
						<form:input path="user.passwd" type="password" class="form-control" placeholder="비밀번호" />
						<form:errors path="user.passwd" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">비밀번호 확인</label> 
						<form:input path="repeatedPassword" type="password" class="form-control" placeholder="비밀번호 확인" />
						<form:errors path="repeatedPassword" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">이름</label> 
						<form:input path="user.userName" class="form-control" placeholder="ex) 홍길동" />
						<form:errors path="user.userName" cssClass="error" />
					</div>
					
					<div class="form-group">
						<label for="name">닉네임</label>
						<form:input path="user.nickname" class="form-control" placeholder="ex) 솜솜이" />
						<form:errors path="user.nickname" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">전화번호</label> 
						<form:input path="user.phone" class="form-control" placeholder="ex) 010-0000-0000" />
						<form:errors path="user.phone" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">주소</label> <br/> 
						<form:input path="user.postcode" class="form-control" placeholder="우편번호" readonly="true" style="width:30%;float:left;margin-bottom:5px"/> &nbsp;
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
						<form:input path="user.address" class="form-control" placeholder="주소" style="margin-bottom:5px" readonly="true" />
						<form:input path="user.detailAddress" class="form-control" placeholder="상세주소" style="width:50%;float:left"/> &nbsp; 
						<form:input path="user.extraAddress" class="form-control" placeholder="참고항목" readonly="true" style="width:49%;float:right"/>
					</div>
					<br>
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

					<div class="form-group">
						<label for="name">계좌번호</label>
						<div class="d-flex">
							<form:select path="user.refundBank" >
								<option value="" selected>은행</option>
								<form:options items="${cardBanks}" />
							</form:select>
							<form:input path="user.refundAccount" class="form-control" placeholder="ex) 123-1234-1234" /> 
						</div>
					</div>
					<br/>

					<div class="form-group" align="center">
						<a class="btn btn-primary py-3 px-5" href="<c:url value='login.do'></c:url>">취소</a> &nbsp;
						<input type="submit" value="완료" class="btn btn-primary py-3 px-5"> 
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>