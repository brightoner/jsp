package kr.or.ddit.servise;

import java.util.List;

import kr.or.ddit.model.DbVo;

public interface IDbService {
	
	/**
	* Method : DbList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : urimapping 전체 리스트 조회
	*/
	List<DbVo> getUrimappingList();

}
