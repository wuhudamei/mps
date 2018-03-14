
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.toDoConstant.UrgeToPayConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.dao.modules.BizBusinessUrgePayDao;
import cn.damei.entity.modules.BizBusinessUrgePayEntity;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizBusinessUrgePayService extends CrudService<BizBusinessUrgePayDao, BizBusinessUrgePayEntity> {
	@Autowired
	private BizBusinessUrgePayDao bizBusinessUrgePayDao;
	public BizBusinessUrgePayEntity get(String id) {
		return super.get(id);
	}
	
	public List<BizBusinessUrgePayEntity> findList(BizBusinessUrgePayEntity bizBusinessUrgePay) {
		return super.findList(bizBusinessUrgePay);
	}
	
	public Page<BizBusinessUrgePayEntity> findPage(Page<BizBusinessUrgePayEntity> page, BizBusinessUrgePayEntity bizBusinessUrgePay) {
		return super.findPage(page, bizBusinessUrgePay);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBusinessUrgePayEntity bizBusinessUrgePay) {
		super.save(bizBusinessUrgePay);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBusinessUrgePayEntity bizBusinessUrgePay) {
		super.delete(bizBusinessUrgePay);
	}
	@Transactional(readOnly = false)
	public String insertUrgePayByOrderId(Integer orderId){
		Backlog backlog = bizBusinessUrgePayDao.getOrderInfo(orderId);
		BizBusinessUrgePayEntity b = new BizBusinessUrgePayEntity();
		b.setRelatedBusinessType(UrgeToPayConstantUtil.RELATED_BUSINESS_TYPE_1001);
		b.setRelatedBusinessIdInt(orderId.toString());
		b.setUrgePayType(UrgeToPayConstantUtil.URGE_PAY_TYPE);
		b.setUrgePayContent("二期款催缴");
		b.setUrgePayChannel(UrgeToPayConstantUtil.URGE_PAY_CHANNEL);
		b.setStatus(UrgeToPayConstantUtil.URGE_STATUS_10);
		b.setStatusOperatorEmployeeId(UserUtils.getUser().getId());
		b.setUrgeTargetName(backlog.getCustomerName());
		b.setUrgeTargetPhone(backlog.getCustomerPhone());
		b.setUrgeTargetName2(backlog.getDesignerName());
		b.setUrgeTargetPhone2(backlog.getDesignerPhone());
		b.setCreateBy(UserUtils.getUser());
		bizBusinessUrgePayDao.insert(b);
		return b.getId();

		
	}
	
	
}