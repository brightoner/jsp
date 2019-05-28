package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
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
		String userId = "brown";

		/***When***/
		List<UserVo> userList = userDao.userList();
		UserVo userVo = userDao.getUser(userId);

		
		/***Then***/
		logger.debug("userList :{}", userList);
		logger.debug("userVo :{}",userVo);

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
		sqlsession.close();
		return userList;
	}


	/**
	* Method : getUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	*/
	@Override
	public UserVo getUser(String userId) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		UserVo userVo = sqlsession.selectOne("user.getUser", userId);
		sqlsession.close();
		return userVo;
	}


	/**
	* Method : userPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public List<UserVo> userPagingList(PageVo pageVo) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<UserVo> userList = sqlsession.selectList("user.userPagingList", pageVo);
		sqlsession.close();
		return userList;
	}


	/**
	* Method : usersCnt
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	*/
	@Override
	public int usersCnt() {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		int usersCnt = (Integer)sqlsession.selectOne("user.usersCnt");
		sqlsession.close();
		return usersCnt;
	}
	
	
	
	

}
