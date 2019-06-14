package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//접속여부를 확인해서 (세션에 USER_INFO속성이 존재  하는지)
//접속이 안되어 있으면  --> login화면으로 이동
//접속이 안되있으면 정상적으로 최초요청한 리소스로 위임

//ex : /user/userList --> /login
//ex : /user/userPagingList --> /login
//ex : /login --> /login


@WebFilter("/*")
public class LoginCheckFilter implements Filter {

  
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		//주소에 붙은 contextPath 없앰 ( /jsp/userPagingList --> /userPagingList)
		uri = uri.substring(req.getContextPath().length());
		
		//세션이 없어도 처리가되는 것들 : /login, /js, /css, /img (.js, .css, .png, .gif)
		if(uri.startsWith("/login") || uri.startsWith("/js") || uri.startsWith("/css") || uri.startsWith("/img"))
		chain.doFilter(request, response);
		
		//seeeion을처크해야하는 대상들
		else if(req.getSession().getAttribute("USER_INFO") != null){
			chain.doFilter(request, response);
			
		}
		else{
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect(req.getContextPath() + "/login");
			
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {

	
	}

}
