package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.controller.UserPagingList;

public class LprodService implements ILprodService {
	
	private ILprodDao dao = new LprodDao();
	private static LprodService service;
	
	
	/**
	* Method : lprodpagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 페이징 처리에 관해 모두 사용 
	*/
	@Override
	public Map<String, Object> lprodpagingList(PageVo pageVo) {
		
		//1.List<UserVo>, userCnt 를 필드로 하는 vo
		//2.List<Object> resultList = new ArrayList<Object>();   <--주의!!
		// result.add(userList);
		// result.add(userCnt);
		//3.Map<String, Object> resultMap = new HashMap<String, Object>();  <-- 추천!!!!!!!
		//  resuqltMap.put("userList", userList);
		//  resuqltMap.put("userCnt", userCnt);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", dao.lprodPagingList(pageVo));
		
		//lprodCnt 대신에 paginationSize로 변경
		int lprodCnt = dao.lprodCnt();
		//pageSize 대신에 pageVo.getPageSize(); 로 변경
		int paginationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}


	@Override
	public List<LprodVo> lprodList() {
		
		return dao.lprodList();
	}


	
	
	


	

}
