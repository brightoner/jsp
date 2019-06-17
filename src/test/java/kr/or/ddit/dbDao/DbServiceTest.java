package kr.or.ddit.dbDao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.dao.DbDao;
import kr.or.ddit.dao.IDbDao;
import kr.or.ddit.model.DbVo;
import kr.or.ddit.servise.DbService;
import kr.or.ddit.servise.IDbService;

import org.junit.Test;

public class DbServiceTest {
	
	private IDbService dbService;

	/**
	* Method : dbListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : urimapping 전체 리스트 조회
	*/
	@Test
	public void getUrimappingList() {
		/***Given***/
		dbService = new DbService();

		/***When***/
		List<DbVo> dbList = dbService.getUrimappingList();

		/***Then***/
		assertNotNull(dbList);
		assertEquals(2, dbList.size());

	}
}
