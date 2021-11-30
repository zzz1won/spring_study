<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 조회 </h3>

			${member.m_number}
			${member.m_id}
			${member.m_password}
			${mebber.m_name}
			${member.m_email}
			${member.m_phone}
			
			
			<a href = "findAll"> 목록으로 돌아가기</a>
			<a href = "/"> 홈(/)</a>		<!-- http://localhost:8081/ -->
			<a href = "./"> 홈(./)</a>	<!-- http://localhost:8081/member/ -->
			<a href = "../"> 홈(../)</a>	<!-- http://localhost:8081/ -->
			
</body>
</html>