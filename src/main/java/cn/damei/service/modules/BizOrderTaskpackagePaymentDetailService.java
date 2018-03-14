
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.entity.modules.PaymentDetailForBook;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailDao;


@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentDetailService extends CrudService2<BizOrderTaskpackagePaymentDetailDao, BizOrderTaskpackagePaymentDetail> {

	public BizOrderTaskpackagePaymentDetail get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackagePaymentDetail> findList(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail) {
		return super.findList(bizOrderTaskpackagePaymentDetail);
	}
	
	public Page<BizOrderTaskpackagePaymentDetail> findPage(Page<BizOrderTaskpackagePaymentDetail> page, BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail) {
		return super.findPage(page, bizOrderTaskpackagePaymentDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail) {
		super.save(bizOrderTaskpackagePaymentDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail) {
		super.delete(bizOrderTaskpackagePaymentDetail);
	}

	public List<BizOrderTaskpackagePaymentDetail> findByPaymentId(Integer paymentId) {

		return dao.findByPaymentId(paymentId);
	}

	@Transactional(readOnly = false)
	public void insert(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail) {
		bizOrderTaskpackagePaymentDetail.preInsert();
		dao.insert(bizOrderTaskpackagePaymentDetail);
	}

	public List<BizOrderTaskpackagePaymentDetail> queryPaymentDetailBySummaryId(Integer id) {
		return dao.queryPaymentDetailBySummaryId(id);
	}

	public List<PaymentDetailForBook> queryPayedByEmployeeId(Integer employeeId,String status) {
		return dao.query1ByEmployeeIdAndStatus(employeeId,status);
	}

	public List<PaymentDetailForBook> queryPayingByEmployeeId(Integer employeeId,String status) {
		return dao.query2ByEmployeeIdAndStatus(employeeId,status);
	}

	public double queryPayedAmountByEmployeeId(Integer employeeId, String status) {
		return dao.queryAmountByEmployeeIdAndStatus(employeeId,status);
	}

	public double queryPayingAmountByEmployeeId(Integer employeeId, String status) {
		return dao.queryAmountByEmployeeIdAndStatus(employeeId,status);
	}

	public List<BizOrderTaskpackagePaymentDetail> queryOrderTaskpackagePaymentDetailEmployeeAmount(Integer orderTaskpackagePaymentId){
		return dao.queryOrderTaskpackagePaymentDetailEmployeeAmount(orderTaskpackagePaymentId);
	}
	
	@Transactional(readOnly = false)
	public void insertList(List<BizOrderTaskpackagePaymentDetail> paymentDetails) {
		dao.insertList(paymentDetails);
	}
}