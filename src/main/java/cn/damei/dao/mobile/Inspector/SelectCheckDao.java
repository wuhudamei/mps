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
/**
 * 质检端   抽检
 * @author Administrator
 *
 */
@MyBatisDao
public interface SelectCheckDao extends CrudDao2<Order>{

	/**
	 * 通过质检单id,搜索文本 查询订单
	 * @param or
	 * @return
	 */
	List<Order> findOrderByInspectorId(Order or);

	/**
	 * 根据订单id查询抽检单
	 * @param orderId
	 * @return
	 */
	BizQcBill findBizQcBillByOrderId(Integer orderId);

	/**
	 * 通过质检单id查询是否有暂存记录
	 * @param qcBillId
	 * @return
	 */
	List<InspectItem> findZanCun(int qcBillId);
	/**
	 * 根据订单id查询订单所在门店
	 * @param orderId
	 * @return
	 */
	Integer findStoreByOrderId(Integer orderId);

	/**
	 * 查询检查分类和检查项
	 * @param storeId 
	 * @return
	 */
	List<InspectKind> selectAllCheckItem(Integer storeId);

	/**
	 * 根据抽检单id查询图片
	 * @param qcBillId
	 * @return
	 */
	List<ReportCheckDetailsPic> findPic(int qcBillId);
	
	/**
	 * 根据质检单id查询是否有签到信息
	 * @param qcBillId
	 * @return
	 */
	Integer findSign(Integer qcBillId);
	
	/**
	 * 根据抽检单id  删除之前所选的检查项
	 * @param qcBillId
	 */
	void deleteCheckItemsByCheckId(int qcBillId);
	/**
	 * 创建抽检单
	 * @param bizQcBill
	 * @return
	 */
	Integer saveBizQcBill(BizQcBill bizQcBill);
	/**
	 * 保存选中的检查项
	 * @param item
	 */
	void saveItems(InspectItem item);
	/**
	 * 查询已经选择过的检查项
	 * @param inspectId
	 * @return
	 */
	List<InspectKind> changeCheckItem(int inspectId);
	/**
	 * 通过检查项id查询违规形式
	 * @param checkItemId
	 * @return
	 */
	List<IllegalModality> findIllegalModalityByCheckItemId(int checkItemId);
	/**
	 * 对应的违规形式id,根据id查询检查项id ,所占分数,是否有备注
	 * @param id
	 * @return
	 */
	InspectItem selectCheckItemInfoByIllegalModalityId(int id);

	/**
	 * 保存检查项纪录表
	 * @param item
	 */
	void updateCheckItem(InspectItem item);
	/**
	 *  parameter: 检查项id 和质检单id
	 * 查询需要保存的违规形式的检查项外键  
	 * @param item
	 * @return
	 */
	Integer selectCheckItemId(InspectItem item);
	/**
	 * 保存违规形式纪录表
	 * @param modality
	 */
	void saveIllegalModality(IllegalModality modality);


	/**
	 * 根据合格的检查项id查询分数
	 * @param checkItemId
	 * @return
	 */
	Double selectCheckItemScores(int checkItemId);
	/**
	 * 更新抽检单
	 * @param kind
	 */
	void updateInspect(InspectKind kind);
	/**
	 * 保存图片到数据库
	 * @param reportCheckDetailsPic
	 */
	void savePic(ReportCheckDetailsPic reportCheckDetailsPic);
	/**
	 * 删除数据库图片
	 * @param picId
	 */
	void deletePic(Integer picId);
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

	public  InspectKind findOrderIdByBillId(Integer billId);
	/**
	 * 保存复检单
	 * @param recheck
	 */
	public void saveRecheck(Recheck recheck);
	/**
	 * 保存复检单对应的不合格检查项
	 * @param recheck
	 */
	public void saveRecheckCheckItem(Recheck recheck);

	/**
	 * 批量保存所有选中的检查项
	 * @param list
	 */
	void saveItemsAll(List<InspectItem> list);

	/**
	 * 批量保存合格的检查项
	 * @param hege
	 */
	void updateCheckItemAll(List<InspectItem> hege);

	/**
	 * 批量保存不合格的检查项
	 * @param buhege
	 */
	void saveRecheckCheckItemAll(List<Recheck> buhege);

	/**
	 * 批量保存违规形式纪录表
	 * @param list
	 */
	void saveIllegalModalityAll(List<IllegalModality> list);

	/**
	 * 查询该订单最新一次抽检的时间是否间隔有5分钟
	 * @param orderId
	 * @return
	 */
	BizQcBill findTimeSpan(Integer orderId);
	//17-2-16 更新
	public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId);

	public int findWorkerInfoByPackId(Integer packId);
	public  QualityControl findMessageInfoByInspectId(Integer inspectId);
	public  QualityControl findMessageInfoByInspectId2(Integer inspectId);

	/**
	 * 质检领导查询订单列表
	 * @param or
	 * @return
	 */
	List<Order> findOrderByLeaderInspectorId(Order or);

	/**
	 * 检查抽检单是否存在, 做重复校验
	 * @param orderId
	 * @return
	 */
  BizQcBill findSelectCheckIsExist(Integer orderId);




	/**
	 * 根据检查项id 查询是否有记录 如果有 ,为不合格, 无为合格
	 * @param map
	 * @return
	 */
	List<InspectItem>  selectScoresFromCheckItemRecord(Map<String,Object> map);








	/**
	 * 批量保存复检单对应的不合格检查项
	 * @param rechecks
	 */
	 void batchSaveRecheckCheckItem(List<Recheck> rechecks);


	/**
	 * 根据抽检单id查询是否存在
	 * @param qcBillId
	 * @return
	 */
	Integer findIsExistSelectQcBillById(String qcBillId);

	/**
	 * 删除之前的该检查项的违规形式
	 * @param checkItemId
	 */
	public void batchDeleteQcBillCheckItemBreak(int checkItemId);
	Order queryOrderid(int orderId);
}



