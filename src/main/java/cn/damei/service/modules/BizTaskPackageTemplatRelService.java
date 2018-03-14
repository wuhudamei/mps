/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizTaskPackageTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageTemplatRel;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2016-09-03
 */
@Service
@Transactional(readOnly = true)
public class BizTaskPackageTemplatRelService extends CrudService<BizTaskPackageTemplatRelDao, BizTaskPackageTemplatRel> {

	@Autowired
	BizTaskPackageTemplatRelDao bizTaskPackageTemplatRelDao;
	
	public BizTaskPackageTemplatRel get(String id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageTemplatRel> findList(BizTaskPackageTemplatRel bizTaskPackageTemplatRel) {
		return super.findList(bizTaskPackageTemplatRel);
	}
	
	public Page<BizTaskPackageTemplatRel> findPage(Page<BizTaskPackageTemplatRel> page, BizTaskPackageTemplatRel bizTaskPackageTemplatRel) {
		return super.findPage(page, bizTaskPackageTemplatRel);
	}

	@Transactional(readOnly = false)
	public void delete(BizTaskPackageTemplatRel bizTaskPackageTemplatRel) {
		super.delete(bizTaskPackageTemplatRel);
	}

	public List<BizTaskPackageTemplatRel> getByProcedureNo(String taskPackageTemplatId, List<String> list) {
		// TODO Auto-generated method stub
		return bizTaskPackageTemplatRelDao.getByProcedureNo(taskPackageTemplatId, list);
	}
	
}