
package cn.damei.web.modules;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizOrderInstallItemProblemService;
import cn.damei.service.modules.BusinessWallAndFloorProblemService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblem/wallAndFloor/problem")
public class BusinessWallAndFloorProblemController extends BaseController {

	@Autowired
	private BusinessWallAndFloorProblemService businessWallAndFloorProblemService;
	
	@Autowired
	private BizOrderInstallItemProblemService bizOrderInstallItemProblemService;
	
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	

	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "preDealList", "" })
	public String preDealList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();

		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}

		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10);

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
			
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		return "modules/bizorderinstallitemproblem/wallAndFloorDealProblemList";
	}
	

	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "dealList", "" })
	public String dealList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();

		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}
		

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		
		return "modules/bizorderinstallitemproblem/wallAndFloorDealProblemList";
	}
	
	
	
	
	

	@RequestMapping(value = "update_problem_ajax")
	public @ResponseBody String updateProblemAjax(String problemId ,String problemSolveNotes){
		
		String result = "0";
		

		if(null==problemId || problemId.equals("")){
			result = "1";
			return result;
		}
		

		if(null==problemSolveNotes ||problemSolveNotes.equals("")){
			result = "2";
			return result;
		}
		

		BizOrderInstallItemProblem bizOrderInstallItemProblem = bizOrderInstallItemProblemService.get(Integer.valueOf(problemId));
		if(null!=bizOrderInstallItemProblem && bizOrderInstallItemProblem.getStatus().equals(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50)){
			result = "3";
			return result;
		}
		

		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null==user){
			result = "4";
			return result;
		}else if(null != user.getEmpId()){
			managerId = Integer.parseInt(user.getEmpId());
		}
		

		boolean flag = businessWallAndFloorProblemService.updateProblem(bizOrderInstallItemProblem,Integer.valueOf(problemId),BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		if(!flag){
			result = "5";
			return result;
		}
				

		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(Integer.valueOf(problemId),managerId,BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50,problemSolveNotes);
		if(null==problemLogId || problemLogId<1){
			result = "6";
			return result;
		}
		
		return result;
	}
	
	
	
	
	

	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();

		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}
		

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
				
		return "modules/bizorderinstallitemproblem/wallAndFloorSelectProblemList";
	}
	

	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();

		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}
		

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		
		return "modules/bizorderinstallitemproblem/wallAndFloorSelectProblemList";
	}
	

	@RequestMapping(value = { "details", "" })
	public String details(String problemId,Model model) {
		
		BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo = null;
		if(null!=problemId && !problemId.equals("")){
			bizOrderInstallItemProblemVo = businessWallAndFloorProblemService.findDetails(Integer.valueOf(problemId));
		}
		model.addAttribute("bizOrderInstallItemProblemVo", bizOrderInstallItemProblemVo);
		return "modules/bizorderinstallitemproblem/wallAndFloorProblemDetails";
	}
	
}