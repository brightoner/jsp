package kr.or.ddit.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/Sumcalculation")
public class Sumcalculation extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(Sumcalculation.class);
	
	private static final long serialVersionUID = 1L;
       
  
	//GET 방식일때 구현 - sumInput에서 POST방식으로 전송 --> 구현X
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	//POST방식일때 구현 - sumInput에서 POST방식으로 전송 - 여기서 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("patameter start : {}", request.getParameter("start"));
		logger.debug("patameter end : {}", request.getParameter("end"));
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		int sum = 0;
		
		for(int i= Integer.parseInt(start); i <= Integer.parseInt(end); i++){
			
			sum += i;
			
		}
		
		request.getSession().setAttribute("sumResult",sum);
		
		request.getRequestDispatcher("/jsp/sumResult.jsp").forward(request, response);
		
		
	}

}
