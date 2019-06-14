package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/userForm")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class UserFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = LoggerFactory
			.getLogger(UserFormController.class);
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	//사용자 등록 화면 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 이동화면으로 이동
		request.getRequestDispatcher("/user/userForm.jsp").forward(request, response);
		
	}

	//사용자 등록 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UserForm doPost");
		
//		request.setCharacterEncoding("utf-8");	
		
		//사용자가 보내 파라미터를 사용해서UserVo인스턴스를 만들어서(파라미터는 문자열(String)으로만 리턴)
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
			userVo = new UserVo(name, userId, alias, pass, addr1, addr2, zipcd, sdf.parse(birth) /*,"",""*/);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//사용자가 입력한 userId 가 이미 존재하는 userId인지 체크
		UserVo dbUser = userService.getUser(userId);
		
		//등록된 사용자가 아닌경우 -- > 정상입력 가능한 상황
		if(dbUser == null){
			
			//profile 파일 업로드 처리
			Part profile = request.getPart("profile");
			
			//샤용자가 파일을 업로드 한 경우
			if(profile.getSize() > 0){
				//실제 파일명
				String contentDisposition = profile.getHeader("content-disposition");
				String filename = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				File uploadFolder = new File(uploadPath);
				if(uploadFolder.exists()){
					
					//파일 디스크에 쓰기
					//		다운받을 경로 + "\\" + random으로 임시 아이디를 만들어준다 + 확장자를 붙여준다
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					userVo.setPath(filePath);
					userVo.setFilename(filename);
					profile.write(filePath);	
					profile.delete();		//임시공간을 사용했다면 지운다
				}
			}
			//(사진을 넣지 않으면)userVo path, filename ==> null;
			
			int insertCnt = userService.insertUser(userVo);
			
			//정상등록이 된경우
			if(insertCnt == 1){
				response.sendRedirect(request.getContextPath()+"/userPagingList");
			}else{
				
			}
			
		//아이디가 중복된경우
		}else{
			//redirect?? forward?? 결정 -- forword(o)
			//request.getRequestDispatcher("/userForm").forward(request, response);		// (x) 경로가 다시 돈다(여기가 /userForm)
			request.setAttribute("msg", "이미 존재하는 사용자 입니다");
			doGet(request, response);    //doFet메소드 다시 호출
		}
		
		//존재하진 않을경우{
		//	- userService객체를 통해서 insertUser(userVo);
		//	  정상적으로 입력된경우
		//	    사용자 페이징 리스트 1페이지로 이동
		//	  정상적으로 입력되지 않은경우
		//	    사용자 등록페이지로 이동, 사용자가 입력한 값을 input에 넣어준다
		// }
		//존재할 경우{
		//	  사용자 등록페이지로 이동, 사용자가 입력한 값을 input에 넣어준다
		//	  이미 존재하는 uerId입니다 (alert 또는 text로 표시)
		// }
		
		
		
	}

}
