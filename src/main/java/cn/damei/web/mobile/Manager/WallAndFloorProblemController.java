package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;
import cn.damei.service.modules.BizProjectInstallItemProblemTypeService;



@Controller
@RequestMapping(value="${adminPath}/app/manager/problem/wallAndFloor")
public class WallAndFloorProblemController {
	
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	@Autowired
	private BizProjectInstallItemProblemTypeService bizProjectInstallItemProblemTypeService;
	
	private Logger  logger =  LoggerFactory.getLogger(AuxiliaryApplyController.class);


	@RequestMapping(value={"wallAndFloor_problem_back",""})
	public String wallAndFloorProblemBack(HttpServletRequest request, Model model){
		
		String wallAndFloorProblem = (String)request.getSession().getAttribute("wallAndFloorProblem");
		request.getSession().removeAttribute("wallAndFloorProblem");
		
		if(StringUtils.isNotBlank(wallAndFloorProblem)){
			if("1".equals(wallAndFloorProblem)){

				return "mobile/modules/Manager/materialManagement/materialManagement";	
			}else if("0".equals(wallAndFloorProblem)){

				return "mobile/modules/Manager/project-build/problem_up";
			}else{
				
				logger.warn("墙地砖问题上报访问参数wallAndFloorProblem有误  wallAndFloorProblem:"+wallAndFloorProblem);
				return "mobile/modules/Manager/project-build/problem_up";
			}
			
		}else{
			
			logger.warn("墙地砖问题上报访问参数为空");
			return "mobile/modules/Manager/materialManagement/materialManagement";
		}
		
	}
	
	

	@RequestMapping(value={"list",""})
	public String list(String wallAndFloorProblem,HttpServletRequest request, Model model){
		
		if("0".equals(wallAndFloorProblem)||"1".equals(wallAndFloorProblem)){
			request.getSession().setAttribute("wallAndFloorProblem", wallAndFloorProblem);
		}else{
			logger.warn("墙地砖问题上报访问参数有误 ,无法识别路径参数: wallAndFloorProblem :"+wallAndFloorProblem);
		}
		

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null!=manager){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/project-build/wallAndFloorProblem/quesUp";
	}
	

	@RequestMapping(value="problem_wallAndFloor_ajax_list")
	public @ResponseBody  List<MaterialManagement> problemWallAndFloorAjaxList(String managerId,String text){
		
		List<MaterialManagement> list = null;
		if(null!=managerId && !("").equals(managerId)){
			list = wallAndFloorProblemService.findOrderList(Integer.valueOf(managerId),text);
		}
		
		return list;
	}
	
	

	@RequestMapping(value="problem_wallAndFloor_ajax_time")
	public @ResponseBody String problemWallAndFloorAjaxTime(String orderId,String businessType){
		
		String result = "0";
		
		if(null==orderId || orderId.equals("")){
			result = "1";
			return result;
		}

		Integer count = wallAndFloorProblemService.findProblemCount(Integer.valueOf(orderId),businessType);
		
		if(null!=count && count>0){
			result = "2";
			return result;
		}
		return result;
	}


	@RequestMapping(value={"problem_wallAndFloor",""})
	public String problemWallAndFloor(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizProjectInstallItemProblemType> problemList = null;
		if(null!=orderId && !orderId.equals("")){

			materialManagement = wallAndFloorProblemService.findOrder(Integer.valueOf(orderId));

			BizProjectInstallItemProblemType problemType = new BizProjectInstallItemProblemType();
			problemType.setStoreId(materialManagement.getStoreId());
			problemType.setIsEnabled(BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_ENABLE_1);
			problemType.setProjectMode(materialManagement.getProjectMode());
			problemType.setDelFlag(BusinessProblemConstantUtil.BUSINESS_PROBLEM_DEL_FLAG_0);
			problemType.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);
			problemList = bizProjectInstallItemProblemTypeService.findList(problemType);
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("problemList", problemList);
		model.addAttribute("orderId", orderId);
	
		return "mobile/modules/Manager/project-build/wallAndFloorProblem/ques";
		
	}	
	

	@RequestMapping(value="wallAndFloor_problem_submit" ,method=RequestMethod.POST)
	public @ResponseBody String wallAndFloorProblemSubmit(String orderId,String problemTypeId,String quality,String delayDays,String problemDescribe,HttpServletRequest request){
		String result = "0";
		

		if(null==orderId || orderId.equals("")){
			result = "1";
			return result;
		}

		if(null==problemTypeId || problemTypeId.equals("")){
			result = "2";
			return result;
		}

		if(null==quality || quality.equals("")){
			result = "3";
			return result;
		}

		String isDelay = null;
		if(quality.equals("yes1")){
			isDelay = BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_DELAY_1;
			if(null==delayDays || delayDays.equals("")){
				result = "4";
				return result;
			}
		}else{
			isDelay = BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_DELAY_0;
		}

		if(null==problemDescribe || problemDescribe.equals("")){
			result = "5";
			return result;
		}

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "6";
			return result;
		}
		

		BizProjectInstallItemProblemType bizProjectInstallItemProblemType = bizProjectInstallItemProblemTypeService.get(Integer.valueOf(problemTypeId));
		if(null==bizProjectInstallItemProblemType){
			result = "7";
			return result;
		}
				

		Integer problemId = wallAndFloorProblemService.saveProblem(Integer.valueOf(orderId),Integer.valueOf(problemTypeId),isDelay,Double.valueOf(delayDays),problemDescribe,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2,null,null,bizProjectInstallItemProblemType);
		if(null==problemId || problemId<1){
			result = "8";
			return result;
		}
		

		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(problemId,manager.getId(),BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_1,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10,problemDescribe);
		if(null==problemLogId || problemLogId<1){
			wallAndFloorProblemService.deleteProblem(problemId);
			result = "9";
			return result;
		}
		
		return result;
	}
	
		

	@RequestMapping(value={"problemRecord",""})
	public String problemRecord(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		if(null!=orderId && !orderId.equals("")){

			materialManagement = wallAndFloorProblemService.findOrder(Integer.valueOf(orderId));
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		
		return "mobile/modules/Manager/project-build/wallAndFloorProblem/quesUpDetails";
	}
	

	@RequestMapping(value="problem_log_ajax_list")
	public @ResponseBody  List<BizOrderInstallItemProblem> problemLogAjaxList(String orderId){
		
		List<BizOrderInstallItemProblem> list = null;
		if(null!=orderId && !("").equals(orderId)){
			list = wallAndFloorProblemService.findProblemLogList(Integer.valueOf(orderId),BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2,BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		}
		
		return list;
	}
	

}
