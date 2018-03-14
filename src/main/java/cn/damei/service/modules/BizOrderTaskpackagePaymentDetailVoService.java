package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailVoDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailVo;
import cn.damei.entity.modules.PaymentDetailForExcel;

@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentDetailVoService extends CrudService2<BizOrderTaskpackagePaymentDetailVoDao, BizOrderTaskpackagePaymentDetailVo>{
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailVoDao bizOrderTaskpackagePaymentDetailVoDao;
	@Autowired
	private BizOrderTaskpackagePaymentDetailService bizOrderTaskpackagePaymentDetailService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	public List<PaymentDetailForExcel> findPaymentDatailsBySummaryId(Integer id) {
		
		List<BizOrderTaskpackagePaymentDetailVo> list = bizOrderTaskpackagePaymentDetailVoDao.findPaymentDatailsBySummaryId(id);
		
		List<PaymentDetailForExcel> details = new ArrayList<PaymentDetailForExcel>();
	
		for (BizOrderTaskpackagePaymentDetailVo paymentDetailVo : list) {
			
			List<BizOrderTaskpackagePaymentDetail> list1 = bizOrderTaskpackagePaymentDetailService.findByPaymentId(paymentDetailVo.getPaymentId());
			
			for (BizOrderTaskpackagePaymentDetail paymentDetail : list1) {
				
				BizEmployee2 employee = bizEmployeeService2.findEmployeeById(paymentDetail.getEmployeeId());
				
				PaymentDetailForExcel paymentDetailForExcel = new PaymentDetailForExcel();
				
				if("0".equals(paymentDetailVo.getPaymentType())){
					paymentDetailForExcel.setAdvancePayment(paymentDetail.getAmount());
					paymentDetailForExcel.setAdvanceRate(paymentDetailVo.getPaymentRates());
				}else{
					paymentDetailForExcel.setRestPayment(paymentDetail.getAmount());
					paymentDetailForExcel.setRestRate(paymentDetailVo.getPaymentRates());
				}
				paymentDetailForExcel.setSettlementCode(paymentDetailVo.getSettlementCode());
				paymentDetailForExcel.setSummaryCode(paymentDetailVo.getSummaryCode());
				paymentDetailForExcel.setContractArea(paymentDetailVo.getContractArea());
				paymentDetailForExcel.setCustomerAddress(paymentDetailVo.getCustomerAddress());
				paymentDetailForExcel.setCustomerName(paymentDetailVo.getCustomerName());
				paymentDetailForExcel.setCustomerPhone(paymentDetailVo.getCustomerPhone());
				paymentDetailForExcel.setPackageName(paymentDetailVo.getPackageName());
				paymentDetailForExcel.setItemManager(paymentDetailVo.getItemManager());
				paymentDetailForExcel.setSettlementAmount(paymentDetailVo.getSettlementAmount());
				paymentDetailForExcel.setWorkerGroupSettleAmount(paymentDetailVo.getWorkerGroupSettleAmount());
				paymentDetailForExcel.setSettlementApplyDate(paymentDetailVo.getSettlementApplyDate());
				paymentDetailForExcel.setSettlementExamineDate(paymentDetailVo.getSettlementExamineDate());
				paymentDetailForExcel.setWorkerName(employee.getRealname());
				paymentDetailForExcel.setIdCard(employee.getIdcardno());
				paymentDetailForExcel.setOrderTaskpackageId(paymentDetailVo.getOrderTaskpackageId());
				paymentDetailForExcel.setOrderNumber(paymentDetailVo.getOrderNumber());
				details.add(paymentDetailForExcel);
			}
		}
		
		return details;
	}
	
}
