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
.btn-danger {
	position: relative;
	top: 0;
	right: 5px;
	bottom: 0;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 8px 30px;
	background: #bb404c;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
</style>

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
var optionStatus = 1;
// delete
function deleteGroupBuy(participants) {
	var deleteCheck = confirm("등록하신 공동구매를 삭제하시겠습니까?")
	
	if(deleteCheck == true && participants > 0){
		alert(participants + "명의 참여자가 존재하여 삭제할 수 없습니다.");
		return false;
	}else{
		return deleteCheck;
	}
}

// 수량 증감
function change(num) {
	var x = document.form;
	var y = Number(x.quantity.value) + num;
	if (y < 1)
		y = 1;
	x.quantity.value = y;
}

function orderCreate() {
	var itemList = document.getElementsByName("item");
	if (itemList.length == 0) {
		alert('(옵션, 수량)쌍을 추가해주세요.');
		return;
	}
	form.submit();
}

// (option, quantity) 선택
function addItem(selectedQuantity) {
	var list = document.getElementsByName("item");
	var target = document.getElementById("option");
	var selectedOption = target.options[target.selectedIndex].text;
	
	var id = list.length;
	
	if (selectedOption == '옵션 선택') {
		alert('옵션을 선택해주세요.');
		return;
	} 
	
 	for(var i = 0; i < list.length; i++){
		list[i].setAttribute("value", list[i].value);
 	}
 	
   	app = document.getElementById("itemBox")

  	 /* list로 받아올 경우 */
  	app.innerHTML += "<div id='itemDiv' class='d-flex'>"
  		+ "<input type='text' id='item' name='item' value='"
  	  	+ selectedOption + ", " + selectedQuantity + "' class='form-control' readonly>" 
  	  	+ "<input type='hidden' id='options[" + id + "]' name='options' value='" + selectedOption + "' />"
  	  	+ "<input type='hidden' id='quantities[" + id + "]' name='quantities' value='" + selectedQuantity + "' />"
  	  	+ "&nbsp;&nbsp;<input type='button' name='del_btn' id='" + id 
  	  	+ "' value='X' onClick='delItem(this.id);'></div>";
}

// (option, quantity) 삭제(선택취소)
function delItem(id) {
	var itemList = document.getElementsByName("item");
	var btnList = document.getElementsByName("del_btn");

	var optionList = document.getElementsByName("options");
	var quantityList = document.getElementsByName("quantities");
	
	itemList[id].outerHTML="";
	btnList[id].outerHTML="";
	optionList[id].outerHTML="";
	quantityList[id].outerHTML="";
	
	btnList = document.getElementsByName("del_btn");
 	for(var i = 0; i < btnList.length; i++){
 		btnList[i].id = i;
 	}  
}
</script>

<body>

 <%@ include file="../header.jsp" %> 
  
 <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>${groupBuy.title}</h2>
          <ol>
            <li><a href="/index.do">Home</a></li>
            <li>공동구매 상세보기</li>
          </ol>
        </div>
        
		<p style="float:left;">작성자 : &nbsp; &nbsp; ${writer} &nbsp;
		<a href="<c:url value='../report/create.do'>
					<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
					<c:param name="writerId" value="${groupBuy.userId}"/>
				</c:url>">신고하기</a> 
		<br/> 
         	작성일 : &nbsp; &nbsp; <fmt:formatDate value="${groupBuy.uploadDate}" pattern="yyyy-MM-dd" />
      		<p style="text-align: end;">조회수 : &nbsp; &nbsp; ${groupBuy.count} &nbsp; </p>
      </div>
    </section><!-- End Breadcrumbs -->

<!-- ======= Portfolio Details Section ======= -->
    <section id="portfolio-details" class="portfolio-details">
      <div class="container">

        <div class="portfolio-details-container">

          <div class="owl-carousel portfolio-details-carousel">
          	<c:forEach items="${groupBuy.imgs_g}" var="img">
				<img src="${img.url}" class="img-fluid" alt="">
			</c:forEach>
          </div>

          <div class="portfolio-info">
            <h3>${groupBuy.title}</h3>
            <ul>
           	  <li><strong>조회수</strong>: ${groupBuy.count}</li>
              <li><strong>참여자 수</strong>: ${groupBuy.participants} / ${groupBuy.minNo}</li>
              <li><strong>남은 시간</strong>: 
				<c:if test="${groupBuy.state eq 'closed'}" > 마감되었습니다. </c:if>
				<c:if test="${groupBuy.state eq 'proceeding' or groupBuy.state eq 'achieved'}" > ${dDay} </c:if>	
			  </li>
              <li><strong>가격</strong>: ${groupBuy.price}원</li><br>
              	<form:form name="form" modelAttribute="lineGroupBuyForm" action="../order/groupBuy/create.do" method="GET">
              	<li><strong>옵션</strong> &nbsp;
					<select name="option" id="option">
						<option value="chooseOption" selected disabled>옵션 선택</option>
						<c:forEach var="option" items="${groupBuy.options}" varStatus="status">
							<option value="${option.name}">${option.name}</option>
						</c:forEach>
					</select></li>
				<li><strong>수량</strong> &nbsp;
					<input type="button" name="minus" value="-"
						onclick="change(-1)" /> &nbsp; 
					<input type="text" name="quantity" id="count" value="1"
						style="text-align: center; width: 50px;" readonly /> &nbsp; 
					<input type="button" name="plus" value="+"
						onclick="change(1)" /> &nbsp; &nbsp; 
						
					<c:if test="${groupBuy.state eq 'closed'}" >
						<input type="button" value="추가하기" onclick="addItem(quantity.value)" disabled />
					</c:if>
					<c:if test="${groupBuy.state eq 'proceeding' or groupBuy.state eq 'achieved'}" >
						<input type="button" value="추가하기" onclick="addItem(quantity.value)" />
					</c:if>
					&nbsp;
					<c:if test="${groupBuy.state eq 'closed'}" >
						<input type="button" style="float:right;" onclick="orderCreate()" value="신청하기" disabled />
					</c:if>
					<c:if test="${groupBuy.state eq 'proceeding' or groupBuy.state eq 'achieved'}" >
						<input type="button" style="float:right;" onclick="orderCreate()" value="신청하기" />
					</c:if>
					</li>
						<br>
					<div id="itemBox"> </div>
				</form:form>
            </ul>
          </div>

        </div>

        <div class="portfolio-description">
          <h2>상세정보</h2>
          <p style="white-space:pre;"><c:out value="${groupBuy.content}" escapeXml="false"></c:out></p>
        </div>

      </div>
    </section><!-- End Portfolio Details Section -->
		<div class="form-group" align="center">
			<dl>
				<c:choose>
					<c:when test="${like eq true}">
						<img id="btn_like" src="/assets/img/liked(bright_red).png" class="img-fluid" style="cursor:pointer;">
					</c:when>
					<c:otherwise>
						<img id="btn_like" src="/assets/img/unliked.png" class="img-fluid" style="cursor:pointer;">
					</c:otherwise>
				</c:choose>	
				<dd id="likeCount" style="margin-left:1px;">${groupBuy.likeCount}</dd>
			</dl>
		</div>
		
		<div class="form-group" align="center">
		<c:if test="${isWriter eq true}">
			<a class="btn-submit" href="<c:url value='../order/groupBuy/manage.do'>
														<c:param name="groupBuyId" value="${groupBuy.groupBuyId}" />
												 	  </c:url>">참여자 현황</a>
			
			<c:if test="${groupBuy.participants eq 0}">			 	  
		   		<a class="btn-submit" href="<c:url value='/groupBuy/form.do'>
					<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
				</c:url>">수정</a>
			</c:if>
	  		<a class="btn-danger" href="<c:url value='/groupBuy/delete.do'>
	  			<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/></c:url>" 
		   	onClick="return deleteGroupBuy('${groupBuy.participants}');">삭제</a>	
	    </c:if>
	    
	   	<a class="btn-submit" href="<c:url value='/groupBuy/list.do'></c:url>">목록</a>
	    </div>

<script>
/* JSP SCRIPT */
var groupBuyId = ${groupBuy.groupBuyId};
var userId = ${loginUserId};
 
var btn_like = document.getElementById("btn_like");
btn_like.onclick = function(){ changeHeart(); }
 
/* 좋아요 버튼 눌렀을떄 */
function changeHeart(){ 
     $.ajax({
            type : "POST",  
            url : "/clickLikeGroupBuy.do",       
            dataType : "json",   
            data : "groupBuyId="+groupBuyId+"&userId="+userId,
			error : function(){
                alert("통신 에러","error","확인",function(){});
            },
            success : function(jdata) {
                if(jdata.resultCode == -1){
                    alert("좋아요 오류","error","확인",function(){});
                }
                else{
                    if(jdata.likeCheck == 1){
                        $("#btn_like").attr("src","/assets/img/liked(bright_red).png");
                        $("#likeCount").empty();
                        $("#likeCount").append(jdata.likeCount);
                    }
                    else if (jdata.likeCheck == 0){
                        $("#btn_like").attr("src","/assets/img/unliked.png");
                        $("#likeCount").empty();
                        $("#likeCount").append(jdata.likeCount);
                    }
                }
            }
        });
 }
</script>

<%@ include file="../includeBottom.jsp" %> 
