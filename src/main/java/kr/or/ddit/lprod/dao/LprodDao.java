package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;

public class LprodDao implements ILprodDao {

	/**
	* Method : lprodPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	*/
	@Override
	public List<LprodVo> lprodPagingList(PageVo pageVo) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<LprodVo> lprodList = sqlsession.selectList("lprod.lprodPagingList",pageVo);
		sqlsession.close();
		return lprodList;
	}

	/**
	* Method : lprodCnt
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체수 조회
	*/
	@Override
	public int lprodCnt() {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		int lprodCnt = sqlsession.selectOne("lprod.lprodCnt");
		sqlsession.close();
		return lprodCnt;
	}

	@Override
	public List<LprodVo> lprodList() {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<LprodVo> lprodList = sqlsession.selectList("lprod.lprodList");
		return lprodList;
	}

}
