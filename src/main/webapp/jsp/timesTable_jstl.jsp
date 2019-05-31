<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Insert title here</title>
</head>
<style>
	td{
		border: 2px solid red;
	}

</style>

<!-- localhost/jsp/jsp/timesTables.jsp -->
<!-- 9*9단 출력 (2~9단) -->

 
<table border="1">

<c:forEach begin="1" end="9" step="1" var="i">
	<tr>
		<c:forEach begin="2" end="9"	step="1" var="j">
			<td>${j }*${i }=${j*i } </td>
		</c:forEach>
	</tr>

</c:forEach>




</table>
</html>