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


	public List<InstallItem> findInstallConstructBillApplyForCompletionList(InstallItem installItem) {
		return dao.findInstallConstructBillApplyForCompletionList(installItem);
	}


	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}


	public String saveInstallConstructBillApplyForCompletion(Integer constructBillId, String[] photo, Worker worker, InstallItem installItem, HttpServletRequest request) {

		String result = "0";

		try {

			boolean picFlag = savePic(constructBillId, photo, PictureTypeContantUtil.PICTURE_TYPE_2071, PicturePathContantUtil.UPLOAD_WORKER_INSTALL_APPLY_COMPLETION_UPLOAD_PHOTO, request);
			if (!picFlag) {
				result = "6";
				return result;
			}


			boolean flag = updateInstallPlan(installItem.getOrderInstallPlanId(), InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330);
			if (!flag) {
				result = "7";
				return result;
			}

			Integer logId = saveBusinessStatusLog(worker.getId(), installItem.getOrderInstallPlanId(), BusinessLogConstantUtil.BUSINESS_TYPE_901, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_330, null);
			if (null == logId || logId < 1) {
				result = "8";
				return result;
			}

			boolean flag1 = updateSupplierInstallBill(installItem.getInstallBillId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40);
			if (!flag1) {
				result = "9";
				return result;
			}


			Integer BillLogId = saveBusinessStatusLog(worker.getId(), installItem.getInstallBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_9011, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_40, null);
			if (null == BillLogId || BillLogId < 1) {
				result = "10";
				return result;
			}

			boolean flag2 = updateSupplierConstructBill(installItem.getConstructBillId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30);
			if (!flag2) {
				result = "11";
				return result;
			}


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


	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status, String remarks, String dataday2) {

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

		enginInstall.setRealCompleteDate(new Date());

		enginInstall.preUpdate();

		return (dao.updateInstallPlan(enginInstall)) ? true : false;
	}


	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();


		bizSupplierInstallBill.setId(installBillId);

		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);

		bizSupplierInstallBill.setStatusDatetime(new Date());

		bizSupplierInstallBill.setRealCompleteDate(new Date());

		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}

	@Transactional(readOnly = false)
	public boolean updateSupplierInstallBillz(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();


		bizSupplierInstallBill.setId(installBillId);

		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);

		bizSupplierInstallBill.setStatusDatetime(new Date());

		bizSupplierInstallBill.setRealAcceptDate(new Date());

		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}


	@Transactional(readOnly = false)
	public boolean updateSupplierConstructBill(Integer constructBillId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();

		Date date = new Date();

		bizSupplierInstallConstructBill.setId(constructBillId);

		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);

		bizSupplierInstallConstructBill.setStatusDatetime(date);

		bizSupplierInstallConstructBill.setRealCompleteDate(date);

		bizSupplierInstallConstructBill.preUpdate();

		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill)) ? true : false;
	}


	@Transactional(readOnly = false)
	public boolean savePic(Integer constructBillId, String[] photo, String pictureType, String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		boolean flag = false;

		if (null != photo && photo.length > 0) {
			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";


				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(constructBillId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}

			flag = (dao.savePicAll(pList)) ? true : false;
		}

		return flag;

	}

}
