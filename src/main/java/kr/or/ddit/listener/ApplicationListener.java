package kr.or.ddit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



// contextPath --> js,css 경로설정
// ${cp}/js/jquery.js
// ==> ${cp}/js/jquery.js
// ==> application cp 속성에 contextPath값을 넣어주면

// web.xml 에서 listener등록!!
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// getServletContext() <== application
		sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		
	}

	
	
	
	
}
