
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetails;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummaryVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.Payment;


@MyBatisDao
public interface BizOrderTaskpackagePaymentDao extends CrudDao2<BizOrderTaskpackagePayment> {

	List<BizOrderTaskpackagePayment> findPaymentBySettlementId(Integer id);
	

	public List<BizOrderTaskpackagePaymentVo> queryPaymentByCondition(Map<String, Object> map);
	

	public List<BizOrderTaskpackagePaymentVo> findPaymentFreezeList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo);
	

	public List<BizOrderTaskpackagePayment> queryPaymentBySummaryId(Integer summaryId);
	

	public List<BizOrderTaskpackagePaymentVo> queryPaymentSettlementBySummaryId(Integer summaryId);
	
	public String queryPaymentStatusByCondition(Map<String, Object> map);


	BizOrderTaskpackagePayment queryPaymentByPaymentDetailId(Integer detailId);


	List<BizOrderTaskpackagePaymentVo> queryPaymentForCheck(Map<String,Object> map);


	List<BizOrderTaskpackagePaymentVo> queryPaymentForCheckByQcBillId(Map<String, Object> map);
	

	BizOrderTaskpackagePaymentDetails findQcBill(Integer qcBillId);


	List<ReportCheckDetailsPic> findPic(Integer qcBillId);

	void updateQcbillStatusById(Integer qcBillId, String status,String reason);

	void updateStatusByPaymentId(Integer paymentId, String status);

	public List<BizOrderTaskpackagePaymentVo> findPaymentList(BizOrderTaskpackagePaymentVo payment);

	public BizOrderTaskpackagePaymentVo findPaymentListView(Integer id);

	List<Payment> findPayments(Map<String, Object> map);

	public List<BizOrderTaskpackagePaymentSummaryVo> findPaymentSummaryList(BizOrderTaskpackagePaymentSummaryVo vo);
	
	public List<BizOrderTaskpackagePaymentVo> checkPaymentByIds(Map<String,Object> map);
	
	public List<BizOrderTaskpackagePaymentVo> findPaymentVoBySettlementId(Integer id);
	
	public int checkPaymentIsExistByParam(Map<String,Object> map);
	
	public Integer getBalPmtCheckNode(OrderTaskpackage orderTaskpackage);
	
	public List<BizOrderTaskpackagePaymentVo> findPaymentVoByOrderId(Integer orderId);
	
	public BizOrderTaskpackagePayment findBalancePaymentByPaymentId(Integer paymentId);
	
	public List<BizOrderTaskpackagePayment> findPaymentBySettlementIds(Map<String,Object> param);
}