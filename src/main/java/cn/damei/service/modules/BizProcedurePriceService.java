
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizProcedurePrice;
import cn.damei.dao.modules.BizProcedurePriceDao;


@Service
@Transactional(readOnly = true)
public class BizProcedurePriceService extends CrudService<BizProcedurePriceDao, BizProcedurePrice> {

	
	public BizProcedurePrice get(String id) {
		BizProcedurePrice bizProcedurePrice = super.get(id);
		return bizProcedurePrice;
	}
	
	public List<BizProcedurePrice> findList(BizProcedurePrice bizProcedurePrice) {
		return super.findList(bizProcedurePrice);
	}
	
	public Page<BizProcedurePrice> findPage(Page<BizProcedurePrice> page, BizProcedurePrice bizProcedurePrice) {
		return super.findPage(page, bizProcedurePrice);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProcedurePrice bizProcedurePrice) {
		super.save(bizProcedurePrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProcedurePrice bizProcedurePrice) {
		super.delete(bizProcedurePrice);
	}
	
}