package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AuxiliaryMaterialsVo;
import cn.damei.entity.mobile.Manager.AuxiliaryPackageState;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.OrderVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoVo;


@MyBatisDao
public interface AuxiliaryApplyDao  extends CrudDao2<OrderVo>{


	public  List<OrderVo> orderByManagerId(Integer itemManagerId);
	

public List<AuxiliaryVo>	auxiliaryChoose(AuxiliaryVo auxiliaryVo);


public List<AuxiliaryVo>categoryItems(String categoryName,String orderId);


public List<AuxiliaryVo> checkIsSubmit(Integer orderId);


public  AuxiliaryVo     selectAuxiliaryById(String auxiMateCode);



public void   saveAuxliary(AuxiliaryVo auxiliaryVo);


public void updateAuxliary(AuxiliaryVo auxiliaryVo);


public  List<AuxiliaryVo> getApplyRecordByOrderId(Integer orderId);



public Integer savePurchase(PurchaseTwoVo purchase);



public AuxiliaryVo  getApplyRecordById(AuxiliaryVo AuxiliaryVo);



public PurchaseTwoCode getCode();


public void updateCode(PurchaseTwoCode code);


public void deleteAuxiliaryByCode(AuxiliaryVo AuxiliaryVo);




public List<PurchaseTwoVo> selectPurchaseByOrderId(Integer orderId);



public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseId(Integer purchaseCode);


	public List<AuxiliaryMaterialsVo> queryAuxiliaryMaterialList(Map<String, Object> map);
	

	public List<AuxiliaryMaterialsVo> querySandMaterialList(Map<String, Object> map);
	

	public List<AuxiliaryMaterialsVo> queryUsedAuxiliaryMaterialList(Map<String, Object> map);
	
	

	public List<AuxiliaryMaterialsVo> queryUsedSandMaterialList(Map<String,Object> map);
	
	public List<AuxiliaryVo>selectAuxiliaryByCodeList(List<AuxiliaryVo> list);


	public List<AuxiliaryPackageState> findAuxiliaryPackageState(Integer orderId);


	public List<AuxiliaryPackageState> findPurchseAmountByWorkType(Integer orderId);
	
	

	public PurchaseTwoVo findPurchaseDetails(Integer purchaseId);

	
}





