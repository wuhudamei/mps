package cn.damei.dao.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.*;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;


@MyBatisDao
public interface OrderInstallPlanDao extends CrudDao2<OrderInstallPlan> {


	OrderInstallPlan getById(Integer id);


	List<EnginInstall> queryInstallAcceptOrderList(EnginInstall enginInstall);


	List<OrderInstallPlan> queryOrderInstallAcceptList(Integer id);


	List<OrderInstallPlan> queryOrderInstallAcceptDetailList(Integer id);


	EnginInstall querySupplierInstallBillMessage(Integer id);


	boolean updateOrderInstallPlan(OrderInstallPlan plan);


	void saveOrderInstallPlanAcceptLog(OrderInstallPlanAcceptLog orderInstallPlanAcceptLog);


	Integer queryOrderInstallPlanAcceptLog(Integer orderInstallPlanId);


	boolean updateSupplierInstallBill(BizSupplierInstallBill bizSupplierInstallBill);


	boolean updateSupplierConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);


	List<OrderInstallPlanAcceptLog> queryAcceptUnqualifiedLog(Integer id);


	List<ReportCheckDetailsPic> queryAcceptUnqualifiedPicList(ReportCheckDetailsPic reportCheckDetailsPic);


	List<OrderInstallPlanPic> queryAcceptQualifiedPicList(Integer id);



	InterfaceOrder findOrderDetail(String orderId);


	List<InterfaceNodePlan> findNodePlanDetail(String orderId);


	List<Integer> findInstallid(String id);

	List<Integer> findOrderInstallid(String id);

	List<String> findCategoryTowCode(String id);

	long isCheckInstallItem(CheckInstallItem check);

	EnginInstall findOrderDtails(String id);

	void saveJSONDate(String string2, String string, Date date);

	List<String> findWallAndFloor(String id);



	boolean saveInstallPlanList(List<OrderInstallPlan> list);

}
