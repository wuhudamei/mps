/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.entity.modules.OrderInformation;

/**
 * 付款单批次DAO接口
 * @author 汪文文
 * @version 2016-10-26
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentSummaryDao extends CrudDao2<BizOrderTaskpackagePaymentSummary> {
	
	/**
	 * 根据编号查询
	 * @param orderTaskpackagePaymentSummaryCode
	 * @return
	 */
	public BizOrderTaskpackagePaymentSummary queryPaymentSummaryByNo(String orderTaskpackagePaymentSummaryCode);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public BizOrderTaskpackagePaymentSummary queryOrderTaskpackagePaymentSummaryById(Integer id);
	
	/**
	 * 财务人员使用批次列表
	 * @param summary
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentSummary> findSummaryList(BizOrderTaskpackagePaymentSummary summary);
	
	/**
	 * wang
	 * @param paymentCode
	 * @return
	 */
	public OrderInformation queryOrderByPaymentCode(String paymentCode);

	public List<BizOrderTaskpackagePaymentSummary> findPaymentSummaryList(BizOrderTaskpackagePaymentSummary summary);

	public List<BizOrderTaskpackagePaymentSummary> findPaymentSummaryAllList(BizOrderTaskpackagePaymentSummary summary);

	/**
	 * 通知付款员-批次审核通过/通知结算员-批次付款完成
	 * @param id
	 * @return
	 */
	public BizOrderTaskpackagePaymentSummary querySendMsgForSummary(Integer id);
}