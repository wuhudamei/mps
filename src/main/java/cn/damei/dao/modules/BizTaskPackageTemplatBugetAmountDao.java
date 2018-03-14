package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageTemplatBugetAmount;


@MyBatisDao
public interface BizTaskPackageTemplatBugetAmountDao extends CrudDao2<BizTaskPackageTemplatBugetAmount>{

	public BizTaskPackageTemplatBugetAmount checkDate(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount);
	
	public List<BizTaskPackageTemplatBugetAmount> queryTaskPackageTemplatByParam(Map<String,Object> param);
}
