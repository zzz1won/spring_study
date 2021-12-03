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

<form action = "/board/search" method = "get">
	<select name="searchtype">
		<option value="b_title">제목</option>
		<option value="b_writer">작성자</option>
	</select>
		<input type="text" name="keyword" placeholder="검색어입력">
		<input type="submit" value="검색">
</form>

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
	<td> <a href = "/board/detail?b_number=${b.b_number}&page=${paging.page}"> ${b.b_title} </a></td>
	<!-- 글 상세조회 후 목록으로 돌아갈 때 페이지값을 유지하기위해 &page=${paging.page} 추가 -->
	<td> ${b.b_writer}</td>
	<td> ${b.b_date}</td>
	<td> ${b.b_hits}</td>
	<%-- <td> <button type="button" onclick="detail?b_number=${b.b_number}"> 글조회 </button>
	<td> <button type="button" onclick="update?b_number=${b.b_number}"> 글수정 </button>
	<td> <button type="button" onclick="delete?b_number=${b.b_number}"> 글삭제 </button> --%>
	
	<td> <a href = "/board/detail?b_number=${b.b_number}">글조회</a></td>
	<%-- <td> <a href = "update?b_number=${b.b_number}">글수정</a></td> --%>
	<td> <a href = "update?b_number=${b.b_number}&page=${paging.page}">글수정+페이징</a></td>
	<td> <a href = "delete?b_number=${b.b_number}">글삭제</a></td>
	
</c:forEach>
</table>

<!-- 1203 페이지를 추가하는 코드 추가_선생님이 카카오워크로 주심! -->
	<div>
	<!-- 첫페이지일 경우 '이전'처리하는 구문 -->
		<c:choose>
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise>
			<!-- 현재 페이지에서 1감소한 페이지 요청하는 링크 -->
				<!-- if(endPage > maxPage) endPage = maxPage; 밥먹어야하니까 나중에하자 --> 
				<a href="/board/paging?page=${paging.page-3}">[이전]</a>&nbsp; <!-- 3개씩 앞으로 가기 -->
					
				<%-- <a href="/board/paging?page=${paging.page-1}">[이전]</a>&nbsp; --%>
				<!-- 3페이지에 있었다면 -1처리해서 이전을 누를 시 2페이지로 이동!  -->
			</c:otherwise>
		</c:choose>
		
		
	<!-- java for(int i=startPage; i<=endPage; i++) 과 비슷한 용도. jstl문법으로 쓴 것 -->
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
					<!-- 현재페이지값과 i값이 같다면 페이지 이름${i}만 출력하게끔 -->
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
					<!-- 현재페이지가 아닌경우 그 해당페이지(i)로 이동하는 링크로 묶어주기 -->
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
	<!-- 마지막페이지일 경우 '다음'처리하는 구문 -->
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<%-- <a href="/board/paging?page=${paging.page+1}">[다음]</a> <!-- 1개씩 뒤로 가기 --> --%>
				<a href="/board/paging?page=${paging.page+3}">[다음]</a> <!-- 3개씩 뒤로 가기 -->
			</c:otherwise>
		</c:choose>
	</div>



</body>
</html>