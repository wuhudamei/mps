package cn.damei.dao.mobile.Inspector;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.mobile.Inspector.Sign;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;

/**
 * 质检系统约检
 * @author Administrator
 *
 */
@MyBatisDao
public interface SelectCheckSignDao extends CrudDao2<Sign>{
	


	//订单经纬度
	public String 	getOrderLngLatByOrderId(Integer orderId);
	
	/**
	 * 质检员签到
	 * @param detail
	 */
	public void inspectorSign(Sign inspectSign);
	
	/**
	 * 该质检单是否有签到记录
	 * @param inspectId
	 */
	public Integer findInspectSignRecord(Integer inspectId);
	
	
	/**
	 * 更新质检员签到记录
	 * @param detail
	 */
	public void updateInspectRecord(Sign InspectSign);

	/**
	 * 创建抽检单
	 * @param bizQcBill
	 */
	public Integer insertQcBill(BizQcBill bizQcBill);
	
	
	/**
	 * 获取抽检单编码
	 * @return
	 */
	PurchaseTwoCode getCode();
	/**
	 * 更新抽检单编码
	 * @param code
	 */
	void updateCode(PurchaseTwoCode code);
	/**
	 * 插入抽检单编码
	 * @param purchaseObjVo
	 */
	void insertPurchase(PurchaseTwoCode purchaseObjVo);

	

}
