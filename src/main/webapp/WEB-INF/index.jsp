<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields -->

	<form method="post" action="add">

		<input type="text" name="noteTitle" placeholder="enter notetitle" id="noteTitle">
		<textarea id="noteContent" placeholder="enter notecontent" name="noteContent"></textarea>
		<input type="text" name="noteStatus" placeholder="enter notestatus" id="noteStatus">
		<button type="submit">Add</button>

	</form>
	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->


		<c:forEach items="${notes}" var="note">
			<p>${note.noteTitle}</p>
			<p >${note.noteContent}</p>
		</c:forEach>




</body>

</html>