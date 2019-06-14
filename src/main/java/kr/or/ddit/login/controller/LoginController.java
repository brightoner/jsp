package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* LoginController.java
* 로그인 처리 컨트롤러
* @author PC22
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC22 최초 생성
*
* </pre>
*/

// web.xml안의  servlet, servlet-mapping --> java annotation
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	private static final long serialVersionUID = 1L;
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
  
	
	//사용자 로그인 화면 요청을 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("loginController doGet()");	//sysout대신 사용
		
		logger.debug("parameter UNT_CD : {}", request.getParameter("UNT_CD") );
		
		//cookie처리
		if(request.getCookies() != null){
			for(Cookie cookie : request.getCookies()){
				logger.debug("cookie : {}, {}", cookie.getName(), cookie.getValue());
			}
		}
		
		//login 화면을 처리해줄 누군가??에게 위임
		//단순 login화면을 html로 응답을 생성해주는 작업이 필요
		//  /login/login.jsp 로 위임 --> 서버상에 별도의 상태 변경을 가하는 요청이 아니기때문에
		//								 dispatch방식으로 위임
		
		
		//session에 사용자 정보가 있을경우 --> main 화면
		//session에 사용자 정보가 없을경우 --> 기존로그인화면
		
		UserVo SESSION_USER = (UserVo) request.getSession().getAttribute("USER_INFO");
		if(SESSION_USER != null){
			request.getRequestDispatcher("/main.jsp").forward(request, response);;
		}else{
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			
		}
		
		
		//방식1
//		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		
		//방식2
//		RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
//		rd.forward(request, response);
		
	}

	//로그인 요청을 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키처리
		logger.debug("parameter rememberme  : {}", request.getParameter("rememberme"));
		
		//id, password
		logger.debug("parameter userId : {}", request.getParameter("userId") );
		logger.debug("parameter password : {}", request.getParameter("password") );
		
		
		logger.debug("parameter UNT_CD : {}", request.getParameter("UNT_CD") );
		
		
		
		//사용자 파라미터 serId, password
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String encyrptPassword = KISA_SHA256.encrypt(password);
		
		
		
		//해당사용자 정보를 이용하여 사용자가 보낸 serId, password가 일치하는지 검사
		//  --> userId : brown이고 password : brown1234라는 값일때 통과, 그 이외의 값을 불일치
		
		//db에서 해당사용자의 정보조회(service, dao 필요)
		//일치하면(로그인 성공) : main화면으로 이동
		UserVo userVo = userService.getUser(userId);
		
		if(userVo != null && encyrptPassword.equals(userVo.getPass())){	//DB(users 테이블)에서 정보 받아올때
//		if(userId.equals("brown") && password.equals("brown1234")) {	//DB없이 가짜로 만든 정보로 받아올때
		
			
			//remember 파라미터가 존재 할때 userId, rememberme cookie 설정해 준다
			//remember 파라미터가 존재하지 않을경우 userId, rememberme cookie 삭제해 준다
			int cookieMaxAge = 0;
			if(request.getParameter("rememberme") != null){
				cookieMaxAge = 60*60*24*30;  //초 단위가 기준// 삭제시는 0 사용
				
				Cookie uesrIdCookie = new Cookie("userId", userId);
				uesrIdCookie.setMaxAge(cookieMaxAge);  //초 단위가 기준// 삭제시는 0 사용
				
				Cookie rememberMeCookie = new Cookie("rememberme", "true");
				rememberMeCookie.setMaxAge(cookieMaxAge);
				
				response.addCookie(uesrIdCookie);
				response.addCookie(rememberMeCookie);
			}
			
			
			//session에 사용자 정보를 넣어준다(사용빈도가 높기때문에)
			//방법1
			
			request.getSession().setAttribute("USER_INFO", userVo);
			
			//방법2
//			HttpSession session = request.getSession();
//			session.setAttribute("USER_INFO", new UserVo("브라운","brown", "곰"));
			
				
			//request에 사용자 정보 넣기
			//방법1
//			RequestDispatcher rd =  request.getRequestDispatcher("/main.jsp");
//			rd.forward(request, response);
			
			//방법2
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
			
			
		}else{		//불일치하면(아이디 또는 비밀번호를  잘못입력) :로그인화면으로 이동
			//로그인화면으로 이동 : localhost/jsp/login
			//현상황에서는 /jsp/login  url로 dispatch방식으로 위임이 불가
			// request.getMethod();  <-- GET, POST 방식 리턴
			
			//request.getRequestDispatcher("/jsp/login").forward(request, response);   <-- 사용X
			
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/login");
			
			
		}
			
		
		
		
	}

}









