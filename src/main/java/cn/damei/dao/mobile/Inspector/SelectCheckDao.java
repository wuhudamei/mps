package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.IllegalModality;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.InspectKind;
import cn.damei.entity.mobile.Inspector.Recheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.mobile.Inspector.Order;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.QualityControl;

@MyBatisDao
public interface SelectCheckDao extends CrudDao2<Order>{


	List<Order> findOrderByInspectorId(Order or);


	BizQcBill findBizQcBillByOrderId(Integer orderId);


	List<InspectItem> findZanCun(int qcBillId);

	Integer findStoreByOrderId(Integer orderId);


	List<InspectKind> selectAllCheckItem(Integer storeId);


	List<ReportCheckDetailsPic> findPic(int qcBillId);
	

	Integer findSign(Integer qcBillId);
	

	void deleteCheckItemsByCheckId(int qcBillId);

	Integer saveBizQcBill(BizQcBill bizQcBill);

	void saveItems(InspectItem item);

	List<InspectKind> changeCheckItem(int inspectId);

	List<IllegalModality> findIllegalModalityByCheckItemId(int checkItemId);

	InspectItem selectCheckItemInfoByIllegalModalityId(int id);


	void updateCheckItem(InspectItem item);

	Integer selectCheckItemId(InspectItem item);

	void saveIllegalModality(IllegalModality modality);



	Double selectCheckItemScores(int checkItemId);

	void updateInspect(InspectKind kind);

	void savePic(ReportCheckDetailsPic reportCheckDetailsPic);

	void deletePic(Integer picId);

	PurchaseTwoCode getCode();

	void updateCode(PurchaseTwoCode code);

	void insertPurchase(PurchaseTwoCode purchaseObjVo);

	public  InspectKind findOrderIdByBillId(Integer billId);

	public void saveRecheck(Recheck recheck);

	public void saveRecheckCheckItem(Recheck recheck);


	void saveItemsAll(List<InspectItem> list);


	void updateCheckItemAll(List<InspectItem> hege);


	void saveRecheckCheckItemAll(List<Recheck> buhege);


	void saveIllegalModalityAll(List<IllegalModality> list);


	BizQcBill findTimeSpan(Integer orderId);

	public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId);

	public int findWorkerInfoByPackId(Integer packId);
	public  QualityControl findMessageInfoByInspectId(Integer inspectId);
	public  QualityControl findMessageInfoByInspectId2(Integer inspectId);


	List<Order> findOrderByLeaderInspectorId(Order or);


  BizQcBill findSelectCheckIsExist(Integer orderId);





	List<InspectItem>  selectScoresFromCheckItemRecord(Map<String,Object> map);









	 void batchSaveRecheckCheckItem(List<Recheck> rechecks);



	Integer findIsExistSelectQcBillById(String qcBillId);


	public void batchDeleteQcBillCheckItemBreak(int checkItemId);
	Order queryOrderid(int orderId);
}



