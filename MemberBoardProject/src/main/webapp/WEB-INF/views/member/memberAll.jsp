<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>

function detailAjax(m_number){
    console.log(m_number);
    const view = document.getElementById('detail-view');
    $.ajax({
       type: 'post',
       url: 'detailAjax',
       data: {'m_number':m_number},
       dataType: 'json',
       success: function(result){ // 요청이 성공적으로 처리 됐을 때 실행할 함수
          console.log('ajax성공');
          console.log(result);
          console.log(result.m_id);
          
        	//선생님이 보내주신 코드
          	let output = "<table class='member_table'>";
			output += "<tr><th>number</th> <th>id</th> <th>password</th> <th>name</th>";
			output += "<th>email</th> <th>phone</th> </tr>";
			output += "<tr>";
			output += "<td>"+result.m_number+"</td>";
			output += "<td>"+result.m_id+"</td>";
			output += "<td>"+result.m_pw+"</td>";
			output += "<td>"+result.m_name+"</td>";
			output += "<td>"+result.m_email+"</td>";
			output += "<td>"+result.m_phone+"</td>";
			output += "</tr>";
			output += "</table>";
			
			view.innerHTML = output;
			//선생님이 보내주신 코드
             
       },
       error: function(){ // 요청이 실패했을 때 실행할 함수
          console.log('오타를 찾으세요');
       }
    });
 }
 </script>
<title>MemberAll</title>
</head>
<body>
<h4> ❤</h4>
<table class="table table-striped">

<thead>
	<tr>
	<th>번호</th>
	<th>아이디</th>
	<th>비밀번호</th>
	<th>이름</th>
	<th>이메일</th>
	<th>연락처</th>
	<th>삭제</th>
	</tr>
</thead>
	<c:forEach items="${memberList}" var="m">
		<tr>
			<td>${m.m_number}</td>
			<td>${m.m_id}</td>
			<td>${m.m_pw}</td>
			<td>${m.m_name}</td>
			<td>${m.m_email}</td>
			<td>${m.m_phone}</td>

			<td><a href="delete?m_number=${m.m_number}" class="btn btn-secondary btn-sm abled" tabindex="-1" role="button" aria-disabled="false">수정test</a></td>
			<%-- <td> <button type="button" class="btn btn-primary btn-sm" onclick="delete?m_number=${m.m_number}"> 삭제 </button> </td> --%>
		</tr>
	</c:forEach>
</table>
<h4> 💙</h4>

</body>
</html>