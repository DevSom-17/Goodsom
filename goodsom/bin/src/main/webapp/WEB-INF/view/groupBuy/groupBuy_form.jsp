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
    <!-- ======= About Section ======= -->
    <section id="about" class="about">
      <div class="container">

        <div class="row">
          <div class="col-lg-6">
            <img src="<%=request.getContextPath()%>/assets/img/about.jpg" class="img-fluid" alt="">
          </div>
          <div class="col-lg-6 pt-4 pt-lg-0">
            <h3>About Us</h3>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>
            <ul>
              <li><i class="bx bx-check-double"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
              <li><i class="bx bx-check-double"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
            </ul>
            <div class="row icon-boxes">
              <div class="col-md-6">
                <i class="bx bx-receipt"></i>
                <h4>Corporis voluptates sit</h4>
                <p>Consequuntur sunt aut quasi enim aliquam quae harum pariatur laboris nisi ut aliquip</p>
              </div>
              <div class="col-md-6 mt-4 mt-md-0">
                <i class="bx bx-cube-alt"></i>
                <h4>Ullamco laboris nisi</h4>
                <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt</p>
              </div>
            </div>
          </div>
        </div>

      </div>
    </section><!-- End About Section -->

  </main><!-- End #main -->

<%@ include file="../includeBottom.jsp" %> 