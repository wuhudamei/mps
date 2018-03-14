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
	

	public List<InstallItem> findInstallConstructBillSignList(InstallItem installItem) {
		return dao.findInstallConstructBillSignList(installItem);
	}
	

	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}


	@Transactional(readOnly = false)
	public String saveInstallConstructBillSign(String lat, String lon, Integer constructBillId, String signDistance,
			String signAddress, Worker worker, InstallItem installItem) {
		
		String result = "0";
		
		try {
			

			Integer signId = saveSignMessage(lat,lon,constructBillId,signDistance,signAddress,worker);
			if(null==signId || signId<1){
				result = "9";
				return result;  
			}
			

			boolean flag = updateInstallPlan(installItem.getOrderInstallPlanId(),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320);
			if(!flag){
				result = "10";
				return result;
			}

			Integer logId = saveBusinessStatusLog(worker.getId(),installItem.getOrderInstallPlanId(),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_320,null);
			if(null==logId  || logId<1){
				result = "11";
				return result;  
			}

			boolean flag1 =updateSupplierInstallBill(installItem.getInstallBillId(),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30);
			if(!flag1){
				result = "12";
				return result;
			}
			

			Integer BillLogId = saveBusinessStatusLog(worker.getId(),installItem.getInstallBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_30,null);
			if(null==BillLogId || BillLogId<1){
				result = "13";
				return result;  
			}

			boolean flag2 =updateSupplierConstructBill(installItem.getConstructBillId(),InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20);
			if(!flag2){
				result = "14";
				return result;
			}
			

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
	

	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status,
			String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);

		bizBusinessStatusLog.setBusinessType(businessType);

		bizBusinessStatusLog.setBusinessStatus(status);

		bizBusinessStatusLog.setBusinessRemarks(remarks);

		bizBusinessStatusLog.setStatusDatetime(new Date());

		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		
		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
		
	}
	

	


	@Transactional(readOnly = false)
	public boolean updateInstallPlan(Integer orderInstallPlanId, String installPlanStatus) {
		
		EnginInstallNew enginInstall = new EnginInstallNew();

		enginInstall.setId(orderInstallPlanId);

		enginInstall.setInstallStatus(installPlanStatus);

		enginInstall.setRealIntoDate(new Date());
		
		enginInstall.preUpdate();
		
		return (dao.updateInstallPlan(enginInstall))?true:false;
	}

	


	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {
		
		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		

		bizSupplierInstallBill.setId(installBillId);

		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);

		bizSupplierInstallBill.setStatusDatetime(new Date());

		bizSupplierInstallBill.setRealIntoDate(new Date());
		
		bizSupplierInstallBill.preUpdate();
		
		return (dao.updateSupplierInstallBill(bizSupplierInstallBill))?true:false;
	}

	

	@Transactional(readOnly = false)
	public boolean updateSupplierConstructBill(Integer constructBillId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
		
		Date date = new Date();

		bizSupplierInstallConstructBill.setId(constructBillId);

		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);

		bizSupplierInstallConstructBill.setStatusDatetime(date);

		bizSupplierInstallConstructBill.setRealIntoDate(date);
		
		bizSupplierInstallConstructBill.preUpdate();
		
		
		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill))?true:false;
	}

	
	
}
