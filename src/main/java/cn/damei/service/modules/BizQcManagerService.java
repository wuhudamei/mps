/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcManager;
import cn.damei.dao.modules.BizQcManagerDao;

/**
 * 质检管理人员Service
 * @author wyb
 * @version 2016-11-02
 */
@Service
@Transactional(readOnly = true)
public class BizQcManagerService extends CrudService2<BizQcManagerDao, BizQcManager> {

	public BizQcManager get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcManager> findList(BizQcManager bizQcManager) {
		return super.findList(bizQcManager);
	}
	
	public Page<BizQcManager> findPage(Page<BizQcManager> page, BizQcManager bizQcManager) {
		return super.findPage(page, bizQcManager);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcManager bizQcManager) {
		super.save(bizQcManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcManager bizQcManager) {
		super.delete(bizQcManager);
	}

	//通过人员id查询门店
	public Integer findStore(Integer managerEmployeeId) {
		return dao.findStore(managerEmployeeId);
	}
	
}