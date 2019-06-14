package kr.or.ddit.prod.doa;

import java.util.List;

import kr.or.ddit.prod.model.ProdVo;

public interface IprodDao {

	List<ProdVo> prodList(String prod_lgu);
	
}
