/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisit;

/**
 * 客户回访节点DAO接口
 * @author LiwanCai
 * @version 2017-05-22
 */
@MyBatisDao
public interface BizCustomerReturnVisitDao extends CrudDao<BizCustomerReturnVisit> {
	
	/**
	 * 获取（字典表中查询不满意类型）
	 * @return
	 */
	List<Map<String,String>> queryReturnVisitBadType();
	
	List<Map<String,Object>> queryReturnVisitNodeByStoreId(@Param("storeId")String storeId,@Param("projectMode")String projectMode);
	
	String queryReturnVisitNodeNamesByStoreId(@Param("storeId")String storeId);
	
	String getReturnVisitName(@Param("projectNode")String projectNode,@Param("storeId")String storeId,@Param("projectMode")String projectMode );
}