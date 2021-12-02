<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>

<h3> 글목록 </h3>
<table>

   <thead>
   <tr>
   <td> 글번호 </td>
   <td> 글제목 </td>
   <td> 글쓴이 </td>
   <td> 날짜 </td>
   <td> 조회수 </td>
   <td> 글조회 </td>
   <td> 글수정 </td>
   <td> 글삭제 </td>

<c:forEach items = "${boardList}" var="b">
	<tr>
	<td> ${b.b_number}</td>
	<td> ${b.b_title}</td>
	<td> ${b.b_writer}</td>
	<td> ${b.b_date}</td>
	<td> ${b.b_hits}</td>
	<%-- <td> <button type="button" onclick="detail?b_number=${b.b_number}"> 글조회 </button>
	<td> <button type="button" onclick="update?b_number=${b.b_number}"> 글수정 </button>
	<td> <button type="button" onclick="delete?b_number=${b.b_number}"> 글삭제 </button> --%>
	<td> <a href = "detail?b_number=${b.b_number}">글조회</a></td>
	<td> <a href = "update?b_number=${b.b_number}">글수정</a></td>
	<td> <a href = "delete?b_number=${b.b_number}">글삭제</a></td>
	
</c:forEach>
</table>


</body>
</html>