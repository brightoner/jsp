<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>c:set - 로그인 후 테스트 할 것!</h2>
	<!-- var : 속성명, value : 값, scope : page(default), request, session, applicatoin -->
	
	<%
// 		UserVo USER_INFO = (UserVo)session.getAttribute("USER_INFO");
// 		pageContext.setAttribute("userId", USER_INFO.getAlias());
	%>
	
	<c:set var="userId" value="${USER_INFO.alias }" scope="request"/>
	userId : ${userId }<br>
	requestScope.userId : ${requestScope.userId }<br>
	
	<!-- target 대상속성, property : 대상속성 필드, value : 대입값 -->
<%-- 	<c:set target="${USER_INFO }" property="alias" value="bear"/> --%>
	USER_INFO.alias : ${USER_INFO.alias }<br>
	
	<h2>c:if *** c:if는 java의 if-else, if, -else중에서 단일 if에만 해당 
		(c:choose태그가 일반적인 if문)</h2>
		
	<!-- if(test) - 조건 -->	
	<c:if test="${USER_INFO.userId == 'brown'}">
		userId는 brown입니다
	</c:if>
	
	<!-- if구문을 통괴 못해서 출력X -->
	<c:if test="${USER_INFO.userId == 'sally'}">
		userId는 sally입니다
	</c:if>
	
	<h2>c:choose 자바의 if- else if - else</h2>
	<%
		request.setAttribute("code", "03");
	%>
	
	<!-- == 또는 eq 사용가능 -->
	<c:choose>
		<c:when test="${code eq '01' }">
			code is '01'
		</c:when>
		<c:when test="${code == '02'}">
			code is '02'
		</c:when>
		<c:when test="${code == '03'}">
			code is '03'
		</c:when>
		<c:otherwise>
			code is ${code }
		</c:otherwise>
	</c:choose>
	
	<!-- el안에서 연사 가능 -->
	<h2>el 연산</h2>
	<%
		PageVo pageVo = new PageVo(1, 10);
		request.setAttribute("pageVo", pageVo);
	%>
	page + 2 : ${pageVo.page + 2 }
	
	
	<h2>c: forEach 일반 반목문</h2>
	<%--for(int i=1; i <10; i++) --%>
	<c:forEach begin="1" end="10" step="1" var="i">
		${i }<br>
	</c:forEach>
	
	
	
	<!-- foreach 로 map 도 가능하다!! 자주 사용은 X -->
	<h2>foreach map</h2>
	<%
		Map<String, String> dataMap = new HashMap<String, String>();
		//name, age,hp
		dataMap.put("name", "brown");
		dataMap.put("age", "8");
		dataMap.put("hp", "101-8888-8888");
		
		for(String key : dataMap.keySet())
			out.write(dataMap.get(key) + "<br>");
		
		request.setAttribute("dataMap", dataMap);
	
	%>
	================================================<br>
	<c:forEach items="${dataMap }" var="data">
		${data.key } / ${data.value }<br>
	</c:forEach>
	<br>
	<br>
	
	
	

</body>
</html>





















