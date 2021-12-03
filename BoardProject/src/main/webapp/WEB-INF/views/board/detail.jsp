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
	
	<a href = "findAll"> 목록으로 돌아가기 </a>
	
	
	<!-- 1202 점심이후 시간, detail.jsp에서 수정, 삭제 링크를 각각 만들고 수정, 삭제기능을 구현해봅시다.
		
		1. 삭제기능 : 삭제 클릭하면 삭제처리하고 목록 출력
		2. 수정기능 : 수정 클릭하면 수정화면(update.jsp) 출력 후 제목, 내용만 수정하도록 하고 (textarea로 수정하는 방법은 구글링하세요)
				 비밀번호 확인하여 맞으면 수정처리, 틀리면 alert 출력
				 수정처리 완료 후 detail.jsp 다시 출력할 것
	-->
	
	<!-- 1203 점심이후 시간, 수정기능에 페이징 추가
		수정처리완료 후 상세페이지를 띄우고 거기서 목록링크를 클릭하면 직전페이지로 돌아가도록 코드를 살짝 수정해봅시다. -->
	
	<a href = "/board/delete?b_number=${board.b_number}"> 삭제 </a>
	<a href = "/board/update?b_number=${board.b_number}&page=${paging.page}"> 수정 </a>
	<!-- <a href = "/board/paging"> 목록 </a> -->
	<a href = "/board/paging?page=${page}"> 목록 </a>
	<!-- page라는 parameter 값을 들고다닌다 -->
	
	
	
</body>
</html>