package kr.or.ddit.user.controller;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;


@WebServlet("/userPagingList")
public class UserPagingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserPagingList.class);
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//parameter가 있을때
//		int page = Integer.parseInt(request.getParameter("page"));
//		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//
//		request.setAttribute("pageList", userService.userPagingList(new PageVo(page, pageSize)));
		
		
		//parameter가 없을때 - 기본값 page = 1, pageSize= 10
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		
		//page와pageSize를 파라미터로 사용해서 PageVo담음
		PageVo pageVo = new PageVo(page, pageSize);
		
		//pageVo를 이용한 사용자 페이징 리스트 조회
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		//request scope에서 사용자 리스트를 공유할수 있도록 속성 설정
		request.setAttribute("userList", userList);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("pageVo", pageVo);
		
		logger.debug("parameter pageList: {}", request.getParameter("pageList"));
		
		//화면 출력을 담당하는 jsp에게 역할위임
		request.getRequestDispatcher("/user/userPagingList.jsp").forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
