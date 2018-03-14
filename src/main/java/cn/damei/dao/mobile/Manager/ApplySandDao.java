package cn.damei.dao.mobile.Manager;


import java.util.List;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Supplier;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.Purchase;


@MyBatisDao
public interface ApplySandDao  extends CrudDao2<SwitchPanelOrderVo>{
	
	

	public List<SwitchPanelOrderVo> getOrderListForSandByManagerId(SwitchPanelOrderVo switchPanelOrderVo); 
	

	public Purchase selectPurchaseByOrderIdNewOne(Purchase purchase);
	

	public List<Supplier> findSupplierAndGoods(Supplier supplier);



	public Integer savePurchase(Purchase purcharse);
	

	public boolean savePurchaseAuxiMate(List<AuxiliaryVo> list);
	

	public List<Purchase> selectPurchaseByOrderId(Purchase purchase);
	

	public Purchase findPurchase(Integer purchaseId);


	public List<AuxiliaryVo> findPurchaseGoods(AuxiliaryVo auxiliaryVo);


	public void deletePurchase(Integer purchaseId);
	

	public PurchaseCode  getCode();
	

	public void updateCode(PurchaseCode code);


	public Integer findQcBillAcceptStatus(Integer orderId);

	
}