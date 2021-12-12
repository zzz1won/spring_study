<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>

<!-- 1209 마이페이지 -->
<h3> ❤❤❤❤ 마이페이지 수정 ❤❤❤❤ </h3>
	<form action ="mupdate" method="post" name="update_form"> 저장되는 이유가 뭐야
	<!-- <form action ="/board/paging" method="post" name="update_form"> -->
		<input type = "hidden" name="m_number" value="${member.m_number}">
		아이디: <input type="text" name="m_id" value="${member.m_id}" readonly>
		비밀번호: <input type="password" name="m_pw" id="m_pw" placeholder="${member.m_pw}">
		이름: <input type="text" name="m_name" id="m_name" placeholder="${member.m_name}">
		이메일: <input type="text" name="m_email" value="${member.m_email}">
		전화번호: <input type="text" name="m_phone" value="${member.m_phone}"><br>
		프로필사진: <input type="file" name="m_file" value="${member.m_file}"><br>
		
		<input type="submit" value="수정">
		</form>
	<h3> ❤❤❤❤❤❤❤❤❤❤❤❤ </h3>

</body>
</html>