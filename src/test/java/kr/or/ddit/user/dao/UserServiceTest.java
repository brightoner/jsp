package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceTest.class);
	
	private IUserService userService;
	
	
	@Before
	public void setup(){
		
		userService = new UserService();
		logger.debug("setup");
	}

	
	@Test
	public void userListTest(){
		
		/***Given***/

		/***When***/
		List<UserVo> userList = userService.userList();

		/***Then***/
		assertEquals("brown", userList.get(0).getUserId());
		assertEquals(105, userList.size());
		logger.debug("userList : {}", userList);
	}
	
	
	
	@Test
	public void getUserTest() {
		
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVo userVo = userService.getUser(userId);

		/***Then***/
//		assertEquals("브라운", userVo.getName());
		assertNotNull(userVo);		//null인지 null이 아닌지 테스트
		
		
		//---------------------------------------------------------------------------------
		//테스트를 먼저 만들고 코딩할때 고려사항(service, dao 없이 테스트먼저하면서 만들때 
		//			--> 테스트 코드 먼저치고 ctrl + space bar를 치면 서비스 인터테이스 바로 만들수있다)
		//사용자 전체 리스트 조회
		//1.메소드 인자가 피요한가?? : 필요X
		//2.리턴타입??	: List<UserVo>
		//3.메소드 이름?? : userList
		
		
	}
	
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
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		
		//pageingList assert
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		//paginationSize assert
		assertEquals(11, paginationSize);
		
	}
		
		@Test
		public void ceilTest(){
			/***Given***/
			int usersCnt = 105;
			int pageSize = 10;

			/***When***/
			double paginationSize = Math.ceil((double)usersCnt/pageSize);
			logger.debug("paginationSize : {}", paginationSize);

			/***Then***/
			assertEquals(11, (int)paginationSize);

			
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			UserVo userVo = null;
			
			try {
				userVo = new UserVo("대덕인", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로76",
											"영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			/***When***/
			int insertCnt = userService.insertUser(userVo);
			
			/***Then***/
			assertEquals(1, insertCnt);
			
			//data 삭제
			//userDao.deleteUser(userId);
			userService.deleteUser(userVo.getUserId());

		}
		
	
		
		
		


}
