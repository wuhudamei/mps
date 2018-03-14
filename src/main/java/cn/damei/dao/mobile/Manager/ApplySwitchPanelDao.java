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

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月9日 上午10:16:31 
* 类说明 
*/
@MyBatisDao
public interface ApplySwitchPanelDao  extends CrudDao2<SwitchPanelOrderVo>{
	
	
	/**
	 * 根据项目经理查询所有订单  订单有状态设置
	 * @param managerId
	 * @return
	 */
	public List<SwitchPanelOrderVo> getOrderListForSwitchPanelByManagerId(Integer managerId); 
	
	
	/**
	 * 查询该门店下的开关面板
	 * @param storeId
	 * @return
	 */
	public List<SwitchPanelVo> selectSwitchPanelByStoreId(Integer storeId);
	
	/**
	 * 根据开关面板的门店id 和编号 查询 开关面板
	 * @param vo
	 * @return
	 */
	public SwitchPanelVo selectAttributeForSwitchPanel(SwitchPanelVo vo);
	
	/**
	 * 采购单编码
	 * 
	 */
	public PurchaseCode  getCode();
	/**
	 * 更新采购单编码
	 */
	public void updateCode(PurchaseCode code);
	

	/**
	 * 保存采购单
	 */
	public Integer savePurchase(PurchaseVo purchase);
	
	//更新采购单
	public void updatePurchaseByid(PurchaseVo purcharse);
	/**
	 * 保存开关面板申请记录
	 * @param vo
	 */
	public void saveSwitchPanel(SwitchPanelVo vo);
	
	/**
	 * 根据经理id查询门店id
	 * @param managerId
	 * @return
	 */
	public Integer selectstoreIdByManagerId(Integer managerId);
	
	
	
	
	/**
	 * 根据orderId 查询辅料采购单   
	 * @param orderId
	 * @return
	 */
	public List<PurchaseVo> selectPurchaseByOrderId(Integer orderId);
	public PurchaseVo selectPurchaseByOrderIdLimitOneOrderByTime(Integer orderId);
	
	
	/**
	 * 根据采购单编号查询采购单详情 开关面板type =2
	 * @param purchaseCode
	 * @return
	 */
	public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseCode(Integer purchaseId);	
	
	public Integer selectSwitchPanelCategoryId();
	
	/**
	 * 查询该订单历史申请记录和 合同的面积
	 * @param orderId
	 * @return
	 */
	public  PurchaseDetailsVo  selectOrderContractAreaAndTotalCount(Integer orderId);


	public void saveSwitchPanelPic(SwitchPanelPic switchPanelPic);

	public void deletePurchaseById(Integer purchaseId);
	
}