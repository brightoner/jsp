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

	
	/**
	* Method : insertUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVo userVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("user.insertUser", userVo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}


	/**
	* Method : deleteUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		int deleteCnt= sqlsession.delete("user.deleteUser", userId);
		sqlsession.commit();	//기본값이 commit (없어도 commit이된다 - test모드 실행하면 commit되므로 지워줘야된다)
		sqlsession.close();
		return deleteCnt;
	}



	/**
	* Method : updateDataUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	@Override
	public int updateDataUser(UserVo userVo) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		int updateDataCnt = sqlsession.update("user.updateDataUser", userVo);
		sqlsession.commit();
		sqlsession.close();
		return updateDataCnt;
	}


	/**
	* Method : userListForPassEncypt
	* 작성자 : PC22
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회
	*/
	@Override
	public List<UserVo> userListForPassEncypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncypt");
	}


	/**
	* Method : updateUserEncyptPass
	* 작성자 : PC22
	* 변경이력 :
	* @param sqlSession
	* @param userVo
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	@Override
	public int updateUserEncyptPass(SqlSession sqlSession, UserVo userVo) {
		return sqlSession.update("user.updateUserEncyptPass", userVo);
	}
	
	
	
	

}












