package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderReportRelatedOrderDao;
import cn.damei.entity.modules.BizOrderReportRelatedOrder;

/**
 * 返单上报关联订单Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderReportRelatedOrderService extends CrudService2<BizOrderReportRelatedOrderDao,BizOrderReportRelatedOrder>{



	public BizOrderReportRelatedOrder get(Integer id){
		return super.get(id);
	}
	
	public List<BizOrderReportRelatedOrder> findList(BizOrderReportRelatedOrder bizOrderReportRelatedOrder){
		return super.findList(bizOrderReportRelatedOrder);
	}
	
	public Page<BizOrderReportRelatedOrder> findPage(Page<BizOrderReportRelatedOrder> page, BizOrderReportRelatedOrder bizOrderReportRelatedOrder) {
		return super.findPage(page, bizOrderReportRelatedOrder);
	} 
	
	@Transactional(readOnly = false)
	public void save(BizOrderReportRelatedOrder bizOrderReportRelatedOrder){
		super.save(bizOrderReportRelatedOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderReportRelatedOrder bizOrderReportRelatedOrder) {
		super.delete(bizOrderReportRelatedOrder);
	}

}
