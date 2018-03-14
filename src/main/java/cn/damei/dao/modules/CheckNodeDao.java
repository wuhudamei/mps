
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.CheckNode;
import cn.damei.entity.modules.DropModel;


@MyBatisDao
public interface CheckNodeDao extends CrudDao2<CheckNode> {
	
	

	 List<CheckNode> findConsList(CheckNode node);


	 List<DropModel> queryNodeListByStoreId(Map<String, Object> map);



	String checkIsOkForBasicNode(String storeId,String projectMode);

	Integer checkIsOkForDelete(Integer qcNodeId);

}