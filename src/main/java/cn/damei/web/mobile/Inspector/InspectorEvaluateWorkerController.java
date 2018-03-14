package cn.damei.web.mobile.Inspector;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.EvalRewardTaskpack;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Manager.BizOrderTaskpackageProcedureService;
import cn.damei.service.mobile.Manager.GuaranteeMoneyService;
import cn.damei.service.mobile.Manager.OrderTaskpackageSettlementService;
import cn.damei.service.modules.BizEvalRewardStarService;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.BizEvalActivityIndex;
/**
 * 质检评价工人
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/app/pqc/evaluate/evalWorker")
public class InspectorEvaluateWorkerController {
	
	@Autowired
	private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;

    @Autowired
    private OrderTaskpackageSettlementService settlementService;

    @Autowired
    private GuaranteeMoneyService guaranteeMoneyService;

    @Autowired
    private BizOrderTaskpackageProcedureService bizOrderTaskpackageProcedureService;
    @Autowired
    private BizEvalRewardStarService bizEvalRewardStarService;
    
    @Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;
	
	/**
	 * 质检评价工人页面
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(String text,HttpServletRequest request,Model model){
		
		Inspector inspector = SessionUtils.getInspectorSession(request);
		EvaluateWorker evaluateWorker = new EvaluateWorker();
		evaluateWorker.setOrderInspectorId(inspector.getId());
		evaluateWorker.setText(text);
		evaluateWorker.setNowDate(new Date());
		evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_2);
		List<EvaluateWorker> list = inspectorEvaluateWorkerService.findEvalList(evaluateWorker);
		
		
		
		model.addAttribute("list", list);
		model.addAttribute("text", text);
		return "mobile/modules/pqc/evaluate/evalWorker/commentWorker";
	}
	
	/**
	 * 质检评价页面
	 * @param text
	 * @param evalTaskpackScoreId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toEvaluate")
	public String toEvaluate(String text,Integer evalTaskpackScoreId,HttpServletRequest request,Model model){
		
		//任务包相关信息
		EvaluateWorker evaluateWorker = inspectorEvaluateWorkerService.findOrderTaskpack(evalTaskpackScoreId);
		//根据任务包ID查询相关联的评价活动
		evaluateWorker.setEvalRoleType("2");
		List<BizEvalActivityIndex> list = inspectorEvaluateWorkerService.findEvalActivityIndex(evaluateWorker);
		
		model.addAttribute("text", text);
		model.addAttribute("evaluateWorker", evaluateWorker);
		model.addAttribute("list", list);
		return "mobile/modules/pqc/evaluate/evalWorker/commentDisplay";
	}
	
	/**
	 * 评价
	 * @param phone
	 * @param evalTaskpackScoreId
	 * @param evalActivityIndexId
	 * @param evalTotalScore
	 * @param number
	 * @param advise
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="evaluate")
	public @ResponseBody String evaluate(String phone,String evalTaskpackScoreId,String[] evalActivityIndexId,String[] evalTotalScore,String[] number,String advise,HttpServletRequest request,Model model){
		
		Date date = new Date();
	
		BizEvalScore item = new BizEvalScore();
		item.setId(Integer.valueOf(evalTaskpackScoreId));
		item.setRange("201,202");
		BizEvalScore a = inspectorEvaluateWorkerService.issave(item);
		
			//质检评价
			Inspector inspector = SessionUtils.getInspectorSession(request);
			String fanhui = inspectorEvaluateWorkerService.evaluate(evalTaskpackScoreId,evalActivityIndexId,evalTotalScore,number,advise,inspector.getId(),phone,ConstantUtils.EVAL_ROLE_TYPE_201);
			
			//判断是否评价结束
			List<BizEvalActivityIndex> activityIndexList= inspectorEvaluateWorkerService.queryEvalActivityIndexByPackageId(a.getRelatedBusinessId());
			String  managerType=null;
			String  pqcType=null;
			String  custemerType=null;
			if(activityIndexList != null && activityIndexList.size()>0){
				for(BizEvalActivityIndex activityIndex : activityIndexList){
					if(activityIndex.getEvalRoleType().equals("1")){//项目经理评价
						managerType="1";
					}else if(activityIndex.getEvalRoleType().equals("2")){//质检评价
						pqcType="2";
					}else if(activityIndex.getEvalRoleType().equals("3")){//客户评价
						custemerType="3";
					}
				}
			}
			BizEvalScoreRole scoreBean = new BizEvalScoreRole();
			scoreBean.setEvalScoreId(Integer.parseInt(evalTaskpackScoreId));
			scoreBean.setManagerType(managerType);
			scoreBean.setPqcType(pqcType);
			scoreBean.setCustemerType(custemerType);
			scoreBean.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
			BizEvalScoreRole evalTaskpackRoleScore = inspectorEvaluateWorkerService.isEndEvaluate(scoreBean);
			if(null!=evalTaskpackRoleScore && evalTaskpackRoleScore.getGotScore() != null && evalTaskpackRoleScore.getGotScore()>0){
				//评价结束
				//更新评价任务包得分表
				BizEvalScore  evalTaskpackScore = new BizEvalScore();
				evalTaskpackScore.setId(Integer.valueOf(evalTaskpackScoreId));
				evalTaskpackScore.setGotScore(evalTaskpackRoleScore.getGotScore());
				evalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
				evalTaskpackScore.setUpdateDate(date);
				evalTaskpackScore.setStatusDatetime(date);
				
				
				//添加星级得分变化表记录
				
				List<BizEmployeegroupVO>listqqq =bizBizEmployeegroupVoDao.getSumAvg(a.getGroupLeaderEmployeeId());
      			
      			if(listqqq.size()==0){
      				bizBizEmployeegroupVoDao.insertStarLog(0,evalTaskpackRoleScore.getGotScore(),a.getGroupLeaderEmployeeId());	
      			}else{
      				for(BizEmployeegroupVO bb:listqqq){
          				if(bb.getCiShu()!=0 && bb.getCiShu()!=null && bb.getGotScore()!=null){
    	      				double beforeScore=bb.getGotScore()/bb.getCiShu();
    	      				double afterScore=(evalTaskpackRoleScore.getGotScore()+bb.getGotScore())/(bb.getCiShu()+1);
    	      				
    	      				List<BizEmployeegroupVO>listSelect= bizBizEmployeegroupVoDao.selectStarLog(a.getRelatedBusinessId());
    	      				if(listSelect.size()==0){
    	      					inspectorEvaluateWorkerService.updateEvalTaskpackScore(evalTaskpackScore);
    	      					bizBizEmployeegroupVoDao.insertStarLog(beforeScore,afterScore,a.getGroupLeaderEmployeeId());	
    	      				}
          				}
          			}
      			}
      			inspectorEvaluateWorkerService.updateEvalTaskpackScore(evalTaskpackScore);
      			bizBizEmployeegroupVoDao.updateStar(a.getGroupLeaderEmployeeId());
      			bizBizEmployeegroupVoDao.updateStarGroup(a.getGroupLeaderEmployeeId());
				 // 查询奖励金额
                Map<String, Object> rewardMap = new HashMap<String, Object>();
                rewardMap.put("orderTaskpackId", a.getRelatedBusinessId());
                rewardMap.put("gotScore", evalTaskpackRoleScore.getGotScore());
                Double rewardAmount = bizEvalRewardStarService.queryEvalRewardStarByMap(rewardMap);

                EvalRewardTaskpack evalRewardTaskpack = new EvalRewardTaskpack();
				evalRewardTaskpack.setOrderTaskpackageId(a.getRelatedBusinessId());
				evalRewardTaskpack.setGroupLeaderEmployeeId(a.getGroupLeaderEmployeeId());
				evalRewardTaskpack.setRewardAmount(rewardAmount);
				evalRewardTaskpack.setRewardDatetime(new Date());
				evalRewardTaskpack.preInsert();
				inspectorEvaluateWorkerService.insertEvalRewardTaskpack(evalRewardTaskpack);
			}
		
		return fanhui;
	}
}
