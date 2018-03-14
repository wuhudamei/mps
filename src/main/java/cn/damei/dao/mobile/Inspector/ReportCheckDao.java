package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;


@MyBatisDao
public interface ReportCheckDao extends CrudDao2<ReportCheck>{


	List<ReportCheck> queryOrderList(ReportCheck reportCheck);


	List<ReportCheck> findReportByInspectorId(Integer id);


	ReportCheck findReportDetailsById(Integer id);


	List<ReportCheckDetails> findItemById(Integer id);


	Integer findPicNum(Integer id);


	List<ReportCheckDetailsPic> findPic(Integer qcBillId);

	List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer id);
}
