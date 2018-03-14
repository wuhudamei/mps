
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisit;


@MyBatisDao
public interface BizCustomerReturnVisitDao extends CrudDao<BizCustomerReturnVisit> {
	

	List<Map<String,String>> queryReturnVisitBadType();
	
	List<Map<String,Object>> queryReturnVisitNodeByStoreId(@Param("storeId")String storeId,@Param("projectMode")String projectMode);
	
	String queryReturnVisitNodeNamesByStoreId(@Param("storeId")String storeId);
	
	String getReturnVisitName(@Param("projectNode")String projectNode,@Param("storeId")String storeId,@Param("projectMode")String projectMode );
}