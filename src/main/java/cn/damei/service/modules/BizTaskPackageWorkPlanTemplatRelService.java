
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.User;
import cn.damei.dao.modules.BizTaskPackageWorkPlanTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplatRel;


@Service
@Transactional(readOnly = true)
public class BizTaskPackageWorkPlanTemplatRelService extends CrudService<BizTaskPackageWorkPlanTemplatRelDao, BizTaskPackageWorkPlanTemplatRel> {
	
	@Autowired
	UserDao userDao;	
	
	public BizTaskPackageWorkPlanTemplatRel get(String id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageWorkPlanTemplatRel> findList(BizTaskPackageWorkPlanTemplatRel bizTaskPackageWorkPlanTemplatRel) {
		return super.findList(bizTaskPackageWorkPlanTemplatRel);
	}
	
	public Page<BizTaskPackageWorkPlanTemplatRel> findPage(Page<BizTaskPackageWorkPlanTemplatRel> page, BizTaskPackageWorkPlanTemplatRel bizTaskPackageWorkPlanTemplatRel) {
		Page<BizTaskPackageWorkPlanTemplatRel> result =  super.findPage(page, bizTaskPackageWorkPlanTemplatRel);
		for (BizTaskPackageWorkPlanTemplatRel tempType : result.getList()) {
			User u = userDao.get(tempType.getUpdateBy().getId());
			tempType.setUpdateBy(u);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageWorkPlanTemplatRel bizTaskPackageWorkPlanTemplatRel) {
		super.save(bizTaskPackageWorkPlanTemplatRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageWorkPlanTemplatRel bizTaskPackageWorkPlanTemplatRel) {
		super.delete(bizTaskPackageWorkPlanTemplatRel);
	}
	
}