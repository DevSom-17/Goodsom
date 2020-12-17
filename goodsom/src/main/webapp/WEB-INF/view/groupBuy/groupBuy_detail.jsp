<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 



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

  	  	
   	/*   	app.innerHTML += "<div id='itemDiv' class='d-flex'>"
		+ "<input type='text' id='item' name='item' value='"
	  	+ selectedOption + ", " + selectedQuantity + "' class='form-control' readonly>" 
	  	+ "<input type='hidden' id='option' name='option' value='" + selectedOption + "' />"
	  	+ "<input type='hidden' id='quantity' name='quantity' value='" + selectedQuantity + "' />"
	  	+ "&nbsp;&nbsp;<input type='button' name='del_btn' id='" + id 
	  	+ "' value='X' onClick='javascript:delItem(this.id);'></div>"; */
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
            <li><a href="index.html">Home</a></li>
            <li>공동구매 상세보기</li>
          </ol>
        </div>
		<p>작성자 : &nbsp; &nbsp; ${writer} <br/> 
         	작성일 : &nbsp; &nbsp; <fmt:formatDate value="${groupBuy.uploadDate}" pattern="yyyy-MM-dd" />
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
              <li><strong>참여자 수</strong>: ${groupBuy.participants} / ${groupBuy.minNo}</li>
              <li><strong>남은 시간</strong>: 
				<c:if test="${groupBuy.state eq 'closed'}" > 마감되었습니다. </c:if>
				<c:if test="${groupBuy.state eq 'proceeding' or groupBuy.state eq 'achieved'}" > ${dDay} </c:if>	
			  </li>
              <li><strong>가격</strong>: ${groupBuy.price}원</li>
              <li><strong>옵션</strong>
              	<form:form name="form" modelAttribute="lineGroupBuyForm" action="../order/groupBuy/create.do" method="GET">
					<select name="option" id="option">
						<option value="chooseOption" selected disabled>옵션 선택</option>
						<c:forEach var="option" items="${groupBuy.options}" varStatus="status">
							<option value="${option.name}">${option.name}</option>
						</c:forEach>
					</select> <br />
					<strong>수량</strong>
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
						
					<div id="itemBox"> </div>
					<c:if test="${groupBuy.state eq 'closed'}" >
						<input type="button" onclick="orderCreate()" value="신청하기" disabled />
					</c:if>
					<c:if test="${groupBuy.state eq 'proceeding' or groupBuy.state eq 'achieved'}" >
						<input type="button" onclick="orderCreate()" value="신청하기" />
					</c:if>
				</form:form>
              </li>
            </ul>
          </div>

        </div>

        <div class="portfolio-description">
          <h2>상세정보</h2>
          <p>
            ${groupBuy.content}
          </p>
        </div>

      </div>
    </section><!-- End Portfolio Details Section -->

		<div class="form-group" align="right">
	    <c:if test="${(isWriter eq true) and (groupBuy.participants eq 0)}">
	   		<a class="btn btn-primary py-3 px-5" href="<c:url value='/groupBuy/form.do'>
				<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
			</c:url>">수정</a>
			
	  		<a class="btn btn-primary py-3 px-5" href="<c:url value='/groupBuy/delete.do'>
	  			<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/></c:url>" 
		   	onClick="return deleteGroupBuy('${groupBuy.participants}');">삭제</a>	
	    </c:if>
	    
	   	<a class="btn btn-primary py-3 px-5" href="<c:url value='/groupBuy/list.do'></c:url>">목록</a>
	    </div>

<%@ include file="../includeBottom.jsp" %> 
