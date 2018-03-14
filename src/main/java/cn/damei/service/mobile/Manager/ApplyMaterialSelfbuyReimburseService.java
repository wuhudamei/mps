package cn.damei.service.mobile.Manager;




import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.MaterialSelfbuyConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ApplyMaterialSelfbuyReimburseDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.dao.modules.BizMaterialSelfbuyReimburseDao;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;



@Service
@Transactional(readOnly = true)
public class ApplyMaterialSelfbuyReimburseService {

	@Autowired
	private ApplyMaterialSelfbuyReimburseDao dao;
	@Autowired
	private BizMaterialSelfbuyReimburseDao bizMaterialSelfbuyReimburseDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	

	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();

		materialManagement.setItemManagerId(managerId);

		materialManagement.setText(text);
		
		return dao.findOrderList(materialManagement);
	}


	public MaterialManagement findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}


	public Integer findMaterialSelfbuyReimburseCount(Integer orderId,String relatedReimburseId,String materialSelfbuyReimburseType) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		

		bizMaterialSelfbuyReimburse.setReimburseType(materialSelfbuyReimburseType);

		if(StringUtils.isNotBlank(relatedReimburseId)){
			bizMaterialSelfbuyReimburse.setRelatedReimburseId(Integer.valueOf(relatedReimburseId));
		}

		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		 
		Integer materialSelfbuyReimburseCount = dao.findMaterialSelfbuyReimburseCount(bizMaterialSelfbuyReimburse);
		
		return materialSelfbuyReimburseCount;
		
	}
	
	

	public List<BizMaterialSelfbuy> findMaterialSelfbuyList(MaterialManagement materialManagement) {

		BizMaterialSelfbuy bizMaterialSelfbuy = new BizMaterialSelfbuy();
		bizMaterialSelfbuy.setStoreId(materialManagement.getStoreId());
		bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1);
		bizMaterialSelfbuy.setProjectMode(materialManagement.getProjectMode());
		bizMaterialSelfbuy.setDelFlag(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_DEL_FLAG_0);
		
		return dao.findMaterialSelfbuyList(bizMaterialSelfbuy);
	}


	@Transactional(readOnly = false)
	public Integer saveMaterialSelfbuyReimburse(Integer orderId, Integer materialId, Integer materialSelfbuyId, Double customerPayAmount,
			Double settleRateTwo, String settleStage, Double settleAmount, String reimburseRemarks,
			String materialSelfbuyReimburseType, String materialSelfbuyReimburseStatus, String materialSelfbuyReimburseStatusRemarks) {

		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		

		bizMaterialSelfbuyReimburse.setReimburseType(materialSelfbuyReimburseType);

		bizMaterialSelfbuyReimburse.setRelatedReimburseId(materialId);

		bizMaterialSelfbuyReimburse.setOrderId(orderId);

		bizMaterialSelfbuyReimburse.setMaterialSelfbuyId(materialSelfbuyId);

		bizMaterialSelfbuyReimburse.setCustomerPayAmount(customerPayAmount);

		bizMaterialSelfbuyReimburse.setSettleRateTwo(settleRateTwo);

		bizMaterialSelfbuyReimburse.setSettleStage(settleStage);

		bizMaterialSelfbuyReimburse.setSettleAmount(settleAmount);

		bizMaterialSelfbuyReimburse.setReimburseRemarks(reimburseRemarks);

		bizMaterialSelfbuyReimburse.setReimburseStatus(materialSelfbuyReimburseStatus);

		bizMaterialSelfbuyReimburse.setReimburseStatusDatetime(new Date());

		bizMaterialSelfbuyReimburse.setReimburseStatusRemarks(materialSelfbuyReimburseStatusRemarks);
		
		bizMaterialSelfbuyReimburse.preInsert();
		 
		bizMaterialSelfbuyReimburseDao.insert(bizMaterialSelfbuyReimburse);
		
		if(null==materialId){

			bizMaterialSelfbuyReimburse.setRelatedReimburseId(bizMaterialSelfbuyReimburse.getId());
			bizMaterialSelfbuyReimburseDao.update(bizMaterialSelfbuyReimburse);
		}
		return bizMaterialSelfbuyReimburse.getId();
	}

	
	
	

	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer materialSelfbuyReimburseId, Integer managerId, String materialSelfbuyReimburseStatus,
			String reimburseRemarks, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(materialSelfbuyReimburseId);

		bizBusinessStatusLog.setBusinessType(businessType);

		bizBusinessStatusLog.setBusinessStatus(materialSelfbuyReimburseStatus);

		bizBusinessStatusLog.setBusinessRemarks(reimburseRemarks);

		bizBusinessStatusLog.setStatusDatetime(new Date());

		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
	}
	
	

	@Transactional(readOnly=false)
	public void deleteMaterialSelfbuyReimburse(Integer materialSelfbuyReimburseId) {
		bizMaterialSelfbuyReimburseDao.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
	}
	
	

	@Transactional(readOnly=false)
	public void saveMaterialSelfbuyReimbursePic(Integer materialSelfbuyReimburseId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {
		
		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();

		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				

				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(materialSelfbuyReimburseId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}

			checkConfirmDao.savePicAll(pList);
		}
		
	}


	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseRecordList(Integer orderId) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		

		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		
		return dao.findMaterialSelfbuyReimburseRecordList(bizMaterialSelfbuyReimburse);
	}


	public BizMaterialSelfbuyReimburse findLastTimeMaterialSelfbuyDetail(Integer orderId, Integer relatedReimburseId) {

		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		bizMaterialSelfbuyReimburse.setRelatedReimburseId(relatedReimburseId);
		
		return dao.findLastTimeMaterialSelfbuyDetail(bizMaterialSelfbuyReimburse);
	}



	@Transactional(readOnly=false)
	public void findLastPicListAndSave(String[] picUrlId, Integer materialSelfbuyReimburseId) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setPicList(Arrays.asList(picUrlId));

		List<ReportCheckDetailsPic> list = dao.findLastPicList(reportCheckDetailsPic);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(ReportCheckDetailsPic pic:list){
				pic.setId(null);
				pic.setBusinessIdInt(materialSelfbuyReimburseId);
				pic.setPicDatetime(new Date());
				pic.preInsert();
			}

			checkConfirmDao.savePicAll(list);
		}
	}


	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer orderId, Integer relatedReimburseId) {
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();

		bizMaterialSelfbuyReimburse.setOrderId(orderId);

		bizMaterialSelfbuyReimburse.setRelatedReimburseId(relatedReimburseId);
		return dao.findMaterialSelfbuyReimburseDetails(bizMaterialSelfbuyReimburse);
	}


	public List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(Integer orderId, Integer relatedReimburseId,
			String businessType) {
		
		ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog = new ApplyMaterialSelfbuyReimburseStatusLog();

		applyMaterialSelfbuyReimburseStatusLog.setOrderId(orderId);

		applyMaterialSelfbuyReimburseStatusLog.setRelatedReimburseId(relatedReimburseId);

		applyMaterialSelfbuyReimburseStatusLog.setBusinessType(businessType);
		return dao.findMaterialStatusLogDetails(applyMaterialSelfbuyReimburseStatusLog);
	}


	

	
	


}
