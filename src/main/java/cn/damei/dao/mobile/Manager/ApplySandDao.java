package cn.damei.dao.mobile.Manager;


import java.util.List;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Supplier;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.Purchase;

/** 
* 材料管理--申请沙子水泥
*/
@MyBatisDao
public interface ApplySandDao  extends CrudDao2<SwitchPanelOrderVo>{
	
	
	/**
	 * 根据项目经理查询所有订单  订单状态等于200-施工中
	 * @param managerId
	 * @return
	 */
	public List<SwitchPanelOrderVo> getOrderListForSandByManagerId(SwitchPanelOrderVo switchPanelOrderVo); 
	
	/**
	 * 查询该订单下已经申请的最新一条沙子水泥采购单
	 * @param purchase
	 * @return
	 */
	public Purchase selectPurchaseByOrderIdNewOne(Purchase purchase);
	
	/**
	 * 查询出所有的供应商及沙子水泥商品
	 * @param supplier
	 * @return
	 */
	public List<Supplier> findSupplierAndGoods(Supplier supplier);


	/**
	 * 保存采购单
	 * @param purcharse
	 * @return
	 */
	public Integer savePurchase(Purchase purcharse);
	
	/**
	 * 批量保存商品
	 * @param list
	 * @return
	 */
	public boolean savePurchaseAuxiMate(List<AuxiliaryVo> list);
	
	/**
	 * 根据orderId 查询沙子水泥采购单
	 * @param purchase
	 * @return
	 */
	public List<Purchase> selectPurchaseByOrderId(Purchase purchase);
	
	/**
	 * 查询沙子水泥采购单
	 * @param purchaseId
	 * @return
	 */
	public Purchase findPurchase(Integer purchaseId);

	/**
	 * 查询采购单商品
	 * @param auxiliaryVo
	 * @return
	 */
	public List<AuxiliaryVo> findPurchaseGoods(AuxiliaryVo auxiliaryVo);

	/**
	 * 如果保存商品失败，则删除采购单
	 * @param purchaseId
	 */
	public void deletePurchase(Integer purchaseId);
	
	/**
	 * 采购单编码
	 * @return
	 */
	public PurchaseCode  getCode();
	
	/**
	 * 更新采购单编码
	 * @param code
	 */
	public void updateCode(PurchaseCode code);

	/**
	 * 判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
	 * @param orderId
	 * @return
	 */
	public Integer findQcBillAcceptStatus(Integer orderId);

	
}