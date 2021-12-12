<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mbbd 회원가입</title>
<style>
	input {
	display:block;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script> <!--MemberProject-real 에서 복붙 -->
<script>
	/* id 입력을 하는 동안에 idDuplicate() 함수를 호출하고 입력된 값을 콘솔에 출력  */
	function idDuplicate()	{
		const id = document.getElementById('m_id').value;
		console.log(id);
		const checkResult = document.getElementById('id-dup-check');
		$.ajax({
			type : 'post', // 전송방식(get,post,delete,put...)
			url : 'idDuplicate', // 요청주소(컨트롤러로 요청하는 주소...컨트롤러에 없으면 안된다...멍청아...)
			data : {'m_id': id}, // 전송할 데이터
			dataType : 'text',   // 요청 후 리턴받을 때의 데이터 형식
			success: function(result)	{
				//요청이 성공적으로 처리됐을 때 실행할 함수
				console.log("ajax성공");
				console.log(result)
				//if문의 결과값으로 ok냐 no냐에 따라 실행시킬 것
				if (result == "ok")	{
					checkResult.style.color = 'green';
					checkResult.innerHTML = "사용가능합니다"
				} else	{
					checkResult.style.color = 'pink';
					checkResult.innerHTML = "이미 사용중인 아이디입니다."
				}
			},
			error: function()	{
				console.log("오타찾으세요^^...");
			}
		});
	}
	
</script> <!--MemberProject-real 에서 복붙 -->
</head>
<body>
<h3> ❤❤❤❤회원가입❤❤❤❤ </h3>
	<form action ="/member/save" method=post enctype="multipart/form-data">
		아이디: <input type="text" name="m_id" onblur="idDuplicate()" id="m_id">
		<span id="id-dup-check"></span><br>
		비밀번호: <input type="text" name="m_pw">
		이름: <input type="text" name="m_name">
		이메일: <input type="text" name="m_email">
		전화번호: <input type="text" name="m_phone">
		프로필사진: <input type="file" name="m_file"><br>
		
		<input type="submit" value="가입">
	<h3> ❤❤❤❤❤❤❤❤❤❤❤❤ </h3>

	</form>
</body>
</html>