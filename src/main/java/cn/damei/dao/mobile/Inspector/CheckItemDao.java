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
	
	

public List<InspectKind>selectAllCheckItem(Integer storeId);


public List<InspectItem> findZanCun(Integer inspectId);



public void saveItems(InspectItem item);



public void updateCheckItem(InspectItem item);

	public void updateCheckItemAll(List<InspectItem> itemList);




public List<InspectKind>changeCheckItem(Integer inspectId);



public List<IllegalModality> findIllegalModalityByCheckItemId(Integer checkItemId);





public InspectItem selectCheckItemInfoByIllegalModalityId(Integer illegalModalityId);


public void saveIllegalModality(List<IllegalModality>list);


public void saveCheckItem(InspectItem item);




public List<InspectItem>  selectScoresFromCheckItemRecord(Map<String,Object> map);



public Double selectCheckItemScores(Integer checkItemId);






public void updateInspect(InspectKind kind);



public void  deleteCheckItemsByCheckId(Integer  billId);



public int selectCheckItemId(InspectItem item);


public void savePic(List<ReportCheckDetailsPic> picList);


public List<ReportCheckDetailsPic> findPic(int inspectId);


 void deletePic(int picId);

 void saveCode();
  ReCheckCode  getCode();
 void updateCode(ReCheckCode code);



public void saveRecheck(Recheck recheck);


public void saveRecheckCheckItem(Recheck recheck);


public void batchSaveRecheckCheckItem(List<Recheck> rechecks);







public  InspectKind findOrderIdByBillId(Integer billId);

public  QualityControl findMessageInfoByInspectId(Integer inspectId);
public  QualityControl findMessageInfoByInspectId2(Integer inspectId);


public BalanceFine findRecordByOrderIdOfInspectId(Integer inspectId);

public  void updateFineCount(BalanceFine fine);


public void saveSettleFineRecord(BalanceFine fine);

public void saveSettleFineRecordAll(List<BalanceFine> fakuan);



public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId);

public int findWorkerInfoByPackId(Integer packId);


	public void batchDeleteQcBillCheckItemBreak(int checkItemId);
}




