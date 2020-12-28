<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp"%>

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
          <h2>구매 진행</h2>
        </div>

		<div class="site-section">
			<div class="container">
				<c:choose>
					<c:when test="${not empty orderForm.order.lineGroupBuys}">
						<div style="width:100%">
						<h3>주문 내역 확인</h3><br>
						<div class="portfolio-wrap" style="padding : 35px">
								<div style="width:100%">
									<div class="portfolio-info">
										<h4>
											<a href="<c:url value='../../groupBuy/detail.do'>
															<c:param name="groupBuyId" value="${orderForm.order.groupBuyId}" />
													 </c:url>"> ${orderForm.order.groupBuy.title}</a>
										</h4>
										<c:forEach var="lineGroupBuy" items="${orderForm.order.lineGroupBuys}" varStatus="status"><br />
										<span class="meta d-inline-block mb-3">
											<span class="mx-2"> 옵션 : ${lineGroupBuy.selectOption}</span> &nbsp;&nbsp; 
											<span class="mx-2"> 수량 : ${lineGroupBuy.quantity}개</span> &nbsp;&nbsp;
											<span class="mx-2"> 금액 : ${lineGroupBuy.unitPrice}원</span>
										</span>
										</c:forEach>
										
										<div class="d-flex" style="float:right;padding:10px">
											<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
										</div>	
									</div>
								</div>
							</div>
							</div>
								<br />
					</c:when>
					<c:otherwise> <!-- auction이 담긴 orderForm이 넘어옴 -->
							<div style="width:100%">
							<h3>주문 내역 확인</h3><br>
							<div class="portfolio-wrap" style="padding : 35px">
							<div style="width:100%">
									<div class="portfolio-info">
										<h4>
											<a href="<c:url value='../auction/detail.do'>
															<c:param name="auctionId" value="${orderForm.order.auction.auctionId}" />
													 </c:url>"> ${orderForm.order.auction.title}</a>
										</h4>
		
										<span class="meta d-inline-block mb-3">
											<span class="mx-2"> 금액 : ${orderForm.order.totalPrice}원</span> <br>
										</span>
									</div>
									<div class="d-flex">
										<h4>TotalPrice : ${orderForm.order.totalPrice}원</h4> &nbsp;
									</div>
								</div><br />
							</div>
							</div>
					</c:otherwise>
				</c:choose>
					<br>
					<div style="width:100%">
						<h3>입금 정보 입력</h3><br>
						<div style="width:100%">
						<form:form modelAttribute="orderForm" class="bg-light p-5 contact-form">
							<form:input type="hidden" path="order.orderId" value="${order.userId}"/>
							<div class="form-group">
								<label for="name">입금자명</label> 
								<div class="d-flex">
									<form:input path="order.name" type="text" class="form-control" placeholder="ex) 홍길동" /> 
								</div>
								<form:errors path="order.name" cssClass="error" />
							</div><br>
							
							<div class="form-group">
								<label for="phone">전화번호</label> 
								<form:input path="order.phone" type="text"
									class="form-control" placeholder="ex) 010-1234-5678" />
								<form:errors path="order.phone" cssClass="error" />	
							</div><br>
							
							<div class="form-group">
								<label for="account">계좌번호</label> 
								<div class="d-flex">
								<form:select path="order.bank" items="${cardBanks}" />
								<form:input path="order.account" type="text"
									class="form-control" placeholder="ex) 503-123456-12345" /> 
								</div>
								<form:errors path="order.account" cssClass="error" />
							</div><br>
							
								<div class="form-group">
									<label for="name">주소</label> <br/> 
									<form:input path="order.postcode" class="form-control" placeholder="우편번호" readonly="true" style="width:30%;float:left;margin-bottom:5px"/> &nbsp;
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
									<form:input path="order.address" class="form-control" placeholder="주소" style="margin-bottom:5px" readonly="true" />
									<form:input path="order.detailAddress" class="form-control" placeholder="상세주소" style="width:50%;float:left"/> &nbsp; 
									<form:input path="order.extraAddress" class="form-control" placeholder="참고항목" readonly="true" style="width:49%;float:right"/>
									<form:errors path="order.address" cssClass="error" />
								</div><br>
											
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
								                    document.getElementById("order.extraAddress").value = extraAddr;
								                
								                } else {
								                    document.getElementById("order.extraAddress").value = '';
								                }
								
								                // 우편번호와 주소 정보를 해당 필드에 넣는다.
								                document.getElementById('order.postcode').value = data.zonecode;
								                document.getElementById("order.address").value = addr;
								                // 커서를 상세주소 필드로 이동한다.
								                document.getElementById("order.detailAddress").focus();
								
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
								<label for="depositTime">입금시간</label> 
								<div class="d-flex">
									<form:input path="order.depositTime" type="text"
										class="form-control" placeholder="ex) yyyy-MM-DD HH:mm:ss" />
								</div>
								<form:errors path="order.depositTime" cssClass="error" />	 
							</div>
							
							<br />

							<div class="form-group" align="center">
								<a class="btn-submit" href="javascript:history.back()">취소</a> &nbsp; 
								<input type="submit" value="완료" class="btn-submit"> 
							</div>
						</form:form>
					</div>
				</div>
			</div>
			</div>
		</div> 
	</section> <!-- END Portfolio Section -->
	</main>
	
	<%@ include file="../includeBottom.jsp" %>