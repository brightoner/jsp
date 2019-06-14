<%@tag import="kr.or.ddit.prod.doa.IprodDao"%>
<%@tag import="kr.or.ddit.prod.doa.ProdDao"%>
<%@tag import="kr.or.ddit.prod.doa.IprodDao"%>
<%@tag import="kr.or.ddit.prod.service.ProdService"%>
<%@tag import="kr.or.ddit.prod.service.IprodService"%>
<%@tag import="java.sql.DriverManager"%>
<%@tag import="java.sql.ResultSet"%>
<%@tag import="org.apache.ibatis.annotations.Results"%>
<%@tag import="java.sql.PreparedStatement"%>
<%@tag import="java.sql.Connection"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<%@ attribute name="code" required="true" %>

code : ${code}<br>

<%
	
	IprodDao prodDao = new ProdDao();
	jspContext.setAttribute("prodList", prodDao.prodList(code));
	
	
	
%>
<select>
	<c:forEach items="${prodList }" var="list">
		<option id="${list.prod_id}">${list.prod_name }</option>
	</c:forEach>
</select>

