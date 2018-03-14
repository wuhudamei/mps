package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ReportDao;

/**
 * 质检报告
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class ReportService extends CrudService2<ReportDao,ReportCheck>{


	/**
	 * 查询质检单订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<ReportCheck> queryOrderList(Integer managerId,String text){
		ReportCheck reportCheck  = new ReportCheck();
		reportCheck.setItemManagerId(managerId);
		reportCheck.setText(text);
		return dao.queryOrderList(reportCheck);
	}

	/**
	 * 查询订单所有的质检报告
	 * @param id
	 * @return
	 */
	public List<ReportCheck> findReportByManagerId(Integer id) {
		return dao.findReportByManagerId(id);
	}

	/**
	 * 根据质检单id查询质检报告详情
	 * @param id
	 * @return
	 */
	public ReportCheck findReportDetailsById(Integer id) {
		return dao.findReportDetailsById(id);
	}

	/**
	 * 查询质检详情
	 * @param id
	 * @return
	 */
	public List<ReportCheckDetails> findItemById(Integer id) {
		return dao.findItemById(id);
	}

	/**
	 * 通过质检单id查询图片数量
	 * @param id
	 * @return
	 */
	public Integer findPicNum(Integer id) {
		return dao.findPicNum(id);
	}

	/**
	 * 通过质检单id查询图片
	 * @param qcBillId
	 * @return
	 */
	public List<ReportCheckDetailsPic> findPic(Integer qcBillId) {
		return dao.findPic(qcBillId);
	}

}
