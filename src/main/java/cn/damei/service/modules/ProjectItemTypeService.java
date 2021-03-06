
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.ProjectItemType;
import cn.damei.dao.modules.ProjectItemTypeDao;


@Service
@Transactional(readOnly = true)
public class ProjectItemTypeService extends CrudService<ProjectItemTypeDao, ProjectItemType> {

	public ProjectItemType get(String id) {
		return super.get(id);
	}
	
	public List<ProjectItemType> findList(ProjectItemType projectItemType) {
		return super.findList(projectItemType);
	}
	
	public Page<ProjectItemType> findPage(Page<ProjectItemType> page, ProjectItemType projectItemType) {
		return super.findPage(page, projectItemType);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectItemType projectItemType) {
		super.save(projectItemType);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectItemType projectItemType) {
		super.delete(projectItemType);
	}
	
}