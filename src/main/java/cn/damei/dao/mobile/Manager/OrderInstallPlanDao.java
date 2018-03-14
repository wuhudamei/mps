package cn.damei.dao.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.*;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;

/**
 * 工程安装
 * 
 * @author wyb
 */
@MyBatisDao
public interface OrderInstallPlanDao extends CrudDao2<OrderInstallPlan> {

	/**
	 * 安装项计划详情
	 */
	OrderInstallPlan getById(Integer id);

	/**
	 * 安装验收 订单列表
	 */
	List<EnginInstall> queryInstallAcceptOrderList(EnginInstall enginInstall);

	/**
	 * 主材安装验收列表页【3：已转给供应商】【330：工人已申请完工】【401：验收不合格】
	 */
	List<OrderInstallPlan> queryOrderInstallAcceptList(Integer id);

	/**
	 * 主材安装验收明细列表页【4：已验收】
	 */
	List<OrderInstallPlan> queryOrderInstallAcceptDetailList(Integer id);

	/**
	 * 查询该安装项【订单】【安装单】【施工单】信息
	 */
	EnginInstall querySupplierInstallBillMessage(Integer id);

	/**
	 * 更新订单安装项计划
	 */
	boolean updateOrderInstallPlan(OrderInstallPlan plan);

	/**
	 * 保存验收日志
	 */
	void saveOrderInstallPlanAcceptLog(OrderInstallPlanAcceptLog orderInstallPlanAcceptLog);

	/**
	 * 查询该安装项【是否合格】验收的5分钟校验
	 */
	Integer queryOrderInstallPlanAcceptLog(Integer orderInstallPlanId);

	/**
	 * 更新安装单
	 */
	boolean updateSupplierInstallBill(BizSupplierInstallBill bizSupplierInstallBill);

	/**
	 * 更新施工单
	 */
	boolean updateSupplierConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);

	/**
	 * 【不合格】验收日志
	 */
	List<OrderInstallPlanAcceptLog> queryAcceptUnqualifiedLog(Integer id);

	/**
	 * 查询验收【不合格】图片
	 */
	List<ReportCheckDetailsPic> queryAcceptUnqualifiedPicList(ReportCheckDetailsPic reportCheckDetailsPic);

	/**
	 * 查询验收【合格】图片
	 */
	List<OrderInstallPlanPic> queryAcceptQualifiedPicList(Integer id);


	/**
	 * 查询订单的信息
	 * 
	 * @param orderId
	 * @return
	 */
	InterfaceOrder findOrderDetail(String orderId);

	/**
	 * 查询进度节点的信息
	 * 
	 * @param orderId
	 * @return
	 */
	List<InterfaceNodePlan> findNodePlanDetail(String orderId);


	List<Integer> findInstallid(String id);

	List<Integer> findOrderInstallid(String id);

	List<String> findCategoryTowCode(String id);

	long isCheckInstallItem(CheckInstallItem check);

	EnginInstall findOrderDtails(String id);

	void saveJSONDate(String string2, String string, Date date);

	List<String> findWallAndFloor(String id);


	/**
	 * 批量生成主材安装项计划
	 * @param list
	 * @return
	 */
	boolean saveInstallPlanList(List<OrderInstallPlan> list);

}
