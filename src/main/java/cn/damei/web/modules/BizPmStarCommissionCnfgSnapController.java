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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.service.modules.BizPmStarCommissionCnfgSnapService;

/**
 * 项目经理星级提成快照Controller
 * @author 汪文文
 * @version 2016-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/starcommissionsnap/bizPmStarCommissionCnfgSnap")
public class BizPmStarCommissionCnfgSnapController extends BaseController {

	@Autowired
	private BizPmStarCommissionCnfgSnapService bizPmStarCommissionCnfgSnapService;
	
	@ModelAttribute
	public BizPmStarCommissionCnfgSnap get(@RequestParam(required=false) Integer id) {
		BizPmStarCommissionCnfgSnap entity = null;
		if (id != null){
			entity = bizPmStarCommissionCnfgSnapService.get(id);
		}
		if (entity == null){
			entity = new BizPmStarCommissionCnfgSnap();
		}
		return entity;
	}
	
	@RequiresPermissions("starcommissionsnap:bizPmStarCommissionCnfgSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmStarCommissionCnfgSnap> page = bizPmStarCommissionCnfgSnapService.findPage(new Page<BizPmStarCommissionCnfgSnap>(request, response), bizPmStarCommissionCnfgSnap); 
		model.addAttribute("page", page);
		return "modules/pmstarcommissionsnap/bizPmStarCommissionCnfgSnapList";
	}

	@RequiresPermissions("starcommissionsnap:bizPmStarCommissionCnfgSnap:view")
	@RequestMapping(value = "form")
	public String form(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap, Model model) {
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		return "modules/pmstarcommissionsnap/bizPmStarCommissionCnfgSnapForm";
	}

	@RequiresPermissions("starcommissionsnap:bizPmStarCommissionCnfgSnap:edit")
	@RequestMapping(value = "save")
	public String save(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmStarCommissionCnfgSnap)){
			return form(bizPmStarCommissionCnfgSnap, model);
		}
		bizPmStarCommissionCnfgSnapService.save(bizPmStarCommissionCnfgSnap);
		addMessage(redirectAttributes, "保存项目经理星级提成快照成功");
		return "redirect:"+Global.getAdminPath()+"/starcommissionsnap/bizPmStarCommissionCnfgSnap/?repage";
	}
	
	@RequiresPermissions("starcommissionsnap:bizPmStarCommissionCnfgSnap:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap, RedirectAttributes redirectAttributes) {
		bizPmStarCommissionCnfgSnapService.delete(bizPmStarCommissionCnfgSnap);
		addMessage(redirectAttributes, "删除项目经理星级提成快照成功");
		return "redirect:"+Global.getAdminPath()+"/starcommissionsnap/bizPmStarCommissionCnfgSnap/?repage";
	}

}