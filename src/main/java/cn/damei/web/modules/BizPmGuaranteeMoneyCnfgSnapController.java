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
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;

/**
 * 项目经理保证金快照Controller
 * @author 汪文文
 * @version 2016-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap")
public class BizPmGuaranteeMoneyCnfgSnapController extends BaseController {

	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	
	@ModelAttribute
	public BizPmGuaranteeMoneyCnfgSnap get(@RequestParam(required=false) Integer id) {
		BizPmGuaranteeMoneyCnfgSnap entity = null;
		if (id != null){
			entity = bizPmGuaranteeMoneyCnfgSnapService.get(id);
		}
		if (entity == null){
			entity = new BizPmGuaranteeMoneyCnfgSnap();
		}
		return entity;
	}
	
	@RequiresPermissions("guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmGuaranteeMoneyCnfgSnap> page = bizPmGuaranteeMoneyCnfgSnapService.findPage(new Page<BizPmGuaranteeMoneyCnfgSnap>(request, response), bizPmGuaranteeMoneyCnfgSnap); 
		model.addAttribute("page", page);
		return "modules/pmguaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnapList";
	}

	@RequiresPermissions("guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:view")
	@RequestMapping(value = "form")
	public String form(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap, Model model) {
		model.addAttribute("bizPmGuaranteeMoneyCnfgSnap", bizPmGuaranteeMoneyCnfgSnap);
		return "modules/pmguaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnapForm";
	}

	@RequiresPermissions("guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:edit")
	@RequestMapping(value = "save")
	public String save(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmGuaranteeMoneyCnfgSnap)){
			return form(bizPmGuaranteeMoneyCnfgSnap, model);
		}
		bizPmGuaranteeMoneyCnfgSnapService.save(bizPmGuaranteeMoneyCnfgSnap);
		addMessage(redirectAttributes, "保存项目经理保证金快照成功");
		return "redirect:"+Global.getAdminPath()+"/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap/?repage";
	}
	
	@RequiresPermissions("guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmGuaranteeMoneyCnfgSnap bizPmGuaranteeMoneyCnfgSnap, RedirectAttributes redirectAttributes) {
		bizPmGuaranteeMoneyCnfgSnapService.delete(bizPmGuaranteeMoneyCnfgSnap);
		addMessage(redirectAttributes, "删除项目经理保证金快照成功");
		return "redirect:"+Global.getAdminPath()+"/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap/?repage";
	}

}