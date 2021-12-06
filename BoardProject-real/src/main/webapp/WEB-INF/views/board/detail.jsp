<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="\resources\css\test.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

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
	<td> 파일: <img alt="hi" src="/resources/upload/${board.b_filename}"></td>
	
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
	
	
	<!-- 1206 11시 댓글기능 -->
	
	<div id="comment-write">
		<input type="text" id="c_writer" placeholder="작성자">
		<input type="text" id="c_contents" placeholder="댓글내용">
		<button id="comment-write-btn">댓글등록</button>
	</div>
	
	<!-- 댓글목록출력 -->
	<div id = "comment-list">
		<table class="table">
			<tr>
				<th>댓글번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성시간</th>
			</tr>
		
			<c:forEach items="${commentList}" var="comment">
				<tr>
					<td>${comment.c_number}</td>
					<td>${comment.c_writer}</td>
					<td>${comment.c_contents}</td>
					<td>${comment.c_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
</body>

	<script>

	  	
/* 	const commentBtn = document.getElementById("comment-write-btn");
	commentBtn.addEventListener("click",function()	{
		console.log("댓글등록버튼 클릭");
		
	}); */
	
	/* j-query로 사용...  */
	$("#comment-write-btn").click(function(){
		console.log("댓글등록버튼 클릭");
	
		/* ajax 문법을 이용하여 댓글작성자, 작성내용을 comment / save 주소로 post 방식으로 전송하는 코드를 작성해봅시다.
		commentController도 하나 선언하여 여기서 보낸 데이터를 받는지 sysout으로 출력해봅시다.
		tip. data: {"c_writer":작성자입력값, "c_contents": 내용입력값}
		*/
		const commentWriter = $("#c_writer").val();
		const commentContents = $("#c_contents").val();
		console.log(commentWriter, commentContents);
		const boardNumber = "${board.b_number}"; /* 현재 detail.jsp에서 ${board.b_number} 가 나와있어서 적을 수 있음 */ 
		console.log(commentWriter, commentContents, boardNumber);
		
		$.ajax({
			type : 'post', // 전송방식(get,post,delete,put...)
			url : '/comment/save', // 요청주소(컨트롤러로 요청하는 주소)
			data : {
				'c_writer' : commentWriter,
				'c_contents' : commentContents,
				'b_number' : boardNumber }, // 전송할 데이터
			dataType : 'json',   // 요청 후 리턴받을 때의 데이터 형식
			success: function(result)	{
				console.log(result);
 				let output = "<table class='table'>";
				output += "<tr><th>댓글번호</th>";
				output += "<th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>작성시간</th></tr>";
				for(let i in result){
					output += "<tr>";
					output += "<td>"+result[i].c_number+"</td>";
					output += "<td>"+result[i].c_writer+"</td>";
					output += "<td>"+result[i].c_contents+"</td>";
					output += "<td>"+result[i].c_date+"</td>";
					output += "</tr>";
				}
				output += "</table>";
				// id가 comment-list인 div에 출력
				document.getElementById('comment-list').innerHTML = output;
				// 댓글 입력창을 비움. 
				document.getElementById('c_writer').value='';
				document.getElementById('c_contents').value='';
			},
			error: function()	{
				console.log("흐름을 따라가보세요, 오타주의 괄호주의");
			}
		})
	});


	
		/* js 방식
		const commentWriter = document.getElementById('c-writer').value;
		const commentContents = document.getElementById('c-contents').value;
		console.log(commentWriter);
		console.log(commentContents); */	
	
	
	</script>
</html>