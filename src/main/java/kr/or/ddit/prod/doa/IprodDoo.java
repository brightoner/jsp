package kr.or.ddit.prod.doa;

import java.util.List;

import kr.or.ddit.prod.model.ProdVo;

public interface IprodDoo {

	List<ProdVo> getProd(String prod_lgu);
	
}
