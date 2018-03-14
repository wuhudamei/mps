package cn.damei.service.mobile.Manager;


import java.util.List;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ProblemDao;
import cn.damei.entity.mobile.Manager.InstallProblem;
import cn.damei.entity.modules.Order;


/**
 * 问题上报Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class ProblemService {

	@Autowired
	private ProblemDao dao;
	
	/**
	 * 订单列表，只显示有安装项并且安装项状态大于等于【已申请】的订单
	 * @param id
	 * @param text 
	 * @return
	 */
	public List<Order> findOrder(Integer id, String text) {
		Order order = new Order();
		order.setItemManagerId(id);
		order.setCustomerAddress(text);
		List<Order> list = dao.findOrder(order);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 显示状态为【已申请】的安装项
	 * @param orderId
	 * @return
	 */
	public List<InstallProblem> findInstall(Integer orderId) {
		List<InstallProblem> list = dao.findInstall(orderId);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 显示状态为【已申请】的安装项 并且只显示项目经理提交了问题的安装项
	 * @param orderId
	 * @return
	 */
	public List<InstallProblem> findInstallAndProblem(Integer orderId) {
		List<InstallProblem> list = dao.findInstallAndProblem(orderId);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 问题上报记录详情
	 * @param id
	 * @return
	 */
	public List<BizOrderInstallItemProblem> findProblemDetails(Integer id) {
		List<BizOrderInstallItemProblem> list = dao.findProblemDetails(id);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 查看图片
	 * @param id
	 * @param text 
	 * @return
	 */
	public List<ReportCheckDetailsPic> findPic(Integer id, String text) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(text);
		List<ReportCheckDetailsPic> list = dao.findPic(reportCheckDetailsPic);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}


}
