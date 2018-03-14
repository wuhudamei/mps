
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ProjectBulletinDao;
import cn.damei.entity.modules.ProjectBulletin;


@Service
@Transactional(readOnly = true)
public class ProjectBulletinService extends CrudService2<ProjectBulletinDao, ProjectBulletin> {

	@Autowired
	private ProjectBulletinDao projectBulletinDao;
	
	public ProjectBulletin get(Integer id) {
		return super.get(id);
	}
	
	public List<ProjectBulletin> findList(ProjectBulletin projectBulletin) {
		return super.findList(projectBulletin);
	}
	
	public Page<ProjectBulletin> findPage(Page<ProjectBulletin> page, ProjectBulletin projectBulletin) {
		return super.findPage(page, projectBulletin);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectBulletin projectBulletin) {
		super.save(projectBulletin);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectBulletin projectBulletin) {
		super.delete(projectBulletin);
	}


	public List<ProjectBulletin> getByIdAndNodePlanOrderId(Integer id) {
		return projectBulletinDao.getByIdAndNodePlanOrderId(id);
	}


	public List<ProjectBulletin> getByShowViewOrderId(Integer orderId) {
		return projectBulletinDao.getByShowViewOrderId(orderId);
	}

	public ProjectBulletin getById(Integer orderId) {
		return projectBulletinDao.getById(orderId);
	}
	
}