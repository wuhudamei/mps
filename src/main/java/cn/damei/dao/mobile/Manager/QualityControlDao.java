package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.entity.mobile.Manager.BizQcBill;
import cn.damei.entity.mobile.Manager.BizQcCheckNode;
import cn.damei.entity.mobile.Manager.Inspector;
import cn.damei.entity.mobile.Manager.QualityControl;

@MyBatisDao
public interface QualityControlDao extends CrudDao2<QualityControl>{

	//通过项目经理id查询项目经理下所有的订单
	List<QualityControl> findOrderByItemManagerId(Integer itemManagerId);

	//通过订单id查询订单详情
	QualityControl findOrderById(Integer id);

	//通过质检员id查询质检员信息
	Inspector findPhoneByOrderInspectorId(Integer orderInspectorId);

	//保存约检单
	void insertQcBill(BizQcBill bizQcBill);

	void saveCode();
	ReCheckCode getCode();
	void updateCode(ReCheckCode code);

	//根据门店查询所有约检节点
	List<BizQcCheckNode> findBizQcCheckNodeByStoreId(QualityControl quality);

	//根据订单id查询所有约检单
	String findBizQcBillByOrderId(BizQcBill bizQcBill);

	void insertPurchase(PurchaseTwoCode purchaseObjVo);

	//根据订单id，查询约检单biz_qc_bill中（约检节点最大）的一条记录
	BizQcBill findMax(BizQcBill bizQcBill);

	//根据订单ID 查询所有传统未约检的节点
	List<BizQcCheckNode> findTraditionalNode(int id);

	//根据订单查询是否有未验收的节点
	Integer findNumber(int id);

	BizQcBill findQcBillByOrderIdForCompleted(Integer orderId);

	/**
	 * 判断该节点是否申请过
	 * @param bizQcBill
	 * @return
	 */
	Integer checkIdIsExists(BizQcBill bizQcBill);

	/**
	 * 距离上次申请时间是否超过5分钟
	 * @param bizQcBill
	 * @return
	 */
	Integer isOverTime(BizQcBill bizQcBill);

	/**
	 * 根据订单查询所有的约检记录
	 * @param bizQcBill
	 * @return
	 */
	List<BizQcBill> findBizQcBillRecordByOrderId(BizQcBill bizQcBill);

	/**
	 * 2017-04-21  门店下有质检申请数量限制
	 */
	 Integer comparePqcDateIsAllowed(String date,Integer orderId);
	
	//批量插入申请约检图片
	void saveCheckitemPicAll(List<BusinessPic> pList);
	 List<BusinessPic> findPic(int businessIdInt);
	 int findPicNum(int businessIdInt);


	/**
	 * 根据约检id 查询是否为基装节点  是为1  否为0
	 * @param qcNodeId
	 * @return 返回基装节点字段
	 */
	Integer findIsBasicByQcNodeId(int qcNodeId);


	/**
	 * 根据(准产业)订单id 查询是否申请开关面板
	 * @param orderId
	 * @return
	 */
 Integer checkIsApplyPanelByOrderId(Integer orderId);


	/**
	 * 查询订单下一节点是否为基装节点
	 * @return
	 */
	Map<String,String> checkIsForBasicNodeByMap(Map<String,String>map);


 String findMaxNodeIdByOrderId(String orderId);

String findCheckNode(Integer qcCheckNodeId);

Integer findFirstNodeIdByOrderId(String orderId);

}
