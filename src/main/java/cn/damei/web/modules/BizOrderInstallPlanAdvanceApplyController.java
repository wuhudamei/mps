/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderInstallPlanAdvanceApplyConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;
import cn.damei.service.modules.BizOrderInstallPlanAdvanceApplyService;

/**
 * 主材安装项提前申请记录Controller
 * @author wyb
 */
@Controller
@RequestMapping(value = "${adminPath}/modules/orderinstallplanadvanceapply/web/bizOrderInstallPlanAdvanceApply")
public class BizOrderInstallPlanAdvanceApplyController extends BaseController {
	
	
	@Autowired
	private BizOrderInstallPlanAdvanceApplyService bizOrderInstallPlanAdvanceApplyService;
	
	@ModelAttribute
	public BizOrderInstallPlanAdvanceApply get(@RequestParam(required=false) Integer id) {
		BizOrderInstallPlanAdvanceApply entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizOrderInstallPlanAdvanceApplyService.get(id);
		}
		if (entity == null){
			entity = new BizOrderInstallPlanAdvanceApply();
		}
		return entity;
	}
	
	/**
	 * 主材可申请安装日期处理页面【列表页-前】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:view")
	@RequestMapping(value = {"preInstallPlanAdvanceApplyList", ""})
	public String preInstallPlanAdvanceApplyList(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		bizOrderInstallPlanAdvanceApplyService.storeIdAndProjectMode(bizOrderInstallPlanAdvanceApply, model);
		
		return "modules/orderinstallplanadvanceapply/bizOrderInstallPlanAdvanceApplyList";
	}
	
	/**
	 * 主材可申请安装日期处理页面【列表页】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:view")
	@RequestMapping(value = {"installPlanAdvanceApplyList", ""})
	public String installPlanAdvanceApplyList(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		bizOrderInstallPlanAdvanceApplyService.storeIdAndProjectMode(bizOrderInstallPlanAdvanceApply, model);
		//申请类型【安装】
		bizOrderInstallPlanAdvanceApply.setApplyType(OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1);
		
		Page<BizOrderInstallPlanAdvanceApply> page = bizOrderInstallPlanAdvanceApplyService.findPage(new Page<BizOrderInstallPlanAdvanceApply>(request, response), bizOrderInstallPlanAdvanceApply); 
		model.addAttribute("page", page);
		return "modules/orderinstallplanadvanceapply/bizOrderInstallPlanAdvanceApplyList";
	}
	
	/**
	 * 主材可申请复尺日期处理页面【列表页-前】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:view")
	@RequestMapping(value = {"preChecksizeAdvanceApplyList", ""})
	public String preChecksizeAdvanceApplyList(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		bizOrderInstallPlanAdvanceApplyService.storeIdAndProjectMode(bizOrderInstallPlanAdvanceApply, model);
		
		return "modules/orderinstallplanadvanceapply/bizOrderChecksizeAdvanceApplyList";
	}
	

	/**
	 * 主材可申请复尺日期处理页面【列表页】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:view")
	@RequestMapping(value = {"checksizeAdvanceApplyList", ""})
	public String checksizeAdvanceApplyList(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		bizOrderInstallPlanAdvanceApplyService.storeIdAndProjectMode(bizOrderInstallPlanAdvanceApply, model);
		//申请类型【复尺】
		bizOrderInstallPlanAdvanceApply.setApplyType(OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2);
		
		Page<BizOrderInstallPlanAdvanceApply> page = bizOrderInstallPlanAdvanceApplyService.findPage(new Page<BizOrderInstallPlanAdvanceApply>(request, response), bizOrderInstallPlanAdvanceApply); 
		model.addAttribute("page", page);
		return "modules/orderinstallplanadvanceapply/bizOrderChecksizeAdvanceApplyList";
	}
	
	
	
	/**
	 * 主材可申请安装日期处理【拒绝】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit")
	@RequestMapping(value = { "save_install_advance_apply_refuse", "" })
	public @ResponseBody String saveInstallAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,HttpServletRequest request) {
		return bizOrderInstallPlanAdvanceApplyService.saveInstallAdvanceApplyRefuse(bizOrderInstallPlanAdvanceApply);
	}
	
	/**
	 * 主材可申请安装日期处理【同意】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit")
	@RequestMapping(value = { "save_install_advance_apply_agree", "" })
	public @ResponseBody String saveInstallAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,HttpServletRequest request) {
		return bizOrderInstallPlanAdvanceApplyService.saveInstallAdvanceApplyAgree(bizOrderInstallPlanAdvanceApply);
	}
	
	/**
	 * 主材可申请复尺日期处理【拒绝】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit")
	@RequestMapping(value = { "save_checksize_advance_apply_refuse", "" })
	public @ResponseBody String saveChecksizeAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,HttpServletRequest request) {
		return bizOrderInstallPlanAdvanceApplyService.saveChecksizeAdvanceApplyRefuse(bizOrderInstallPlanAdvanceApply);
	}
	
	/**
	 * 主材可申请复尺日期处理【同意】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit")
	@RequestMapping(value = { "save_checksize_advance_apply_agree", "" })
	public @ResponseBody String saveChecksizeAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,HttpServletRequest request) {
		return bizOrderInstallPlanAdvanceApplyService.saveChecksizeAdvanceApplyAgree(bizOrderInstallPlanAdvanceApply);
	}


	

}