/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizProcedurePrice;
import cn.damei.dao.modules.BizProcedurePriceDao;

/**
 * 工序价格管理Service
 * @author 魏建勇
 * @version 2016-09-03
 */
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