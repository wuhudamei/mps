package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;

import cn.damei.entity.mobile.Inspector.*;
import cn.damei.entity.mobile.Manager.QualityControl;
@MyBatisDao
public interface CheckItemDao  extends CrudDao2<InspectKind>{

	
	public InspectKind useInspectIdToFindInspectBillByOrderIdOrderByDateLimitOneInOrderToCheckTimeIsAllowedWithFiveMinutes(Integer inspectId);
	
	
	/**
	 * 根据门店查询所有检查分类和检查项
	 * @return
	 */
public List<InspectKind>selectAllCheckItem(Integer storeId);

/**
 * 根据质检单id查询是否有暂存
 * @param inspectId
 * @return
 */
public List<InspectItem> findZanCun(Integer inspectId);


/**
 * 保存质检员选择的检查项
 * @param item
 */
public void saveItems(InspectItem item);


/**
 * 更新质检单的选择项
 * @param item
 */
public void updateCheckItem(InspectItem item);

	public void updateCheckItemAll(List<InspectItem> itemList);



/**
 * 根据质检单id  查询检查项申请记录  以供页面回显
 * @param inspectId
 * @return
 */
public List<InspectKind>changeCheckItem(Integer inspectId);


/**
 * 根据检查项,查询违规形式
 * @return
 */
public List<IllegalModality> findIllegalModalityByCheckItemId(Integer checkItemId);




/**
 *  根据违规项id 查询 检查项id,分数,及是否有备注
 * @param illegalModalityId
 * @return
 */
public InspectItem selectCheckItemInfoByIllegalModalityId(Integer illegalModalityId);

/**
 * 保存违规形式记录
 * @param list
 */
public void saveIllegalModality(List<IllegalModality>list);

/**
 * 保存检查项的各项不合格数据
 * @param item
 */
public void saveCheckItem(InspectItem item);



/**
 * 根据检查项id,质检单id 查询是否有记录 如果有 ,为不合格, 无为合格   -->biz_qc_bill_check_item
 * @param map
 * @return id
 */
public List<InspectItem>  selectScoresFromCheckItemRecord(Map<String,Object> map);


/**
 * 根据合格的检查项id 查询分数      --> biz_qc_check_item
 * @return
 */
public Double selectCheckItemScores(Integer checkItemId);





/**
 * 更新质检单    状态, 得分情况 实际质检人 等
 * @param kind
 */
public void updateInspect(InspectKind kind);


/**
 * 业务逻辑: 更改检查项     根据质检单id  删除之前选择过的检查项
 * @param billId
 */
public void  deleteCheckItemsByCheckId(Integer  billId);


/*
 * parameter: 检查项id 和质检单id
 * 查询需要保存的违规形式的检查项外键  
 */
public int selectCheckItemId(InspectItem item);

/**
 * 批量保存图片
 * @param picList
 */
public void savePic(List<ReportCheckDetailsPic> picList);

/**
 * 查询图片
 * @param inspectId
 * @return
 */
public List<ReportCheckDetailsPic> findPic(int inspectId);

/**
 * 删除数据库图片
 * @param picId
 */
 void deletePic(int picId);

 void saveCode();
  ReCheckCode  getCode();
 void updateCode(ReCheckCode code);


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
 * 批量保存复检单对应的不合格检查项
 * @param rechecks
 */
public void batchSaveRecheckCheckItem(List<Recheck> rechecks);







public  InspectKind findOrderIdByBillId(Integer billId);

public  QualityControl findMessageInfoByInspectId(Integer inspectId);
public  QualityControl findMessageInfoByInspectId2(Integer inspectId);


public BalanceFine findRecordByOrderIdOfInspectId(Integer inspectId);

public  void updateFineCount(BalanceFine fine);


public void saveSettleFineRecord(BalanceFine fine);
//批量保存罚款结算类目明细
public void saveSettleFineRecordAll(List<BalanceFine> fakuan);


//17-2-16 更新
public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId);

public int findWorkerInfoByPackId(Integer packId);

	/**
	 * 删除之前的该检查项的违规形式
	 * @param checkItemId
	 */
	public void batchDeleteQcBillCheckItemBreak(int checkItemId);
}




