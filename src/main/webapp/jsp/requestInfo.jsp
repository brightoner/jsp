<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 스키립틀릿 <% %> : 자바로 로직을 작성하는 공간--%>
	<%-- 표현식 <%=%> : 결과물 출력 --%>
	
	<%
		String msg = "test";
		msg += "_append";
		
		//jsp의 내장객체, 묵시적 객체(implict) - 이미선언되어있기때문에 별도의 선언없이 사용가능한객체
		
	
	%>

	request.getRemoteAddr() : <%=request.getRemoteAddr() %><br>
	request.getLocalAddr() : <%=request.getLocalAddr() %><br>
	request.getContentType() : <%=request.getContentType() %><br>
	request.getContextType() : <%=request.getContextPath() %><br>
	request.getMethod() : <%=request.getMethod() %><br>
	request.getRequestURL() : <%=request.getRequestURI() %><br>
	request.getProtocol() : <%=request.getProtocol() %><br>
	
	<img src="<%=request.getContextPath() %>/img/sally.png">
	
	


</body>
</html>