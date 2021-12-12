<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.basic	{
	position : center;
	}
</style>
</head>
<body>

<h2> index.jsp </h2>

	${loginDTO.m_name}<br>
	<br>
	<br>
	<br>
	<div id=basic>
		<a href="member/save" class="btn btn-info btn-sm abled" tabindex="-1" role="button" aria-disabled="false"> 회원가입 </a> 
		<a href="member/login" class="btn btn-info btn-sm abled" tabindex="-1" role="button" aria-disabled="false"> 로그인 </a>
		<a href="board/save" class="btn btn-info btn-sm abled" tabindex="-1" role="button" aria-disabled="false"> 글쓰기 </a><br><br>
	</div>
<!-- 	<a href = "/member/save"> 회원가입 💨💨 </a><br>
	<a href = "/member/login"> 로그인 💨💨</a><br>
	<a href = "/board/paging"> 글목록 💨💨</a><br>
	<a href = "/board/save"> 글쓰기 💨💨</a><br> -->
		
		<!-- 12.11 관리자페이지 추가 -->
		<c:if test="${sessionScope.loginDTO.m_id eq 'admin'}">
		<a href="member/memberAll" class="btn btn-primary btn-sm abled" tabindex="-1" role="button" aria-disabled="false"> 회원목록 </a>	 	
		
		</c:if>
	
</body>
</html>