<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>sum 테스트</h2>
	<form action="${pageContext.request.contextPath}/Sumcalculation" method="post">
		<input type="text" name="start">
		<input type="text" name="end">
		<button>전송</button>
	</form>

</body>
</html>