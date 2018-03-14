package cn.damei.service.mobile.Worker;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.dao.mobile.Worker.WorkTaskPackageDao;
import cn.damei.entity.mobile.Worker.TaskPackagePic;
import cn.damei.entity.mobile.Worker.UrgeRecord;
import cn.damei.entity.mobile.Worker.WorkTaskPackage;
import cn.damei.entity.mobile.Worker.WorkerPackProcedure;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementDao;

@Service
@Transactional(readOnly=false)
public class WorkTaskPackageService extends CrudService2<WorkTaskPackageDao, WorkTaskPackage>{

	@Autowired
	private BizOrderTaskpackageSettlementDao bizOrderTaskpackageSettlementDao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	
	
	public List<WorkTaskPackage> findTaskPackageByGroupId(Integer  groupId) {
		
		return dao.findTaskPackageByGroupId(groupId);
	}
	/**		
	 * 查询状态为80 工人申请完工的任务包  以供催促验收
	 * @param groupId
	 * @return
	 */
	public List<WorkTaskPackage> selectPackByWorkerIdForManagerCheck(Integer  groupId) {
		
		return dao.selectPackByWorkerIdForManagerCheck(groupId);
	}

	public void acceptTaskPackage(Integer id, HttpServletRequest request) {
		dao.acceptTaskPackage(id);
		//添加状态日志信息
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_1101);
		statusLog.setBusinessOnlyMarkInt(id);
		statusLog.setBusinessStatus(ConstantUtils.ORDER_TASKPACKAGE_STATUS_50);//预算员审核通过
		statusLog.setStatusDatetime(new Date());
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if (null != worker.getId()) {
			statusLog.setBusinessEmployeeId(worker.getId());
		}
		statusLog.setBusinessRemarks("工人接受任务包");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
		
		
	}

	public void refuseTaskPackage(Integer id, HttpServletRequest request) {
		dao.refuseTaskPackage(id);
		//添加状态日志信息
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_1201);
		statusLog.setBusinessOnlyMarkInt(id);
		statusLog.setBusinessStatus(ConstantUtils.ORDER_TASKPACKAGE_STATUS_55);//预算员审核通过
		statusLog.setStatusDatetime(new Date());
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if (null != worker.getId()) {
			statusLog.setBusinessEmployeeId(worker.getId());
		}
		statusLog.setBusinessRemarks("工人拒绝任务包");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
	}


	public WorkTaskPackage  getPackById(Integer packId){
		
		return dao.getPackById(packId);
	}
	
	/**
	 * 根据 任务包id 查询 工序
	 * @return
	 */
	public List<WorkerPackProcedure> findProcedureByPackId(Integer packageId){
		
		return dao.findProcedureByPackId(packageId);
	}
	
	/**
	 * 根据组长id 获取组长手机
	*/
	public String getLeaderPhoneById(Integer leaderId){
		
		return dao.getLeaderPhoneById(leaderId);
	}
	
	/**
	 * 根据组长id 获取组长name
	 */
	public String getLeaderNameById(Integer leaderId){
		
		return dao.getLeaderNameById(leaderId);
	}
	
	
	
	//通过工人id查询状态是施工中的任务包
	public List<WorkTaskPackage> findDoneApplyListByGroupId(Integer id) {
		return dao.findDoneApplyListByGroupId(id);
	}
	//保存图片到数据库中
	public void savePic(TaskPackagePic taskPackagePic) {
		dao.savePic(taskPackagePic);
		
	}
	
	//申请完工
	public void applyTaskPackage(Integer id){
		dao.applyTaskPackage(id);
	}

	//查询已完工的任务包数量
	public int findCountCompleted(Integer groupId) {

		return dao.findCountCompleted(groupId);
	}
	//查询未完工的任务包的数量
	public int findCountDiscompleted(Integer groupId) {
		
		return dao.findCountDiscompleted(groupId);
	}

	//短信内容
	public WorkTaskPackage   selectMessageInfoByPackId(Integer packId){
		
		return dao.selectMessageInfoByPackId(packId);
	}
	
	/**
	 * 任务包干完, 有实际开工和结束时间     查询工期
	 * @param packid
	 * @return
	 */
public    WorkTaskPackage	packActuallyDoneDays(Integer packid){
	return dao.packActuallyDoneDays(packid);
}



/**
 * 根据任务包id查询催促记录表
 * @param packId
 * @return
 */
public  List<UrgeRecord> urgeRecord(Integer packId){
	return dao.urgeRecord(packId);
}


/**
 * 保存催促验收记录
 * @param record
 */
	public void saveUrgeRecord(UrgeRecord  record){
		
		dao.saveUrgeRecord(record);
	}

	/**
	 * 确认薪酬的任务包
	 * @param groupId
	 * @return
	 */
	public List<WorkTaskPackage> findTaskPackageForSettlement(Integer groupId) {
		return dao.findTaskPackageForSettlement(groupId);
	}
	
	public WorkTaskPackage findTaskPackageById(Integer id) {
		return dao.get(id);
	}
	
	/**
	 * 修改任务包的状态根据任务包id--同时修改结算单的状态
	 * @param taskPackageId
	 * @param packageStateid
	 * @param packageStatename
	 */
	public void updateOrderTaskPackage(Integer taskPackageId, String packageStateid, String packageStatename) {
		if(packageStateid.equals("110")){//任务包状态为110表示工人不同意
			bizOrderTaskpackageSettlementDao.updateSettlementStatus(taskPackageId,ConstantUtils.SETTLEMENT_STATUS_60);
		}else{
			//所有人都同意修改结算单的状态--根据任务包的id 修改为工人已确认分配金额-65
			bizOrderTaskpackageSettlementDao.updateSettlementStatusAndDate(taskPackageId,ConstantUtils.SETTLEMENT_STATUS_65,new Date());
		}
		dao.updateOrderTaskPackage(taskPackageId, packageStateid, packageStatename);
		
	}
	public String findPhone(Integer orderTaskpackageId) {
		return dao.findPhone(orderTaskpackageId);
	}
	
	//已结算的任务包
	public Integer querySettled(Integer groupId) {
		return dao.querySettled(groupId);
	}
	
	//未结算的任务包
	public Integer querySettling(Integer groupId) {
		return dao.querySettling(groupId);
	}
	public List<WorkTaskPackage> queryAllTaskpackage(Integer groupId) {
		// TODO Auto-generated method stub
		return dao.queryAllTaskpackage(groupId);
	}
	public Integer findTaskPackageForSettlementCount(Map<String, Object> map) {
		return dao.findTaskPackageForSettlementCount(map);
	}
	//根据任务包id查询订单详情
	public WorkTaskPackage findOrder(Integer orderTaskpackageId) {
		return dao.findOrder(orderTaskpackageId);
	}
	/**
	 * 批量插入申请完工图片
	 * @param list
	 */
	public void savePicAll(List<TaskPackagePic> list) {
		dao.savePicAll(list);
	}

}
