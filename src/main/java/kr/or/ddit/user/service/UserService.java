package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVo;

public class UserService implements IUserService{

	private UserDao dao = new UserDao();
	private static UserService service;
	

	
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
	

	
	
	
	

}
