<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert jsp</title>
</head>
<body>
	<h2> insert page</h2>
	훈련생 등록화면
	
	<form action = "traineeparam" method = "post">
	이름 : <input type = "text" name = "name"><br>
	나이 : <input type = "text" name = "age"><br>
	전화번호 : <input type = "text" name = "phone"><br>
	성별 : <input type = "radio" name = "gender" value = "male"> 남자 <input type = "radio" name = "gender" value = "female"> 여자 <br>
	생년월일 : <input type = "date" name = "birth"><br>
	주소 : <input type = "text" name = "address"><br>
	
	<input type = "submit" value= "등록">
</body>
</html>