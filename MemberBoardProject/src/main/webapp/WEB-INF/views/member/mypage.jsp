<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
	input	{
	display : block;
	}
</style>
<script>
	function mypageCheck()	{
		
		const pw = document.querySelector("#m_pw").value;
		const pw1 = document.getElementById("m_pw").value;
		const pwDB = '${member.m_pw}';
		
		if(pw1 == pwDB){
			update_form.submit();
			console.log("마이페이지출력에서-성공")
		} else {
			alert ("비밀번호가 틀립니다.")
			console.log("마이페이지출력에서-실패")
		}
	}
</script>
</head>
<body>
	<!-- 1210 마이페이지 출력 -->
	<h3> ❤❤❤❤ 마이페이지 출력 ❤❤❤❤ </h3>
		<form action ="/member/updatepage?m_number=${loginDTO.m_number}" method="post">
		아이디 : <input type="text" name="m_id" value="${member.m_id}" readonly>
		비밀번호 : <input type = "password" name = "m_pw" id = "m_pw" placeholder = "비밀번호">
		<input type = "hidden" name = "m_name" id = "m_name" value="${member.m_name}">
		<input type = "hidden" name = "m_phone" id = "m_phone" value="${member.m_phone}">
		<input type = "hidden" name = "m_email" id = "m_email" value="${member.m_email}">
		
		
		<input type="submit" value="확인" onclick="mypageCheck()">
		<!-- 이 상태라면 확인버튼 누를 시 비번이 맞으면 updatepage로 이동, 비번이 틀리면 alert가 뜬다! -->
			<h3> ❤❤❤❤❤❤❤❤❤❤❤❤ </h3>
		</form>
	<!-- 1209 마이페이지 -->
	<!--  <h3> ❤❤❤❤ 마이페이지 출력 ❤❤❤❤ </h3>
	<form action ="/member/mypage" method="post">
		아이디: input"${member.m_id}"
		비밀번호: "${member.m_pw}"
		이름: "${member.m_name}"
		이메일: "${member.m_email}"
		전화번호: "${member.m_phone}"
		프로필사진: "${member.m_file}"
		
		<a href="/member/mypage?m_number=${loginDTO.m_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 수정 </a>
		</form>
	<h3> ❤❤❤❤❤❤❤❤❤❤❤❤ </h3> -->
	
	
		

</body>
</html>