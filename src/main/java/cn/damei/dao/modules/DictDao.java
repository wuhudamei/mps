/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Dict;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);

	public List<Dict> getTaskPackageStatusList(String type);
	
	public List<Dict> getOrderTaskPackageStatusList(String type);

	public List<Dict> getSummaryStatusList(String type);
	
	public List<Dict> getSummaryCheckedStatusList(String type);

	public List<Dict> getDictListByType(Map<String, Object> map);
	
	public List<Dict> getByType(String type);

	public List<Dict> getInstallProblemStatusList(String type);
}
