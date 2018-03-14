
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.entity.modules.OrderInformation;


@MyBatisDao
public interface BizOrderTaskpackagePaymentSummaryDao extends CrudDao2<BizOrderTaskpackagePaymentSummary> {
	

	public BizOrderTaskpackagePaymentSummary queryPaymentSummaryByNo(String orderTaskpackagePaymentSummaryCode);
	

	public BizOrderTaskpackagePaymentSummary queryOrderTaskpackagePaymentSummaryById(Integer id);
	

	public List<BizOrderTaskpackagePaymentSummary> findSummaryList(BizOrderTaskpackagePaymentSummary summary);
	

	public OrderInformation queryOrderByPaymentCode(String paymentCode);

	public List<BizOrderTaskpackagePaymentSummary> findPaymentSummaryList(BizOrderTaskpackagePaymentSummary summary);

	public List<BizOrderTaskpackagePaymentSummary> findPaymentSummaryAllList(BizOrderTaskpackagePaymentSummary summary);


	public BizOrderTaskpackagePaymentSummary querySendMsgForSummary(Integer id);
}