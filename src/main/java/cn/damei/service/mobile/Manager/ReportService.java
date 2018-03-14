package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ReportDao;


@Service
@Transactional(readOnly=true)
public class ReportService extends CrudService2<ReportDao,ReportCheck>{



	public List<ReportCheck> queryOrderList(Integer managerId,String text){
		ReportCheck reportCheck  = new ReportCheck();
		reportCheck.setItemManagerId(managerId);
		reportCheck.setText(text);
		return dao.queryOrderList(reportCheck);
	}


	public List<ReportCheck> findReportByManagerId(Integer id) {
		return dao.findReportByManagerId(id);
	}


	public ReportCheck findReportDetailsById(Integer id) {
		return dao.findReportDetailsById(id);
	}


	public List<ReportCheckDetails> findItemById(Integer id) {
		return dao.findItemById(id);
	}


	public Integer findPicNum(Integer id) {
		return dao.findPicNum(id);
	}


	public List<ReportCheckDetailsPic> findPic(Integer qcBillId) {
		return dao.findPic(qcBillId);
	}

}
