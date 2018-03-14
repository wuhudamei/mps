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
import cn.damei.entity.modules.BizQcStarCommissionLog;
import cn.damei.service.modules.BizQcStarCommissionLogService;

/**
 * 质检员星级提成记录Controller
 * @author 汪文文
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/bizqcstarcommissionlog/bizQcStarCommissionLog")
public class BizQcStarCommissionLogController extends BaseController {

	@Autowired
	private BizQcStarCommissionLogService bizQcStarCommissionLogService;
	
	@ModelAttribute
	public BizQcStarCommissionLog get(@RequestParam(required=false) Integer id) {
		BizQcStarCommissionLog entity = null;
		if (id != null){
			entity = bizQcStarCommissionLogService.get(id);
		}
		if (entity == null){
			entity = new BizQcStarCommissionLog();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqcstarcommissionlog:bizQcStarCommissionLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcStarCommissionLog bizQcStarCommissionLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizQcStarCommissionLog> page = bizQcStarCommissionLogService.findPage(new Page<BizQcStarCommissionLog>(request, response), bizQcStarCommissionLog); 
		model.addAttribute("page", page);
		return "modules/bizqcstarcommissionlog/bizQcStarCommissionLogList";
	}

	@RequiresPermissions("bizqcstarcommissionlog:bizQcStarCommissionLog:view")
	@RequestMapping(value = "form")
	public String form(BizQcStarCommissionLog bizQcStarCommissionLog, Model model) {
		model.addAttribute("bizQcStarCommissionLog", bizQcStarCommissionLog);
		return "modules/bizqcstarcommissionlog/bizQcStarCommissionLogForm";
	}

	@RequiresPermissions("bizqcstarcommissionlog:bizQcStarCommissionLog:edit")
	@RequestMapping(value = "save")
	public String save(BizQcStarCommissionLog bizQcStarCommissionLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcStarCommissionLog)){
			return form(bizQcStarCommissionLog, model);
		}
		bizQcStarCommissionLogService.save(bizQcStarCommissionLog);
		addMessage(redirectAttributes, "保存质检员星级提成记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcstarcommissionlog/bizQcStarCommissionLog/?repage";
	}
	
	@RequiresPermissions("bizqcstarcommissionlog:bizQcStarCommissionLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcStarCommissionLog bizQcStarCommissionLog, RedirectAttributes redirectAttributes) {
		bizQcStarCommissionLogService.delete(bizQcStarCommissionLog);
		addMessage(redirectAttributes, "删除质检员星级提成记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcstarcommissionlog/bizQcStarCommissionLog/?repage";
	}

}