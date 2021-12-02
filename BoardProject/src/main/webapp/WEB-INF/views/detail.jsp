<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> datail 글조회 detail </h2>

	<table>
	<tr>
	<td> 번호: ${board.b_number}</td>
	<td> 제목: ${board.b_title}</td>
	<td> 작성자: ${board.b_writer}</td>
	<td> 작성일: ${board.b_date}</td>
	<td> 조회수: ${board.b_hits}</td>
	</tr>
	</table>
	
	<a href = "findAll"> 글조회로 돌아가기 </a>
</body>
</html>