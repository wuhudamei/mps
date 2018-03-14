
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.OrderTaskpackageAudit;
import cn.damei.entity.modules.Dict;


@MyBatisDao
public interface OrderTaskpackageAuditDao extends CrudDao<OrderTaskpackageAudit> {

	void updateOrderTaskpackageByPackageStatusId(String auditResult,String auditRemarks,String id);

	OrderTaskpackageAudit getById(String id);


	boolean updateOrderTaskpackageByPackageStatusIdReturn(String auditResult,String auditRemarks,String id);

	OrderTaskpackageAudit getByIdOrEmployee(String id);
	
    List<Dict> getPackageStateid(Integer num1, Integer num2);

    List<DropModel> findPackNameByStoreId(String storeId);
    
    Integer getStayCountByStoreId(OrderTaskpackageAudit orderTaskpackageAudit);

	List<OrderTaskpackageAudit> myFindList(OrderTaskpackageAudit orderTaskpackageAudit);

	List<OrderTaskpackageAudit> findSpecialList(OrderTaskpackageAudit orderTaskpackageAudit);

	Integer getSpecialStayCountByStoreId(OrderTaskpackageAudit orderTaskpackageAudit);
}