/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizProcedure;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.OrderTaskpackVo;
import cn.damei.dao.modules.BizProcedureDao;

/**
 * 工序管理Service
 * @author 魏建勇
 * @version 2016-09-03
 */
@Service
@Transactional(readOnly = true)
public class BizProcedureService extends CrudService<BizProcedureDao, BizProcedure> {

	@Autowired
	private BizProcedureDao bizProcedureDao;
	
	public BizProcedure get(String id) {
		return super.get(id);
	}
	
	public List<BizProcedure> findList(BizProcedure bizProcedure) {
		return super.findList(bizProcedure);
	}
	
	public Page<BizProcedure> findPage(Page<BizProcedure> page, BizProcedure bizProcedure) {
		return super.findPage(page, bizProcedure);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProcedure bizProcedure) {
		super.save(bizProcedure);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProcedure bizProcedure) {
		super.delete(bizProcedure);
	}

	public List<BizProcedure> getByBatchProcedureNo(List<String> list) {
		// TODO Auto-generated method stub
		return bizProcedureDao.getByBatchProcedureNo(list);
	}

	public List<DropModel> findAllProcedure() {
		
		return bizProcedureDao.findAllProcedure();
	}

	public OrderTaskpackVo findProcedureById(String procedureId, String storeId,String projectMode, Date contractStartDate) {
		
		return bizProcedureDao.findProcedureById(procedureId,storeId,contractStartDate,projectMode);
	}

	public OrderTaskpackGenVo findTaskpackageProcedureById(String procedureId, String storeId, Date contractStartDate) {
		
		return bizProcedureDao.findTaskpackageProcedureById(procedureId,storeId,contractStartDate);
	}
	
}