package cn.damei.service.mobile.Worker;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.dao.mobile.Worker.InstallProblemDao;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.dao.modules.BizOrderInstallItemProblemDao;
import cn.damei.dao.modules.BizOrderInstallItemProblemLogDao;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;


@Service
@Transactional(readOnly = false)
public class InstallProblemService{
	
	
	@Autowired
	private InstallProblemDao dao;
	@Autowired
	private InstallApplyForCompletionService installApplyForCompletionService;
	@Autowired
	private BizOrderInstallItemProblemDao bizOrderInstallItemProblemDao;
	@Autowired
	private BizOrderInstallItemProblemLogDao bizOrderInstallItemProblemLogDao;
	
	

	public List<InstallItem> findInstallConstructBillProblemList(InstallItem installItem) {
		return dao.findInstallConstructBillProblemList(installItem);
	}


	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}
	

	public Integer findProblemCount(Integer constructBillId, String businessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();

		bizOrderInstallItemProblem.setBusinessOnlyMarkId(constructBillId);

		bizOrderInstallItemProblem.setBusinessType(businessType);
		
		return dao.findProblemCount(bizOrderInstallItemProblem);
	}


	public String saveConstructBillProblem(Integer constructBillId, String problemTypeId, String problemDescribe, String[] photo, Worker worker,
			InstallItem installItem, BizProjectInstallItemProblemType bizProjectInstallItemProblemType, HttpServletRequest request) {
		
		String result = "0";
		
		try {
			

			Integer problemId = saveProblem(constructBillId,Integer.valueOf(problemTypeId),null,null,problemDescribe,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5,null,null,bizProjectInstallItemProblemType);
			if(null==problemId || problemId<1){
				result = "8";
				return result;
			}
			

			Integer problemLogId = saveProblemLog(problemId,worker.getId(),BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_6,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80,problemDescribe);
			if(null==problemLogId || problemLogId<1){
				deleteProblem(problemId);
				result = "9";
				return result;
			}

			boolean picFlag = installApplyForCompletionService.savePic(problemId,photo,PictureTypeContantUtil.PICTURE_TYPE_2072,PicturePathContantUtil.UPLOAD_WORKER_INSTALL_PROBLEM_UPLOAD_PHOTO,request);
			if(!picFlag){
				result = "10";
				return result;  
			}
			
		} catch (Exception e) {
			result = "11";
			return result;
		}
		
		return result;
	}


	@Transactional(readOnly = false)
	public Integer saveProblem(Integer orderId, Integer problemTypeId, String isDelay, Double delayDays,
			String problemDescribe, String businessProblemStatus, String businessProblemBusinessType, 
			Date txtBeginDate, String inchargeName, BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		
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


	public List<BizOrderInstallItemProblem> findProblemLogList(Integer constructBillId, String businessProblemBusinessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();

		bizOrderInstallItemProblem.setBusinessOnlyMarkId(constructBillId);

		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		
		return dao.findProblemLogList(bizOrderInstallItemProblem);
	}


	public List<ReportCheckDetailsPic> findPic(Integer id, String pictureType) {
		
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(pictureType);
		List<ReportCheckDetailsPic> list = dao.findPic(reportCheckDetailsPic);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	





	
	
}
