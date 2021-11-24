<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input {
	disply = block;
	}
</style>
</head>
<body>
	<h3> 회원가입 *^^* </h3>
	
	<form action = "inputparam2" method = "post">
	
	아이디: <input type = "text" name="id"><br>
	비밀번호: <input type = "text" name="pw"><br>
	이름: <input type = "text" name="uname"><br>
	이메일: <input type = "text" name="email"><br>
	성별: <input type="radio" id = "m" name = "gender" value="male">
        <label for = "m"> 남자 </label>
        <input type="radio" id = "f" name = "gender" value="female">
        <label for = "f"> 여자 </label><br>
        
        <input type = "submit" value= "가입">
</body>
</html>