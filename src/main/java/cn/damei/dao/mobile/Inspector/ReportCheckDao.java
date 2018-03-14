package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;

/**
 * 质检报告
 * @author Administrator
 *
 */
@MyBatisDao
public interface ReportCheckDao extends CrudDao2<ReportCheck>{

	/**
	 * 查询质检单订单列表
	 * @param reportCheck
	 * @return
	 */
	List<ReportCheck> queryOrderList(ReportCheck reportCheck);

	/**
	 * 查询该订单所有的质检报告
	 * @param id
	 * @return
	 */
	List<ReportCheck> findReportByInspectorId(Integer id);

	/**
	 * 根据质检单id查询质检报告详情
	 * @param id
	 * @return
	 */
	ReportCheck findReportDetailsById(Integer id);

	/**
	 * 查询质检详情
	 * @param id
	 * @return
	 */
	List<ReportCheckDetails> findItemById(Integer id);

	/**
	 * 通过质检单id查询图片数量
	 * @param id
	 * @return
	 */
	Integer findPicNum(Integer id);

	/**
	 * 通过质检单id查询图片
	 * @param qcBillId
	 * @return
	 */
	List<ReportCheckDetailsPic> findPic(Integer qcBillId);

	List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer id);
}
