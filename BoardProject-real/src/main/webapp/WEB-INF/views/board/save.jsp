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
	내용: <textarea name="b_contents" rows="5"></textarea><br>
	
	<input type = "submit" value = "전송">
	
	</form>
	
	<!-- 1206-01 -->
	<h2> 파일첨부하여 글쓰기 </h2>
	<form action = "/board/saveFile" method = "POST" enctype="multipart/form-data">
	작성자: <input type = "text" name = "b_writer" >
	글비밀번호: <input type = "text" name = "b_password">
	제목: <input type = "text" name = "b_title">
	<!-- 내용: <input type = "text" name = "b_contents"> -->
	내용: <textarea name="b_contents" rows="5"></textarea><br>
	● 파일: <input type="file" name="b_file">	
	<input type = "submit" value = "전송">
	
	</form>
	
</body>
</html>