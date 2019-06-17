package kr.or.ddit.dbDao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.dao.DbDao;
import kr.or.ddit.dao.IDbDao;
import kr.or.ddit.model.DbVo;

import org.junit.Test;

public class DbDaoTest {
	
	private IDbDao dbDao;

	/**
	* Method : dbListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : urimapping 전체 리스트 조회
	*/
	@Test
	public void getUrimappingList() {
		/***Given***/
		dbDao = new DbDao();

		/***When***/
		List<DbVo> dbList = dbDao.getUrimappingList();

		/***Then***/
		assertNotNull(dbList);
		assertEquals(2, dbList.size());

	}
}
