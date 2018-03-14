
package cn.damei.dao.modules;



import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.orderVo;


@MyBatisDao
public interface dao extends CrudDao<orderVo> {
	

	

	public List<OrderTaskpackage> getPackageByOrderId(Integer orderId); 
	

	public  void updatePackTime(OrderTaskpackage   pack);

	public  void updatePackTime1(OrderTaskpackage   pack);
	
	
	

	public List<OrderTaskpackage>sendFixedTimeMessageToManagerForPackPlanTime(String planStartdate);
	
	
}