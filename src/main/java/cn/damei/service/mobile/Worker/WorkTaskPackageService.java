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

	public List<WorkTaskPackage> selectPackByWorkerIdForManagerCheck(Integer  groupId) {
		
		return dao.selectPackByWorkerIdForManagerCheck(groupId);
	}

	public void acceptTaskPackage(Integer id, HttpServletRequest request) {
		dao.acceptTaskPackage(id);

		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_1101);
		statusLog.setBusinessOnlyMarkInt(id);
		statusLog.setBusinessStatus(ConstantUtils.ORDER_TASKPACKAGE_STATUS_50);
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

		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_1201);
		statusLog.setBusinessOnlyMarkInt(id);
		statusLog.setBusinessStatus(ConstantUtils.ORDER_TASKPACKAGE_STATUS_55);
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
	

	public List<WorkerPackProcedure> findProcedureByPackId(Integer packageId){
		
		return dao.findProcedureByPackId(packageId);
	}
	

	public String getLeaderPhoneById(Integer leaderId){
		
		return dao.getLeaderPhoneById(leaderId);
	}
	

	public String getLeaderNameById(Integer leaderId){
		
		return dao.getLeaderNameById(leaderId);
	}
	
	
	

	public List<WorkTaskPackage> findDoneApplyListByGroupId(Integer id) {
		return dao.findDoneApplyListByGroupId(id);
	}

	public void savePic(TaskPackagePic taskPackagePic) {
		dao.savePic(taskPackagePic);
		
	}
	

	public void applyTaskPackage(Integer id){
		dao.applyTaskPackage(id);
	}


	public int findCountCompleted(Integer groupId) {

		return dao.findCountCompleted(groupId);
	}

	public int findCountDiscompleted(Integer groupId) {
		
		return dao.findCountDiscompleted(groupId);
	}


	public WorkTaskPackage   selectMessageInfoByPackId(Integer packId){
		
		return dao.selectMessageInfoByPackId(packId);
	}
	

public    WorkTaskPackage	packActuallyDoneDays(Integer packid){
	return dao.packActuallyDoneDays(packid);
}




public  List<UrgeRecord> urgeRecord(Integer packId){
	return dao.urgeRecord(packId);
}



	public void saveUrgeRecord(UrgeRecord  record){
		
		dao.saveUrgeRecord(record);
	}


	public List<WorkTaskPackage> findTaskPackageForSettlement(Integer groupId) {
		return dao.findTaskPackageForSettlement(groupId);
	}
	
	public WorkTaskPackage findTaskPackageById(Integer id) {
		return dao.get(id);
	}
	

	public void updateOrderTaskPackage(Integer taskPackageId, String packageStateid, String packageStatename) {
		if(packageStateid.equals("110")){
			bizOrderTaskpackageSettlementDao.updateSettlementStatus(taskPackageId,ConstantUtils.SETTLEMENT_STATUS_60);
		}else{

			bizOrderTaskpackageSettlementDao.updateSettlementStatusAndDate(taskPackageId,ConstantUtils.SETTLEMENT_STATUS_65,new Date());
		}
		dao.updateOrderTaskPackage(taskPackageId, packageStateid, packageStatename);
		
	}
	public String findPhone(Integer orderTaskpackageId) {
		return dao.findPhone(orderTaskpackageId);
	}
	

	public Integer querySettled(Integer groupId) {
		return dao.querySettled(groupId);
	}
	

	public Integer querySettling(Integer groupId) {
		return dao.querySettling(groupId);
	}
	public List<WorkTaskPackage> queryAllTaskpackage(Integer groupId) {

		return dao.queryAllTaskpackage(groupId);
	}
	public Integer findTaskPackageForSettlementCount(Map<String, Object> map) {
		return dao.findTaskPackageForSettlementCount(map);
	}

	public WorkTaskPackage findOrder(Integer orderTaskpackageId) {
		return dao.findOrder(orderTaskpackageId);
	}

	public void savePicAll(List<TaskPackagePic> list) {
		dao.savePicAll(list);
	}

}
