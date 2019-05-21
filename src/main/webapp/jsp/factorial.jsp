<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! public String hello(){
		return "hello~";
	}	
%>

	hello() : <%= hello() %> <br>
	
<%! 	
	public int factorial(int fac){
		
		if(fac ==0)
			return  1;
		else
			return factorial(fac-1)*fac;
	}
%>	
		
	
	fac(1) : <%= factorial(1) %><br>
	fac(2) : <%= factorial(2) %><br>
	fac(3) : <%= factorial(3) %><br>
	fac(4) : <%= factorial(4) %><br>
	fac(5) : <%= factorial(5) %><br>
	fac(6) : <%= factorial(6) %><br>
	fac(7) : <%= factorial(7) %><br>
	fac(8) : <%= factorial(8) %><br>
	fac(9) : <%= factorial(9) %><br>



</body>
</html>