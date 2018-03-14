package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EnginInstall;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.modules.BizBusinessStatusLog;

/**
 * 工程安装
 * @author wyb
 */
@MyBatisDao
public interface NewEnginInstallDao {

	/**
	 * 动态加载申请安装列表
	 * @param enginInstall
	 * @return
	 */
	List<EnginInstall> queryInstallOrderList(EnginInstall enginInstall);

	/**
	 * 申请安装后24小时内不能进行催促安装
	 * @param id
	 * @return
	 */
	Integer findInstallOneDayCount(Integer id);

	/**
	 * 该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
	 * @param id
	 * @return
	 */
	OrderInstallPlan findInstallCanApplyDate(Integer id);

	/**
	 * 查询订单信息
	 * @param orderId
	 * @return
	 */
	EnginInstall findOrderMessage(Integer orderId);

	/**
	 * 查询安装项信息
	 * @param id
	 * @return
	 */
	OrderInstallPlan findInstallMessage(Integer id);

	/**
	 * 查询安装项详情
	 * @param orderInstallItemId
	 * @return
	 */
	OrderInstallPlan findInstallDetaill(Integer orderInstallItemId);

	/**
	 * 更新订单安装项
	 * @param installPlan
	 * @return
	 */
	boolean updateInstallPlan(OrderInstallPlan installPlan);

	/**
	 * 查询操作日志
	 * @param bizBusinessStatusLog
	 * @return
	 */
	List<BizBusinessStatusLog> findOperationList(BizBusinessStatusLog bizBusinessStatusLog);

	/**
	 * 家具申请时，订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。
	 * @param id
	 * @return
	 */
	Integer findNotAcceptInstallCount(Integer id);


	/**
	 * 查询安装项信息
	 * @param id
	 * @return
	 */
    OrderInstallPlan queryInstallPlanDetails(Integer id);

	/**
	 * 查询订单主材申请列表
	 * @param orderId
	 * @return
	 */
	List<OrderInstallPlan> queryInstallPlanApplyList(Integer orderId);

	/**
	 * 是否需要复尺
	 *
	 * @param id
	 * @return
	 */
	String isChecksize(String id);

	/**
	 * 有没有复尺
	 *
	 * @param id
	 * @return
	 */
	String isChecksizeProblem(String id);

	/**
	 * 查询订单可申请的安装项数量
	 * @param orderId
	 * @return
	 */
	Integer selectInstallPlanCount(Integer orderId);


	/**
	 * 查看是否在business_status_log中 该订单今日已经催促了
	 * @param orderId
	 * @param businessStatus
	 * @param businessType
	 * @param inhibitDate
	 * @return
	 */
	Integer findBusinessLogCount(String orderId, String businessStatus, String businessType, String inhibitDate);


}
