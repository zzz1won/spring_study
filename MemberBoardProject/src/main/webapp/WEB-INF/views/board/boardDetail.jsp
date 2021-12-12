<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 조회</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- CSS only -->
<link href="\resources\css\test.css" rel="stylesheet">
</head>
<body>

<h2> 글조회 </h2>
${board.b_writer}
	<table class="table table-dark table-striped">
	<tr class="table-warning"> 
		<td class="table-warning"> 번호: ${board.b_number}</td>
		<td class="table-warning"> 제목: ${board.b_title}</td>
		<td class="table-warning"> 작성자: ${board.b_writer}</td>
		<td class="table-warning"> 작성일: ${board.b_date}</td>
		<td class="table-warning"> 조회수: ${board.b_hits}</td>		
		<%-- <td class="table-warning"> b_wr: ${board.b_writer}</td> --%>
		
	</tr>
	</table>
		내용-----------------------------------<br>
		${board.b_contents}<br>
		<img alt="" src="/resources/upload/${board.b_filename}"><br>
<br>
	<a href="/board/paging" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 목록 </a>
		<c:if test="${sessionScope.loginDTO.m_id eq board.b_writer}">
			<a href="/board/boardUpdate?b_number=${board.b_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 수정 </a>
			<a href="/board/boardDelete?b_number=${board.b_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 삭제 </a>
		</c:if>
		<c:if test="${sessionScope.loginDTO.m_id eq 'admin'}">
			<a href="/board/boardDelete?b_number=${board.b_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 삭제 </a>
		</c:if>

	<div class = reply>
		<div id = "comment-write">
			<!-- 댓글입력창 -->
			<input type ="text" id="c_writer" value="${loginDTO.m_id}" readonly><br>
			<input type ="text" id="c_comment" placeholder="댓글입력">
			<button id = "comment-write-btn">댓글등록</button>
		</div>
		
		<div id = "comment-list">
			<!-- 댓글목록출력 -->
			<table class = "table">
				<tr>
					<th>댓글번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>작성시간</th>
					
				<c:forEach items="${commentList}" var="comment">
				<tr>
					<td>${comment.c_number}</td>
					<td>${comment.c_writer}</td>
					<td>${comment.c_comment}</td>
					<td>${comment.c_date}</td>
				</tr>	
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>

	<script>
		/* j-query로 사용...  */
		$("#comment-write-btn").click(function(){
			console.log("댓글등록버튼 클릭");
			
		/* ajax 한번 쳐보자...^^ */
		const commentWriter =$('#c_writer').val();
		const commentComment =$('#c_comment').val();
		const boardNumber = "${board.b_number}";
		console.log(commentWriter, commentComment, boardNumber);
		/* });
 */		
		$.ajax({
		
			type : 'post', // 전송방식
			url: '/comment/commentSave', //컨트롤러로 요청주소
			data : 
			{	'c_writer' : commentWriter,
				'c_comment' : commentComment,
				'b_number' : boardNumber },
			dataType : 'json',
			success: function(result)	{
				console.log(result);
				let output = "<table class ='table'>";
				output += "<tr><th>댓글번호</th>";
				output += "<th>작성자</th>";
				output += "<th>댓글</th>";
				output += "<th>작성시간</th></tr>";
					for(let i in result){
						output += "<tr>";
						output += "<td>"+result[i].c_number+"</td>";
						output += "<td>"+result[i].c_writer+"</td>";
						output += "<td>"+result[i].c_comment+"</td>";
						output += "<td>"+result[i].c_date+"</td>";
						output += "</tr>";
					}
					output += "</table>";
					document.getElementById('comment-list').innerHTML = output;
					// 댓글 입력창을 비움. 
					/* document.getElementById('c_writer').value=''; 얘까지 비워버리면 댓글작성자가 지워지니까*/
					document.getElementById('c_comment').value='';
			},
			error: function()	{
					console.log ("난 오타가 많이 난다...");
			}
		})
	});
		
		
	</script>
</html>