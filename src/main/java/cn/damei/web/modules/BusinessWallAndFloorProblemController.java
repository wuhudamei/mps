/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 墙地砖问题上报
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblem/wallAndFloor/problem")
public class BusinessWallAndFloorProblemController extends BaseController {

	@Autowired
	private BusinessWallAndFloorProblemService businessWallAndFloorProblemService;
	
	@Autowired
	private BizOrderInstallItemProblemService bizOrderInstallItemProblemService;
	
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	
	/**
	 * 墙地砖问题上报处理
	 * @param bizOrderInstallItemProblemVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "preDealList", "" })
	public String preDealList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
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
		//状态
		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10);
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);
		//材料部处理角色
		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);
		//材料部处理状态
		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
			
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		return "modules/bizorderinstallitemproblem/wallAndFloorDealProblemList";
	}
	
	/**
	 * 墙地砖问题上报处理
	 * @param bizOrderInstallItemProblemVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "dealList", "" })
	public String dealList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
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
		
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);
		//材料部处理角色
		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);
		//材料部处理状态
		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		
		return "modules/bizorderinstallitemproblem/wallAndFloorDealProblemList";
	}
	
	
	
	
	
	/**
	 * 问题上报处理
	 * @param problemId
	 * @param problemSolveNotes
	 * @return
	 */
	@RequestMapping(value = "update_problem_ajax")
	public @ResponseBody String updateProblemAjax(String problemId ,String problemSolveNotes){
		
		String result = "0";
		
		//1.问题上报id为空
		if(null==problemId || problemId.equals("")){
			result = "1";
			return result;
		}
		
		//2.问题上报处理回复内容为空
		if(null==problemSolveNotes ||problemSolveNotes.equals("")){
			result = "2";
			return result;
		}
		
		//3.问题是否已处理
		BizOrderInstallItemProblem bizOrderInstallItemProblem = bizOrderInstallItemProblemService.get(Integer.valueOf(problemId));
		if(null!=bizOrderInstallItemProblem && bizOrderInstallItemProblem.getStatus().equals(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50)){
			result = "3";
			return result;
		}
		
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null==user){
			result = "4";
			return result;
		}else if(null != user.getEmpId()){
			managerId = Integer.parseInt(user.getEmpId());
		}
		
		//5.更新问题上报状态
		boolean flag = businessWallAndFloorProblemService.updateProblem(bizOrderInstallItemProblem,Integer.valueOf(problemId),BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		if(!flag){
			result = "5";
			return result;
		}
				
		//6.保存问题上报日志
		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(Integer.valueOf(problemId),managerId,BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50,problemSolveNotes);
		if(null==problemLogId || problemLogId<1){
			result = "6";
			return result;
		}
		
		return result;
	}
	
	
	
	
	
	/**
	 * 墙地砖问题查询
	 * @param bizOrderInstallItemProblemVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
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
		
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);
		//材料部处理角色
		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);
		//材料部处理状态
		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
				
		return "modules/bizorderinstallitemproblem/wallAndFloorSelectProblemList";
	}
	
	/**
	 * 墙地砖问题查询
	 * @param bizOrderInstallItemProblemVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wallAndFloorproblem:wallAndFloorproblem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
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
		
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_2);
		//材料部处理角色
		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_3);
		//材料部处理状态
		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_50);
		
		Page<BizOrderInstallItemProblemVo> page = businessWallAndFloorProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);
		
		
		return "modules/bizorderinstallitemproblem/wallAndFloorSelectProblemList";
	}
	
	/**
	 * 墙地砖问题上报详情
	 * @param problemId
	 * @param model
	 * @return
	 */
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