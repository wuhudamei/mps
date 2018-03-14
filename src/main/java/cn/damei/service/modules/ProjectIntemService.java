/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.modules.ProjectIntem;
import cn.damei.entity.modules.ProjectItemType;
import cn.damei.dao.modules.ProjectIntemDao;

/**
* @Description: 施工项
* @Author zhangkangjian
* @Date 2017/12/5 15:03
*/
@Service
@Transactional(readOnly = true)
public class ProjectIntemService extends CrudService<ProjectIntemDao, ProjectIntem> {

	public ProjectIntem get(String id) {
		return super.get(id);
	}

	public List<ProjectIntem> findList(ProjectIntem projectIntem) {
		return super.findList(projectIntem);
	}

	public Page<ProjectIntem> findPage(Page<ProjectIntem> page, ProjectIntem projectIntem) {
		return super.findPage(page, projectIntem);
	}

	@Transactional(readOnly = false)
	public void save(ProjectIntem projectIntem) {
		super.save(projectIntem);
	}

	@Transactional(readOnly = false)
	public void delete(ProjectIntem projectIntem) {
		super.delete(projectIntem);
	}

	/**
	 * 查询启用的施工项分类
	 * 
	 * @return
	 */
	public List<ProjectItemType> findProjectItemTypeList() {

		return dao.findProjectItemTypeList();
	}

	// 编号 SG
	@Transactional(readOnly = false)
	public void saveCode() {

		dao.saveCode();

	}

	public ReCheckCode getCode(){
		
		return dao.getCode();
	}
	@Transactional(readOnly = false)
	public void updateCode(ReCheckCode code){
		
		dao.updateCode(code);
	}

}