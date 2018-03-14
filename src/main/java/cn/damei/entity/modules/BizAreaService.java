/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizArea;
import cn.damei.dao.modules.BizAreaDao;

/**
 * 区域管理Service
 * @author qhy
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class BizAreaService extends CrudService<BizAreaDao, BizArea> {

	public BizArea get(String id) {
		return super.get(id);
	}
	
	public List<BizArea> findList(BizArea bizArea) {
		return super.findList(bizArea);
	}
	
	public Page<BizArea> findPage(Page<BizArea> page, BizArea bizArea) {
		return super.findPage(page, bizArea);
	}
	
	@Transactional(readOnly = false)
	public void save(BizArea bizArea) {
		super.save(bizArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizArea bizArea) {
		super.delete(bizArea);
	}
	
}