package cn.damei.service.mobile.Worker;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Worker.InstallApplyForCompletionDao;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;

@Service
@Transactional(readOnly = false)
public class InstallApplyForCompletionService {

	@Autowired
	private InstallApplyForCompletionDao dao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

	/**
	 * 查询施工单列表（完工）
	 * 
	 * @param installItem
	 * @return
	 */
	public List<InstallItem> findInstallConstructBillApplyForCompletionList(InstallItem installItem) {
		return dao.findInstallConstructBillApplyForCompletionList(installItem);
	}

	/**
	 * 根据施工单id查询相关信息（完工）
	 * 
	 * @param constructBillId
	 * @return
	 */
	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}

	/**
	 * 保存完工信息（完工）
	 * 
	 * @param constructBillId
	 * @param photo
	 * @param worker
	 * @param installItem
	 * @param request
	 * @return
	 */
	public String saveInstallConstructBillApplyForCompletion(Integer constructBillId, String[] photo, Worker worker, InstallItem installItem, HttpServletRequest request) {

		String result = "0";

		try {
			// 6.保存完工信息
			boolean picFlag = savePic(constructBillId, photo, PictureTypeContantUtil.PICTURE_TYPE_2071, PicturePathContantUtil.UPLOAD_WORKER_INSTALL_APPLY_COMPLETION_UPLOAD_PHOTO, request);
			if (!picFlag) {
				result = "6";
				return result;
			}

			// 7.更新安装项的状态
			boolean flag = updateInstallPlan(installItem.getOrderInstallPlanId(), InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330);
			if (!flag) {
				result = "7";
				return result;
			}
			// 8.保存安装项状态日志
			Integer logId = saveBusinessStatusLog(worker.getId(), installItem.getOrderInstallPlanId(), BusinessLogConstantUtil.BUSINESS_TYPE_901, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_330, null);
			if (null == logId || logId < 1) {
				result = "8";
				return result;
			}
			// 9.更新安装单
			boolean flag1 = updateSupplierInstallBill(installItem.getInstallBillId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40);
			if (!flag1) {
				result = "9";
				return result;
			}

			// 10.保存安装单日志
			Integer BillLogId = saveBusinessStatusLog(worker.getId(), installItem.getInstallBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_9011, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_40, null);
			if (null == BillLogId || BillLogId < 1) {
				result = "10";
				return result;
			}
			// 11.更新施工单
			boolean flag2 = updateSupplierConstructBill(installItem.getConstructBillId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30);
			if (!flag2) {
				result = "11";
				return result;
			}

			// 12.保存施工单日志
			Integer constructBillLogId = saveBusinessStatusLog(worker.getId(), installItem.getConstructBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_9012, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_30, null);
			if (null == constructBillLogId || constructBillLogId < 1) {
				result = "12";
				return result;
			}

		} catch (Exception e) {
			result = "13";
			return result;
		}

		return result;
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
	 * 更新安装项计划
	 * 
	 * @param orderInstallPlanId
	 * @param installPlanStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateInstallPlan(Integer orderInstallPlanId, String installPlanStatus) {

		EnginInstallNew enginInstall = new EnginInstallNew();
		// 安装项id
		enginInstall.setId(orderInstallPlanId);
		// 安装项状态
		enginInstall.setInstallStatus(installPlanStatus);
		// 实际完工日期
		enginInstall.setRealCompleteDate(new Date());

		enginInstall.preUpdate();

		return (dao.updateInstallPlan(enginInstall)) ? true : false;
	}

	/**
	 * 更新安装单
	 * 
	 * @param installBillId
	 * @param supplierInstallBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();

		// 1.安装单id
		bizSupplierInstallBill.setId(installBillId);
		// 2.状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		// 3.状态时间
		bizSupplierInstallBill.setStatusDatetime(new Date());
		// 4.实际完工日期
		bizSupplierInstallBill.setRealCompleteDate(new Date());

		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}

	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBillz(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();

		// 1.安装单id
		bizSupplierInstallBill.setId(installBillId);
		// 2.状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		// 3.状态时间
		bizSupplierInstallBill.setStatusDatetime(new Date());
		// 4.实际完工日期
		bizSupplierInstallBill.setRealAcceptDate(new Date());

		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}

	/**
	 * 更新施工单
	 * 
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
		// 4.实际完工日期
		bizSupplierInstallConstructBill.setRealCompleteDate(date);

		bizSupplierInstallConstructBill.preUpdate();

		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill)) ? true : false;
	}

	/**
	 * 保存 图片 （必须有图片）
	 * 
	 * @param problemId
	 * @param pictureType
	 * @param photo
	 * @param uploadSiteDesignProblemManagerApply
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean savePic(Integer constructBillId, String[] photo, String pictureType, String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		boolean flag = false;
		// 保存图片
		if (null != photo && photo.length > 0) {
			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());
				// 判断该文件是否存在
				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

				// 保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(constructBillId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}
			// 批量插入图片
			flag = (dao.savePicAll(pList)) ? true : false;
		}

		return flag;

	}

}
