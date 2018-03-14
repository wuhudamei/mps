
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplit;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplitVo;


@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailSplitDao extends CrudDao2<BizOrderTaskpackagePaymentDetailSplit> {


	List<BizOrderTaskpackagePaymentDetailSplitVo> findPaymentDetailSplitBySummaryId(Integer summaryId);

	List<BizOrderTaskpackagePaymentDetailSplit> queryPaymentDetailSplitByRelateIdCard(String relatedIdcardNo,Integer summaryId);
	
	public int deleteBySummaryId(Integer summaryId);

	void updateDetailSplitById(int id, double money);

	public Double queryPaymentAmountSplit(Map<String, Object> map);

	public void insertBatch(List<BizOrderTaskpackagePaymentDetailSplit> list);
	
	public List<Integer> queryPaymentDetailId(Integer summaryId);
}