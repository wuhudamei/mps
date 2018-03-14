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
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.service.modules.BizQcStarCommissionCnfgSnapService;

/**
 * 质检员星级提成快照Controller
 * @author 汪文文
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap")
public class BizQcStarCommissionCnfgSnapController extends BaseController {

	@Autowired
	private BizQcStarCommissionCnfgSnapService bizQcStarCommissionCnfgSnapService;
	
	@ModelAttribute
	public BizQcStarCommissionCnfgSnap get(@RequestParam(required=false) Integer id) {
		BizQcStarCommissionCnfgSnap entity = null;
		if (id != null){
			entity = bizQcStarCommissionCnfgSnapService.get(id);
		}
		if (entity == null){
			entity = new BizQcStarCommissionCnfgSnap();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizQcStarCommissionCnfgSnap> page = bizQcStarCommissionCnfgSnapService.findPage(new Page<BizQcStarCommissionCnfgSnap>(request, response), bizQcStarCommissionCnfgSnap); 
		model.addAttribute("page", page);
		return "modules/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnapList";
	}

	@RequiresPermissions("bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:view")
	@RequestMapping(value = "form")
	public String form(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap, Model model) {
		model.addAttribute("bizQcStarCommissionCnfgSnap", bizQcStarCommissionCnfgSnap);
		return "modules/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnapForm";
	}

	@RequiresPermissions("bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:edit")
	@RequestMapping(value = "save")
	public String save(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcStarCommissionCnfgSnap)){
			return form(bizQcStarCommissionCnfgSnap, model);
		}
		bizQcStarCommissionCnfgSnapService.save(bizQcStarCommissionCnfgSnap);
		addMessage(redirectAttributes, "保存质检员星级提成快照成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/?repage";
	}
	
	@RequiresPermissions("bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap, RedirectAttributes redirectAttributes) {
		bizQcStarCommissionCnfgSnapService.delete(bizQcStarCommissionCnfgSnap);
		addMessage(redirectAttributes, "删除质检员星级提成快照成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/?repage";
	}

}