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


/**
 * 墙地砖问题上报Service
 * @author Administrator
 *
 */
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

	/**
	 * 墙地砖问题上报订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();
		//项目经理
		materialManagement.setItemManagerId(managerId);
		//搜索文本框
		materialManagement.setText(text);
		//采购单类型
		materialManagement.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_5);
		
		return dao.findOrderList(materialManagement);
	}

	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	public MaterialManagement findOrder(Integer orderId) {
		return dao.findOrder(orderId);
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
			String problemDescribe, String businessProblemStatus, String businessProblemBusinessType, Date txtBeginDate, String inchargeName, BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		
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
	 * 查询该订单5分钟内提交问题上报的数量
	 * @param orderId
	 * @param businessType
	 * @return
	 */
	public Integer findProblemCount(Integer orderId, String businessType) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();
		//唯一标识
		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);
		//业务类型
		bizOrderInstallItemProblem.setBusinessType(businessType);
		
		return bizOrderInstallItemProblemDao.findProblemCount(bizOrderInstallItemProblem);
	}

	/**
	 * 动态加载墙地砖问题上报记录页面
	 * @param orderId
	 * @param businessProblemBusinessType2
	 * @param businessProblemSolveRole3
	 * @param businessProblemStatus50
	 * @return
	 */
	public List<BizOrderInstallItemProblem> findProblemLogList(Integer orderId, String businessProblemBusinessType,
			String businessProblemSolveRole, String businessProblemStatus) {
		
		BizOrderInstallItemProblem bizOrderInstallItemProblem = new BizOrderInstallItemProblem();
		// 唯一标识id
		bizOrderInstallItemProblem.setBusinessOnlyMarkId(orderId);
		// 状态
		bizOrderInstallItemProblem.setStatus(businessProblemStatus);
		// 问题处理角色
		bizOrderInstallItemProblem.setProblemSolveRole(businessProblemSolveRole);
		//业务类型
		bizOrderInstallItemProblem.setBusinessType(businessProblemBusinessType);
		
		return dao.findProblemLogList(bizOrderInstallItemProblem);
	}

	/**
	 * 保存问题上报  图片
	 * @param problemId
	 * @param pictureType
	 * @param photo
	 * @param uploadSiteDesignProblemManagerApply
	 * @param request 
	 */
	@Transactional(readOnly=false)
	public void saveProblemPic(Integer problemId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {
		
		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		//保存图片
		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				//保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(problemId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}
			//批量插入约检验收图片
			checkConfirmDao.savePicAll(pList);
		}
		
	}

	
	
	


}
