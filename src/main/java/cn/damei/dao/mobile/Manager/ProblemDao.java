package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.InstallProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.Order;


@MyBatisDao
public interface ProblemDao {


	List<Order> findOrder(Order order);


	List<InstallProblem> findInstall(Integer orderId);


	List<InstallProblem> findInstallAndProblem(Integer orderId);


	List<BizOrderInstallItemProblem> findProblemDetails(Integer id);


	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);



}
