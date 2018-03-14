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
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplat;
import cn.damei.dao.modules.BizTaskPackageWorkPlanTemplatDao;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplatRel;
import cn.damei.dao.modules.BizTaskPackageWorkPlanTemplatRelDao;

/**
 * 任务包派工计划模板Service
 * @author chy
 * @version 2016-09-03
 */
@Service
@Transactional(readOnly = true)
public class BizTaskPackageWorkPlanTemplatService extends CrudService<BizTaskPackageWorkPlanTemplatDao, BizTaskPackageWorkPlanTemplat> {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private BizTaskPackageWorkPlanTemplatRelDao bizTaskPackageWorkPlanTemplatRelDao;
	
	public BizTaskPackageWorkPlanTemplat get(String id) {
		BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat = super.get(id);
		bizTaskPackageWorkPlanTemplat.setBizTaskPackageWorkPlanTemplatRelList(bizTaskPackageWorkPlanTemplatRelDao.findList(new BizTaskPackageWorkPlanTemplatRel(bizTaskPackageWorkPlanTemplat)));
		return bizTaskPackageWorkPlanTemplat;
	}
	
	public List<BizTaskPackageWorkPlanTemplat> findList(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		return super.findList(bizTaskPackageWorkPlanTemplat);
	}
	
	public Page<BizTaskPackageWorkPlanTemplat> findPage(Page<BizTaskPackageWorkPlanTemplat> page, BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		Page<BizTaskPackageWorkPlanTemplat> result=super.findPage(page, bizTaskPackageWorkPlanTemplat);
		for (BizTaskPackageWorkPlanTemplat tempType : result.getList()) {
			User u = userDao.get(tempType.getCreateBy().getId());
			tempType.setCreateBy(u);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		super.save(bizTaskPackageWorkPlanTemplat);
		for (BizTaskPackageWorkPlanTemplatRel bizTaskPackageWorkPlanTemplatRel : bizTaskPackageWorkPlanTemplat.getBizTaskPackageWorkPlanTemplatRelList()){
			if (bizTaskPackageWorkPlanTemplatRel.getId() == null){
				continue;
			}
			if (BizTaskPackageWorkPlanTemplatRel.DEL_FLAG_NORMAL.equals(bizTaskPackageWorkPlanTemplatRel.getDelFlag())){
				if (StringUtils.isBlank(bizTaskPackageWorkPlanTemplatRel.getId())){
					bizTaskPackageWorkPlanTemplatRel.setBizTaskPackageWorkPlanTemplat(bizTaskPackageWorkPlanTemplat);
					bizTaskPackageWorkPlanTemplatRel.setStoreId(UserUtils.getUser().getOffice().getId());
					bizTaskPackageWorkPlanTemplatRel.preInsert();
					bizTaskPackageWorkPlanTemplatRelDao.insert(bizTaskPackageWorkPlanTemplatRel);
				}else{
					bizTaskPackageWorkPlanTemplatRel.preUpdate();
					bizTaskPackageWorkPlanTemplatRelDao.update(bizTaskPackageWorkPlanTemplatRel);
				}
			}else{
				bizTaskPackageWorkPlanTemplatRelDao.delete(bizTaskPackageWorkPlanTemplatRel);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		super.delete(bizTaskPackageWorkPlanTemplat);
		bizTaskPackageWorkPlanTemplatRelDao.delete(new BizTaskPackageWorkPlanTemplatRel(bizTaskPackageWorkPlanTemplat));
	}
	
	@Transactional(readOnly = false)
	public void update(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat) {
		super.save(bizTaskPackageWorkPlanTemplat);
	}
	
}