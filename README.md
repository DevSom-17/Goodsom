# Goodsom


# 1. 실행
프로젝트 우클릭 -> Run As -> Spring Boot App <br>
콘솔에 Web Server START! 문구가 뜨면 크롬에 localhost:8080 입력(8080은 설정한 포트번호)<br><br>

-서버 재시작시, <br>
1) 변경사항 저장<br>
2) 툴바에 있는 빨간네모 두번 눌러 서버 중지 <br>
(중지하지 않으면 재시작시 포트가 충돌함 -> 이 경우 중지하고 다시 시작하면 정상 작동)<br>
3) Spring Boot App 실행 & 크롬 새로고침<br><br>


# 2. Spring Security
local 실행 후 뜨는 로그인 페이지 (Spring Security 기본 페이지)<br>
유저 아이디: user<br>
비밀번호: 콘솔창에 <br>
	Using generated security password: <이부분 복붙><br>

# *** 에러 ***
1)<br>
***************************
APPLICATION FAILED TO START
***************************
Description:<br>
Web server failed to start. Port 8080 was already in use.<br><br>

-해결<br>
src/main/resources내의 application.properties 설정파일<br>
server.port=8008 // 포트번호 변경<br><br>

2)<br>
ClassNotFoundException: # Licensed to the Apache Software Foundation (ASF) under one or more 500 에러<br><br>

-해결<br>
프로젝트 우클릭 -> Properties -> Server -> Tomcat v9.0 선택 -> Apply and Close<br>
