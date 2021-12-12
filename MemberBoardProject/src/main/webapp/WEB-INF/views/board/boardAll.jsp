<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든글보기</title>
<style>
/* 검색을 좌측하단으로 보내버리고싶음 */
/* 글쓰기를 우측하단으로 보내버리고싶음 */

</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
<h3> ❤❤ 회원제 게시판입니다 ❤❤ </h3>

<div class=head-right>
	<a href="/" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 메인화면 </a>
		<a href="/member/mypage?m_number=${loginDTO.m_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 마이페이지 </a>
		<!-- 마이페이지 불러올 때 /member/mypage 로 끝나는게 아니라 /member/mypage?m_number= 달러!{로그인정보}를 데려와줘야한다. -->
</div>


<table class="table table-warning table-striped">
	<thead>
		<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>글쓴이</td>
		<td>작성일</td>
		<td>조회수</td>		
	</thead>
	
	<c:forEach items="${boardList}" var="b">
		<tr> 
		<td> ${b.b_number} </td>
		<td> <a href="/board/boardDetail?b_number=${b.b_number}">${b.b_title}</a></td> <!-- 제목누르면 글로 이동하게끔 -->
		<td> ${b.b_writer} </td>
		<td> ${b.b_date} </td>
		<td> ${b.b_hits} </td>
		</tr>
		
	</c:forEach>
</table>

	<div class = "boardAllOption">
		<div id = search>
			<form action = "/board/search" method="get">
				<select name="searchtype">
					<option value="b_title"> 제목 </option>
					<option value="b_writer"> 작성자 </option>
				</select>
					<input type = "text" name ="keyword" placeholder = "검색어입력">
					<input type = "submit" value="검색"> 
			</form>
		</div>
	
		<div id = write-logout>
			<!-- 12.11 아이디가 admin인 관리자가 로그인했을때만 목록 링크가 보이도록 설정-->
		<c:if test="${sessionScope.loginDTO.m_id eq 'admin'}">
				<a href = "/member/memberAll" class="btn btn-outline-warning btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 회원목록</a>
		</c:if>
		
				<a href="/board/save?$b_writer=${writer}" class="btn btn-outline-warning btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 글쓰기 </a>
				<a href="/member/logout?m_number=${m_number}" class="btn btn-outline-dark btn-sm abled" tabindex="-1" role="button" aria-disabled="true"> 로그아웃 </a>
		</div>
	
		<div id = paging>
			<!-- 1203 페이지를 추가하는 코드 추가_선생님이 카카오워크로 주심! -->
			<div>
			<!-- 첫페이지일 경우 '이전'처리하는 구문 -->
				<c:choose>
					<c:when test="${paging.page<=1}">
						[이전]&nbsp;
					</c:when>
					<c:otherwise>
					<!-- 현재 페이지에서 1감소한 페이지 요청하는 링크 -->
						<!-- if(endPage > maxPage) endPage = maxPage; 밥먹어야하니까 나중에하자 --> 
						<a href="/board/paging?page=${paging.page-3}">[이전]</a>&nbsp; <!-- 3개씩 앞으로 가기 -->

					</c:otherwise>
				</c:choose>
				
				
			<!-- java for(int i=startPage; i<=endPage; i++) 과 비슷한 용도. jstl문법으로 쓴 것 -->
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
					<c:choose>
						<c:when test="${i eq paging.page}">
							<!-- 현재페이지값과 i값이 같다면 페이지 이름${i}만 출력하게끔 -->
							${i}
						</c:when>
						<c:otherwise>
							<a href="/board/paging?page=${i}">${i}</a>
							<!-- 현재페이지가 아닌경우 그 해당페이지(i)로 이동하는 링크로 묶어주기 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>
			
			<!-- 마지막페이지일 경우 '다음'처리하는 구문 -->
				<c:choose>
					<c:when test="${paging.page>=paging.maxPage}">
						[다음]
					</c:when>
					<c:otherwise>
						<a href="/board/paging?page=${paging.page+3}">[다음]</a> <!-- 3개씩 뒤로 가기 -->
					</c:otherwise>
				</c:choose>
			</div>
		</div>	
	</div>
	
	
	
	
<!-- 1208 : console에 데이터가 안뜬다 -->
<!-- 상단에 글쓰기버튼 만들기! / 1208 처리완료 > 작성자에 이름안뜨는건 어떻게해? /  -->
<!-- 하단에 로그아웃버튼 만들기! / 1208 버튼완료 -->
<!-- 상단에 검색창 만들기! / 검색창은 만들었음 / -->
<!-- 페이징처리해야해! / 페이징은 해뒀음 -->
</body>
</html>