package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;


@MyBatisDao
public interface BizOrderReportDao extends CrudDao2<BizOrderReport>{


	 Integer getBizOrderReportByCustomerPhone(String customerPhone);
	
	 List<BizOrderReport> findByParam(BizOrderReport bizOrderReport);


	void updateReturnOrderRemarks(BizOrderReport report);

	void updateStatus(BizOrderReport report);


	String findRemarksByReturnOrderId(String remarks);


	List<BizOrderReport> findServiceList(Map<String,String> map);


	void saveTransferServiceInfo(BizOrderReport report);

	void batchUpdateOrderReportStatusByQuarz(BizOrderReport bizOrderReport);
	void batchUpdateOrderReportStatusByQuarzTwo(BizOrderReport bizOrderReport);
	List<BizOrderReport> findListForExcel(BizOrderReport orderReport);

	void batchInsertRelatedContractContract(List<BizOrderReport> list);

	Integer findOrderNumberCountIsExist(Map<String,String> map);
	BizOrderReport selectReportInfoByNameAndPhone(BizOrderReport bizOrderReport);

	List<OrderReportLogEntity> findLogList1(Integer reportId);
	List<OrderReportLogEntity> findLogList2(Integer reportId);
	List<OrderReportLogEntity> findLogList3(Integer reportId);
	List<OrderReportLogEntity> findLogList4(Integer reportId);
	List<OrderReportLogEntity> findLogList5(Integer reportId);
	List<OrderReportLogEntity> findLogList6(Integer reportId);
	List<OrderReportLogEntity> findLogList7(Integer reportId);
}
