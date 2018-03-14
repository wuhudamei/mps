/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetaiVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.entity.modules.PaymentDetailForBook;

/**
 * 付款单明细DAO接口
 * @author qww
 * @version 2016-10-26
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailDao extends CrudDao2<BizOrderTaskpackagePaymentDetail> {
	
	/**
	 * 查询单个批次下所有员工所有总金额
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetaiVo> queryEmployeeAllAmount(Integer summaryId);
	
	/**
	 * 查询单个批次下单个员工所属的付款明细id
	 * @param map
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetail> queryEmployeePaymentDetailId(Map<String, Object> map);

	public List<BizOrderTaskpackagePaymentDetail> findByPaymentId(Integer paymentId);
	
	/**
	 * 根据付款单明细合并id查询付款单明细
	 * @param mergeId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetail> queryPaymentDetailByMergeId(Integer mergeId);
	
	/**
	 * 根据批次id查询付款明细
	 * @param map
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetaiVo> queryPaymentDetailCountBySummaryId(Integer summaryId);
	
	/**
	 * 查询一个付款单下状态为已付款的付款明细条数
	 * @param map
	 * @return
	 */
	public Integer queryPaymentDetailCountByPaymentIdAndStatus(Map<String, Object> map);

	/**
	 * 查询一个批次下所有付款单明细
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetail> queryPaymentDetailBySummaryId(Integer summaryId);

	/**
	 * 查询付款明细根据状态和员工id
	 * @param employeeId
	 * @return
	 */
	public List<PaymentDetailForBook> query1ByEmployeeIdAndStatus(Integer employeeId,String status);

	public List<PaymentDetailForBook> query2ByEmployeeIdAndStatus(Integer employeeId,String status);
	
	public double queryAmountByEmployeeIdAndStatus(Integer employeeId, String status);

	public List<BizOrderTaskpackagePaymentDetail> queryOrderTaskpackagePaymentDetailEmployeeAmount(Integer orderTaskpackagePaymentId);

	public void insertList(List<BizOrderTaskpackagePaymentDetail> paymentDetails);

	public Double querySumAmountBySummaryIdAndEmployeeId(Map<String, Object> map);
}