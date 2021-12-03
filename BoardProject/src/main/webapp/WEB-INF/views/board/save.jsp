<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<STYLE>
input {
display : block;
}
</STYLE>
</head>
<body>

	<h2> save.jsp </h2>
	<h2> 글쓰기 </h2>
	<form action = "/board/save" method = POST>
	작성자: <input type = "text" name = "b_writer">
	글비밀번호: <input type = "text" name = "b_password">
	제목: <input type = "text" name = "b_title">
	<!-- 내용: <input type = "text" name = "b_contents"> -->
	내용: <textarea name="b_contents" rows="5"></textarea>
	
	<input type = "submit" value = "전송">
	
	</form>
</body>
</html>