<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/MulCalculation" method="post">
		<input type="text" name="param1">
		<input type="text" name="param2">
		<button>전송</button>
	</form>



</body>
</html>