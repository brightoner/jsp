package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.List;

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
		assertEquals(5, userList.size());
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

}
