/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizSupplierInstallBillDao;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.SupplierInstallWorker;
import cn.damei.dao.modules.BizSupplierInstallConstructBillDao;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.dao.modules.EnginInstallNewDealDao;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;

/**
 * 供应商安装单表Service
 * 
 * @author wyb
 * @version 2017-07-13
 */
@Service
@Transactional(readOnly = true)
public class BizSupplierInstallBillService extends CrudService2<BizSupplierInstallBillDao, BizSupplierInstallBill> {

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	@Autowired
	private EnginInstallNewDealDao enginInstallNewDealDao;
	@Autowired
	private BizSupplierInstallConstructBillDao bizSupplierInstallConstructBillDao;
	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;

	public BizSupplierInstallBill get(Integer id) {
		return super.get(id);
	}

	public List<BizSupplierInstallBill> findList(BizSupplierInstallBill bizSupplierInstallBill) {
		return super.findList(bizSupplierInstallBill);
	}

	public Page<BizSupplierInstallBill> findPage(Page<BizSupplierInstallBill> page, BizSupplierInstallBill bizSupplierInstallBill) {
		return super.findPage(page, bizSupplierInstallBill);
	}

	@Transactional(readOnly = false)
	public void save(BizSupplierInstallBill bizSupplierInstallBill) {
		super.save(bizSupplierInstallBill);
	}

	@Transactional(readOnly = false)
	public void delete(BizSupplierInstallBill bizSupplierInstallBill) {
		super.delete(bizSupplierInstallBill);
	}

	/**
	 * 查询安装单的相关信息
	 * 
	 * @param installBillId
	 * @return
	 */
	public BizSupplierInstallBill findInstallBillDetails(Integer installBillId) {
		return dao.findInstallBillDetails(installBillId);
	}

	/**
	 * 查询供应商列表
	 * 
	 * @param employeePhone
	 * @return
	 */
	public List<EnginInstallSupplier> findSupplierList(String employeePhone) {
		EnginInstallSupplier enginInstallSupplier = new EnginInstallSupplier();
		enginInstallSupplier.setSupplierContactsPhone(employeePhone);

		return dao.findSupplierList(enginInstallSupplier);
	}

	/**
	 * 根据供应商加载安装项列表
	 * 
	 * @param supplierId
	 * @return
	 */
	public List<BizProjectInstallItem> findProjectInstallItemList(Integer supplierId) {
		return dao.findProjectInstallItemList(supplierId);
	}

	/**
	 * 查询工人组
	 * 
	 * @param installBillId
	 * @param workerName
	 * @return
	 */
	public List<SupplierInstallWorker> findInstallWorkerList(Integer installBillId, String workerName) {

		SupplierInstallWorker supplierInstallWorker = new SupplierInstallWorker();

		// 安装单id
		supplierInstallWorker.setInstallBillId(installBillId);

		// 工人姓名
		supplierInstallWorker.setWorkerName(workerName);

		return dao.findInstallWorkerList(supplierInstallWorker);
	}

	/**
	 * 查询工人组施工单信息
	 * 
	 * @param installConstructBillId
	 * @return
	 */
	public BizSupplierInstallConstructBill findEmployeeGroupMessage(Integer installConstructBillId) {
		return dao.findEmployeeGroupMessage(installConstructBillId);
	}

	/**
	 * 更新安装单--确认工期
	 * 
	 * @param bizSupplierInstallBill
	 * @param supplierInstallBillStatus
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmCompleteDate
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateSupplierConfirmDate(BizSupplierInstallBill bizSupplierInstallBill, String supplierInstallBillStatus, String supplierConfirmIntoDate, String supplierConfirmCompleteDate) {

		// 1.状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		// 2.状态时间
		bizSupplierInstallBill.setStatusDatetime(new Date());
		// 3.供应商确认进场日期
		if (StringUtils.isNotBlank(supplierConfirmIntoDate)) {
			bizSupplierInstallBill.setSupplierConfirmIntoDate(DateUtils.parseDate(supplierConfirmIntoDate));
		}
		// 4.供应商确认完工日期
		if (StringUtils.isNotBlank(supplierConfirmIntoDate)) {
			bizSupplierInstallBill.setSupplierConfirmCompleteDate(DateUtils.parseDate(supplierConfirmCompleteDate));
		}

		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierConfirmDate(bizSupplierInstallBill)) ? true : false;
	}

	/**
	 * 保存状态日志
	 * 
	 * @param id
	 * @param orderInstallItemId
	 * @param businessType
	 * @param status
	 * @param remarks
	 * @param dataday2
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);
		// 2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		// 3.业务状态
		bizBusinessStatusLog.setBusinessStatus(status);
		// 4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(remarks);
		// 5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		// 6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);

		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();

		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);

		return bizBusinessStatusLog.getId();

	}

	/**
	 * 分配工人组
	 * 
	 * @param bizSupplierInstallBill
	 * @param installBillId
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmCompleteDate
	 * @param employeegroupId
	 * @param empId
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateSupplierDistributionWorkers(BizSupplierInstallBill bizSupplierInstallBill, Integer installBillId, String supplierConfirmIntoDate, String supplierConfirmCompleteDate, Integer employeegroupId, Integer empId) {

		String result = "0";
		try {

			if (!bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10)) {
				supplierConfirmIntoDate = DateFormatUtils.format(bizSupplierInstallBill.getSupplierConfirmIntoDate(), "yyyy-MM-dd");
				supplierConfirmCompleteDate = DateFormatUtils.format(bizSupplierInstallBill.getSupplierConfirmCompleteDate(), "yyyy-MM-dd");
			}

			// 8.更新安装项的状态
			boolean flag = updateInstallPlan(bizSupplierInstallBill.getInstallPlanId(), InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310, supplierConfirmIntoDate, supplierConfirmCompleteDate);
			if (!flag) {
				result = "8";
				return result;
			}
			// 9.保存安装项状态日志
			Integer logId = saveBusinessStatusLog(empId, bizSupplierInstallBill.getInstallPlanId(), BusinessLogConstantUtil.BUSINESS_TYPE_901, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_310, null);
			if (null == logId || logId < 1) {
				result = "9";
				return result;
			}

			// 10.更新安装单
			boolean flag1 = updateSupplierConfirmDate(bizSupplierInstallBill, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_20, supplierConfirmIntoDate, supplierConfirmCompleteDate);
			if (!flag1) {
				result = "10";
				return result;
			}

			// 11.保存安装单日志
			Integer BillLogId = saveBusinessStatusLog(empId, installBillId, BusinessLogConstantUtil.BUSINESS_TYPE_9011, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_20, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_20, null);
			if (null == BillLogId || BillLogId < 1) {
				result = "11";
				return result;
			}

			// 12.删除之前的施工单
			if (null != bizSupplierInstallBill.getInstallConstructBillId()) {
				// 12.更新施工单
				BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
				bizSupplierInstallConstructBill.setId(bizSupplierInstallBill.getInstallConstructBillId());
				bizSupplierInstallConstructBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_90);
				bizSupplierInstallConstructBill.setStatusDatetime(new Date());
				bizSupplierInstallConstructBill.preUpdate();
				boolean supplierInstallBillFlag = (enginInstallNewDealDao.updateSupplierInstallConstructBill(bizSupplierInstallConstructBill)) ? true : false;
				if (!supplierInstallBillFlag) {
					result = "12";
					return result;
				}
				// 13.保存施工单废除日志
				Integer BillLogIdBefore = saveBusinessStatusLog(empId, bizSupplierInstallBill.getInstallConstructBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_9012, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_90, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_90, null);
				if (null == BillLogIdBefore || BillLogIdBefore < 1) {
					result = "13";
					return result;
				}
			}

			// 14.保存施工单
			Integer supplierInstallConstructBillId = saveSupplierInstallConstructBill(installBillId, employeegroupId, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10);
			if (null == supplierInstallConstructBillId || supplierInstallConstructBillId < 1) {
				result = "14";
				return result;
			}

			// 15.保存施工单插入日志
			Integer BillLogIdAfter = saveBusinessStatusLog(empId, supplierInstallConstructBillId, BusinessLogConstantUtil.BUSINESS_TYPE_9012, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_10, null);
			if (null == BillLogIdAfter || BillLogIdAfter < 1) {
				result = "15";
				return result;
			}

			// 16.根据工人组id查询工人组长信息
			SupplierInstallWorker supplierInstallWorker = dao.findWorkerMessage(employeegroupId);
			if (null == supplierInstallWorker) {
				result = "16";
				return result;
			}

			// 17.发送短信给工人组长
			if (StringUtils.isNotBlank(supplierInstallWorker.getWorkerPhone())) {

				// 短信内容“订单（合顺家园-18-2-205）的主材安装（橱柜）已经派单给您了，要求工期为（2017-07-13至2017-07-15），项目经理（张小峰-13210203030），请您及时登录美装工匠APP查看。”
				String content = "订单（" + bizSupplierInstallBill.getCommunityName() + "-" + bizSupplierInstallBill.getBuildNumber() + "-" + bizSupplierInstallBill.getBuildUnit() + "-" + bizSupplierInstallBill.getBuildRoom() + "）的主材安装（" + bizSupplierInstallBill.getInstallItemName() + "）已经派单给您了，要求工期为（" + supplierConfirmIntoDate + "至" + supplierConfirmCompleteDate + "），项目经理（" + bizSupplierInstallBill.getItemManagerName() + "-" + bizSupplierInstallBill.getItemManagerPhone() + "），请您及时登录美装工匠APP查看。";
				Integer messageId = saveMessage(supplierInstallWorker.getWorkerId(), supplierInstallWorker.getWorkerPhone(), content, installBillId, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110206);
				if (null == messageId || messageId < 1) {
					result = "17";
					return result;
				}

			}
			// 18.发送短信给项目经理
			if (StringUtils.isNotBlank(bizSupplierInstallBill.getItemManagerPhone())) {

				// 短信内容“订单（合顺家园-18-2-205-张三丰）的主材安装（橱柜）供应商已经分派给工人组了，工人组长（张小小-13820203030），要求工期为（2017-07-13至2017-07-15），请您知晓。”
				String content = "订单（" + bizSupplierInstallBill.getCommunityName() + "-" + bizSupplierInstallBill.getBuildNumber() + "-" + bizSupplierInstallBill.getBuildUnit() + "-" + bizSupplierInstallBill.getBuildRoom() + "-" + bizSupplierInstallBill.getCustomerName() + "）的主材安装（" + bizSupplierInstallBill.getInstallItemName() + "）供应商已经分派给工人组了，工人组长（" + supplierInstallWorker.getWorkerName() + "-" + supplierInstallWorker.getWorkerPhone() + "），要求工期为（" + supplierConfirmIntoDate + "至" + supplierConfirmCompleteDate + "），请您知晓。";
				Integer messageId = saveMessage(bizSupplierInstallBill.getItemManagerId(), bizSupplierInstallBill.getItemManagerPhone(), content, installBillId, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110207);
				if (null == messageId || messageId < 1) {
					result = "18";
					return result;
				}

			}

		} catch (Exception e) {
			result = "19";
			return result;
		}

		return result;
	}

	/**
	 * 更新安装项--分派工人组
	 * 
	 * @param installPlanId
	 * @param installPlanStatus
	 * @param supplierConfirmCompleteDate
	 * @param supplierConfirmIntoDate
	 * @return
	 */
	@Transactional(readOnly = false)
	private boolean updateInstallPlan(Integer installPlanId, String installPlanStatus, String supplierConfirmIntoDate, String supplierConfirmCompleteDate) {

		EnginInstallNew enginInstall = new EnginInstallNew();
		// 安装项id
		enginInstall.setId(installPlanId);
		// 安装项状态
		enginInstall.setInstallStatus(installPlanStatus);
		// 供应商确认进场日期
		if (StringUtils.isNotBlank(supplierConfirmIntoDate)) {
			enginInstall.setSupplierConfirmIntoDate(DateUtils.parseDate(supplierConfirmIntoDate));
		}
		// 供应商确认完工日期
		if (StringUtils.isNotBlank(supplierConfirmCompleteDate)) {
			enginInstall.setSupplierConfirmCompleteDate(DateUtils.parseDate(supplierConfirmCompleteDate));
		}

		enginInstall.preUpdate();

		return (dao.updateSupplier(enginInstall)) ? true : false;
	}

	/**
	 * 保存施工单
	 * 
	 * @param supplierInstallConstructBillStatus
	 * @param employeegroupId
	 * @param installBillId
	 * @return
	 */
	@Transactional(readOnly = false)
	private Integer saveSupplierInstallConstructBill(Integer installBillId, Integer employeegroupId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();

		// 1.施工单号
		bizSupplierInstallConstructBill.setConstructBillCode(constructBillCode());
		// 2.供应商安装单id
		bizSupplierInstallConstructBill.setSupplierInstallBillId(installBillId);
		// 3.工人组id
		bizSupplierInstallConstructBill.setEmployeeGroupId(employeegroupId);
		// 4.状态
		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);
		// 5.状态日期时间
		bizSupplierInstallConstructBill.setStatusDatetime(new Date());

		bizSupplierInstallConstructBill.preInsert();

		bizSupplierInstallConstructBillDao.insert(bizSupplierInstallConstructBill);

		return bizSupplierInstallConstructBill.getId();
	}

	/**
	 * 发送短信
	 * 
	 * @param employeeId
	 * @param employeeIdPhone
	 * @param content
	 * @param installId
	 * @param relatedBusinessType
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveMessage(Integer employeeId, String employeeIdPhone, String content, Integer installId, String relatedBusinessType) {

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
	 * 施工单号
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	private String constructBillCode() {
		// 以SZ开头

		StringBuilder builder = new StringBuilder();
		// num
		ReCheckCode code1 = enginInstallNewDealDao.getCode(ConstantUtils.SUPPLIER_INSTALL_CONSTRUCT_BILL_CODE);
		if (null == code1) {
			ReCheckCode reCheckCode = new ReCheckCode();

			reCheckCode.setBussinessType(ConstantUtils.SUPPLIER_INSTALL_CONSTRUCT_BILL_CODE);
			reCheckCode.setLastSeiralnum(0);
			reCheckCode.setGenerateTime(new Date());

			enginInstallNewDealDao.saveCode(reCheckCode);
			code1 = enginInstallNewDealDao.getCode(ConstantUtils.SUPPLIER_INSTALL_CONSTRUCT_BILL_CODE);
		}
		builder.append(code1.getBussinessType());
		// 格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
			code1.setGenerateTime(new Date());
		}
		builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
		enginInstallNewDealDao.updateCode(code1);
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

	public BizSupplierInstallBill getnot90(BizSupplierInstallBill entity) {
		return dao.getnot90(entity);
	}

}