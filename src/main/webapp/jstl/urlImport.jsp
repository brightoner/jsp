<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 참고사항 -->

<c:import url="https://search.naver.com/search.naver">
	<c:param name="query" value="html5"/>
</c:import>
 <br>
 <br>

<c:import url="https://search.naver.com/search.naver" var="test">
	<c:param name="query" value="html5"/>
</c:import>
${test }
