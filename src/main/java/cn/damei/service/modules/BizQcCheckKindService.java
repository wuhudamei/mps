/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcCheckKind;
import cn.damei.dao.modules.BizQcCheckKindDao;

/**
 * 检查分类Service
 * @author wyb
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class BizQcCheckKindService extends CrudService2<BizQcCheckKindDao, BizQcCheckKind> {

	public BizQcCheckKind get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcCheckKind> findList(BizQcCheckKind bizQcCheckKind) {
		return super.findList(bizQcCheckKind);
	}
	
	public Page<BizQcCheckKind> findPage(Page<BizQcCheckKind> page, BizQcCheckKind bizQcCheckKind) {
		return super.findPage(page, bizQcCheckKind);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcCheckKind bizQcCheckKind) {
		if(StringUtils.isEmpty(bizQcCheckKind.getTaskPackageTemplatId())){
			bizQcCheckKind.setTaskPackageTemplatId(null);
		}
		super.save(bizQcCheckKind);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcCheckKind bizQcCheckKind) {
		super.delete(bizQcCheckKind);
	}


}