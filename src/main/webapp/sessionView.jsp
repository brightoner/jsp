<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- application 속성 sessionMap 정보를 화면에 표현 --%>

<h2>sessionMap</h2>
<%-- HttpSession ==> getAttribute를해서 userVo를 불러온다 --%>
<table border="1">
	<tr>
		<th>session id</th>
		<th>USER_INFO.name</th>
	</tr>
	<c:forEach items="${sessionMap }" var="sess">
		<tr>
			<td>${sess.key }</td>
			<td>${sess.value.getAttribute("USER_INFO").name }</td>
		</tr>
	</c:forEach>
</table>


<h2>sessionUserMap</h2>
<%-- sessionUserMap ==> sessionUserMap 에 UserVo를 담아서 바로 불러온다 --%>
<table border="1">
	<tr>
		<td>사용자아이디</td>
		<td>사용자이름</td>
	</tr>
	<c:forEach items="${sessionUserMap }" var="ses">
		<tr>
			<td>${ses.key }</td>
			<td>${ses.value.name }</td>
		</tr>
	</c:forEach>
	
</table>

</body>
</html>




