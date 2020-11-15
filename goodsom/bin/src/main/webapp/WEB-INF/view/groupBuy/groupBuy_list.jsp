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

        <div class="row">
          <div class="col-lg-12">
            <ul id="portfolio-flters">
              <li data-filter="*" class="filter-active">All</li>
              <li data-filter=".filter-app">App</li>
              <li data-filter=".filter-card">Card</li>
              <li data-filter=".filter-web">Web</li>
            </ul>
          </div>
        </div>




        <div class="row portfolio-container">
		<c:forEach var="groupBuy" items="${groupBuyList}" varStatus="status">
          <div class="col-lg-4 col-md-6 portfolio-item filter-app wow fadeInUp">
            <div class="portfolio-wrap">
              <figure>
              	<a href="<c:url value='/groupBuy/detail.do'>
									<c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/></c:url>">
										<img src="${groupBuy.img}" class="img-fluid" alt=""> </a>
                <a href="<%=request.getContextPath()%>/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="link-preview venobox" title="Preview"><i class="bx bx-plus"></i></a>
                <a href="portfolio-details.html" class="link-details" title="More Details"><i class="bx bx-link"></i></a>
              </figure>
              

              <div class="portfolio-info">
              	<div style="float:right">조회수: ${groupBuy.count}&nbsp;&nbsp;</div>
				<div>
					<div>
						<c:if test="${groupBuy.state eq 'proceeding'}" >
							<h5>Proceeding</h5>
						</c:if>
						<c:if test="${groupBuy.state eq 'achieved'}" >
							<h5>Achieved</h5>
						</c:if>
						<c:if test="${groupBuy.state eq 'closed'}" >
							<h5>Closed</h5>
						</c:if>
					
						<span>&nbsp; ~ <fmt:formatDate value="${groupBuy.endDate}" pattern="yyyy-MM-dd" /></span>
					</div>
              
	                <h4><a  href="<c:url value='/groupBuy/detail.do'><c:param name="groupBuyId" value="${groupBuy.groupBuyId}"/>
								 </c:url>">${groupBuy.title}</a></h4>
								 
					<span class="mx-2">가격</span> 
					<a href="#"><fmt:formatNumber value="${groupBuy.price}" pattern="#,###원"/></a> <br/><br/>
				
					<span class="mx-2">달성률</span> 
					<a href="#"><fmt:formatNumber value="${groupBuy.participants}"/>명 / </a>
					<a href="#"><fmt:formatNumber value="${groupBuy.minNo}"/>명</a>
					<a href="#"><fmt:formatNumber value="${groupBuy.rate}"/>%</a>
			
                <p>${groupBuy.content}</p>
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