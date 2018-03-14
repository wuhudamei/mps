package cn.damei.service.modules;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.dao.modules.BizProjectChangeBillDao;
import cn.damei.entity.modules.BizMsg;
import cn.damei.dao.modules.BizSupplierInstallBillDao;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.dao.modules.EnginInstallNewDealDao;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.entity.modules.BizSupplier;


@Service
@Transactional(readOnly = true)
public class EnginInstallNewDealService{

	@Autowired
	private EnginInstallNewDealDao dao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;
	@Autowired
	private BizProjectChangeBillDao bizProjectChangeBillDao;
	@Autowired
	private BizSupplierInstallBillDao bizSupplierInstallBillDao;


	@Transactional(readOnly = false)
	public boolean updateSupplier(Integer installId, String installPlanStatus, String supplierConfirmIntoDate,
			String supplierConfirmRemarks, Integer supplierId) {
		
		EnginInstallNew enginInstall = new EnginInstallNew();

		enginInstall.setId(installId);

		enginInstall.setInstallStatus(installPlanStatus);

		enginInstall.setSupplierConfirmIntoDate(DateUtils.parseDate(supplierConfirmIntoDate));

		enginInstall.setSupplierConfirmRemarks(supplierConfirmRemarks);

		enginInstall.setSendSupplierId(supplierId);
		
		enginInstall.preUpdate();
		
		return (dao.updateSupplier(enginInstall))?true:false;
	}
	
	

	@Transactional(readOnly = false)
	public boolean updateReject(Integer installId, String installPlanStatus) {
		EnginInstallNew enginInstall = new EnginInstallNew();

		enginInstall.setId(installId);

		enginInstall.setInstallStatus(installPlanStatus);
		
		enginInstall.preUpdate();
		
		return (dao.updateReject(enginInstall))?true:false;
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
	public Integer saveMessage(Integer employeeId,String employeeIdPhone,String content, Integer installId, String relatedBusinessType) {
		
		
		BizPhoneMsg ddMsg = new BizPhoneMsg();
		ddMsg.setId(null);
		ddMsg.setReceiveEmployeeId(employeeId);
		ddMsg.setReceivePhone(employeeIdPhone);
		ddMsg.setMsgContent(content);
		ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
		ddMsg.setMsgTosendDatetime(null);
		ddMsg.setMsgSendedDatetime(null);
		ddMsg.setMsgStatus("0");
		ddMsg.setRelatedBusinessType(relatedBusinessType);
		ddMsg.setRelatedBusinessIdInt(installId);
		ddMsg.setRelatedBusinessIdVarchar("");
		bizPhoneMsgDao.insert(ddMsg);
		
		return ddMsg.getId();
	}


	@Transactional(readOnly = false)
	public void saveMsg(EnginInstallNew enginInstall, Integer installId, String supplierConfirmIntoDate) {
		

		
		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("下达供应商");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent("订单（" + enginInstall.getCommunityName() + "-" + enginInstall.getBuildNumber() + "-" + enginInstall.getBuildUnit() + "-"
				+ enginInstall.getBuildRoom() + "-" + enginInstall.getCustomerName() + "-" + enginInstall.getInstallItemName() + ") 材料员已转单给供应商，供应商确认进场日期为("
				+ supplierConfirmIntoDate + ")，请您知晓");
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType("55555");
		bizMsg.setBusiIdInt(installId);
		bizMsg.setEmployeeId(enginInstall.getManagerId());
		bizProjectChangeBillDao.saveBizMsg(bizMsg);
		
	}



	public BizProjectInstallItem installExplain(Integer installId) {
		return dao.installExplain(installId);
	}



	public BizOrderChecksizeEntity findCheckSize(Integer orderId, Integer orderInstallItemId) {
		
		BizOrderChecksizeEntity bizOrderChecksize = new BizOrderChecksizeEntity();
		
		bizOrderChecksize.setOrderId(orderId);
		bizOrderChecksize.setOrderInstallItemId(orderInstallItemId);
		
		return dao.findCheckSize(bizOrderChecksize);
	}

	

	public BizPrePmSettleFin findSecondPayment(Integer orderId) {
		return dao.findSecondPayment(orderId);
	}



	public List<EnginInstallSupplier> findSupplierList(Integer installId) {
		return dao.findSupplierList(installId);
	}


	public BizSupplier findSupplierMessage(Integer supplierId) {
		return dao.findSupplierMessage(supplierId);
	}
	


	@Transactional(readOnly = false)
	public Integer saveSupplierInstallBill(Integer installId, Integer supplierId, String supplierInstallBillStatus) {
		
		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		

		bizSupplierInstallBill.setInstallBillCode(installBillCode());

		bizSupplierInstallBill.setOrderInstallPlanId(installId);

		bizSupplierInstallBill.setSupplierId(supplierId);

		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);

		bizSupplierInstallBill.setStatusDatetime(new Date());
		bizSupplierInstallBill.preInsert();
		
		bizSupplierInstallBillDao.insert(bizSupplierInstallBill);
		
		return bizSupplierInstallBill.getId();
	}
	
	


	@Transactional(readOnly = false)
    public String installBillCode() {


        StringBuilder builder = new StringBuilder();

        ReCheckCode code1 = dao.getCode(ConstantUtils.SUPPLIER_INSTALL_BILL_CODE);
        if (null == code1) {
        	ReCheckCode reCheckCode = new ReCheckCode();
        	
        	reCheckCode.setBussinessType(ConstantUtils.SUPPLIER_INSTALL_BILL_CODE);
        	reCheckCode.setLastSeiralnum(0);
        	reCheckCode.setGenerateTime(new Date());
        	
            dao.saveCode(reCheckCode);
            code1 = dao.getCode(ConstantUtils.SUPPLIER_INSTALL_BILL_CODE);
        }
        builder.append(code1.getBussinessType());

        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
            code1.setGenerateTime(new Date());
        }
        builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
        code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
        dao.updateCode(code1);
        String code = String.valueOf(code1.getLastSeiralnum());

        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {

            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() == 4) {
            builder.append(code);
        }


        return builder.toString();

    }


	@Transactional(readOnly = false)
	public String updateByStatusSupplierIdAgain(Integer installId, Integer empId, String supplierId, String supplierConfirmRemarks) {
		
		String result = "0";
		try {
			

			boolean flag = updateSupplier(installId,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,null,supplierConfirmRemarks,Integer.valueOf(supplierId));
			if(!flag){
				result = "7";
				return result;
			}
			

			Integer logId = saveBusinessStatusLog(empId,installId,BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierId);
			if(null==logId || logId < 1){
				result = "8";
				return result;  
			}
			
			

			BizSupplierInstallBill bizSupplierInstallBill = dao.findInstallBillAndConstructBill(installId);
			if(null!=bizSupplierInstallBill){
				

				if(null != bizSupplierInstallBill.getInstallBillId()){

					bizSupplierInstallBill.setId(bizSupplierInstallBill.getInstallBillId());
					bizSupplierInstallBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_90);
					bizSupplierInstallBill.setStatusDatetime(new Date());
					bizSupplierInstallBill.preUpdate();
					boolean SupplierInstallBillFlag = (dao.updateSupplierInstallBill(bizSupplierInstallBill))?true:false;
					if(!SupplierInstallBillFlag){
						result = "10";
						return result;
					}
					

					Integer BillLogId = saveBusinessStatusLog(empId,bizSupplierInstallBill.getInstallBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_90,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_90,null);
					if(null == BillLogId || BillLogId < 1){
						result = "11";
						return result;  
					}
				}

				if(null!=bizSupplierInstallBill.getInstallConstructBillId()){

					BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
					bizSupplierInstallConstructBill.setId(bizSupplierInstallBill.getInstallConstructBillId());
					bizSupplierInstallConstructBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_90);
					bizSupplierInstallConstructBill.setStatusDatetime(new Date());
					bizSupplierInstallConstructBill.preUpdate();
					boolean supplierInstallBillFlag = (dao.updateSupplierInstallConstructBill(bizSupplierInstallConstructBill))?true:false;
					if(!supplierInstallBillFlag){
						result = "12";
						return result;
					}
					

					Integer BillLogId = saveBusinessStatusLog(empId,bizSupplierInstallBill.getInstallConstructBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9012,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_90,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_90,null);
					if(null==BillLogId || BillLogId<1){
						result = "13";
						return result;  
					}
				}
				
			}
			
			

			Integer supplierInstallBillId = saveSupplierInstallBill(installId,Integer.valueOf(supplierId),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10);
			if(null==supplierInstallBillId || supplierInstallBillId < 1){
				result = "14";
				return result;  
			}
			

			Integer BillLogId = saveBusinessStatusLog(empId,supplierInstallBillId,BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_10,supplierId);
			if(null==BillLogId || BillLogId < 1){
				result = "15";
				return result;  
			}
			
		} catch (Exception e) {
			result = "16";
			return result;  
		}
		
		return result;
	}




	
	
}