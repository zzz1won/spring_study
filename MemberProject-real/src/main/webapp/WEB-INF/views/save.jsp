<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	input {
	display : block;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	/* id 입력을 하는 동안에 idDuplicate() 함수를 호출하고 입력된 값을 콘솔에 출력  */
	function idDuplicate()	{
		const id = document.getElementById('m_id').value;
		console.log(id);
		const checkResult = document.getElementById('id-dup-check');
		$.ajax({
			type : 'post', // 전송방식(get,post,delete,put...)
			url : 'idDuplicate', // 요청주소(컨트롤러로 요청하는 주소)
			data : {'m_id': id}, // 전송할 데이터
			dataType : 'text',   // 요청 후 리턴받을 때의 데이터 형식
			seccess: function(result)	{
				//요청이 성공적으로 처리됐을 때 실행할 함수
				console.log("ajax성공");
				console.log(result)
				//if문의 결과값으로 ok냐 no냐에 따라 실행시킬 것
				if (result == "ok")	{
					CheckResult.style.color = blue;
					checkResult.InnerHTML = "사용가능합니다"
				} else	{
					CheckResult.style.color = red;
					checkResult.InnerHTML = "이미 사용중인 아이디입니다."
				}
			},
			error: function()	{
				//요청이 실패했ㅇ르 때 실행할 함수
				console.log("오타찾으세요^^...");
			}
		});
	}
	
</script>
</head>
<body>
	<h2>회원가입페이지입니다</h2>
	<form action = "save" method = "post">
		<!-- 아이디 : <input type = "text" name = "m_id"> -->
		아이디 : <input type = "text" name = "m_id" onkeyup = "idDuplicate()" id="m_id">
		<!-- 아이디체크를 위한 함수설정, span  -->
		<span id="id-dup-check"></span>
		비밀번호 : <input type = "text" name = "m_password">
		이름 : <input type = "text" name = "m_name">
		이메일 : <input type = "text" name = "m_email">
		연락처 : <input type = "text" name = "m_phone">
		
		<input type = "submit" value = "가입">	
	</form>
</body>
</html>