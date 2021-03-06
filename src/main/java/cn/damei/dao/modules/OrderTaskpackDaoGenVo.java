
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackGenVo;


@MyBatisDao
public interface OrderTaskpackDaoGenVo extends CrudDao<OrderTaskpackGenVo> {

	List<OrderTaskpackGenVo> getByOrderIdAndTaskpacksgeId(String contractStartDate,String storeId,String store,String projectMode);


	OrderTaskpackGenVo getByOrderAndEffectiveDate(String storeID, String storeID1, Date 
			contractStartDate, String procedureNo, String taskPackageTemplatNo,String projectMode);
	
	List<OrderTaskpackGenVo> getTemplatByOrderIdAndTaskpacksgeId(String contractStartDate,String storeId,String store,String projectMode);
	

}