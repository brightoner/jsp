package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LprodDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LprodDaoTest.class);
	
	private ILprodDao lprodDao;
	
	//junit 실행순서
	//@BeforeClass가 적용된  메소드가 최소 1회 실행
	
	//다음구간은 @Test가 적용된 모든 메소드에 대해 반복 적용
	//@Before가 적용된 메서드 실행
	//@Test가 적용된 메소드가 실행
	//@After가 적용된 메소드가 실행
	
	//@AfterClass가 적용된 메소드가 1회실행
	
	
	//before, after을 많이 씀 (왜냐면 static이 안붙기 때문에)
	@BeforeClass
	public static void beforeClass(){
		logger.debug("beforeClass");
	}
	
	@Before
	public void setup(){
		lprodDao = new LprodDao();
		logger.debug("setup");
	}
	
	@After
	public void teardown(){
		logger.debug("teardown");
	}
	
	@AfterClass
	public static void afterClass(){
		logger.debug("afterClass");
	}
	
	
	
	

	/**
	* Method : userListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 전체조회 테스트 
	*/
	@Test
	public void lprodListTest() {
		/***Given***/
		

		/***When***/
		List<LprodVo> lprodList = lprodDao.lprodList();

		/***Then***/
		//assertEquals("brown", lprodList.get(0).getLprod_id());
		assertEquals(13, lprodList.size()); 	// (첫번째 는 기대값(예상값), size())
		logger.debug("lprodList : {}", lprodList);

	}
	
	
	
	//사용자 페이징 리스트 조회
	//고려사항
	//몇번째 페이지 조회인지?? 페이징 몇건씩 데이터를 보여줄건지?? : 쿼리실행 파라미터
	//정렬순서?? : 로직 --> 파라미터화 시킬수 있다
	// --> 사용자 아이디순으로 정렬
	
	/**
	* Method : userPagingListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	*/
	@Test
	public void lprodPagingListTest(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		
		/***When***/
		List<LprodVo> lprodList = lprodDao.lprodPagingList(pageVo);

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(5, lprodList.size());
		

	}
	
	/**
	* Method : usersCntTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 전체수 조회 테스트
	*/
	@Test
	public void lprodCntTest(){
		/***Given***/
		

		/***When***/
		int lprodCnt = lprodDao.lprodCnt();

		/***Then***/
		assertEquals(13, lprodCnt);
	}
	

}







