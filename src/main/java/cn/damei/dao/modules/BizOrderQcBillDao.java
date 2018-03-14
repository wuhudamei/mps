
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.modules.BizOrderQcBill;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.entity.modules.ReportCheckDetails;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizQcCheckKind;


@MyBatisDao
public interface BizOrderQcBillDao extends CrudDao2<BizOrderQcBill> {


	List<BizQcBill> findReport(int orderId);


	BizOrderQcBill findOrder(int orderId);


	BizQcBill findReportDetails(int qcBillId);


	List<ReportCheckDetails> finditemById(ReportCheckDetails reportCheckDetails);


	List<BizQcCheckKind> findCheckKind();


	List<ReportCheckDetailsPic> findPic(int qcBillId);

    List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer qcBillId);
}