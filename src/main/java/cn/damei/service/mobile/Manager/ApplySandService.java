package cn.damei.service.mobile.Manager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessViewLogConstantUtil;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.ApplySandDao;
import cn.damei.entity.mobile.Manager.Supplier;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.Purchase;

/** 
* 材料管理--申请沙子水泥
* 
*/

@Service
@Transactional(readOnly=true)
public class ApplySandService extends  CrudService2<ApplySandDao,SwitchPanelOrderVo> {
	
	
	
	/**
	 * 查询项目经理名下的订单  订单状态等于200-施工中
	 * @param managerId
	 * @return
	 */
	public List<SwitchPanelOrderVo> getOrderListForSandByManagerId(Integer managerId){
		
		SwitchPanelOrderVo switchPanelOrderVo = new SwitchPanelOrderVo();
		//项目经理
		switchPanelOrderVo.setItemManagerId(managerId);
		//订单状态200施工中
		switchPanelOrderVo.setOrderStatus(OrderConstantUtil.ORDER_STATUS_CONFIRM_START_200);
		
		return  dao.getOrderListForSandByManagerId(switchPanelOrderVo);
	}
	
	/**
	 * 查询该订单下已经申请的最新一条沙子水泥采购单
	 * @param orderId
	 * @param manager 
	 * @return
	 */
	public Purchase selectPurchaseByOrderIdNewOne(Integer orderId, Manager manager) {
		
		Purchase purchase = new Purchase();
		//订单ID
		purchase.setOrderId(orderId);
		//采购单类型
		purchase.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);
		//日志表类型
		purchase.setBusinessType(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_BUSINESS_TYPE_401);
		//日志表唯一标识  手机号
		purchase.setBusinessViewerOnlyMark(manager.getPhone());
		//日志表项目经理ID
		purchase.setBusinessViewerEmployeeId(manager.getId());
		
		return dao.selectPurchaseByOrderIdNewOne(purchase);
	}
	
	/**
	 * 查询出所有的供应商及沙子水泥商品
	 * @param storeid
	 * @return
	 */
	public List<Supplier> findSupplierAndGoods(Integer orderId) {
		Supplier supplier = new Supplier();
		//订单ID
		supplier.setOrderId(orderId);
		//供应商状态
		supplier.setStatus(PurchaseConstantUtil.PURCHASE_STATUS_YES_1);
		//数据是否删除标记
		supplier.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		//商品是否沙子水泥
		supplier.setIsSandCement(PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1);
		
		return dao.findSupplierAndGoods(supplier);
	}
	
	/**
	 * 保存采购单
	 * @param orderId 订单编号
	 * @param totalCount 商品总数
	 * @param totalMoney 商品总价
	 * @param txtBeginDate 期望到场日期
	 * @param remark 备注
	 * @param supplierId 供应商
	 * @param delayTypeValue 是否给供应商上楼费
	 * @param floorNumber 楼层
	 * @param manager  项目经理
	 * @param purchaseType6 采购单类型
	 * @param purchaseAuxiliaryStatus10 采购单状态
	 * @param purchaseDelFlagNo0 采购单是否删除
	 * @return
	 */
	@Transactional(readOnly=false)
	public Purchase savePurchase(Integer orderId, String totalCount, String totalMoney, String txtBeginDate,
			String remark, String supplierId, String isElevator, String floorNumber, Manager manager, 
			String purchaseType6, String purchaseAuxiliaryStatus10, String purchaseDelFlagNo0){
		
		Date date = new Date();
		
		Purchase purcharse = new Purchase();
		//订单ID
		purcharse.setOrderId(orderId);
		//采购单编号
		purcharse.setPurchaseCode(purchaseCode());
		//采购单类型
		purcharse.setPurchaseType(purchaseType6);
		//期望送货日期
		purcharse.setApplyReceiveTime(DateUtils.parseDate(txtBeginDate));
		//申请人
		purcharse.setApplyEmployee(manager.getId());
		//申请时间
		purcharse.setApplyTime(date);
		//备注
		purcharse.setRemarks(remark);
		//采购单状态
		purcharse.setStatus(purchaseAuxiliaryStatus10);
		//采购单总价格
		purcharse.setTotalPrice(Double.parseDouble(totalMoney));
		//创建日期
		purcharse.setCreateDate(date);
		//更新日期
		purcharse.setUpdateDate(date);
		//是否删除标记
		purcharse.setDelFlag(purchaseDelFlagNo0);
		//供应商ID
		purcharse.setSupplierId(Integer.valueOf(supplierId));
		//是否有电梯
		purcharse.setIsElevator(isElevator);
		//上楼费金额
		purcharse.setUpstairsPay(null);
		//楼层
		if(StringUtils.isNotBlank(floorNumber) && Integer.valueOf(floorNumber)>0 ){
			purcharse.setUpstairsFloor(Integer.valueOf(floorNumber));
		}
		try {
			dao.savePurchase(purcharse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return purcharse;
		
	}
	
	/**
	 * 保存商品
	 * @param auxiliaryMaterialsId 商品ID
	 * @param laborPrice	商品价格
	 * @param brand		商品品牌
	 * @param goodCount	商品数量
	 * @param orderId	订单ID
	 * @param storeId	门店
	 * @param id	采购单ID
	 * @param manager 
	 * @param purchaseDelFlagNo0 
	 * @param purchaseSandIsSandCementYes1 
	 * @param i 
	 * @param string 
	 * @return
	 */
	@Transactional(readOnly=false)
	public boolean savePurchaseAuxiMate(String[] auxiliaryMaterialsId,String[] auxiliaryMaterialsNo, String[] laborPrice,
			String[] supplierPrice,String[] wangzhenPrice,String[] goodCount, String orderId, Integer purchaseId, Manager manager, String purchaseDelFlagNo0, 
			String purchaseSandIsSandCementYes1, String submmitStatus, Integer receivedAuxiMateCount) {
		
		boolean flag = false;
		List<AuxiliaryVo> list = new ArrayList<AuxiliaryVo>(); 
		Date date = new Date();
		if(null!=auxiliaryMaterialsId && auxiliaryMaterialsId.length>0){
			for(int v=0; v<auxiliaryMaterialsId.length; v++){
				if(null!=goodCount[v] && goodCount[v] != "" && goodCount[v] != "," && !goodCount[v].equals("0")){
					
					AuxiliaryVo AuxiliaryVo = new AuxiliaryVo();
					//采购单ID
					AuxiliaryVo.setPurchaseId(purchaseId);
					//辅料（沙子水泥）编码
					AuxiliaryVo.setAuxiMateCode(auxiliaryMaterialsNo[v]);
					//申请数量
					AuxiliaryVo.setAuxiMateCount(Integer.valueOf(goodCount[v]));
					//是否暂存
					AuxiliaryVo.setSubmmitStatus(submmitStatus);
					//创建日期
					AuxiliaryVo.setCreateDate(date);
					//更新日期
					AuxiliaryVo.setUpdateDate(date);
					//是否删除
					AuxiliaryVo.setDelFlag(purchaseDelFlagNo0);
					//订单ID
					AuxiliaryVo.setOrderId(Integer.valueOf(orderId));
					//已收货数量
					AuxiliaryVo.setReceivedAuxiMateCount(receivedAuxiMateCount);
					//欠货数量
					AuxiliaryVo.setOwedAuxiMateCount(Integer.valueOf(goodCount[v]));
					//商品价格
					AuxiliaryVo.setPrice(Double.parseDouble(laborPrice[v]));
					//供应商价格
					AuxiliaryVo.setSupplierPrice(Double.parseDouble(supplierPrice[v]));
					//网真价格
					AuxiliaryVo.setWangzhenPrice(Double.parseDouble(wangzhenPrice[v]));
					//是否沙子水泥
					AuxiliaryVo.setIsSandCement(purchaseSandIsSandCementYes1);
					
					list.add(AuxiliaryVo);
				}
			}
		}
		//如果有商品批量更新
		if(null!=list && list.size()>0){
			flag = dao.savePurchaseAuxiMate(list);
		}
		
		//如果保存商品失败，则删除采购单
		if(!flag){
			dao.deletePurchase(purchaseId);
		}
		return flag;
	}
	
	/**
	 * 根据orderId 查询沙子水泥采购单
	 * @param orderId
	 * @return
	 */
	public List<Purchase> selectPurchaseByOrderId(Integer orderId) {
		
		Purchase purchase = new Purchase();
		//订单ID
		purchase.setOrderId(orderId);
		//采购单类型
		purchase.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);
		//是否删除标记
		purchase.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		
		return dao.selectPurchaseByOrderId(purchase);
	}

	/**
	 * 查询沙子水泥采购单
	 * @param purchaseId
	 * @return
	 */
	public Purchase findPurchase(Integer purchaseId) {
		return dao.findPurchase(purchaseId);
	}

	/**
	 * 查询采购单商品
	 * @param purchaseId
	 * @return
	 */
	public List<AuxiliaryVo> findPurchaseGoods(Integer purchaseId) {
		
		AuxiliaryVo auxiliaryVo = new AuxiliaryVo();
		//是否沙子水泥
		auxiliaryVo.setIsSandCement(PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1);
		//采购单ID
		auxiliaryVo.setPurchaseId(purchaseId);
		//删除标记
		auxiliaryVo.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		
		return dao.findPurchaseGoods(auxiliaryVo);
	}
	
	
	/**
	 * 采购单编号生成方法
	 * 
	 * @param auxiliaryVo
	 * @param request
	 * @param model
	 */
	public String purchaseCode() {
		// 以PO开头
		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();

		// num和date
		PurchaseCode purchaseObj = dao.getCode();
		// 流水号+1
		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));
		// 更新数据库
		dao.updateCode(purchaseObj);

		// 格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());
		// 得到的流水号
		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);
		// 判断长度
		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {
			// 拼接采购单编号
			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}

		// 返回采购单编号
		return builder.toString();
	}

	/**
	 * 判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
	 * @param orderId
	 * @return
	 */
	public Integer findQcBillAcceptStatus(Integer orderId) {
		return dao.findQcBillAcceptStatus(orderId);
	}

	

	

	
}	