/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.service.CrudService2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;

/**
 * 结算类目明细Service
 * @author qww
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizPmSettleCategoryDetailService extends CrudService2<BizPmSettleCategoryDetailDao, BizPmSettleCategoryDetail> {

	public BizPmSettleCategoryDetail get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmSettleCategoryDetail> findList(BizPmSettleCategoryDetail bizPmSettleCategoryDetail) {
		return super.findList(bizPmSettleCategoryDetail);
	}
	
	public Page<BizPmSettleCategoryDetail> findPage(Page<BizPmSettleCategoryDetail> page, BizPmSettleCategoryDetail bizPmSettleCategoryDetail) {
		return super.findPage(page, bizPmSettleCategoryDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmSettleCategoryDetail bizPmSettleCategoryDetail) {
		super.save(bizPmSettleCategoryDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmSettleCategoryDetail bizPmSettleCategoryDetail) {
		super.delete(bizPmSettleCategoryDetail);
	}
	
	@Transactional(readOnly = false)
	public void updateStatusByOrderId(Map<String,Object> map) {
		dao.updateStatusByOrderId(map);
	}

	public Double findMoneyByemployeeId(Integer pmEmployeeId, String pmSettleCategory6) {
		// TODO Auto-generated method stub
		return dao.findMoneyByemployeeId(pmEmployeeId,pmSettleCategory6);
	}
	
	@Transactional(readOnly = false)
	public void insert(BizPmSettleCategoryDetail details) {
		// TODO Auto-generated method stub
		dao.insert(details);
	}
	
	public BizPmSettleCategoryDetail querySettleCategoryDetailByPmSettleBillId(Integer pmSettleBillId){
		return dao.querySettleCategoryDetailByPmSettleBillId(pmSettleBillId);
	}
	
	public BizPmSettleCategoryDetail querySettleCategoryDetailByParam(Map<String,Object> param){
		return dao.querySettleCategoryDetailByParam(param);
	}
	
	public List<BizPmSettleCategoryDetail> queryCateGoryAmountByCondition(Map<String,Object> map){
		return dao.queryCateGoryAmountByCondition(map);
	}
	@Transactional(readOnly = false)
	public void updateRelateSummary(Map<String, Object> map){
		dao.updateRelateSummary(map);
	}
	@Transactional(readOnly = false)
	public void updateRelateSummaryCategory(Map<String, Object> map){
		dao.updateRelateSummaryCategory(map);
	}
}