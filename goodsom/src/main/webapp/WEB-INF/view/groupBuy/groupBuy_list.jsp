<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../includeTop.jsp" %> 

<body>

 <%@ include file="../header.jsp" %> 

  <main id="main">

    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="section-title">
          <h2>공동구매</h2>
          <p>솜솜이들의 공동구매를 위한 공간</p>
        </div>

		<div class="form-group" align="left" style="margin-bottom:50px;">
          <a class="btn btn-primary py-3 px-5" href="<c:url value='/groupBuy/form.do'></c:url>">공동구매 등록</a>
        </div>

        <div class="row portfolio-container">
        <c:forEach var="groupBuy" items="${groupBuyList}" varStatus="status">
          <div class="col-lg-4 col-md-6 portfolio-item filter-card wow fadeInUp">
            <div class="portfolio-wrap">
              <figure style="background: white; text-align: center;">
					<img src="${groupBuy.imgs_g[0].url}" class="img-fluid" alt="" style="height: 100%;">
                <a href="<%=request.getContextPath()%>/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="link-preview venobox" title="Preview"><i class="bx bx-plus"></i></a>
                <a href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/></c:url>" 
                	class="link-details" title="More Details"><i class="bx bx-link"></i></a>
              </figure>
              
              <div class="portfolio-info" style="height: 105px;">
              	<div>
              		<h4><a href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
							</c:url>">${groupBuy.title}</a></h4>
					<div style="float:left; color: #898a8c;">
						<strong>금액</strong>: <fmt:formatNumber value="${groupBuy.price}" pattern="#,###원"/>
					</div>
					<div style="float:right; color: #2f94d8;">
						<strong>달성률</strong>: <fmt:formatNumber value="${groupBuy.rate}" /> % 달성
					</div>
					<br/>
					<div style="float:left; color: #898a8c;">
						<strong>마감일</strong>: <fmt:formatDate value="${groupBuy.endDate}" pattern="yyyy-MM-dd" />
					</div>
					 
               		<c:if test="${groupBuy.state eq 'proceeding'}">
						<span style ="color: #2f94d8; float:right; font-weight: bold;">진행 중</span>
					</c:if>
					<c:if test="${groupBuy.state eq 'achieved'}">
						<span style ="color: #2f94d8; float:right; font-weight: bold;">달성</span>
					</c:if>
					<c:if test="${groupBuy.state eq 'closed'}">
						<span style ="color: #ff5757; float:right; font-weight: bold;">마감</span>
					</c:if>
               </div>
              </div>
          	</div>
         </div>
        </c:forEach>
      	 </div>
      </div>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  <%@ include file="../includeBottom.jsp" %> 