package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.paging.model.PageVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/lprodPagingList")
public class LprodPagingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(LprodPagingList.class);
	
	private ILprodService lprodService;
	
	@Override
	public void init() throws ServletException {
		lprodService = new LprodService();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//parameter가 없을때 - 기본값 page = 1, pageSize= 10
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		int page = pageString == null? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null? 5 : Integer.parseInt(pageSizeString);
		
		//page와pageSize를 파라미터로 사용해서 PageVo담음
		PageVo pageVo = new PageVo(page, pageSize);
		
		//pageVo를 이용한 사용자 페이징 리스트 조회
		Map<String, Object> resultMap = lprodService.lprodpagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		//request scope에서 사용자 리스트를 공유할수 있도록 속성 설정
		request.setAttribute("lprodList", lprodList);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("pageVo", pageVo);
		
		//화면 출력을 담당하는 jsp에게 역할위임
		request.getRequestDispatcher("/lprod/lprodPagingList.jsp").forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
