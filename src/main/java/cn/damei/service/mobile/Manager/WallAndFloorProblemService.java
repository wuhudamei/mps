package cn.damei.service.mobile.Manager;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.dao.mobile.Manager.WallAndFloorProblemDao;
import cn.damei.dao.modules.BizOrderInstallItemProblemDao;
import cn.damei.dao.modules.BizOrderInstallItemProblemLogDao;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;



@Service
@Transactional(readOnly = true)
public class WallAndFloorProblemService {

	@Autowired
	private WallAndFloorProblemDao dao;
	@Autowired
	private BizOrderInstallItemProblemDao bizOrderInstallItemProblemDao;
	@Autowired
	private BizOrderInstallItemProblemLogDao bizOrderInstallItemProblemLogDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;


	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();

		materialManagement.setItemManagerId(managerId);

		materialManagement.setText(text);

		materialManagement.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_5);
		
		return dao.findOrderList(materialManagement);
	}


	public MaterialManagement findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}


	@Transactional(readOnly = false)
	public Integer saveProblem(Integer orderId, Integer problemTypeId, String isDelay, Double delayDays,
			String problemDescribe, String businessProblemStatus, String businessProblemBusinessType, Date txtBeginDate, String inchargeName, BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();

		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);

		bizOrderInstallItemProblem.setProblemTypeId(problemTypeId);

		bizOrderInstallItemProblem.setIsDelay(isDelay);

		bizOrderInstallItemProblem.setDelayDays(delayDays);

		bizOrderInstallItemProblem.setProblemDescribe(problemDescribe);

		bizOrderInstallItemProblem.setStatus(businessProblemStatus);

		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		

		bizOrderInstallItemProblem.setExpectSolveDatetime(txtBeginDate);

		bizOrderInstallItemProblem.setInchargeName(inchargeName);

		bizOrderInstallItemProblem.setPunishScore(bizProjectInstallItemProblemType.getPunishScore());

		bizOrderInstallItemProblem.setPunishMoney(bizProjectInstallItemProblemType.getPunishMoney());

		bizOrderInstallItemProblem.setPunishRemarks(bizProjectInstallItemProblemType.getPunishRemarks());
		
		
		bizOrderInstallItemProblem.preInsert();
		
		bizOrderInstallItemProblemDao.insert1(bizOrderInstallItemProblem);
		
		
		return bizOrderInstallItemProblem.getId();
	}


	@Transactional(readOnly = false)
	public Integer saveProblemLog(Integer problemId, Integer managerId, String businessProblemSolveRole,
			String businessProblemStatus, String problemDescribe) {
		
		BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog = new BizOrderInstallItemProblemLog();

		bizOrderInstallItemProblemLog.setBusinessProblemId(problemId);

		bizOrderInstallItemProblemLog.setStatus(businessProblemStatus);

		bizOrderInstallItemProblemLog.setProblemSolveRole(businessProblemSolveRole);

		bizOrderInstallItemProblemLog.setProblemSolveEmployeeId(managerId);

		bizOrderInstallItemProblemLog.setProblemSolveNotes(problemDescribe);
		
		bizOrderInstallItemProblemLog.preInsert();
		
		bizOrderInstallItemProblemLogDao.insert(bizOrderInstallItemProblemLog);
		
		return bizOrderInstallItemProblemLog.getId();
	}


	@Transactional(readOnly = false)
	public void deleteProblem(Integer problemId) {
		bizOrderInstallItemProblemDao.deleteProblem(problemId);
		
	}


	public Integer findProblemCount(Integer orderId, String businessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();

		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);

		bizOrderInstallItemProblem.setBusinessType(businessType);
		
		return bizOrderInstallItemProblemDao.findProblemCount(bizOrderInstallItemProblem);
	}


	public List<BizOrderInstallItemProblem> findProblemLogList(Integer orderId, String businessProblemBusinessType,
			String businessProblemSolveRole, String businessProblemStatus) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();

		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);

		bizOrderInstallItemProblem.setStatus(businessProblemStatus);

		bizOrderInstallItemProblem.setProblemSolveRole(businessProblemSolveRole);

		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		
		return dao.findProblemLogList(bizOrderInstallItemProblem);
	}


	@Transactional(readOnly=false)
	public void saveProblemPic(Integer problemId, String pictureType, String[] photo,
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
				reportCheckDetailsPic.setBusinessIdInt(problemId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}

			checkConfirmDao.savePicAll(pList);
		}
		
	}

	
	
	


}
