/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.CheckNode;
import cn.damei.entity.modules.DropModel;

/**
 * 约检节点设置DAO接口
 * @author 梅浩
 * @version 2016-10-26
 */
@MyBatisDao
public interface CheckNodeDao extends CrudDao2<CheckNode> {
	
	
	/**
	 * 根据门店id 动态查询节点
	 * @param node
	 * @return
	 */
	 List<CheckNode> findConsList(CheckNode node);

	/**
	 * 根据门店id查询节点
	 * @param map
	 * @return
	 */
	 List<DropModel> queryNodeListByStoreId(Map<String, Object> map);



	String checkIsOkForBasicNode(String storeId,String projectMode);

	Integer checkIsOkForDelete(Integer qcNodeId);

}