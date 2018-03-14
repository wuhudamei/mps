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

/**
 * 主材安装申请 处理Service
 */
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

	/**
	 * 更新状态--下达供应商
	 * @param installId
	 * @param installPlanStatus
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmRemarks
	 * @param supplierId 
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateSupplier(Integer installId, String installPlanStatus, String supplierConfirmIntoDate,
			String supplierConfirmRemarks, Integer supplierId) {
		
		EnginInstallNew enginInstall = new EnginInstallNew();
		//安装项id
		enginInstall.setId(installId);
		//安装项状态
		enginInstall.setInstallStatus(installPlanStatus);
		//供应商确认时间
		enginInstall.setSupplierConfirmIntoDate(DateUtils.parseDate(supplierConfirmIntoDate));
		//下达供应商说明
		enginInstall.setSupplierConfirmRemarks(supplierConfirmRemarks);
		//供应商
		enginInstall.setSendSupplierId(supplierId);
		
		enginInstall.preUpdate();
		
		return (dao.updateSupplier(enginInstall))?true:false;
	}
	
	
	/**
	 * 更新状态    驳回
	 * @param installId
	 * @param installPlanStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateReject(Integer installId, String installPlanStatus) {
		EnginInstallNew enginInstall = new EnginInstallNew();
		//安装项id
		enginInstall.setId(installId);
		//安装项状态
		enginInstall.setInstallStatus(installPlanStatus);
		
		enginInstall.preUpdate();
		
		return (dao.updateReject(enginInstall))?true:false;
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
	 * 发送短信
	 * @param employeeId
	 * @param employeeIdPhone
	 * @param content
	 * @param installId
	 * @param relatedBusinessType
	 * @return
	 */
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

	/**
	 * 栈内消息
	 * @param enginInstall
	 * @param installId
	 * @param supplierConfirmIntoDate 
	 * @return
	 */
	@Transactional(readOnly = false)
	public void saveMsg(EnginInstallNew enginInstall, Integer installId, String supplierConfirmIntoDate) {
		
		//订单（小区名称-楼号-单元号-门牌号-客户姓名-带货安装项名称）材料员已转单给供应商，供应商确认进场日期为（2017-02-20），请您知晓。”
		
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


	/**
	 * 查询安装说明
	 * @param installId
	 * @return
	 */
	public BizProjectInstallItem installExplain(Integer installId) {
		return dao.installExplain(installId);
	}


	/**
	 * 查询复尺内容
	 * @param orderId
	 * @param orderInstallItemId
	 * @return
	 */
	public BizOrderChecksizeEntity findCheckSize(Integer orderId, Integer orderInstallItemId) {
		
		BizOrderChecksizeEntity bizOrderChecksize = new BizOrderChecksizeEntity();
		
		bizOrderChecksize.setOrderId(orderId);
		bizOrderChecksize.setOrderInstallItemId(orderInstallItemId);
		
		return dao.findCheckSize(bizOrderChecksize);
	}

	
	/**
	 * 查询二期款
	 * @param orderId
	 * @return
	 */
	public BizPrePmSettleFin findSecondPayment(Integer orderId) {
		return dao.findSecondPayment(orderId);
	}


	/**
	 * 查询该安装项的供应商列表
	 * @param installId
	 * @return
	 */
	public List<EnginInstallSupplier> findSupplierList(Integer installId) {
		return dao.findSupplierList(installId);
	}

	/**
	 * 查询供应商信息
	 * @param supplierId
	 * @return
	 */
	public BizSupplier findSupplierMessage(Integer supplierId) {
		return dao.findSupplierMessage(supplierId);
	}
	

	/**
	 * 保存安装计划-安装单
	 * @param installId
	 * @param supplierId
	 * @param supplierInstallBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveSupplierInstallBill(Integer installId, Integer supplierId, String supplierInstallBillStatus) {
		
		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		
		// 安装单号
		bizSupplierInstallBill.setInstallBillCode(installBillCode());
		// 订单安装计划id
		bizSupplierInstallBill.setOrderInstallPlanId(installId);
		// 供应商id
		bizSupplierInstallBill.setSupplierId(supplierId);
		// 状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		// 状态日期时间
		bizSupplierInstallBill.setStatusDatetime(new Date());
		bizSupplierInstallBill.preInsert();
		
		bizSupplierInstallBillDao.insert(bizSupplierInstallBill);
		
		return bizSupplierInstallBill.getId();
	}
	
	

	/**
	 * 安装单号
	 * @return
	 */
	@Transactional(readOnly = false)
    public String installBillCode() {
        // 以AZ开头

        StringBuilder builder = new StringBuilder();
        // num
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
        // 格式后的时间戳
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
            code1.setGenerateTime(new Date());
        }
        builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
        code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
        dao.updateCode(code1);
        String code = String.valueOf(code1.getLastSeiralnum());
        // 判断长度
        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {
            // 拼接编号
            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() == 4) {
            builder.append(code);
        }

        // 返回编号
        return builder.toString();

    }

	/**
	 * 重新转给供应商
	 * @param installId
	 * @param empId
	 * @param supplierId
	 * @param supplierConfirmRemarks
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateByStatusSupplierIdAgain(Integer installId, Integer empId, String supplierId, String supplierConfirmRemarks) {
		
		String result = "0";
		try {
			
			//7.更新订单安装项
			boolean flag = updateSupplier(installId,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,null,supplierConfirmRemarks,Integer.valueOf(supplierId));
			if(!flag){
				result = "7";
				return result;
			}
			
			//8.保存安装项状态日志
			Integer logId = saveBusinessStatusLog(empId,installId,BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierId);
			if(null==logId || logId < 1){
				result = "8";
				return result;  
			}
			
			
			//9.查询最新一次的安装单以及施工单
			BizSupplierInstallBill bizSupplierInstallBill = dao.findInstallBillAndConstructBill(installId);
			if(null!=bizSupplierInstallBill){
				
				//9.1    如果安装单存在，更新安装单
				if(null != bizSupplierInstallBill.getInstallBillId()){
					//10.更新安装单
					bizSupplierInstallBill.setId(bizSupplierInstallBill.getInstallBillId());
					bizSupplierInstallBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_90);
					bizSupplierInstallBill.setStatusDatetime(new Date());
					bizSupplierInstallBill.preUpdate();
					boolean SupplierInstallBillFlag = (dao.updateSupplierInstallBill(bizSupplierInstallBill))?true:false;
					if(!SupplierInstallBillFlag){
						result = "10";
						return result;
					}
					
					//11.保存安装单废除日志
					Integer BillLogId = saveBusinessStatusLog(empId,bizSupplierInstallBill.getInstallBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_90,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_90,null);
					if(null == BillLogId || BillLogId < 1){
						result = "11";
						return result;  
					}
				}
				//9.2    如果施工单存在，更新施工单
				if(null!=bizSupplierInstallBill.getInstallConstructBillId()){
					//12.更新施工单
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
					
					//13.保存施工单废除日志
					Integer BillLogId = saveBusinessStatusLog(empId,bizSupplierInstallBill.getInstallConstructBillId(),BusinessLogConstantUtil.BUSINESS_TYPE_9012,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_90,InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_90,null);
					if(null==BillLogId || BillLogId<1){
						result = "13";
						return result;  
					}
				}
				
			}
			
			
			//14.保存安装单
			Integer supplierInstallBillId = saveSupplierInstallBill(installId,Integer.valueOf(supplierId),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10);
			if(null==supplierInstallBillId || supplierInstallBillId < 1){
				result = "14";
				return result;  
			}
			
			//15.保存安装单状态日志
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