package kr.or.ddit.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;


@WebServlet("/userModify")
public class UserModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserModifyController.class);
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	//사용자 수정 화면 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doGet");
		
		String userId = request.getParameter("userId");
		
		UserVo userVo = userService.getUser(userId);
		request.setAttribute("userVo", userVo);
		
		request.getRequestDispatcher("/user/userModidfy.jsp").forward(request, response);
	
	}

	//사용자 수정 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");	
		
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String alias = request.getParameter("alias");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcd = request.getParameter("zipcd");
		String birth = request.getParameter("birth");
		String pass = request.getParameter("pass");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
		UserVo userVo1 = userService.getUser(userId);
		request.setAttribute("userVo", userVo1);
		try {
			userVo = new UserVo(name, userId, alias, pass, addr1, addr2, zipcd,sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(userVo1 != null ){
			
			int updateDataCnt = userService.updateDataUser(userVo);
		
			
			if(updateDataCnt == 1){
				response.sendRedirect(request.getContextPath()+"/userPagingList");
			}else{
				doGet(request, response);
			}
		}
		
		
		
	
	}

}
