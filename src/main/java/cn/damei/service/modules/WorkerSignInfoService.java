/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.WorkerSignInfoDao;
import cn.damei.entity.modules.WorkerSign;

/**
 * 工人签到查询Service
 * @author 梅浩
 * @version 2016-09-26
 */
@Service
@Transactional(readOnly = true)
public class WorkerSignInfoService extends CrudService2<WorkerSignInfoDao, WorkerSign> {

	
	public WorkerSign get(Integer id) {
		WorkerSign WorkerSign = super.get(id);
		return WorkerSign;
	}
	
	public List<WorkerSign> findList(WorkerSign WorkerSign) {
		return super.findList(WorkerSign);
	}
	
	public Page<WorkerSign> findPage(Page<WorkerSign> page, WorkerSign WorkerSign) {
		return super.findPage(page, WorkerSign);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkerSign WorkerSign) {
		super.save(WorkerSign);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkerSign WorkerSign) {
		super.delete(WorkerSign);
	}
}