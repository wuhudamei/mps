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

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月28日 上午10:16:31 
* 类说明 
*/
@MyBatisDao
public interface AuxiliaryApplyDao  extends CrudDao2<OrderVo>{

	/**
	 * 根据项目经理查询订单
	 * @param itemManagerId
	 * @return
	 */
	public  List<OrderVo> orderByManagerId(Integer itemManagerId);
	
/**
 * 查询辅料	
 * @return
 */
public List<AuxiliaryVo>	auxiliaryChoose(AuxiliaryVo auxiliaryVo);

/**
 * ajax 根据分类查询辅料
 * @param categoryName
 * @return
 */
public List<AuxiliaryVo>categoryItems(String categoryName,String orderId);

/**
 * 根据订单id查询是否申请过辅料记录,且提交未结算的  (返回辅材编号和数量)
 * @return
 */
public List<AuxiliaryVo> checkIsSubmit(Integer orderId);

/**
 * 根据id查询辅料表,到结算页
 * @return
 */
public  AuxiliaryVo     selectAuxiliaryById(String auxiMateCode);


/**
 * 暂存购买辅料记录到  辅料记录表  biz_purchase-auxi_mate
 * @param auxiliaryVo
 */
public void   saveAuxliary(AuxiliaryVo auxiliaryVo);

/**
 * 更新辅料表  (count  和状态)
 * @param auxiliaryVo
 */
public void updateAuxliary(AuxiliaryVo auxiliaryVo);

/**
 * 根据orderId 和状态为NO 查看辅料申请记录中是否有记录
 * @param orderId
 * @return
 */
public  List<AuxiliaryVo> getApplyRecordByOrderId(Integer orderId);


/**
 * 保存采购单
 */
public Integer savePurchase(PurchaseTwoVo purchase);


/*
 * 
 * 根据编号和订单id查询辅料申请记录
 */
public AuxiliaryVo  getApplyRecordById(AuxiliaryVo AuxiliaryVo);


/**
 * 采购单编码
 * 
 */
public PurchaseTwoCode getCode();

/**
 * 更新采购单编码
 */
public void updateCode(PurchaseTwoCode code);

/**
 * 根据辅材编号删除辅材申请记录
 * @param AuxiliaryVo
 */
public void deleteAuxiliaryByCode(AuxiliaryVo AuxiliaryVo);



/**
 * 根据orderId 查询辅料采购单   
 * @param orderId
 * @return
 */
public List<PurchaseTwoVo> selectPurchaseByOrderId(Integer orderId);


/**
 * 根据采购单ID查询采购单详情
 * @param purchaseCode
 * @return
 */
public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseId(Integer purchaseCode);

	/**
	 * 查询结算单中辅料列表
	 * @param map
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> queryAuxiliaryMaterialList(Map<String, Object> map);
	
	/**
	 * 查询结算单中沙子水泥列表
	 */
	public List<AuxiliaryMaterialsVo> querySandMaterialList(Map<String, Object> map);
	
	/**
	 * 更新结算单时查询结算单中辅料列表
	 * @param map
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> queryUsedAuxiliaryMaterialList(Map<String, Object> map);
	
	
	/**
	 * 更新结算单时查询结算单中沙子水泥列表
	 * @param map
	 * @return
	 */
	public List<AuxiliaryMaterialsVo> queryUsedSandMaterialList(Map<String,Object> map);
	
	public List<AuxiliaryVo>selectAuxiliaryByCodeList(List<AuxiliaryVo> list);

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
	public List<AuxiliaryPackageState> findAuxiliaryPackageState(Integer orderId);

	/**
	 * 查询该订单不同工种已经申请的辅料金额
	 * @param orderId
	 * @return
	 */
	public List<AuxiliaryPackageState> findPurchseAmountByWorkType(Integer orderId);
	
	
	/**
	 * 根据采购单id查询采购单信息
	 * @param purchaseId
	 * @return
	 */
	public PurchaseTwoVo findPurchaseDetails(Integer purchaseId);

	
}





