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
	/**
	 * 查询状态为80的任务包List
	 * @param groupId
	 * @return
	 */
	public List<WorkTaskPackage> selectPackByWorkerIdForManagerCheck(Integer groupId);

	public void acceptTaskPackage(Integer id);

	public void refuseTaskPackage(Integer id);


	/**
	 * 根据id得到任务包对象
	 * @param packId
	 * @return
	 */
	public WorkTaskPackage  getPackById(Integer packId);


	/**
	 * 根据 任务包id 查询 工序
	 * @return
	 */
	public List<WorkerPackProcedure> findProcedureByPackId(Integer packId);

	/**
	 * 根据组长id 获取组长手机
	*/
	public String getLeaderPhoneById(Integer leaderId);
	/**
	 * 根据组长id 获取组长name
	 */
	public String getLeaderNameById(Integer leaderId);
	
	
	//通过工人id查询状态为施工中的任务包
	public List<WorkTaskPackage> findDoneApplyListByGroupId(Integer id);
	
	//保存图片到数据库中
	public void savePic(TaskPackagePic taskPackagePic);
	
	//申请完工
	public void applyTaskPackage(Integer id);

	public int findCountCompleted(Integer groupId);

	public int findCountDiscompleted(Integer groupId);

	
	/**
	 * 根据任务包id查询短信内容  包括 客户信息,名称, 任务包名称,要发送的项目经理手机号
	 * @param packId
	 * @return
	 */
	public WorkTaskPackage   selectMessageInfoByPackId(Integer packId);

	
	/**
	 * 任务包干完, 有实际开工和结束时间     查询工期
	 * @param packid
	 * @return
	 */
	public    WorkTaskPackage	packActuallyDoneDays(Integer packid);
	
	
	
	/**
	 * 根据任务包id查询催促记录表
	 * @param packId
	 * @return
	 */
	public  List<UrgeRecord> urgeRecord(Integer packId);
	
	/**
	 * 保存催促验收记录
	 * @param record
	 */
	public void saveUrgeRecord(UrgeRecord  record);

	/**
	 * 确认薪酬的任务包
	 * @param groupId
	 * @return
	 */
	public List<WorkTaskPackage> findTaskPackageForSettlement(Integer groupId);
	
	/**
	 * 修改任务包的状态为120根据任务包id
	 * @param taskPackageId
	 * @param packageStateid
	 * @param packageStatename
	 */
	void updateOrderTaskPackage(Integer taskPackageId, String packageStateid, String packageStatename);
	
	public String findPhone(Integer orderTaskpackageId);
	
	//已结算的任务包
	public Integer querySettled(Integer groupId);
	//未结算的任务包
	public Integer querySettling(Integer groupId);
	//工人的所有任务包
	public List<WorkTaskPackage> queryAllTaskpackage(Integer groupId);
	
	public Integer findTaskPackageForSettlementCount(Map<String, Object> map);
	//根据任务包id查询订单详情
	public WorkTaskPackage findOrder(Integer orderTaskpackageId);
	
	//批量插入申请完工图片
	public void savePicAll(List<TaskPackagePic> list);
}
