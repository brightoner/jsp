package kr.or.ddit.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/MulCalculation")
public class MulCalculation extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(MulCalculation.class);
	
	private static final long serialVersionUID = 1L;
 
	//GET 방식일때 구현 - sumInput에서 POST방식으로 전송했으므로 구현X
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	//POST방식일때 구현 - sumInput에서 POST방식으로 전송 - 여기서 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("parameter param1 : {}", request.getParameter("param1"));
		logger.debug("parameter param2 : {}", request.getParameter("param2"));
		
		String param1 = request.getParameter("param1");
		String param2 = request.getParameter("param2");
		
		int mul = Integer.parseInt(param1) * Integer.parseInt(param2);
		
		request.getSession().setAttribute("mulResult",mul);
		
		request.getRequestDispatcher("/jsp/mulResult.jsp").forward(request, response);
		
		
	}

}
