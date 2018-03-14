/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizQcMaxCount;
import cn.damei.dao.modules.BizQcMaxCountDao;

/**
 * 约检数量配置Service
 * @author 梅浩
 * @version 2017-04-20
 */
@Service
@Transactional(readOnly = true)
public class BizQcMaxCountService extends CrudService<BizQcMaxCountDao, BizQcMaxCount> {

	public BizQcMaxCount get(String id) {
		return super.get(id);
	}
	
	public List<BizQcMaxCount> findList(BizQcMaxCount bizQcMaxCount) {
		return super.findList(bizQcMaxCount);
	}
	
	public Page<BizQcMaxCount> findPage(Page<BizQcMaxCount> page, BizQcMaxCount bizQcMaxCount) {
		return super.findPage(page, bizQcMaxCount);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcMaxCount bizQcMaxCount) {
		super.save(bizQcMaxCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcMaxCount bizQcMaxCount) {
		super.delete(bizQcMaxCount);
	}
	public Integer storeOnlyForPqcCount(Integer storeId){


		return dao.storeOnlyForPqcCount(storeId);
	}
}