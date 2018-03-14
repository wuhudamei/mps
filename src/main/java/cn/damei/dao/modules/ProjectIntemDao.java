
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.modules.ProjectIntem;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.entity.modules.ProjectItemType;


@MyBatisDao
public interface ProjectIntemDao extends CrudDao<ProjectIntem> {
	

	public List<ProjectItemType>findProjectItemTypeList();
	
	

	public void saveCode();
	public  ReCheckCode  getCode();
	public  void updateCode(ReCheckCode code);


	public List<ProjectItemPrice> findProjectItemPrice();
	
}