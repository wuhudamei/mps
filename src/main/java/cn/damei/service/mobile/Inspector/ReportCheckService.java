package cn.damei.service.mobile.Inspector;

import java.util.List;

import cn.damei.entity.mobile.Inspector.InspectItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Inspector.ReportCheckDao;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;

/**
 * 质检报告
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class ReportCheckService extends CrudService2<ReportCheckDao,ReportCheck>{

	/**
	 * 查询质检单订单列表
	 * @param inspectorId
	 * @param text
	 * @return
	 */
	public List<ReportCheck> queryOrderList(Integer inspectorId,String text){
		ReportCheck reportCheck  = new ReportCheck();
		reportCheck.setCheckEmployeeId(inspectorId);
		reportCheck.setText(text);
		return dao.queryOrderList(reportCheck);
	}

	/**
	 * 查询该订单所有的质检报告
	 * @param id
	 * @return
	 */
	public List<ReportCheck> findReportByInspectorId(Integer id) {
		return dao.findReportByInspectorId(id);
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
        List<ReportCheckDetails> itemById = dao.findItemById(id);
        for (ReportCheckDetails reportCheckDetails : itemById) {
            List<InspectItem> workerManagerInspectorPackageInfoByOrderId = dao.findWorkerManagerInspectorPackageInfoByOrderId(id);
            for (InspectItem inspectItem : workerManagerInspectorPackageInfoByOrderId) {
                if(inspectItem.getWorkerGroupLeaderName().equals(reportCheckDetails.getWorkGroupPerson())){
                    reportCheckDetails.setTaskName(inspectItem.getPackName());
                }
            }
        }

        return itemById;
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
