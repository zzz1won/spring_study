<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>

<script>
function boardDelete()	{
	const pw = document.querySelector("#m_pw").value;
	const pw1 = document.getElementById("m_pw").value;
	const pwDB = '${member.m_pw}';
	
	if(pw1==pwDB){
		update_form.submit();
	}	else {
		alert('비밀번호가 틀립니다.');
	}
}

</script>
</head>
<body>


<h3> 삭제완료 </h3>

<input type = "button" value = "삭제" onclick="boardDelete()">	
<a href="/board/boardAll" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 글목록 </a>
<a href="/member/logout?m_number=${m_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 로그아웃 </a>
</body>
</html>