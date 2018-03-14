
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlanProject;
import cn.damei.entity.modules.BizTraditionOrder;



@MyBatisDao
public interface BizProjectProgressBoningTraditionOrderDao extends CrudDao2<BizTraditionOrder> {


	List<BizNodePlanProject> findPlanList(Integer orderId);
	

	List<BizTraditionOrder> findListExcel(BizTraditionOrder bizTraditionOrder);

    List<BizTraditionOrder> findListExcel1(BizTraditionOrder bizTraditionOrder);
}