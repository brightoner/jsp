package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);
	
	private IUserDao userDao;
	
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
		userDao = new UserDao();
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
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userDao.userList();

		/***Then***/
		assertEquals("brown", userList.get(0).getUserId());
		assertEquals(105, userList.size()); 	// (첫번째 는 기대값(예상값), size())
		logger.debug("userList : {}", userList);

	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 조회테스트
	*/
	@Test
	public void getUserTest(){
		
		/***Given***/
		
		String userId = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운", userVo.getName());
		logger.debug("userVo : {}", userVo);

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
	public void userPagingListTest(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		List<UserVo> userList = userDao.userPagingList(pageVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
		

	}
	
	/**
	* Method : usersCntTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 전체수 조회 테스트
	*/
	@Test
	public void usersCntTest(){
		/***Given***/
		

		/***When***/
		int usersCnt = userDao.usersCnt();

		/***Then***/
		assertEquals(105, usersCnt);
	}
	
	/**
	* Method : insertUserTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void insertUserTest(){
		/***Given***/
		//사용자 정보를 담고있는 vo객체 준비
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("대덕인", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		//userDao.insertUser()
		int insertCnt = userDao.insertUser(userVo);
				
		/***Then***/
		//insertCnt(1)
		assertEquals(1, insertCnt);
		
		//data 삭제
		//userDao.deleteUser(userId);
		userDao.deleteUser(userVo.getUserId());

	}
	
	@Test
	public void updataDataUserTest(){
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("더덕인", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		/***When***/
		int updateDataUser = userDao.updateDataUser(userVo);

		/***Then***/
		assertEquals(1, updateDataUser);
	}
	
	
	
	

}







