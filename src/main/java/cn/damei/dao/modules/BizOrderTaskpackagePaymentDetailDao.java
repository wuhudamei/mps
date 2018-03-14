
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetaiVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.entity.modules.PaymentDetailForBook;


@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailDao extends CrudDao2<BizOrderTaskpackagePaymentDetail> {
	

	public List<BizOrderTaskpackagePaymentDetaiVo> queryEmployeeAllAmount(Integer summaryId);
	

	public List<BizOrderTaskpackagePaymentDetail> queryEmployeePaymentDetailId(Map<String, Object> map);

	public List<BizOrderTaskpackagePaymentDetail> findByPaymentId(Integer paymentId);
	

	public List<BizOrderTaskpackagePaymentDetail> queryPaymentDetailByMergeId(Integer mergeId);
	

	public List<BizOrderTaskpackagePaymentDetaiVo> queryPaymentDetailCountBySummaryId(Integer summaryId);
	

	public Integer queryPaymentDetailCountByPaymentIdAndStatus(Map<String, Object> map);


	public List<BizOrderTaskpackagePaymentDetail> queryPaymentDetailBySummaryId(Integer summaryId);


	public List<PaymentDetailForBook> query1ByEmployeeIdAndStatus(Integer employeeId,String status);

	public List<PaymentDetailForBook> query2ByEmployeeIdAndStatus(Integer employeeId,String status);
	
	public double queryAmountByEmployeeIdAndStatus(Integer employeeId, String status);

	public List<BizOrderTaskpackagePaymentDetail> queryOrderTaskpackagePaymentDetailEmployeeAmount(Integer orderTaskpackagePaymentId);

	public void insertList(List<BizOrderTaskpackagePaymentDetail> paymentDetails);

	public Double querySumAmountBySummaryIdAndEmployeeId(Map<String, Object> map);
}