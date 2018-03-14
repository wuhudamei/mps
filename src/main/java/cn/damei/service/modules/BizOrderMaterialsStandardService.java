/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderMaterialsStandardDao;
import cn.damei.entity.modules.BizOrderMaterialsStandard;

/**
 * 标化辅料（筒灯灯带）订单Service
 * @author lft
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class BizOrderMaterialsStandardService extends CrudService2<BizOrderMaterialsStandardDao, BizOrderMaterialsStandard> {
	public List<BizOrderMaterialsStandard> getMaterialsByOrderId(String orderId,String materialsLargeType){
		return dao.getMaterialsByOrderId(orderId,materialsLargeType);
	}
	public String selectBillVile(String billId){
		return dao.selectBillView(billId);
	}
	@Transactional
	public void updateBill(String isViewed,
				Date viewDatetime,
			String id){
		dao.updateBill(isViewed, viewDatetime, id);
	}
	
	public String getMaterialIsView(String orderId){
		return dao.getMaterialIsView(orderId);
	}
	public List<BizOrderMaterialsStandard> getList(String storeId,String orderId,String materialsLargeType) {
		return dao.getList(storeId,orderId,materialsLargeType);
	}
	
	public BizOrderMaterialsStandard get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderMaterialsStandard> findList(BizOrderMaterialsStandard bizOrderMaterialsStandard) {
		return super.findList(bizOrderMaterialsStandard);
	}
	
	public Page<BizOrderMaterialsStandard> findPage(Page<BizOrderMaterialsStandard> page, BizOrderMaterialsStandard bizOrderMaterialsStandard) {
		return super.findPage(page, bizOrderMaterialsStandard);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderMaterialsStandard bizOrderMaterialsStandard) {
		super.save(bizOrderMaterialsStandard);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderMaterialsStandard bizOrderMaterialsStandard) {
		super.delete(bizOrderMaterialsStandard);
	}
	@Transactional(readOnly = false)
	public void update(BizOrderMaterialsStandard bizOrderMaterialsStandard) {
		dao.update(bizOrderMaterialsStandard);
	}
	public BizOrderMaterialsStandard getBizOrderMaterialsStandard(String materialsStandardId,String orderId){
		return dao.getBizOrderMaterialsStandard(materialsStandardId, orderId);
	}
	@Transactional(readOnly = false)
	public void updateOrderMaterialsByOrderIdAndStandId(BizOrderMaterialsStandard bizOrderMaterialsStandard){
		dao.updateOrderMaterialsByOrderIdAndStandId(bizOrderMaterialsStandard);
	}
	
}