/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlan;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizNodePlanService;

/**
 * 进度节点Controller
 * @author llp
 * @version 2016-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/biznodeplan/bizNodePlan")
public class BizNodePlanController extends BaseController {

	@Autowired
	private BizNodePlanService bizNodePlanService;
	
	@ModelAttribute
	public BizNodePlan get(@RequestParam(required=false) Integer id) {
		BizNodePlan entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizNodePlanService.get(id);
		}
		if (entity == null){
			entity = new BizNodePlan();
		}
		return entity;
	}
	
	@RequiresPermissions("biznodeplan:bizNodePlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNodePlan bizNodePlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizNodePlan> page = bizNodePlanService.findPage(new Page<BizNodePlan>(request, response), bizNodePlan); 
		model.addAttribute("page", page);
		return "modules/biznodeplan/bizNodePlanList";
	}

	@RequiresPermissions("biznodeplan:bizNodePlan:view")
	@RequestMapping(value = "form")
	public String form(BizNodePlan bizNodePlan, Model model) {
		model.addAttribute("bizNodePlan", bizNodePlan);
		return "modules/biznodeplan/bizNodePlanForm";
	}

	@RequiresPermissions("biznodeplan:bizNodePlan:edit")
	@RequestMapping(value = "save")
	public String save(BizNodePlan bizNodePlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizNodePlan)){
			return form(bizNodePlan, model);
		}
		bizNodePlanService.save(bizNodePlan);
		addMessage(redirectAttributes, "保存llp成功");
		return "redirect:"+Global.getAdminPath()+"/biznodeplan/bizNodePlan/?repage";
	}
	
	@RequiresPermissions("biznodeplan:bizNodePlan:edit")
	@RequestMapping(value = "delete")
	public String delete(BizNodePlan bizNodePlan, RedirectAttributes redirectAttributes) {
		bizNodePlanService.delete(bizNodePlan);
		addMessage(redirectAttributes, "删除llp成功");
		return "redirect:"+Global.getAdminPath()+"/biznodeplan/bizNodePlan/?repage";
	}
}