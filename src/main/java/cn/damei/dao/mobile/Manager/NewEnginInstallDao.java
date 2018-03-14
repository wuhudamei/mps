package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EnginInstall;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.modules.BizBusinessStatusLog;


@MyBatisDao
public interface NewEnginInstallDao {


	List<EnginInstall> queryInstallOrderList(EnginInstall enginInstall);


	Integer findInstallOneDayCount(Integer id);


	OrderInstallPlan findInstallCanApplyDate(Integer id);


	EnginInstall findOrderMessage(Integer orderId);


	OrderInstallPlan findInstallMessage(Integer id);


	OrderInstallPlan findInstallDetaill(Integer orderInstallItemId);


	boolean updateInstallPlan(OrderInstallPlan installPlan);


	List<BizBusinessStatusLog> findOperationList(BizBusinessStatusLog bizBusinessStatusLog);


	Integer findNotAcceptInstallCount(Integer id);



    OrderInstallPlan queryInstallPlanDetails(Integer id);


	List<OrderInstallPlan> queryInstallPlanApplyList(Integer orderId);


	String isChecksize(String id);


	String isChecksizeProblem(String id);


	Integer selectInstallPlanCount(Integer orderId);



	Integer findBusinessLogCount(String orderId, String businessStatus, String businessType, String inhibitDate);


}
