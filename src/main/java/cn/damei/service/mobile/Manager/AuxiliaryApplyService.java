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

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月28日 上午10:14:21 
* 类说明 
*/

@Service
@Transactional(readOnly=true)
public class AuxiliaryApplyService  extends  CrudService2<AuxiliaryApplyDao,OrderVo> {
public  List<OrderVo> orderByManagerId(Integer itemManagerId){
		
		
		return dao.orderByManagerId(itemManagerId);
	}
/**
 * 查询辅料	
 * @return
 */
public   List<AuxiliaryVo>	auxiliaryChoose(AuxiliaryVo auxiliaryVo){
	
	return dao.auxiliaryChoose(auxiliaryVo);
}



public List<AuxiliaryVo>	 categoryItems(String categoryName,String orderId){
	return dao.categoryItems(categoryName,orderId);
}



/**
 * 根据订单id查询是否申请过辅料记录,且提交未结算的
 * @return
 */
public  List<AuxiliaryVo>  checkIsSubmit(Integer orderId){
	
	return dao.checkIsSubmit(orderId);
}

/**
 * 根据辅料编号查询辅料表,到结算页
 * @return
 */
public  AuxiliaryVo     selectAuxiliaryById(String auxiMateCode){
	
	return dao.selectAuxiliaryById(auxiMateCode);
}
/**
 * 暂存购买辅料记录到  辅料记录表  biz_purchase-auxi_mate
 * @param auxiliaryVo
 */
@Transactional(readOnly=false)
public void   saveAuxliary(AuxiliaryVo auxiliaryVo){
	 dao.saveAuxliary(auxiliaryVo);
}/**
 * 根据orderId 和状态为NO 查看辅料申请记录中是否有记录
 * @param orderId
 * @return
 */
public  List<AuxiliaryVo> getApplyRecordByOrderId(Integer orderId){
	
	return dao.getApplyRecordByOrderId(orderId);
}/**
 * 保存采购单
 */
@Transactional(readOnly=false)
public Integer savePurchase(PurchaseTwoVo purchase){
	
return 	dao.savePurchase(purchase);
}

/**
 * 更新辅料表  (count  和状态)
 * @param auxiliaryVo
 */
@Transactional(readOnly=false)
public void updateAuxliary(AuxiliaryVo auxiliaryVo){
	
	dao.updateAuxliary(auxiliaryVo);
}


/*
 * 
 * 根据订单id和code查询辅料申请记录
 */
public AuxiliaryVo  getApplyRecordById(AuxiliaryVo auxiMateCode){
	
	return dao.getApplyRecordById( auxiMateCode);
}

/**
 * 采购单编码
 * 
 */
public PurchaseTwoCode getCode(){
	
	return dao.getCode();
}
/**
 * 更新采购单编码
 */
@Transactional(readOnly=false)
public void updateCode(PurchaseTwoCode code){
	dao.updateCode(code);
}
/**
 * 根据辅材编号删除辅材申请记录 orderId   status --NO
 * @param auxiMateCode
 */
@Transactional(readOnly=false)
public void deleteAuxiliaryByCode(AuxiliaryVo auxiliaryVo){
	
	dao.deleteAuxiliaryByCode(auxiliaryVo);
}

/**
 * 根据orderId 查询辅料采购单   
 * @param orderId
 * @return
 */
public List<PurchaseTwoVo> selectPurchaseByOrderId(Integer orderId){
	
	return dao.selectPurchaseByOrderId(orderId);
}

/**
 * 根据采购单id查询采购单详情
 * @param purchaseCode
 * @return
 */
public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseId(Integer purchaseId){
	
	return dao.selectPurchaseDetailsByPurchaseId(purchaseId);
}

	/**
	 * 查询结算单中辅料列表
	 * @param orderId
	 * @param taskpackageTemplatId
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> queryAuxiliaryMaterialList(Integer orderId, Integer taskpackageTemplatId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		return dao.queryAuxiliaryMaterialList(map);
	}
	
	/**
	 * 查询结算单中的沙子水泥列表
	 * @param orderId
	 * @param taskpackageTemplatId
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> querySandMaterialList(Integer orderId, Integer taskpackageTemplatId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		return dao.querySandMaterialList(map);
	}
	
	/**
	 * 更新结算单时查询结算单中辅料列表
	 * @param orderId
	 * @param taskpackageTemplatId
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> queryUsedAuxiliaryMaterialList(Integer orderId, Integer taskpackageTemplatId,Integer orderTaskPackageId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("measurementUnitType", ConstantUtils.AUXILIARY_MEASUREMENT_UNIT_TYPE);
		map.put("submmitStatus", ConstantUtils.SUBMMIT_STATUS_YES);
		map.put("orderTaskpackageId", orderTaskPackageId);
		return dao.queryUsedAuxiliaryMaterialList(map);
	}
	
	/**
	 * 更新结算单时查询结算单中沙子水泥列表
	 * @return
	 */
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
	
	/**
	 * 辅料申请校验
	 * 如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】
	 * 【1】：任务包限制
     * 【1.1】：如果没有工种对应的任务包模板，不做限制
     * 		【isCanApplyAuxiliary:1】【不限】
     * 【1.2】：如果没有生产该模板的任务包，必须生产任务包
     * 		【isCanApplyAuxiliary:2】【不可】：“水电路改造工程”任务包的未生成，请您联系工程部的拆单员进行拆单。
     * 【1.3】：如果存在任务包，但是任务包已经验收，则不允许申请其对应的辅料
     * 		【isCanApplyAuxiliary:3】【不可】：水电路改造工程”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品
     * 【2】：金额限制
     * 【2.1】：存在任务包，但是任务包预算金额为空，必须要有金额
     * 		【isCanApplyAuxiliary:4】【不可】：“水电路改造工程”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。
     * 【2.2】：任务包预算金额不为空，但是申请辅料预算比例为空，不做限制
     * 		【isCanApplyAuxiliary:5】【不限】
     * 【2.3】：任务包预算金额*比例-已申请金额<0，不可以申请
     * 		【isCanApplyAuxiliary:6】【不可】【选择页面】：您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。
     * 		【isCanApplyAuxiliary:6】【不可】【提交页面】：您【油】类辅料商品申请金额还剩余0元，请删除高亮显示的【油】辅料商品。
     * 【2.4】：任务包预算金额*比例-已申请金额>0，可以申请,注意校验
     * 		【isCanApplyAuxiliary:7】【限制】您【水电】类辅料商品申请金额还剩余10.00元，请修改【水电】辅料商品数量，如果商品数量确实不够请联系拆单员修改任务包预算金额。
	 * @param orderId
	 * @return
	 */
	public List<AuxiliaryPackageState> findAuxiliaryPackageState(Integer orderId) {
		List<AuxiliaryPackageState> list = dao.findAuxiliaryPackageState(orderId);
		//查询该订单不同工种已经申请的辅料金额
		List<AuxiliaryPackageState> purchaseList = dao.findPurchseAmountByWorkType(orderId);
		if(null != purchaseList && purchaseList.size() > 0){
			if(null != list && list.size() > 0){
				for(AuxiliaryPackageState a:list){
					//如果任务包预算金额不为空，申请辅料预算比例不为空时
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
	
	/**
	 * 根据采购单id查询采购单信息
	 * @param purchaseId
	 * @return
	 */
	public PurchaseTwoVo findPurchaseDetails(Integer purchaseId) {
		return dao.findPurchaseDetails(purchaseId);
	}
}
