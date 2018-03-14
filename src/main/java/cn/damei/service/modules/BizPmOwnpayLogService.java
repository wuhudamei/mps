/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPmOwnpayLog;
import cn.damei.dao.modules.BizPmOwnpayLogDao;

/**
 * 自主支配日志表Service
 * @author wyb
 * @version 2017-02-15
 */
@Service
@Transactional(readOnly = true)
public class BizPmOwnpayLogService extends CrudService2<BizPmOwnpayLogDao, BizPmOwnpayLog> {

	public BizPmOwnpayLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmOwnpayLog> findList(BizPmOwnpayLog bizPmOwnpayLog) {
		return super.findList(bizPmOwnpayLog);
	}
	
	public Page<BizPmOwnpayLog> findPage(Page<BizPmOwnpayLog> page, BizPmOwnpayLog bizPmOwnpayLog) {
		return super.findPage(page, bizPmOwnpayLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmOwnpayLog bizPmOwnpayLog) {
		super.save(bizPmOwnpayLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmOwnpayLog bizPmOwnpayLog) {
		super.delete(bizPmOwnpayLog);
	}
	
	@Transactional(readOnly = false)
	public Integer insert1(BizPmOwnpayLog bizPmOwnpayLog) {
		return dao.insert1(bizPmOwnpayLog);
	}
}