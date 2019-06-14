<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="/common/basicLib.jsp"%>

<script>
$(document).ready(function(){
	$("#localeSelect").val("${locale}");
	$("#localeSelect").on("change",function(){
		$("#frm").submit();
//  console.log($("#frm").serialize());
		
	});
});

</script>

</head>
<body>

<h2>select locale</h2>

<form id="frm" action="${cp }/selectLocale" method="get">
<select id="localeSelect" name="locale">
	<option value="ko">한국어</option>
	<option value="en">english</option>
	<option value="ja">日本言</option>
</select>
locale : ${locale }
<fmt:setLocale value="${locale }"/>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="GREETING"/><br>
	<fmt:message key="VISITOR">
		<fmt:param value="brown"/><br>
	</fmt:message>
</fmt:bundle>
</form>


</body>
</html>










