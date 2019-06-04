package kr.or.ddit.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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


@WebServlet("/fileUpload")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadServlet.class);
       
	private IUserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//userId, profile 파라미터 확인
		String userId = request.getParameter("userId");
		String profile = request.getParameter("profile");
		logger.debug("userId :{}", userId);
		logger.debug("profile :{}", profile);
		
		UserVo userVo = new UserVo();
		
		Part part = request.getPart("profile"); 	//jsp와 맞출 parameter
		logger.debug("part.getSize() : {}", part.getSize());
		
		//파일이 존재할때만 파일을 정해진 위치에 기록한다
		if(part.getSize() > 0 ){
			
			logger.debug("part.getContentType() : {}", part.getContentType());
			logger.debug("part.getName() : {}", part.getName());
			
			String contentDisposition = part.getHeader("content-disposition");
			//split을해서 파일이름을 추출
			String fileName = PartUtil.getFileName(contentDisposition);	
			//파일명으로 부터 파일 확장자를 반환
			String ext = PartUtil.getExt(fileName);
			
//			//파일을 받는폴더
//			//년도에 해당하는 폴더가 있는지, 년도 안에 월에 해당하는 폴더가 있는지
//			Date dt = new Date();
//			//방법1
////			SimpleDateFormat yyyySdf = new SimpleDateFormat("yyyy");
////			SimpleDateFormat mmSdf = new SimpleDateFormat("MM");
////			String yyyy = yyyySdf.format(dt);
////			String mm = mmSdf.format(dt);
//			
//			//방법2
//			SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
//			String yyyyMM = yyyyMMSdf.format(dt);
//			String yyyy = yyyyMM.substring(0,4);
//			String mm = yyyyMM.substring(4,6);
			
			String uploadPath = PartUtil.getUploadPath();
			File uploadFolder = new File(uploadPath);
			if(uploadFolder.exists()){
				
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(fileName);
				userVo.setFilename(fileName);
				//파일 디스크에 쓰기
				//		다운받을 경로 + "\\" + random으로 임시 아이디를 만들어준다 + 확장자를 붙여준다
				part.write(uploadPath + File.separator + UUID.randomUUID().toString() + ext);	
				part.delete();		//임시공간을 사용했다면 지운다
			}
			
		}
	}
}








