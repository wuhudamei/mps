package cn.damei.service.mobile.Inspector;


import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.dao.mobile.Inspector.SelectCheckSignDao;
import cn.damei.entity.mobile.Inspector.Sign;

/**
 * 质检系统   抽检  签到
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class SelectCheckSignService extends CrudService2<SelectCheckSignDao,Sign>{

	/**
	 * 得到订单经纬度
	 * @param orderId
	 * @return
	 */
	public  String	getOrderLngLatByOrderId(Integer orderId){
		
		return dao.getOrderLngLatByOrderId(orderId);
	}



	/**
	 * 质检员签到
	 * @param detail
	 */
	@Transactional(readOnly=false)
	public void inspectorSign(Sign InspectSign){
		
		dao.inspectorSign(InspectSign);
	}
	
	
	/**
	 * 该质检单是否有签到记录
	 * @param inspectId
	 */
	public Integer findInspectSignRecord(Integer inspectId){
		
		return dao.findInspectSignRecord(inspectId);
	}
	
	/**
	 * 更新质检员的签到记录
	 * @param detail
	 */
	@Transactional(readOnly=false)
	public void updateInspectRecord(Sign detail){
		
		dao.updateInspectRecord(detail);
	}
	
	/**
	 * 创建抽检单
	 * @param bizQcBill
	 * @return 
	 */
	@Transactional(readOnly=false)
	public Integer insertQcBill(BizQcBill bizQcBill) {
		return dao.insertQcBill(bizQcBill);
	}



	/**
	 * 获取抽检单编码
	 * @return
	 */
	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}
	/**
	 * 更新抽检单编码
	 * @param code
	 */
	@Transactional(readOnly=false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}
	/**
	 * 插入抽检单编码
	 * @param purchaseObjVo
	 */
	@Transactional(readOnly=false)
	public void insertPurchase(PurchaseTwoCode purchaseObjVo) {
		dao.insertPurchase(purchaseObjVo);
	}



	
}
