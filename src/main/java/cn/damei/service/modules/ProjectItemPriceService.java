/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.dao.modules.ProjectItemPriceDao;

/**
 * 施工项价格Service
 * @author 梅浩
 * @version 2016-11-16
 */
@Service
@Transactional(readOnly = true)
public class ProjectItemPriceService extends CrudService<ProjectItemPriceDao, ProjectItemPrice> {

	public ProjectItemPrice get(String id) {
		return super.get(id);
	}
	
	public List<ProjectItemPrice> findList(ProjectItemPrice projectItemPrice) {
		return super.findList(projectItemPrice);
	}
	
	public Page<ProjectItemPrice> findPage(Page<ProjectItemPrice> page, ProjectItemPrice projectItemPrice) {
		return super.findPage(page, projectItemPrice);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectItemPrice projectItemPrice) {
		super.save(projectItemPrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectItemPrice projectItemPrice) {
		super.delete(projectItemPrice);
	}
	
	public Integer   setMaxVersion(ProjectItemPrice price){
		return dao.setMaxVersion(price);
	}

	public boolean checkedData(ProjectItemPrice projectItemPrice) {
//		检验生效日期是否重复
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/*	sdf.format()
*/

		return dao.checkedDate(projectItemPrice);
	}
}