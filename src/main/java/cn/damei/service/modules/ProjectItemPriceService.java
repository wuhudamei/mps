
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.dao.modules.ProjectItemPriceDao;


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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


		return dao.checkedDate(projectItemPrice);
	}
}