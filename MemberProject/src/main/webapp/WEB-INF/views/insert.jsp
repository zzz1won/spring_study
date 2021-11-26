<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input{
	display : block;
	}
</style>
</head>
<body>
<h3> 회원가입 </h3>

	<form action = "m_param" method = "POST">
	
	아이디: <input type = "text" name = "m_id">
	비밀번호: <input type = "text" name = "m_password">
	이름: <input type = "text" name = "m_name"> 
	메일: <input type = "text" name = "m_emaiil">
	연락처: <input type = "text" name = "m_phone">
	
	<input type = "submit" value = "가입" >	
	</form>

</body>
</html>