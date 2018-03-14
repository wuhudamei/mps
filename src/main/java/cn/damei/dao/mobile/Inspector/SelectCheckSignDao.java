package cn.damei.dao.mobile.Inspector;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.mobile.Inspector.Sign;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;


@MyBatisDao
public interface SelectCheckSignDao extends CrudDao2<Sign>{
	



	public String 	getOrderLngLatByOrderId(Integer orderId);
	

	public void inspectorSign(Sign inspectSign);
	

	public Integer findInspectSignRecord(Integer inspectId);
	
	

	public void updateInspectRecord(Sign InspectSign);


	public Integer insertQcBill(BizQcBill bizQcBill);
	
	

	PurchaseTwoCode getCode();

	void updateCode(PurchaseTwoCode code);

	void insertPurchase(PurchaseTwoCode purchaseObjVo);

	

}
