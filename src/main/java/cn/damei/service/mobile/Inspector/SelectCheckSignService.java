package cn.damei.service.mobile.Inspector;


import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.dao.mobile.Inspector.SelectCheckSignDao;
import cn.damei.entity.mobile.Inspector.Sign;


@Service
@Transactional(readOnly=true)
public class SelectCheckSignService extends CrudService2<SelectCheckSignDao,Sign>{


	public  String	getOrderLngLatByOrderId(Integer orderId){
		
		return dao.getOrderLngLatByOrderId(orderId);
	}




	@Transactional(readOnly=false)
	public void inspectorSign(Sign InspectSign){
		
		dao.inspectorSign(InspectSign);
	}
	
	

	public Integer findInspectSignRecord(Integer inspectId){
		
		return dao.findInspectSignRecord(inspectId);
	}
	

	@Transactional(readOnly=false)
	public void updateInspectRecord(Sign detail){
		
		dao.updateInspectRecord(detail);
	}
	

	@Transactional(readOnly=false)
	public Integer insertQcBill(BizQcBill bizQcBill) {
		return dao.insertQcBill(bizQcBill);
	}




	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}

	@Transactional(readOnly=false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}

	@Transactional(readOnly=false)
	public void insertPurchase(PurchaseTwoCode purchaseObjVo) {
		dao.insertPurchase(purchaseObjVo);
	}



	
}
