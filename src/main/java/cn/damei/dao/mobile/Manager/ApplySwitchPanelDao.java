package cn.damei.dao.mobile.Manager;


import java.util.List;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.PurchaseVo;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.SwitchPanelPic;
import cn.damei.entity.mobile.Manager.SwitchPanelVo;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;


@MyBatisDao
public interface ApplySwitchPanelDao  extends CrudDao2<SwitchPanelOrderVo>{
	
	

	public List<SwitchPanelOrderVo> getOrderListForSwitchPanelByManagerId(Integer managerId); 
	
	

	public List<SwitchPanelVo> selectSwitchPanelByStoreId(Integer storeId);
	

	public SwitchPanelVo selectAttributeForSwitchPanel(SwitchPanelVo vo);
	

	public PurchaseCode  getCode();

	public void updateCode(PurchaseCode code);
	


	public Integer savePurchase(PurchaseVo purchase);
	

	public void updatePurchaseByid(PurchaseVo purcharse);

	public void saveSwitchPanel(SwitchPanelVo vo);
	

	public Integer selectstoreIdByManagerId(Integer managerId);
	
	
	
	

	public List<PurchaseVo> selectPurchaseByOrderId(Integer orderId);
	public PurchaseVo selectPurchaseByOrderIdLimitOneOrderByTime(Integer orderId);
	
	

	public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseCode(Integer purchaseId);	
	
	public Integer selectSwitchPanelCategoryId();
	

	public  PurchaseDetailsVo  selectOrderContractAreaAndTotalCount(Integer orderId);


	public void saveSwitchPanelPic(SwitchPanelPic switchPanelPic);

	public void deletePurchaseById(Integer purchaseId);
	
}