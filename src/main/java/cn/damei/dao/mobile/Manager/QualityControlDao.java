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


	List<QualityControl> findOrderByItemManagerId(Integer itemManagerId);


	QualityControl findOrderById(Integer id);


	Inspector findPhoneByOrderInspectorId(Integer orderInspectorId);


	void insertQcBill(BizQcBill bizQcBill);

	void saveCode();
	ReCheckCode getCode();
	void updateCode(ReCheckCode code);


	List<BizQcCheckNode> findBizQcCheckNodeByStoreId(QualityControl quality);


	String findBizQcBillByOrderId(BizQcBill bizQcBill);

	void insertPurchase(PurchaseTwoCode purchaseObjVo);


	BizQcBill findMax(BizQcBill bizQcBill);


	List<BizQcCheckNode> findTraditionalNode(int id);


	Integer findNumber(int id);

	BizQcBill findQcBillByOrderIdForCompleted(Integer orderId);


	Integer checkIdIsExists(BizQcBill bizQcBill);


	Integer isOverTime(BizQcBill bizQcBill);


	List<BizQcBill> findBizQcBillRecordByOrderId(BizQcBill bizQcBill);


	 Integer comparePqcDateIsAllowed(String date,Integer orderId);
	

	void saveCheckitemPicAll(List<BusinessPic> pList);
	 List<BusinessPic> findPic(int businessIdInt);
	 int findPicNum(int businessIdInt);



	Integer findIsBasicByQcNodeId(int qcNodeId);



 Integer checkIsApplyPanelByOrderId(Integer orderId);



	Map<String,String> checkIsForBasicNodeByMap(Map<String,String>map);


 String findMaxNodeIdByOrderId(String orderId);

String findCheckNode(Integer qcCheckNodeId);

Integer findFirstNodeIdByOrderId(String orderId);

}
