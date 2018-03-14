package cn.damei.web.mobile.home;


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
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.EvalRewardTaskpack;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.service.mobile.home.CustomerEvaluateWorkerService;
import cn.damei.service.modules.BizEvalRewardStarService;
import cn.damei.entity.modules.BizEvalActivityIndex;
/**
 * 客户评价工人
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/app/home/evaluate/evalWorker")
public class CustomerEvaluateWorkerController {
	
	@Autowired
	private CustomerEvaluateWorkerService customerEvaluateWorkerService;
	@Autowired
	private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;
    @Autowired
    private BizEvalRewardStarService bizEvalRewardStarService;
	    
	@RequestMapping(value = "list")
	public String list(Integer orderId,HttpServletRequest request, Model model) {
		
		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		
		//查询订单列表
		List<BizOrder> list = customerEvaluateWorkerService.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			if(list.size()>1){
				model.addAttribute("list", list);
			}
			model.addAttribute("ordersLength",list.size());
		}else{
			return "mobile/modules/home/evaluate/evalWorker/comment_null";
		}
		
		//查询订单
		BizOrder bizOrder = new BizOrder();
		bizOrder.setOrderId(orderId);
		bizOrder.setCustomerPhone(customerPhone);
		BizOrder order = customerEvaluateWorkerService.findOrder(bizOrder);
		
		//查询评价列表
		List<EvaluateWorker> evaluateWorkerList = customerEvaluateWorkerService.findEvaluateList(order.getOrderId());
		String exists ="0";
		if(null!=evaluateWorkerList && evaluateWorkerList.size()>0){
			exists="1";
		}
		model.addAttribute("order",order);
		model.addAttribute("evaluateWorkerList",evaluateWorkerList);
		model.addAttribute("exists",exists);
		return "mobile/modules/home/evaluate/evalWorker/commentList";
	}
	
	/**
	 * 客户评价页面
	 * @param evalTaskpackScoreId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toEvaluate")
	public String toEvaluate(String evalTaskpackScoreId,HttpServletRequest request,Model model){
		
		//任务包相关信息
		EvaluateWorker evaluateWorker = inspectorEvaluateWorkerService.findOrderTaskpack(Integer.valueOf(evalTaskpackScoreId));
		//根据任务包ID查询相关联的评价活动
		evaluateWorker.setEvalRoleType("3");
		List<BizEvalActivityIndex> list = inspectorEvaluateWorkerService.findEvalActivityIndex(evaluateWorker);
		
		model.addAttribute("evaluateWorker", evaluateWorker);
		model.addAttribute("list", list);
		return "mobile/modules/home/evaluate/evalWorker/commentDisplay";
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
		
		phone = (String) request.getSession().getAttribute("customerPhone");
		
		Date date = new Date();
		
		String fanhui = "1";
		//判断是否超过了12小时
		BizEvalScore item = new BizEvalScore();
		item.setEvalStartDatetime(date);
		item.setId(Integer.valueOf(evalTaskpackScoreId));
		item.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
		item.setRange("301,302");
		BizEvalScore a = inspectorEvaluateWorkerService.issave(item);
		if(null!=a){
			//客户评价
			Inspector inspector = new Inspector();
			fanhui = inspectorEvaluateWorkerService.evaluate(evalTaskpackScoreId,evalActivityIndexId,evalTotalScore,number,advise,inspector.getId(),phone,ConstantUtils.EVAL_ROLE_TYPE_301);
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
			//判断是否评价结束
		    BizEvalScoreRole scoreBean = new BizEvalScoreRole();
			scoreBean.setEvalScoreId(Integer.parseInt(evalTaskpackScoreId));
			scoreBean.setManagerType(managerType);
			scoreBean.setPqcType(pqcType);
			scoreBean.setCustemerType(custemerType);
			BizEvalScoreRole evalTaskpackRoleScore = inspectorEvaluateWorkerService.isEndEvaluate(scoreBean);
			if(null!=evalTaskpackRoleScore && evalTaskpackRoleScore.getGotScore()!=null && evalTaskpackRoleScore.getGotScore()>0){
				//更新评价任务包得分表
				BizEvalScore evalTaskpackScore = new BizEvalScore();
				evalTaskpackScore.setId(Integer.valueOf(evalTaskpackScoreId));
				evalTaskpackScore.setGotScore(evalTaskpackRoleScore.getGotScore());
				evalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
				evalTaskpackScore.setUpdateDate(date);
				evalTaskpackScore.setStatusDatetime(date);
				inspectorEvaluateWorkerService.updateEvalTaskpackScore(evalTaskpackScore);
				
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
		}
		
		return fanhui;
	}
	
	/**
	 * 客户评价详情页面
	 * @param evalTaskpackScoreId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toDetails")
	public String toDetails(String evalTaskpackScoreId,HttpServletRequest request,Model model){
		
		List<EvaluateWorker> list = customerEvaluateWorkerService.toDetails(Integer.valueOf(evalTaskpackScoreId));
		String groupRealname = "";
		String packageName = "";
		String evalFeedback = "";
		if(null!=list && list.size()>0){
			for(EvaluateWorker a :list){
				groupRealname = a.getGroupRealname();
				packageName = a.getPackageName();
				evalFeedback = a.getEvalFeedback();
				break;
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("groupRealname", groupRealname);
		model.addAttribute("packageName", packageName);
		model.addAttribute("evalFeedback", evalFeedback);
		return "mobile/modules/home/evaluate/evalWorker/commentDetails";
	}
	
}
