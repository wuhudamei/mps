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
	
	
	/**
	 * 查询施工单列表(问题上报)
	 * @param installItem
	 * @return
	 */
	public List<InstallItem> findInstallConstructBillProblemList(InstallItem installItem) {
		return dao.findInstallConstructBillProblemList(installItem);
	}

	/**
	 * 根据施工单id查询相关信息(问题上报)
	 * @param constructBillId
	 * @return
	 */
	public InstallItem findInstallConstructBillMessage(Integer constructBillId) {
		return dao.findInstallConstructBillMessage(constructBillId);
	}
	
	/**
	 * 查询该订单5分钟内提交问题上报的数量
	 * @param constructBillId
	 * @param businessType
	 * @return
	 */
	public Integer findProblemCount(Integer constructBillId, String businessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();
		//唯一标识
		bizOrderInstallItemProblem.setBusinessOnlyMarkId(constructBillId);
		//业务类型
		bizOrderInstallItemProblem.setBusinessType(businessType);
		
		return dao.findProblemCount(bizOrderInstallItemProblem);
	}

	/**
	 * 保存上报问题
	 * @param constructBillId
	 * @param problemTypeId
	 * @param problemDescribe
	 * @param photo
	 * @param worker
	 * @param installItem
	 * @param bizProjectInstallItemProblemType
	 * @param request 
	 * @return
	 */
	public String saveConstructBillProblem(Integer constructBillId, String problemTypeId, String problemDescribe, String[] photo, Worker worker,
			InstallItem installItem, BizProjectInstallItemProblemType bizProjectInstallItemProblemType, HttpServletRequest request) {
		
		String result = "0";
		
		try {
			
			//8.保存上报问题
			Integer problemId = saveProblem(constructBillId,Integer.valueOf(problemTypeId),null,null,problemDescribe,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5,null,null,bizProjectInstallItemProblemType);
			if(null==problemId || problemId<1){
				result = "8";
				return result;
			}
			
			//9.保存上报问题日志
			Integer problemLogId = saveProblemLog(problemId,worker.getId(),BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_6,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80,problemDescribe);
			if(null==problemLogId || problemLogId<1){
				deleteProblem(problemId);
				result = "9";
				return result;
			}
			//10.保存上传图片
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

	/**
	 * 保存问题上报
	 * @param orderId
	 * @param problemTypeId
	 * @param isDelay
	 * @param delayDays
	 * @param problemDescribe
	 * @param businessProblemStatus
	 * @param businessProblemBusinessType
	 * @param inchargeName 
	 * @param txtBeginDate 
	 * @param bizProjectInstallItemProblemType 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveProblem(Integer orderId, Integer problemTypeId, String isDelay, Double delayDays,
			String problemDescribe, String businessProblemStatus, String businessProblemBusinessType, 
			Date txtBeginDate, String inchargeName, BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();
		//唯一标识
		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);
		//问题分类id
		bizOrderInstallItemProblem.setProblemTypeId(problemTypeId);
		//是否延期
		bizOrderInstallItemProblem.setIsDelay(isDelay);
		//延期天数
		bizOrderInstallItemProblem.setDelayDays(delayDays);
		//问题描述
		bizOrderInstallItemProblem.setProblemDescribe(problemDescribe);
		//状态
		bizOrderInstallItemProblem.setStatus(businessProblemStatus);
		//业务类型
		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		
		//期望完成日期
		bizOrderInstallItemProblem.setExpectSolveDatetime(txtBeginDate);
		// 责任人
		bizOrderInstallItemProblem.setInchargeName(inchargeName);
		// 扣除分数
		bizOrderInstallItemProblem.setPunishScore(bizProjectInstallItemProblemType.getPunishScore());
		// 罚款金额
		bizOrderInstallItemProblem.setPunishMoney(bizProjectInstallItemProblemType.getPunishMoney());
		// 罚款说明
		bizOrderInstallItemProblem.setPunishRemarks(bizProjectInstallItemProblemType.getPunishRemarks());
		
		
		bizOrderInstallItemProblem.preInsert();
		
		bizOrderInstallItemProblemDao.insert1(bizOrderInstallItemProblem);
		
		
		return bizOrderInstallItemProblem.getId();
	}
	
	/**
	 * 保存问题上报日志
	 * @param problemId
	 * @param managerId
	 * @param businessProblemSolveRole
	 * @param businessProblemStatus
	 * @param problemDescribe
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveProblemLog(Integer problemId, Integer managerId, String businessProblemSolveRole,
			String businessProblemStatus, String problemDescribe) {
		
		BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog = new BizOrderInstallItemProblemLog();
		// 问题上报id
		bizOrderInstallItemProblemLog.setBusinessProblemId(problemId);
		// 状态
		bizOrderInstallItemProblemLog.setStatus(businessProblemStatus);
		// 问题处理角色
		bizOrderInstallItemProblemLog.setProblemSolveRole(businessProblemSolveRole);
		// 问题处理员工id
		bizOrderInstallItemProblemLog.setProblemSolveEmployeeId(managerId);
		// 问题处理说明
		bizOrderInstallItemProblemLog.setProblemSolveNotes(problemDescribe);
		
		bizOrderInstallItemProblemLog.preInsert();
		
		bizOrderInstallItemProblemLogDao.insert(bizOrderInstallItemProblemLog);
		
		return bizOrderInstallItemProblemLog.getId();
	}

	/**
	 * 删除问题上报
	 * @param problemId
	 */
	@Transactional(readOnly = false)
	public void deleteProblem(Integer problemId) {
		bizOrderInstallItemProblemDao.deleteProblem(problemId);
		
	}

	/**
	 *  动态加载问题上报记录页面
	 * @param valueOf
	 * @param businessProblemBusinessType
	 * @return
	 */
	public List<BizOrderInstallItemProblem> findProblemLogList(Integer constructBillId, String businessProblemBusinessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();
		// 唯一标识id
		bizOrderInstallItemProblem.setBusinessOnlyMarkId(constructBillId);
		//业务类型
		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		
		return dao.findProblemLogList(bizOrderInstallItemProblem);
	}

	/**
	 * 查看图片
	 * @param id
	 * @param pictureType
	 * @return
	 */
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
