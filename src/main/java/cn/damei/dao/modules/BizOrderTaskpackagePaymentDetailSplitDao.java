/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplit;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplitVo;

/**
 * 付款单明细拆分DAO接口
 * @author www
 * @version 2016-10-31
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailSplitDao extends CrudDao2<BizOrderTaskpackagePaymentDetailSplit> {

	//List<BizOrderTaskpackagePaymentDetailSplit> findPaymentDetailSplitBySummaryId(Integer summaryId);
	List<BizOrderTaskpackagePaymentDetailSplitVo> findPaymentDetailSplitBySummaryId(Integer summaryId);

	List<BizOrderTaskpackagePaymentDetailSplit> queryPaymentDetailSplitByRelateIdCard(String relatedIdcardNo,Integer summaryId);
	
	public int deleteBySummaryId(Integer summaryId);

	void updateDetailSplitById(int id, double money);

	public Double queryPaymentAmountSplit(Map<String, Object> map);

	public void insertBatch(List<BizOrderTaskpackagePaymentDetailSplit> list);
	
	public List<Integer> queryPaymentDetailId(Integer summaryId);
}