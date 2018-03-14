/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 付款单DAO接口
 * @author qww
 * @version 2016-10-26
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentDao extends CrudDao2<BizOrderTaskpackagePayment> {

	List<BizOrderTaskpackagePayment> findPaymentBySettlementId(Integer id);
	
	/**
	 * 付款单列表
	 * @param map
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentVo> queryPaymentByCondition(Map<String, Object> map);
	
	/**
	 * 查询冻结/解冻的付款单
	 * @param bizOrderTaskpackagePaymentVo
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentVo> findPaymentFreezeList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo);
	
	/**
	 * 根据批次id查询付款单
	 * @return
	 */
	public List<BizOrderTaskpackagePayment> queryPaymentBySummaryId(Integer summaryId);
	
	/**
	 * 根据批次id查询结算单id
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentVo> queryPaymentSettlementBySummaryId(Integer summaryId);
	
	public String queryPaymentStatusByCondition(Map<String, Object> map);

	/**
	 * 根据付款明细id查询付款单
	 * @param detailId
	 * @return
	 */
	BizOrderTaskpackagePayment queryPaymentByPaymentDetailId(Integer detailId);

	/**
	 * 需审核尾款付款单列表
	 * @return
	 */
	List<BizOrderTaskpackagePaymentVo> queryPaymentForCheck(Map<String,Object> map);

	/**
	 * 根据质检单查询尾款付款单
	 * @param map
	 * @return
	 */
	List<BizOrderTaskpackagePaymentVo> queryPaymentForCheckByQcBillId(Map<String, Object> map);
	
	/**
	 * 查看验收详情
	 * @param qcBillId
	 * @return
	 */
	BizOrderTaskpackagePaymentDetails findQcBill(Integer qcBillId);

	/**
	 * 查看验收图片
	 * @param qcBillId
	 * @return
	 */
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