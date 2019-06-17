package kr.or.ddit.servise;

import java.util.List;

import kr.or.ddit.dao.DbDao;
import kr.or.ddit.dao.IDbDao;
import kr.or.ddit.model.DbVo;

public class DbService implements IDbService {
	
	private IDbDao dao = new DbDao();

	/**
	* Method : DbList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : urimapping 전체 리스트 조회
	*/
	@Override
	public List<DbVo> getUrimappingList() {
		return dao.getUrimappingList();
	}

}
