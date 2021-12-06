<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function update()	{
	const pw = document.querySelector("#b_password").value;
	const pw1 = document.getElementById("b_password").value;
	const pwDB = '${board.b_password}';
	
	if(pw1==pwDB){
		update_form.submit();
	}	else {
		alert('비밀번호가 틀립니다.');
	}
}

</script>

</head>
<body>

<h2> update.jsp</h2>
	<form action = "/board/update" method = "POST" name="update_form">
		<input type = "hidden" name="page" value = "${page}"><br>
		<input type = "hidden" name="b_number" value = "${board.b_number}">
		글제목: <input type = "text" name = "b_title" value = "${board.b_title}"><br>
		작성자: <input type = "text" name = "b_writer" value = "${board.b_writer}" readonly><br>
		비밀번호: <input type = "text" name = "b_password" id="b_password"><br>
		글내용: <textarea id = "text" name = "b_contents">"${board.b_contents}"</textarea>		
		<input type = "button" value = "수정" onclick="update()">	
	</form>
</body>
</html>