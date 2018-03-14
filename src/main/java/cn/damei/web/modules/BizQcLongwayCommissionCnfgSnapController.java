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
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.service.modules.BizQcLongwayCommissionCnfgSnapService;

/**
 * 质检员远程费快照Controller
 * @author 汪文文
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap")
public class BizQcLongwayCommissionCnfgSnapController extends BaseController {

	@Autowired
	private BizQcLongwayCommissionCnfgSnapService bizQcLongwayCommissionCnfgSnapService;
	
	@ModelAttribute
	public BizQcLongwayCommissionCnfgSnap get(@RequestParam(required=false) Integer id) {
		BizQcLongwayCommissionCnfgSnap entity = null;
		if (id != null ){
			entity = bizQcLongwayCommissionCnfgSnapService.get(id);
		}
		if (entity == null){
			entity = new BizQcLongwayCommissionCnfgSnap();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizQcLongwayCommissionCnfgSnap> page = bizQcLongwayCommissionCnfgSnapService.findPage(new Page<BizQcLongwayCommissionCnfgSnap>(request, response), bizQcLongwayCommissionCnfgSnap); 
		model.addAttribute("page", page);
		return "modules/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnapList";
	}

	@RequiresPermissions("bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:view")
	@RequestMapping(value = "form")
	public String form(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap, Model model) {
		model.addAttribute("bizQcLongwayCommissionCnfgSnap", bizQcLongwayCommissionCnfgSnap);
		return "modules/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnapForm";
	}

	@RequiresPermissions("bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:edit")
	@RequestMapping(value = "save")
	public String save(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcLongwayCommissionCnfgSnap)){
			return form(bizQcLongwayCommissionCnfgSnap, model);
		}
		bizQcLongwayCommissionCnfgSnapService.save(bizQcLongwayCommissionCnfgSnap);
		addMessage(redirectAttributes, "保存质检员远程费快照成功");
		return "redirect:"+Global.getAdminPath()+"/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap/?repage";
	}
	
	@RequiresPermissions("bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap, RedirectAttributes redirectAttributes) {
		bizQcLongwayCommissionCnfgSnapService.delete(bizQcLongwayCommissionCnfgSnap);
		addMessage(redirectAttributes, "删除质检员远程费快照成功");
		return "redirect:"+Global.getAdminPath()+"/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap/?repage";
	}

}