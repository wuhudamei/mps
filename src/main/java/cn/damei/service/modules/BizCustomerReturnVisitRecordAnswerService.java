/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizCustomerReturnVisitRecordAnswerDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;

/**
 * 客户回访答案Service
 * @author lft
 * @version 2017-05-26
 */
@Service
@Transactional(readOnly = true)
public class BizCustomerReturnVisitRecordAnswerService extends CrudService2<BizCustomerReturnVisitRecordAnswerDao, BizCustomerReturnVisitRecordAnswer> {

	public BizCustomerReturnVisitRecordAnswer get(Integer id) {
		return super.get(id);
	}
	
	public List<BizCustomerReturnVisitRecordAnswer> findList(BizCustomerReturnVisitRecordAnswer bizCustomerReturnVisitRecordAnswer) {
		return super.findList(bizCustomerReturnVisitRecordAnswer);
	}
	
	public Page<BizCustomerReturnVisitRecordAnswer> findPage(Page<BizCustomerReturnVisitRecordAnswer> page, BizCustomerReturnVisitRecordAnswer bizCustomerReturnVisitRecordAnswer) {
		return super.findPage(page, bizCustomerReturnVisitRecordAnswer);
	}
	
	@Transactional(readOnly = false)
	public void save(BizCustomerReturnVisitRecordAnswer bizCustomerReturnVisitRecordAnswer) {
		super.save(bizCustomerReturnVisitRecordAnswer);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizCustomerReturnVisitRecordAnswer bizCustomerReturnVisitRecordAnswer) {
		super.delete(bizCustomerReturnVisitRecordAnswer);
	}
	//根据回访记录id 查询 问题和答案
	public List<BizCustomerReturnVisitRecordAnswer> getListByRecordId(Integer returnVisitRecordId){
		return dao.getListByRecordId(returnVisitRecordId);
	}

	//根据回访记录id 查询 问题和答案
	public List<BizCustomerReturnVisitRecordAnswer> getListByRecordIds(List<Integer> list){
		return dao.getListByRecordIds(list);
	}
}