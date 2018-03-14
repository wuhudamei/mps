
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitContent;


@MyBatisDao
public interface BizCustomerReturnVisitContentDao extends CrudDao<BizCustomerReturnVisitContent> {
	
	void insertBatch(List<BizCustomerReturnVisitContent> list);
	
	List<BizCustomerReturnVisitContent> getByReturnVisitId(String returnVisitId);
	
	List<BizCustomerReturnVisitContent> getByProjectNode(@Param("projectNode")String projectNode,@Param("storeId")String storeId,@Param("projectMode")String projectMode );
	
	void deleteByReturnVisitId(String returnVisitId);
}