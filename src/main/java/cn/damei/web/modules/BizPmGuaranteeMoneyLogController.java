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
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.service.modules.BizPmGuaranteeMoneyLogService;

/**
 * 质保金日志Controller
 * @author 汪文文
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog")
public class BizPmGuaranteeMoneyLogController extends BaseController {

	@Autowired
	private BizPmGuaranteeMoneyLogService bizPmGuaranteeMoneyLogService;
	
	@ModelAttribute
	public BizPmGuaranteeMoneyLog get(@RequestParam(required=false) Integer id) {
		BizPmGuaranteeMoneyLog entity = null;
		if (id != null){
			entity = bizPmGuaranteeMoneyLogService.get(id);
		}
		if (entity == null){
			entity = new BizPmGuaranteeMoneyLog();
		}
		return entity;
	}
	
	@RequiresPermissions("pmguaranteemoneylog:bizPmGuaranteeMoneyLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmGuaranteeMoneyLog> page = bizPmGuaranteeMoneyLogService.findPage(new Page<BizPmGuaranteeMoneyLog>(request, response), bizPmGuaranteeMoneyLog); 
		model.addAttribute("page", page);
		return "modules/pmguaranteemoneylog/bizPmGuaranteeMoneyLogList";
	}

	@RequiresPermissions("pmguaranteemoneylog:bizPmGuaranteeMoneyLog:view")
	@RequestMapping(value = "form")
	public String form(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog, Model model) {
		model.addAttribute("bizPmGuaranteeMoneyLog", bizPmGuaranteeMoneyLog);
		return "modules/pmguaranteemoneylog/bizPmGuaranteeMoneyLogForm";
	}

	@RequiresPermissions("pmguaranteemoneylog:bizPmGuaranteeMoneyLog:edit")
	@RequestMapping(value = "save")
	public String save(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmGuaranteeMoneyLog)){
			return form(bizPmGuaranteeMoneyLog, model);
		}
		bizPmGuaranteeMoneyLogService.save(bizPmGuaranteeMoneyLog);
		addMessage(redirectAttributes, "保存质保金日志成功");
		return "redirect:"+Global.getAdminPath()+"/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/?repage";
	}
	
	@RequiresPermissions("pmguaranteemoneylog:bizPmGuaranteeMoneyLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmGuaranteeMoneyLog bizPmGuaranteeMoneyLog, RedirectAttributes redirectAttributes) {
		bizPmGuaranteeMoneyLogService.delete(bizPmGuaranteeMoneyLog);
		addMessage(redirectAttributes, "删除质保金日志成功");
		return "redirect:"+Global.getAdminPath()+"/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/?repage";
	}

}