/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.InstallPlanQuery;
import cn.damei.dao.modules.InstallPlanQueryDao;

/**
 * 安装项计划查询Service
 * @author 梅浩
 * @version 2017-02-06
 */
@Service
@Transactional(readOnly = true)
public class InstallPlanQueryService extends CrudService<InstallPlanQueryDao, InstallPlanQuery> {

	public InstallPlanQuery get(String id) {
		return super.get(id);
	}
	
	public List<InstallPlanQuery> findList(InstallPlanQuery installPlanQuery) {
		return super.findList(installPlanQuery);
	}
	
	public Page<InstallPlanQuery> findPage(Page<InstallPlanQuery> page, InstallPlanQuery installPlanQuery) {
		return super.findPage(page, installPlanQuery);
	}
	
	@Transactional(readOnly = false)
	public void save(InstallPlanQuery installPlanQuery) {
		super.save(installPlanQuery);
	}
	
	@Transactional(readOnly = false)
	public void delete(InstallPlanQuery installPlanQuery) {
		super.delete(installPlanQuery);
	}
	
	public List<InstallPlanQuery> findDetailByOrderId(Integer orderId){
		
		return dao.findDetailByOrderId(orderId);
	}
	
}