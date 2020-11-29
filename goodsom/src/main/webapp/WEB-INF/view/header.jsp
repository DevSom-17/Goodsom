<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	function logoutCheck() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href= window.location.origin + "/user/logout.do";
		}
	}
</script>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center">

      <div class="logo mr-auto">
        <h1><a href="<%=request.getContextPath()%>/index.do">GOODSOM</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
      </div>



      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="active"><a href="<%=request.getContextPath()%>/index.do">홈</a></li>
          <li><a href="<%=request.getContextPath()%>/groupBuy/list.do" class="nav-link">공동구매</a></li>
          <li><a href="<%=request.getContextPath()%>/auction/list.do">경매</a></li>
          <li><a href="#커뮤니티">커뮤니티</a></li>
          <li class="drop-down"><a href="<%=request.getContextPath()%>/user/detail.do">${userSession.user.nickname}</a>
          	  <ul>
	              <li><a href="<%=request.getContextPath()%>/user/detail.do">회원정보</a></li>
	              <li class="drop-down"><a href="<%=request.getContextPath()%>/mypage/list.do">목록보기</a>
	              	<ul>
	                  <li><a href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="1" />
	                  			   </c:url>">등록한 게시글 목록</a></li>
	                  <li><a href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="2" />
	                  			   </c:url>">결제 목록</a></li>
	                  <li><a href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="3" />
	                  			   </c:url>">좋아요한 목록</a></li>
	                  <li><a href="<%=request.getContextPath()%>/noti/list.do">알림 목록</a></li>
	                </ul>
	              </li>
	              <li><a href="javascript:logoutCheck()">로그아웃</a></li>
              </ul>
          </li>
        </ul>
      </nav><!-- .nav-menu -->

      <div class="header-social-links">
        <a href="#" class="twitter"><i class="icofont-twitter"></i></a>
        <a href="#" class="facebook"><i class="icofont-facebook"></i></a>
        <a href="#" class="instagram"><i class="icofont-instagram"></i></a>
        <a href="#" class="linkedin"><i class="icofont-linkedin"></i></a>
      </div>

    </div>
  </header><!-- End Header -->
  
  
  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex flex-column justify-content-center align-items-center">
    <div class="container text-center text-md-left" data-aos="fade-up">
      <h1>Welcome to <span>Goodsom</span></h1>
      <h2>We are team of talanted designers making websites with Bootstrap</h2>
      <a href="#about" class="btn-get-started scrollto">Get Started</a>
    </div>
  </section><!-- End Hero -->