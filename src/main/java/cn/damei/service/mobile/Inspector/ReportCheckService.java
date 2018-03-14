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


@Service
@Transactional(readOnly=true)
public class ReportCheckService extends CrudService2<ReportCheckDao,ReportCheck>{


	public List<ReportCheck> queryOrderList(Integer inspectorId,String text){
		ReportCheck reportCheck  = new ReportCheck();
		reportCheck.setCheckEmployeeId(inspectorId);
		reportCheck.setText(text);
		return dao.queryOrderList(reportCheck);
	}


	public List<ReportCheck> findReportByInspectorId(Integer id) {
		return dao.findReportByInspectorId(id);
	}


	public ReportCheck findReportDetailsById(Integer id) {
		return dao.findReportDetailsById(id);
	}


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


	public Integer findPicNum(Integer id) {
		return dao.findPicNum(id);
	}


	public List<ReportCheckDetailsPic> findPic(Integer qcBillId) {
		return dao.findPic(qcBillId);
	}

}
