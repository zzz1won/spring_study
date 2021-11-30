<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- FINDALL에서 가져옴 
    관리자만 findAll이 보이고 일반유저는 안보이도록-->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>

function logout()	{
	location.href = "logout"; 
}
</script>

<body>
	<h2>main.jsp</h2>
	세션에 저장한 데이터 출력: ${sessionScope.loginId}
	<!-- 로그아웃 버튼을 클릭하면 logout 이라는 주소 요청 -->
	<button onclick ="logout()"> 로그아웃 </button> 
	
	<!-- 아래링크 클릭시 전체회원목록 조회하여 findAll.jsp에 출력 -->
	<a href ="findAll">회원목록</a>
	
	<!-- 아이디가 admin인 관리자가 로그인했을때만 목록 링크가 보이도록 설정-->
	<c:if test="${sessionScope.loginId eq 'admin'}">
	<a href = "findAll"> 회원목록(관리자만 보여요)</a>
	</c:if>			<!-- JSTL c태그, eq는 이콜 =  -->
	
</body>
</html>