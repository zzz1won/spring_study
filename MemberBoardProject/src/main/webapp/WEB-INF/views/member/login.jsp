<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input	{
display : block;
}
</style>
</head>
<body>

<h3> login </h3>

<form action="/member/loginForm" method="post">
아이디 : <input type = "text" name = "m_id" placeholder ="아이디입력">
비밀번호 : <input type = "text" name = "m_pw" placeholder ="비밀번호입력">
<input type ="submit" value="로그인">
</form>

</body>
</html>