package cn.damei.dao.mobile.Worker;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.TaskPackagePic;
import cn.damei.entity.mobile.Worker.UrgeRecord;
import cn.damei.entity.mobile.Worker.WorkTaskPackage;
import cn.damei.entity.mobile.Worker.WorkerPackProcedure;


@MyBatisDao
public interface WorkTaskPackageDao extends CrudDao2<WorkTaskPackage>{

	public List<WorkTaskPackage> findTaskPackageByGroupId(Integer groupId);

	public List<WorkTaskPackage> selectPackByWorkerIdForManagerCheck(Integer groupId);

	public void acceptTaskPackage(Integer id);

	public void refuseTaskPackage(Integer id);



	public WorkTaskPackage  getPackById(Integer packId);



	public List<WorkerPackProcedure> findProcedureByPackId(Integer packId);


	public String getLeaderPhoneById(Integer leaderId);

	public String getLeaderNameById(Integer leaderId);
	
	

	public List<WorkTaskPackage> findDoneApplyListByGroupId(Integer id);
	

	public void savePic(TaskPackagePic taskPackagePic);
	

	public void applyTaskPackage(Integer id);

	public int findCountCompleted(Integer groupId);

	public int findCountDiscompleted(Integer groupId);

	

	public WorkTaskPackage   selectMessageInfoByPackId(Integer packId);

	

	public    WorkTaskPackage	packActuallyDoneDays(Integer packid);
	
	
	

	public  List<UrgeRecord> urgeRecord(Integer packId);
	

	public void saveUrgeRecord(UrgeRecord  record);


	public List<WorkTaskPackage> findTaskPackageForSettlement(Integer groupId);
	

	void updateOrderTaskPackage(Integer taskPackageId, String packageStateid, String packageStatename);
	
	public String findPhone(Integer orderTaskpackageId);
	

	public Integer querySettled(Integer groupId);

	public Integer querySettling(Integer groupId);

	public List<WorkTaskPackage> queryAllTaskpackage(Integer groupId);
	
	public Integer findTaskPackageForSettlementCount(Map<String, Object> map);

	public WorkTaskPackage findOrder(Integer orderTaskpackageId);
	

	public void savePicAll(List<TaskPackagePic> list);
}
