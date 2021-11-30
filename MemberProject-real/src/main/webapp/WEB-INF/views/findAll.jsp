<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
function detailAjax(m_number){
    console.log(m_number);
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
          	let output = "<table class='table'>";
			output += "<tr><th>number</th> <th>id</th> <th>password</th> <th>name</th>";
			output += "<th>email</th> <th>phone</th> </tr>";
			output += "<tr>";
			output += "<td>"+result.m_number+"</td>";
			output += "<td>"+result.m_id+"</td>";
			output += "<td>"+result.m_password+"</td>";
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
</head>
<body>

<h2> 회원목록</h2>
<table class="table table-striped">


       <thead>
        	<tr>
            <th>번호</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>이메일</th>
            <th>연락처</th>
            <th>조회</th>
            <th>상세조회</th>
            <th>삭제</th>
            <th>수정</th>
        	</tr>
		</thead>
<c:forEach items="${mList}" var="m">
화면에 출력할 때는 JSTL 
		<tr>
			<td>${m.m_number}</td>
			<td>${m.m_id}</td>
			<td>${m.m_password}</td>
			<td>${m.m_name}</td>
			<td>${m.m_email}</td>
			<td>${m.m_phone}</td>
			
			<!-- detail 이라는 주소요청을 통해 회원 상세조회를 해봅니다. 상세조회 데이터는 detail.jsp에 출력 -->
			<td> <a href = "detail?m_number=${m.m_number}">조회</a></td>
			<td><button onclick="detailAjax(${m.m_number})">조회 ajax</button></td>
			<td> <a href = "delete?m_number=${m.m_number}">삭제</a></td>
			
			<!-- 1. 수정화면 요청, 수정화면 요청시 DB로부터 해당회원의 정보를 가져와 update.jsp에 출력함(form태그 사용)
				 2. 수정처리, update.jsp에 입력한 내용을 서버로 전달하여 수정을 처리하고 해당 회원의 detail 페이지 출력
				 	이메일, 전화번호만 수정가능하게!  -->
			<td> <a href = "update?m_number=${m.m_number}">수정</a></td>
			
			</tr>
	</c:forEach>
    </table>
    
    
    <!-- ajax로 가져온 상세조회 결과를 여기에 보여줌 -->
    <div id="detail-view">
    
    </div>
    
    
    
    
    
</body>
</html>