<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	td{
		border: 2px solid red;
	}

</style>

<!-- localhost/jsp/jsp/timesTables.jsp -->
<!-- 9*9단 출력 (2~9단) -->

 
<table border="1">

<% 
	
	String param = request.getParameter("i");
    String param2 = request.getParameter("j");
%>


<% for(int i = 2; i<=Integer.parseInt(param); i++){ %>

<tr>	
	
	<% for(int j = 1; j<=Integer.parseInt(param2); j++){ %>
	
	<td> <%= i + "*" + j + "="  + i *j %> </td>
	
	<% }%>

</tr>

<% }%>

</table>

</head>

<body>

</body>
</html>