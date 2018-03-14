package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.InstallProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.Order;

/**
 * 问题上报
 * @author Administrator
 *
 */
@MyBatisDao
public interface ProblemDao {

	/**
	 * 订单列表，只显示有安装项并且安装项状态大于等于【已申请】的订单
	 * @param order
	 * @return
	 */
	List<Order> findOrder(Order order);

	/**
	 * 显示状态为【已申请】的安装项
	 * @param orderId
	 * @return
	 */
	List<InstallProblem> findInstall(Integer orderId);

	/**
	 * 显示状态为【已申请】的安装项 并且只显示项目经理提交了问题的安装项
	 * @param orderId
	 * @return
	 */
	List<InstallProblem> findInstallAndProblem(Integer orderId);

	/**
	 * 问题上报记录详情
	 * @param id
	 * @return
	 */
	List<BizOrderInstallItemProblem> findProblemDetails(Integer id);

	/**
	 * 查看图片
	 * @param reportCheckDetailsPic
	 * @return
	 */
	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);



}
