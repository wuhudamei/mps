package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;

/**
 * 返单上报Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizOrderReportDao extends CrudDao2<BizOrderReport>{

	//通过客户手机号查询返单信息和订单信息
	 Integer getBizOrderReportByCustomerPhone(String customerPhone);
	
	 List<BizOrderReport> findByParam(BizOrderReport bizOrderReport);

	 //更新返单补签合同备注
	void updateReturnOrderRemarks(BizOrderReport report);
	 //更新返单补签合同备注
	void updateStatus(BizOrderReport report);

	//查询备注
	String findRemarksByReturnOrderId(String remarks);

	/**
	 * 查询客户列表(搜索)
	 * @return
	 */
	List<BizOrderReport> findServiceList(Map<String,String> map);

	/**
	 * 更新返单的客服信息
	 * @param report
	 */
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
