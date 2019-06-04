package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVo;

public class UserService implements IUserService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);

	private IUserDao dao = new UserDao();
//	private static UserService service;
	
	/**
	* Method : userList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVo> userList() {
		return dao.userList();
	}

	/**
	* Method : getUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVo getUser(String userId) {
		return dao.getUser(userId);
	}

	/**
	* Method : userPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 페이징 처리에 관한것 모두 사용
	*/
	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		//1.List<UserVo>, userCnt 를 필드로 하는 vo
		//2.List<Object> resultList = new ArrayList<Object>();   <--주의!!
		// result.add(userList);
		// result.add(userCnt);
		//3.Map<String, Object> resultMap = new HashMap<String, Object>();  <-- 추천!!!!!!!
		//  resuqltMap.put("userList", userList);
		//  resuqltMap.put("userCnt", userCnt);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", dao.userPagingList(pageVo));
		
		//usersCnt 대신에 paginationSize로 변경
		int usersCnt = dao.usersCnt();
		//pageSize 대신에 pageVo.getPageSize(); 로 변경
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	
	/**
	* Method : insertUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVo userVo) {
		return dao.insertUser(userVo);
	}

	/**
	* Method : deleteUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		return dao.deleteUser(userId);
	}

	/**
	* Method : updateDataUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	@Override
	public int updateDataUser(UserVo userVo) {
		return dao.updateDataUser(userVo);
	}

	/**
	* Method : encyptPassAllUser
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 이괄적용 패치
	* 			  : 재실행 하지말것!!!! 암호화는 1번만 해야하므로
	*/
	@Override
	public int encyptPassAllUser() {
		//재실행 방지용!!!!
		//사용하지말것!!!!!!!! 이미 암호화 적용되었음
		if(1 == 1)
			return 0;
		
		//0.sql실행에 필료한 sqlSession객체 생성
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		//1.모든 사용자 정보를 조회(단, 기존 암호화 적용 사용자 제외(brown, korea))
		List<UserVo> userList = dao.userListForPassEncypt(sqlSession);
		
		//2.조회된 사용자의 비밀번호를 암호화적용후 사용자 업데이트
		int updateCntSum = 0;
		for(UserVo userVo : userList){
			String encyptPass =  KISA_SHA256.encrypt(userVo.getPass());
			userVo.setPass(encyptPass);
			
			//update실행건수
			 int updateCnt = dao.updateUserEncyptPass(sqlSession, userVo);
			 updateCntSum += updateCnt;
			 
			 //비정상일경우
			 if(updateCnt !=1){
				 sqlSession.rollback();
				 break;
			 }
		}
		
		//3. sqlSession 객체를 commit, close
		sqlSession.commit();
		sqlSession.close();
		
		return updateCntSum;
	}
	
	public static void main(String[] args) {
		IUserService userService = new UserService();
		int updateCnt = userService.encyptPassAllUser();
		logger.debug("updateCnt : {}", updateCnt);
		
	}
	

	
	
	
	

}
