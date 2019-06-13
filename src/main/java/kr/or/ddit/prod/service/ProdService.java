package kr.or.ddit.prod.service;



import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.prod.doa.ProdDao;
import kr.or.ddit.prod.model.ProdVo;

public class ProdService implements IprodService {
	
	private ProdDao dao = new ProdDao();
	private static ProdService service;

	@Override
	public List<ProdVo> getProd(String prod_lgu) {
		return dao.getProd(prod_lgu);
	}
	
	

}
