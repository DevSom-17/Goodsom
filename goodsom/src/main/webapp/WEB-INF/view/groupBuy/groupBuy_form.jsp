<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<script>
// submit
function groupBuySubmit(isNewGroupBuy) {
	if(isNewGroupBuy){
		document.groupBuyForm.action="<c:url value='/groupBuy/create.do'></c:url>";
	}else{
		document.groupBuyForm.action="<c:url value='/groupBuy/update.do'></c:url>";
	}
	document.groupBuyForm.submit();
}

// radio
function hasClass(target, className) {
    if( (' ' + target.className + ' ').replace(/[\n\t]/g, ' ').indexOf(' ' + className + ' ') > -1 ) 
        return true;
    return false;
}
function removeClass(target, className){
    var elClass = ' ' + target.className + ' ';
    while(elClass.indexOf(' ' + className + ' ') !== -1){
         elClass = elClass.replace(' ' + className + ' ', '');
    }
    target.className = elClass;
}
function addClass(target, className){
    target.className += ' ' + className;   
}

// option
function input_append(ff){
 	var list = document.getElementsByName("groupBuy.optionList");

 	for(var i = 0; i < list.length; i++){
		list[i].setAttribute("value", list[i].value);
 	}
   	app = document.getElementById("optionBox")
  	app.innerHTML += "<input type='text' id='groupBuy.options' name='groupBuy.optionList' class='form-control'><br>";
}

</script>
<body>

<%@ include file="../header.jsp" %> 

  <main id="main">
    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact section-bg">
      <div class="container">

        <div class="section-title">
          <h2>공동구매 등록</h2>
          <p>새로운 공동구매를 등록합니다.</p>
        </div>
        
        <div class="row mt-5 justify-content-center">
          <div class="col-lg-10">
        <form:form modelAttribute="groupBuyForm" method="post" name="groupBuyForm" enctype="multipart/form-data" role="form" class="php-email-form">
			<div class="form-row">
                <div class="col-md-6 form-group">
					<label for="title">제목</label> 
					<form:errors path="groupBuy.title" cssClass="error"/> 
					
					<c:choose>
						<c:when test="${groupBuyForm.newGroupBuy}">
							<form:input type="text" id="title" path="groupBuy.title" class="form-control" placeholder="Title" />
						</c:when>
						<c:otherwise>
							<form:input type="text" id="title" path="groupBuy.title" class="form-control" value="${groupBuyForm.groupBuy.title}" />
						</c:otherwise>
					</c:choose>
					</div>
			
			 <div class="col-md-6 form-group">
					<label for="price">가격</label>
					<form:errors path="groupBuy.price" cssClass="error"/> 
					
					<c:choose>
						<c:when test="${groupBuyForm.newGroupBuy}">
							<form:input type="number" id="price" path="groupBuy.price" class="form-control" placeholder="price" />
						</c:when>
						<c:otherwise>
							<form:input type="number" id="price" path="groupBuy.price" class="form-control" value="${groupBuyForm.groupBuy.price}" />
						</c:otherwise>
					</c:choose>
				
				</div>
				</div>
			
			
			<div class="form-group">
				<label for="report">대표 이미지</label>&nbsp;&nbsp;&nbsp;<form:errors path="groupBuy.report" cssClass="error"/><br/>
				<form:input type="file" path="groupBuy.report"/>
          	</div>
          				
			<div class="form-group">
					<label for="content">상세설명</label> 
					<form:errors path="groupBuy.content" cssClass="error"/>
					
					<c:choose>
						<c:when test="${groupBuyForm.newGroupBuy}">
							<form:textarea id="content" path="groupBuy.content" class="form-control"
								placeholder="상세 설명을 작성해주세요." cols="30" rows="10"></form:textarea>
						</c:when>
						<c:otherwise>
							<form:textarea id="content" path="groupBuy.content" class="form-control"
								placeholder="상세 설명을 작성해주세요." cols="30" rows="10" items="${groupBuyForm.groupBuy.content}" ></form:textarea>
						</c:otherwise>
					</c:choose>
			</div>
			
			<div class="form-group">
				<label for="option">옵션</label>  <input type="button" id="addOption" value="추가" onclick="input_append(this.form)"/> 
				<form:errors path="groupBuy.optionList" cssClass="error"/>
				
				<div id="optionBox">
					<c:choose>
						<c:when test="${groupBuyForm.newGroupBuy}">
							<form:input type="text" id="groupBuy.options" path="groupBuy.optionList" class="form-control"/><br>
						</c:when>
						<c:otherwise>
							<c:forEach var="option" items="${groupBuyForm.groupBuy.options}" varStatus="status">
								<form:input type="text" id="groupBuy.options" path="groupBuy.optionList" 
										class="form-control" value="${option.name}"/><br>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="form-group">
				<label for="catId">태그</label> <br/>
				<form:errors path="groupBuy.catId" cssClass="error"/>

			    <div class="radio-items">
			        <div class="col-2">  <!-- width auto important, 소수점 백그라운드 이슈로 인해 auto 설정 -->
			        	<form:radiobutton id="clothing" path="groupBuy.catId" class="only-sr" value="1" checked="${groupBuyForm.groupBuy.catId==1 ? 'checked':''}"  />
			            <label for="clothing">의류</label>
			        </div>
			        <div class="col-2">
			        	<form:radiobutton id="schoolUniform" path="groupBuy.catId" class="only-sr" value="2" checked="${groupBuyForm.groupBuy.catId==2 ? 'checked':''}" />
						<label for="schoolUniform">학잠</label>
			        </div>
			        <div class="col-2">
			            <form:radiobutton id="writing" path="groupBuy.catId" class="only-sr" value="3" checked="${groupBuyForm.groupBuy.catId==3 ? 'checked':''}" />
			            <label for="writing">필기구</label>
			        </div>
			        <div class="col-2">
			            <form:radiobutton id="tumbler" path="groupBuy.catId" class="only-sr" value="4" checked="${groupBuyForm.groupBuy.catId==4 ? 'checked':''}" />
						<label for="tumbler">텀블러</label>
			        </div>
			        <div class="col-2">
			            <form:radiobutton id="sticker" path="groupBuy.catId" class="only-sr" value="5" checked="${groupBuyForm.groupBuy.catId==5 ? 'checked':''}" />
						<label for="sticker">스티커</label>
			        </div>
			        <div class="col-2">
			            <form:radiobutton id="bagAndPouch" path="groupBuy.catId" class="only-sr" value="6" checked="${groupBuyForm.groupBuy.catId==6 ? 'checked':''}" />
						<label for="bagAndPouch">에코백/파우치</label>
			        </div>
			        <div class="col-2">
			            <form:radiobutton id="etc" path="groupBuy.catId" class="only-sr" value="7" checked="${groupBuyForm.groupBuy.catId==7 ? 'checked':''}" />
						<label for="etc">기타</label>
			        </div>
			    </div>
				   
			</div>
			
			<div class="form-group row">
				<div class="col-md-12">
					<label for="minNo">최소수량</label> 
					<form:errors path="groupBuy.minNo" cssClass="error"/>
					<div class="d-flex">
						<div class="form-group mr-2">
						<c:choose>
							<c:when test="${groupBuyForm.newGroupBuy}">
								<form:input type="text" id="minNo" path="groupBuy.minNo" class="form-control" placeholder="ex) 40" />
							</c:when>
							<c:otherwise>
								<form:input type="text" id="minNo" path="groupBuy.minNo" class="form-control" value="${groupBuyForm.groupBuy.minNo}" />
							</c:otherwise>
						</c:choose>
						
						</div>
					</div>
				</div>
			</div>
             	
             	<div class="form-group">
             	<label for="endDate">마감일</label>
             	<form:errors path="groupBuy.endDate" cssClass="error"/>
             	
                <div class="d-flex">
	    		  <div class="form-group mr-2">
              		<c:choose>
						<c:when test="${groupBuyForm.newGroupBuy}">
							<form:input type="date" id="endDate" path="groupBuy.endDate" class="form-control" placeholder="ex) yyyy-MM-dd" />
						</c:when>
						<c:otherwise>
							<fmt:formatDate value='${groupBuyForm.groupBuy.endDate}' pattern='yyyy-MM-dd' var="dateFormat"/>
							<form:input type="date" id="endDate" path="groupBuy.endDate" class="form-control" value="${dateFormat}"/>
						</c:otherwise>
					</c:choose>
	              </div>
             		</div>
             	</div>
             
			<div class="form-group">
             		<form:radiobuttons items="${amPm}" id="amPm" path="groupBuy.isAmPm"/> &nbsp;&nbsp;&nbsp;
	            <form:errors path="groupBuy.isAmPm" cssClass="error"/>  &nbsp;&nbsp;&nbsp;
	        </div>
	        <div class="drop-down"> 
				<form:select path="groupBuy.hour">
					<form:options path="groupBuy.hour" items="${hourData}" itemLabel="label" itemValue="code"/>
				</form:select>
				&nbsp;&nbsp;&nbsp;
				<form:select path="groupBuy.minute">
					<form:options path="groupBuy.minute" items="${minuteData}" itemLabel="label" itemValue="code"/>
				</form:select>
             </div>

			<div class="text-center">
				<input type="button" value="취소" onclick="location.href='/groupBuy/list.do'" /> &nbsp;
				<input type="button" value="등록" onClick="groupBuySubmit(${groupBuyForm.newGroupBuy})" />
			</div>
		</form:form>
		</div>
		</div>

      </div>
    </section><!-- End Contact Section -->
  </main><!-- End #main -->

<%@ include file="../includeBottom.jsp" %> 