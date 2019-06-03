package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.PartUtil;


@WebServlet("/userModify")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
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
		
		try {
			userVo = new UserVo(name, userId, alias, pass, addr1, addr2, zipcd,sdf.parse(birth)/*,"",""*/);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserVo userVo1 = userService.getUser(userId);
		request.setAttribute("userVo", userVo1);
		
		
		if(userVo1 != null ){
			
			Part profile = request.getPart("profile");
			
			
			if(profile.getSize() >0){
				
				String contentDisposition = profile.getHeader("content-disposition");
				String filename = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				File uploadFolder = new File(uploadPath);
			
				
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					userVo.setPath(filePath);
					userVo.setFilename(filename);
					profile.write(filePath);
					profile.delete();
					
					
				}
				
				int updateDataCnt = userService.updateDataUser(userVo);
				
				if(updateDataCnt == 1){
					response.sendRedirect(request.getContextPath()+"/userPagingList");
				}else{
					
				}
			}else{
				doGet(request, response);
			}
		}
		
		
		
	
	}

}
