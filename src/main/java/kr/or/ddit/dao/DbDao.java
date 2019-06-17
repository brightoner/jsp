package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.model.DbVo;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class DbDao implements IDbDao {

	/**
	* Method : DbList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : urimapping 전체 리스트 조회
	*/
	@Override
	public List<DbVo> getUrimappingList() {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<DbVo> DbList = sqlsession.selectList("db.getUrimappingList");
		sqlsession.close();
		return DbList;
	}

}
