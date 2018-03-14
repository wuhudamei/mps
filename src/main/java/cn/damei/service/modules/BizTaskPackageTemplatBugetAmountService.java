package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizTaskPackageTemplatBugetAmountDao;
import cn.damei.entity.modules.BizTaskPackageTemplatBugetAmount;

/**
 * 任务包模板预算金额Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizTaskPackageTemplatBugetAmountService extends CrudService2<BizTaskPackageTemplatBugetAmountDao, BizTaskPackageTemplatBugetAmount>{

	public Page<BizTaskPackageTemplatBugetAmount> findPage(Page<BizTaskPackageTemplatBugetAmount> page, BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount) {
		return super.findPage(page, bizTaskPackageTemplatBugetAmount);
	}
	
	public BizTaskPackageTemplatBugetAmount checkDate(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount){
		return dao.checkDate(bizTaskPackageTemplatBugetAmount);
	}
	
	public List<BizTaskPackageTemplatBugetAmount> queryTaskPackageTemplatByParam(Map<String,Object> param){
		return dao.queryTaskPackageTemplatByParam(param);
	}
}
