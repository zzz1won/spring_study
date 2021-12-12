<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글을 씁시다</title>
<style>
input	{
display : block;
}
</style>
</head>
<body>

			<!-- 작성자는 입력하지않아도 자동으로 입력되어있는 상태...(안되고있음)  -->
	<form action = "/board/save" method="post" enctype="multipart/form-data">
		<input type="text" name="b_title" placeholder ="제목"><br>
		<input type="text" name="b_writer" value ="${loginDTO.m_id}" id="b_writer" readonly><br>
		<textarea name="b_contents" rows="10" placeholder ="내용을 작성하세용"></textarea><br>
		<input type="file" name= "b_file"><br>
		<input type ="submit" value="작성" >	
	</form>
</body>
</html>