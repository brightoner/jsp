package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.user.model.UserVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao implements IUserDao{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDao.class);

	public static void main(String[] args) {

		/***Given***/
		IUserDao userDao = new UserDao();

		/***When***/
		List<UserVo> userList = userDao.userList();
		
		/***Then***/
		logger.debug("userList :{}", userList);

		
	}

	
	/**
	* Method : userList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	*/
	@Override
	public List<UserVo> userList() {
		
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<UserVo> userList = sqlsession.selectList("user.userList");
		
		return userList;
	}

}
