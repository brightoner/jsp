package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.model.ProdVo;

public interface IprodService {
	
	List<ProdVo> getProd(String prod_lgu);

}
