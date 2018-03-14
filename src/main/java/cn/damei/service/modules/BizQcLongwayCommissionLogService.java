/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.dao.modules.BizQcLongwayCommissionLogDao;

/**
 * 质检员远程费记录Service
 * @author 汪文文
 * @version 2017-02-13
 */
@Service
@Transactional(readOnly = true)
public class BizQcLongwayCommissionLogService extends CrudService2<BizQcLongwayCommissionLogDao, BizQcLongwayCommissionLog> {

	public BizQcLongwayCommissionLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcLongwayCommissionLog> findList(BizQcLongwayCommissionLog bizQcLongwayCommissionLog) {
		return super.findList(bizQcLongwayCommissionLog);
	}
	
	public Page<BizQcLongwayCommissionLog> findPage(Page<BizQcLongwayCommissionLog> page, BizQcLongwayCommissionLog bizQcLongwayCommissionLog) {
		return super.findPage(page, bizQcLongwayCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcLongwayCommissionLog bizQcLongwayCommissionLog) {
		super.save(bizQcLongwayCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcLongwayCommissionLog bizQcLongwayCommissionLog) {
		super.delete(bizQcLongwayCommissionLog);
	}
	
	@Transactional(readOnly = false)
	public Integer insert1(BizQcLongwayCommissionLog bizQcLongwayCommissionLog) {
		// TODO Auto-generated method stub
		return dao.insert1(bizQcLongwayCommissionLog);
	}
	
	public BizQcLongwayCommissionLog queryCommissionLogByParam(BizQcLongwayCommissionLog bizQcLongwayCommissionLog){
		return dao.queryCommissionLogByParam(bizQcLongwayCommissionLog);
	}
	
}