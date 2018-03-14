
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Dict;


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
