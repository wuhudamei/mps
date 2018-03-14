/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.service.CrudService2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;

/**
 * 结算类目汇总Service
 * @author qww
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizPmSettleCategorySummaryService extends CrudService2<BizPmSettleCategorySummaryDao, BizPmSettleCategorySummary> {

	public BizPmSettleCategorySummary get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmSettleCategorySummary> findList(BizPmSettleCategorySummary bizPmSettleCategorySummary) {
		return super.findList(bizPmSettleCategorySummary);
	}
	
	public Page<BizPmSettleCategorySummary> findPage(Page<BizPmSettleCategorySummary> page, BizPmSettleCategorySummary bizPmSettleCategorySummary) {
		return super.findPage(page, bizPmSettleCategorySummary);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmSettleCategorySummary bizPmSettleCategorySummary) {
		super.save(bizPmSettleCategorySummary);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmSettleCategorySummary bizPmSettleCategorySummary) {
		super.delete(bizPmSettleCategorySummary);
	}
	
	public BizPmSettleCategorySummary queryCateGrrySummaryByParam(Map<String,Object> param){
		return dao.queryCateGrrySummaryByParam(param);
	}
	
	@Transactional(readOnly = false)
	public void insertBatch(List<BizPmSettleCategorySummary> list){
		 dao.insertBatch(list);
	}
	
	public List<BizPmSettleBill> queryCateGorySummaryLastByCondition(Map<String,Object> map){
		return dao.queryCateGorySummaryLastByCondition(map);
	}
	@Transactional(readOnly = false)
	public void updateRelate(Map<String,Object> map){
		dao.updateRelate(map);
	}
	
}