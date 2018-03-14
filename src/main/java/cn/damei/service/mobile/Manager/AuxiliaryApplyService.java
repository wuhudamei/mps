package cn.damei.service.mobile.Manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.AuxiliaryApplyDao;
import cn.damei.entity.mobile.Manager.AuxiliaryMaterialsVo;
import cn.damei.entity.mobile.Manager.AuxiliaryPackageState;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.OrderVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoVo;



@Service
@Transactional(readOnly=true)
public class AuxiliaryApplyService  extends  CrudService2<AuxiliaryApplyDao,OrderVo> {
public  List<OrderVo> orderByManagerId(Integer itemManagerId){
		
		
		return dao.orderByManagerId(itemManagerId);
	}

public   List<AuxiliaryVo>	auxiliaryChoose(AuxiliaryVo auxiliaryVo){
	
	return dao.auxiliaryChoose(auxiliaryVo);
}



public List<AuxiliaryVo>	 categoryItems(String categoryName,String orderId){
	return dao.categoryItems(categoryName,orderId);
}




public  List<AuxiliaryVo>  checkIsSubmit(Integer orderId){
	
	return dao.checkIsSubmit(orderId);
}


public  AuxiliaryVo     selectAuxiliaryById(String auxiMateCode){
	
	return dao.selectAuxiliaryById(auxiMateCode);
}

@Transactional(readOnly=false)
public void   saveAuxliary(AuxiliaryVo auxiliaryVo){
	 dao.saveAuxliary(auxiliaryVo);
}
public  List<AuxiliaryVo> getApplyRecordByOrderId(Integer orderId){
	
	return dao.getApplyRecordByOrderId(orderId);
}
@Transactional(readOnly=false)
public Integer savePurchase(PurchaseTwoVo purchase){
	
return 	dao.savePurchase(purchase);
}


@Transactional(readOnly=false)
public void updateAuxliary(AuxiliaryVo auxiliaryVo){
	
	dao.updateAuxliary(auxiliaryVo);
}



public AuxiliaryVo  getApplyRecordById(AuxiliaryVo auxiMateCode){
	
	return dao.getApplyRecordById( auxiMateCode);
}


public PurchaseTwoCode getCode(){
	
	return dao.getCode();
}

@Transactional(readOnly=false)
public void updateCode(PurchaseTwoCode code){
	dao.updateCode(code);
}

@Transactional(readOnly=false)
public void deleteAuxiliaryByCode(AuxiliaryVo auxiliaryVo){
	
	dao.deleteAuxiliaryByCode(auxiliaryVo);
}


public List<PurchaseTwoVo> selectPurchaseByOrderId(Integer orderId){
	
	return dao.selectPurchaseByOrderId(orderId);
}


public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseId(Integer purchaseId){
	
	return dao.selectPurchaseDetailsByPurchaseId(purchaseId);
}


	public List<AuxiliaryMaterialsVo> queryAuxiliaryMaterialList(Integer orderId, Integer taskpackageTemplatId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		return dao.queryAuxiliaryMaterialList(map);
	}
	

	public List<AuxiliaryMaterialsVo> querySandMaterialList(Integer orderId, Integer taskpackageTemplatId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		return dao.querySandMaterialList(map);
	}
	

	public List<AuxiliaryMaterialsVo> queryUsedAuxiliaryMaterialList(Integer orderId, Integer taskpackageTemplatId,Integer orderTaskPackageId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		map.put("orderTaskpackageId", orderTaskPackageId);
		return dao.queryUsedAuxiliaryMaterialList(map);
	}
	

	public List<AuxiliaryMaterialsVo> queryUsedSandMaterialList(Integer orderId, Integer taskpackageTemplatId,Integer orderTaskPackageId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		map.put("orderTaskpackageId", orderTaskPackageId);
		return dao.queryUsedSandMaterialList(map);
	}
	
	public List<AuxiliaryVo>selectAuxiliaryByCodeList(List<AuxiliaryVo> list){
		
		return dao.selectAuxiliaryByCodeList(list);
		
		
	}
	

	public List<AuxiliaryPackageState> findAuxiliaryPackageState(Integer orderId) {
		List<AuxiliaryPackageState> list = dao.findAuxiliaryPackageState(orderId);

		List<AuxiliaryPackageState> purchaseList = dao.findPurchseAmountByWorkType(orderId);
		if(null != purchaseList && purchaseList.size() > 0){
			if(null != list && list.size() > 0){
				for(AuxiliaryPackageState a:list){

					if(PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_7.equals(a.getIsCanApplyAuxiliary())){
						for(AuxiliaryPackageState b:purchaseList){
							if(a.getEmpWorkType().equals(b.getEmpWorkType())){
								double afterAmount = a.getTotalAmount()-b.getBeginAmount();
								a.setBeginAmount(b.getBeginAmount());
								if(afterAmount>0d){
									a.setAfterAmount(afterAmount);
								}else{
									a.setAfterAmount(0d);
									a.setIsCanApplyAuxiliary(PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_6);
								}
							}
						}
					}
				}
			}
		}
		return list;
	}
	

	public PurchaseTwoVo findPurchaseDetails(Integer purchaseId) {
		return dao.findPurchaseDetails(purchaseId);
	}
}
