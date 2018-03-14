/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.dao.modules.BizPmGuaranteeMoneyLogDao;

/**
 * 质保金日志Service
 * @author 汪文文
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class BizPmGuaranteeMoneyLogService extends CrudService2<BizPmGuaranteeMoneyLogDao, BizPmGuaranteeMoneyLog> {

	public BizPmGuaranteeMoneyLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmGuaranteeMoneyLog> findList(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog) {
		return super.findList(bizPmGuaranteeMoneyLog);
	}
	
	public Page<BizPmGuaranteeMoneyLog> findPage(Page<BizPmGuaranteeMoneyLog> page, BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog) {
		return super.findPage(page, bizPmGuaranteeMoneyLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog) {
		super.save(bizPmGuaranteeMoneyLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog) {
		super.delete(bizPmGuaranteeMoneyLog);
	}

	public BizPmGuaranteeMoneyLog findByEmployeeId(Integer pmEmployeeId) {
		// TODO Auto-generated method stub
		return dao.findByEmployeeId(pmEmployeeId);
	}
	
	@Transactional(readOnly = false)
	public Integer insert1(BizPmGuaranteeMoneyLog gml) {
		// TODO Auto-generated method stub
		return dao.insert1(gml);
	}
	
	public BizPmGuaranteeMoneyLog findByParam(Map<String,Object> param){
		return dao.findByParam(param);
	}
	
}