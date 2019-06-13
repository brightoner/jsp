package kr.or.ddit.prod.doa;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.prod.model.ProdVo;

public class ProdDao implements IprodDoo {

	@Override
	public List<ProdVo> getProd(String prod_lgu) {
		SqlSession sqlsession = MyBatisUtil.getSqlSession();
		List<ProdVo> prodList = sqlsession.selectList("lprod.prodList",prod_lgu);
		sqlsession.close();
		return prodList;
	}

	

}
