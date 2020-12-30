<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<script>
	
	function home(targetUri) {
		form.action = targetUri;
		form.submit();
	}

	// submit
	function emailSubmit(){
		var emailId = document.getElementById('user.email').value;
		var reqUrl = "/email/send";
		var emailList = emailId.split('@');
		
		if(emailList.length < 2){
			alert("이메일 형식은 xxx@dongduk.ac.kr 형태여야 합니다.");
			return;
		}
		if(emailId.split('@')[1] != 'dongduk.ac.kr'){
			alert("이메일은 @dongduk.ac.kr 형태여야 합니다.");
			return;
		}
		
		$.ajax({
			type: 'post',
			url: reqUrl,
			processData: false,
			contentType: 'application/json',
			data: JSON.stringify(emailId),
			success: function(){	// object parsed from JSON text	
				var codeBtn = document.getElementById('codeVerify');
				codeBtn.disabled=false;
				Swal.fire('인증번호 발송 완료!')
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
					var emailId = document.getElementById('user.email');
					emailId.readOnly=true;
					
					var checkBtn = document.getElementById('emailVerify');
					checkBtn.value="이메일 인증 완료";
					checkBtn.disabled=true;
					var codeBtn = document.getElementById('codeVerify');
					codeBtn.disabled=true;
					
					var inputEmail = document.getElementById('user.email');
					inputEmail.readOnly=true;
					var inputCode = document.getElementById('user.code');
					inputCode.readOnly=true;
				}else{
					Swal.fire({
                        icon: 'error',
                        title: '인증오류',
                        text: '인증번호가 올바르지 않습니다!',
                        confirmButtonText: '다시 인증하기',
                        confirmButtonColor: '#2778c4'
                    })
				}
			},
			error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	};

	function emailCheck(){
		var checkBtn = document.getElementById('emailVerify');
		var checkBtn_sms = document.getElementById('checkBtn');
		if(checkBtn.value == '이메일 인증 완료' && checkBtn_sms.value == '휴대폰 인증 완료'){
			return true;
		}else{
			Swal.fire({
                icon: 'error',
                title: '인증 필요',
                text: '모든 인증을 완료해주세요!',
                confirmButtonText: '인증 완료하기',
                confirmButtonColor: '#2778c4'
            })
			return false;
		}
	}

</script>
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

<body bgcolor="black">
	<div class="container" style="padding: 100px">
		<div class="row block-9 justify-content-center mb-5">
			<div class="col-md-8 mb-md-5">

				<h2 class="text-center">회원가입</h2><br/>

				<form:form modelAttribute="userForm" method="POST" onsubmit="return emailCheck()" action="register.do" class="bg-light p-5 contact-form">

					<label for="name">이메일</label> <span style="color:red">*</span>
					<div class="form-group">
						<c:choose>
							<c:when test="${empty userForm.user.email}">
								<form:input path="user.email" class="form-control" style="width:70%;float:left" placeholder="ex) 20170000@dongduk.ac.kr" /> &nbsp;
								<input type="button" style="height: calc(1.5em + .75rem + 2px)" id="emailVerify" value="이메일 인증" onClick="emailSubmit()" /> <br>
							</c:when>
							<c:otherwise>
								<form:input path="user.email" class="form-control" style="width:70%;float:left" readonly="true" /> &nbsp;
								<input type="button" style="height: calc(1.5em + .75rem + 2px)" id="emailVerify" value="이메일 인증 완료" onClick="emailSubmit()" disabled /> <br>
							</c:otherwise>
						</c:choose>
						<form:errors cssClass="error" />
					</div>
					
					<label for="name">인증번호</label> <span style="color:red">*</span>
					<div class="form-group">
						<c:choose>
							<c:when test="${empty userForm.user.email}">
								<form:input path="user.code" class="form-control" style="width:50%;float:left" placeholder="ex) D2f24fd1" /> &nbsp;
								<input type="button" id="codeVerify" style="height: calc(1.5em + .75rem + 2px)" value="확인" onClick="codeSubmit()" disabled/>
							</c:when>
							<c:otherwise>
								<form:input path="user.code" class="form-control" style="width:50%;float:left" readonly="true" /> &nbsp;
								<input type="button" id="codeVerify" style="height: calc(1.5em + .75rem + 2px)" value="확인" onClick="codeSubmit()" disabled/>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="form-group">
						<label for="name">비밀번호</label> <span style="color:red">*</span>
						<form:input path="user.passwd" type="password" class="form-control" placeholder="비밀번호" />
						<form:errors path="user.passwd" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">비밀번호 확인</label> <span style="color:red">*</span>
						<form:input path="repeatedPassword" type="password" class="form-control" placeholder="비밀번호 확인" />
						<form:errors path="repeatedPassword" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name">이름</label> <span style="color:red">*</span>
						<form:input path="user.userName" class="form-control" placeholder="ex) 홍길동" />
						<form:errors path="user.userName" cssClass="error" />
					</div>
					
					<div class="form-group">
						<label for="name">닉네임</label> <span style="color:red">*</span>
						<form:input path="user.nickname" class="form-control" placeholder="ex) 솜솜이" />
						<form:errors path="user.nickname" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="name" style="display: block;">전화번호 <span style="color:red">*</span> </label> 
						<c:choose>
							<c:when test="${empty userForm.user.phone}">
								<form:input path="user.phone" class="form-control" placeholder="ex) 010-0000-0000" style="width: 50%; float: left; display: block;"/> &nbsp;
								<input type="button" style="height: calc(1.5em + .75rem + 2px)" id="sendPhoneNumber" value="인증번호 발송" />
							</c:when>
							<c:otherwise>
								<form:input path="user.phone" class="form-control" style="width: 50%; float: left; display: block;" readonly="true"/> &nbsp;
								<input type="button" style="height: calc(1.5em + .75rem + 2px)" id="sendPhoneNumber" value="인증번호 발송" disabled />
							</c:otherwise>
						</c:choose>		
						<form:errors path="user.phone" id="inputPhoneNumber" cssClass="error" />
					</div>
					
					<div class="form-group">
						<label for="name" style="display: block;">인증번호 <span style="color:red">*</span> </label> 
						<c:choose>
							<c:when test="${empty userForm.user.phone}">
								<input type="text" id="inputCertifiedNumber" class="form-control" disabled style="width: 50%; float: left; display: block;"/> &nbsp;
								<input type="button" id="checkBtn" style="height: calc(1.5em + .75rem + 2px)" value="휴대폰 인증하기" disabled/>
							</c:when>
							<c:otherwise>
								<input type="text" id="inputCertifiedNumber" class="form-control" readonly style="width: 50%; float: left; display: block;"/> &nbsp;
								<input type="button" id="checkBtn" style="height: calc(1.5em + .75rem + 2px)" value="휴대폰 인증 완료" disabled/>
							</c:otherwise>
						</c:choose>	
					</div>

					<div class="form-group">
						<label for="name">주소</label> <br/> 
						<form:input path="user.postcode" class="form-control" placeholder="우편번호" readonly="true" style="width:30%;float:left;margin-bottom:5px"/> &nbsp;
						<input type="button" style="height: calc(1.5em + .75rem + 2px)" onclick="execDaumPostcode()" value="우편번호 찾기">
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
							<form:errors path="user.refundBank" cssClass="error" />
							<form:errors path="user.refundAccount" cssClass="error" />
					</div>
					<br/>

					<div class="form-group" align="center">
						<a class="btn-submit" href="<c:url value='login.do'></c:url>">취소</a> &nbsp;
						<input type="submit" value="완료" class="btn-submit"> 
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
	
<script>
var inputCertifiedNum = document.getElementById('inputCertifiedNumber');
var checkBtn = document.getElementById('checkBtn');
var sendPhoneNumberBtn = document.getElementById('sendPhoneNumber');
var inputPhoneNum = document.getElementById('user.phone');

$(sendPhoneNumberBtn).click(function(){
	let phoneNumber = $(inputPhoneNum).val();
	inputCertifiedNum.value='';
	
	var regPhone = /^01(0|1|[6-9]{1})-([0-9]{3,4})-([0-9]{4})$/; //숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
	if (regPhone.test(phoneNumber)==false) {
		Swal.fire({
            icon: 'error',
            title: '주의',
            text: '전화번호를 제대로 입력해주세요!',
            confirmButtonText: '다시 입력하기',
            confirmButtonColor: '#2778c4'
        })
        $(inputPhoneNum).val('');
        return false;
	}

    $.ajax({
        type: "GET",
        url: "/sendSMS.do",
        data: {
            "phoneNumber" : phoneNumber
        },
        success: function(res){
        	inputCertifiedNum.disabled=false;
        	checkBtn.disabled=false;
        	Swal.fire('인증번호 발송 완료!')
            
            $('#checkBtn').click(function(){
                if($.trim(res) ==$('#inputCertifiedNumber').val()){
                    Swal.fire(
                        '인증성공!',
                        '휴대폰 인증이 정상적으로 완료되었습니다.',
                        'success'
                    )
                    checkBtn.value='휴대폰 인증 완료';
                    inputCertifiedNum.readOnly=true;
                	checkBtn.disabled=true;
                	sendPhoneNumberBtn.disabled=true;
                	inputPhoneNum.readOnly=true;
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '인증오류',
                        text: '인증번호가 올바르지 않습니다!',
                        confirmButtonText: '다시 인증하기',
                        confirmButtonColor: '#2778c4'
                    })
                }
            })


        }
    })
});

</script>
</body>
</html>