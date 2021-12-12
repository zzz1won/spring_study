<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input	{
display : block;
}
</style>
</head>
<body>

	<form action = "/board/boardUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="b_number"  value ="${board.b_number}"><br>
		<input type="text" name="b_title" id="b_title" value ="${board.b_title}"><br>
		<input type="text" name="b_writer" value ="${loginDTO.m_name}" readonly><br>
		<textarea name="b_contents" id="b_contents" rows="10">${board.b_contents}</textarea><br>
		<input type="file" name= "b_file"><br>
		<input type ="submit" value="작성" >	
	</form>

</body>
</html>