package cn.damei.service.mobile.Worker;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.BusinessSignConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.entity.mobile.Inspector.Sign;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.dao.mobile.Worker.InstallSignDao;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;


@Service
@Transactional(readOnly = false)
public class InstallSignService{
	
	
	@Autowired
	private InstallSignDao dao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	/**
	 * 查询施工单列表（签到）
	 * @param installItem
	 * @return
	 */
	public List<InstallItem> findInstallConstructBillSignList(InstallItem installItem) {
		return dao.findInstallConstructBillSignList(installItem);
	}
	
	/**
	 * 根据施工单id查询相关信息（签到）
	 * @param constructBillId
	 * @return
	 */
	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}

	/**
	 * 保存签到信息（签到）
	 * @param lat
	 * @param lon
	 * @param constructBillId
	 * @param signDistance
	 * @param signAddress
	 * @param worker
	 * @param installItem 
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallConstructBillSign(String lat, String lon, Integer constructBillId, String signDistance,
			String signAddress, Worker worker, InstallItem installItem) {
		
		String result = "0";
		
		try {
			
			//9.保存签到信息
			Integer signId = saveSignMessage(lat,lon,constructBillId,signDistance,signAddress,worker);
			if(null==signId || signId<1){
				result = "9";
				return result;  
			}
			
			//10.更新安装项的状态
			boolean flag = updateInstallPlan(installItem.getOrderInstallPlanId(),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320);
			if(!flag){
				result = "10";
				return result;
			}
			//11.保存安装项状态日志
			Integer logId = saveBusinessStatusLog(worker.getId(),installItem.getOrderInstallPlanId(),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_320,null);
			if(null==logId  || logId<1){
				result = "11";
				return result;  
			}
			//12.更新安装单
			boolean flag1 =updateSupplierInstallBill(installItem.getInstallBillId(),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30);
			if(!flag1){
				result = "12";
				return result;
			}
			
			//13.保存安装单日志
			Integer BillLogId = saveBusinessStatusLog(worker.getId(),installItem.getInstallBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_30,null);
			if(null==BillLogId || BillLogId<1){
				result = "13";
				return result;  
			}
			//14.更新施工单
			boolean flag2 =updateSupplierConstructBill(installItem.getConstructBillId(),InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20);
			if(!flag2){
				result = "14";
				return result;
			}
			
			//15.保存施工单日志
			Integer constructBillLogId = saveBusinessStatusLog(worker.getId(),installItem.getConstructBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9012,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_20,null);
			if(null==constructBillLogId  || constructBillLogId<1){
				result = "15";
				return result;  
			}
			
			
		} catch (Exception e) {
			result = "16";
			return result;  
		}
		
		return result;
	}

	
	

	/**
	 * 保存签到信息
	 * @param lat
	 * @param lon
	 * @param constructBillId
	 * @param signDistance
	 * @param signAddress
	 * @param worker
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveSignMessage(String lat, String lon, Integer constructBillId, String signDistance, String signAddress, Worker worker) {
		
		Sign sign = new Sign();
		
		sign.setRelatedBusinessId(constructBillId);
		sign.setSignType(BusinessSignConstantUtil.BUSINESS_SIGN_TYPE_202);
		sign.setSignEmployeeId(worker.getId());
		sign.setSignDateTime(new Date());
		sign.setSignAddress(signAddress);
		sign.setSignXy(lon+","+lat);
		sign.setSignErrorDistance(Double.valueOf(signDistance));
		sign.preInsert();
		
		Integer signId = dao.saveSignMessage(sign);
		return signId;
	}
	
	/**
	 * 保存状态日志
	 * @param id
	 * @param orderInstallItemId
	 * @param businessType
	 * @param status
	 * @param remarks
	 * @param dataday2
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status,
			String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		//1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);
		//2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		//3.业务状态
		bizBusinessStatusLog.setBusinessStatus(status);
		//4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(remarks);
		//5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		//6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		
		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
		
	}
	

	

	/**
	 * 更新安装项计划
	 * @param orderInstallPlanId
	 * @param installPlanStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateInstallPlan(Integer orderInstallPlanId, String installPlanStatus) {
		
		EnginInstallNew enginInstall = new EnginInstallNew();
		//安装项id
		enginInstall.setId(orderInstallPlanId);
		//安装项状态
		enginInstall.setInstallStatus(installPlanStatus);
		//实际进场日期
		enginInstall.setRealIntoDate(new Date());
		
		enginInstall.preUpdate();
		
		return (dao.updateInstallPlan(enginInstall))?true:false;
	}

	

	/**
	 * 更新安装单
	 * @param installBillId
	 * @param supplierInstallBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {
		
		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		
		//1.安装单id
		bizSupplierInstallBill.setId(installBillId);
		//2.状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		//3.状态时间
		bizSupplierInstallBill.setStatusDatetime(new Date());
		//4.实际进场日期
		bizSupplierInstallBill.setRealIntoDate(new Date());
		
		bizSupplierInstallBill.preUpdate();
		
		return (dao.updateSupplierInstallBill(bizSupplierInstallBill))?true:false;
	}

	
	/**
	 * 更新施工单
	 * @param constructBillId
	 * @param supplierInstallConstructBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateSupplierConstructBill(Integer constructBillId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
		
		Date date = new Date();
		// 1.施工单id
		bizSupplierInstallConstructBill.setId(constructBillId);
		// 2.状态
		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);
		// 3.状态日期时间
		bizSupplierInstallConstructBill.setStatusDatetime(date);
		//4.实际进场日期
		bizSupplierInstallConstructBill.setRealIntoDate(date);
		
		bizSupplierInstallConstructBill.preUpdate();
		
		
		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill))?true:false;
	}

	
	
}
