package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.List;

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
	//@Bㄷfore가 적용된 메서드 실행
	//@Test가 저용된 메소드가 실행
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
		assertEquals(5, userList.size()); 	// (첫번째 는 기대값(예상값), size())
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
	

}







