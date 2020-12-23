<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function logoutCheck() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = window.location.origin + "/user/logout.do";
		}
	}
	function formCheck(frm) {
		if ( frm.keyword.value=="") {
			alert("키워드를 입력해주세요!") ;
			frm.keyword.focus();
			document.getElementById("errorMsg").innerText="키워드를 입력해주세요!";
			return false;
		}
		return true;
	}
</script>
<style>
#search {
	color: #444444;
	font-size: 14px;
	background: #f7fbfe;
	padding: 0px;
}

#search .search-top {
	padding: 130px 0 5px 0;
	background: #fff;
}

#search .search-keyword form input[type="text"] {
	background: #fff;
	padding: 6px 8px;
	position: relative;
	border-radius: 0px;
	text-align: left;
	border: 2px solid #b6daf2;
	width: 45%;
	font-size: 16px;
}

#search .search-keyword form select {
	width: 150px;
	height: 40px;
	position: relative;
	left: -10px;
	z-index: 1;
	bottom: 1px;
	border: 0;
	background: none;
	font-size: 16px;
	padding: 0 20px 2px 20px;
	background: #3498db;
	color: #fff;
	transition: 0.3s;
	border-radius: 0px 0px 0px 0px;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}

#search .search-keyword form input[type="submit"] {
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
<script>
function check() {
    if(re.test()) {
        return true;
    }
    alert(message);
    what.value = "";
    div.in
    what.focus();
    return false;
}
</script>
<!-- ======= Header ======= -->
<header id="header" class="fixed-top d-flex align-items-center">
	<div class="container d-flex align-items-center">

		<div class="logo mr-auto">
			<h1>
				<a href="<%=request.getContextPath()%>/index.do">GOODSOM</a>
			</h1>
			<!-- Uncomment below if you prefer to use an image logo -->
			<!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
		</div>



		<nav class="nav-menu d-none d-lg-block">
			<ul>
				<li class="active"><a
					href="<%=request.getContextPath()%>/index.do">홈</a></li>
				<li><a href="<%=request.getContextPath()%>/groupBuy/list.do"
					class="nav-link">공동구매</a></li>
				<li><a href="<%=request.getContextPath()%>/auction/list.do">경매</a></li>
				<li class="drop-down"><a
					href="<%=request.getContextPath()%>/user/detail.do">${userSession.user.nickname}</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/user/detail.do">회원정보</a></li>
						<li class="drop-down"><a
							href="<%=request.getContextPath()%>/mypage/list.do">목록보기</a>
							<ul>
								<li><a
									href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="1" />
	                  			   </c:url>">등록한
										게시글 목록</a></li>
								<li><a
									href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="2" />
	                  			   </c:url>">구매한
										목록</a></li>
								<li><a
									href="<c:url value='<%=request.getContextPath() + "/mypage/list.do"%>'>
	                  					<c:param name="listType" value ="3" />
	                  			   </c:url>">좋아요한
										목록</a></li>
								<li><a href="<%=request.getContextPath()%>/noti/list.do">알림
										목록</a></li>
							</ul></li>
						<li><a href="javascript:logoutCheck()">로그아웃</a></li>
					</ul></li>
			</ul>
		</nav>
		<!-- .nav-menu -->

		<div class="header-social-links">
			<a href="#" class="twitter"><i class="icofont-twitter"></i></a> <a
				href="#" class="facebook"><i class="icofont-facebook"></i></a> <a
				href="#" class="instagram"><i class="icofont-instagram"></i></a> <a
				href="#" class="linkedin"><i class="icofont-linkedin"></i></a>
		</div>

	</div>
</header>
<!-- End Header -->

<section id="search">

	<div class="search-top">
		<div class="container">
			<div class="row" align="center">
				<div class="search-keyword" style="width: 100%;">
					<form action="<c:url value='/list/search.do' />" method="post" onsubmit="return formCheck(this);">
						<div class="search-form" align="center">
							<select data-trigger="" name="choice" id="choice">
								<option value="1">공동구매</option>
								<option value="2">경매</option>
							</select>
							<input type="text" id="keyword" name="keyword">
							<input type="submit" value="검색">
						</div>
						<div id="errorMsg" style="color: red;">
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</section>
