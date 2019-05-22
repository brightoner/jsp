<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 어디로 요청할지?? form테그의 action속성
		 어떻게 보낼지(http method) ?? form테그 method속성(get-default/ post) -->
	<%-- /login/login.jsp  -->  /login/loginProcess.jsp --%>
	<form action="<%=request.getContextPath() %>/login/loginProcess.jsp"
			method="post">
		userId : <input type="text" name="userId" value="sally"/><br>
		userId : <input type="text" name="userId" value="셀리"/><br>
		password : <input type="password" name="password" value="pass1234"/><br>
	<input type="submit" value="로그인"/>
	</form>
</body>
</html>