
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
import cn.damei.entity.modules.BizPmStarCommissionLog;
import cn.damei.service.modules.BizPmStarCommissionLogService;


@Controller
@RequestMapping(value = "${adminPath}/pmstarcommissionlog/bizPmStarCommissionLog")
public class BizPmStarCommissionLogController extends BaseController {

	@Autowired
	private BizPmStarCommissionLogService bizPmStarCommissionLogService;
	
	@ModelAttribute
	public BizPmStarCommissionLog get(@RequestParam(required=false) Integer id) {
		BizPmStarCommissionLog entity = null;
		if (id != null){
			entity = bizPmStarCommissionLogService.get(id);
		}
		if (entity == null){
			entity = new BizPmStarCommissionLog();
		}
		return entity;
	}
	
	@RequiresPermissions("pmstarcommissionlog:bizPmStarCommissionLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmStarCommissionLog bizPmStarCommissionLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmStarCommissionLog> page = bizPmStarCommissionLogService.findPage(new Page<BizPmStarCommissionLog>(request, response), bizPmStarCommissionLog); 
		model.addAttribute("page", page);
		return "modules/pmstarcommissionlog/bizPmStarCommissionLogList";
	}

	@RequiresPermissions("pmstarcommissionlog:bizPmStarCommissionLog:view")
	@RequestMapping(value = "form")
	public String form(BizPmStarCommissionLog bizPmStarCommissionLog, Model model) {
		model.addAttribute("bizPmStarCommissionLog", bizPmStarCommissionLog);
		return "modules/pmstarcommissionlog/bizPmStarCommissionLogForm";
	}

	@RequiresPermissions("pmstarcommissionlog:bizPmStarCommissionLog:edit")
	@RequestMapping(value = "save")
	public String save(BizPmStarCommissionLog bizPmStarCommissionLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmStarCommissionLog)){
			return form(bizPmStarCommissionLog, model);
		}
		bizPmStarCommissionLogService.save(bizPmStarCommissionLog);
		addMessage(redirectAttributes, "保存biz_pm_star_commission_log成功");
		return "redirect:"+Global.getAdminPath()+"/pmstarcommissionlog/bizPmStarCommissionLog/?repage";
	}
	
	@RequiresPermissions("pmstarcommissionlog:bizPmStarCommissionLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmStarCommissionLog bizPmStarCommissionLog, RedirectAttributes redirectAttributes) {
		bizPmStarCommissionLogService.delete(bizPmStarCommissionLog);
		addMessage(redirectAttributes, "删除biz_pm_star_commission_log成功");
		return "redirect:"+Global.getAdminPath()+"/pmstarcommissionlog/bizPmStarCommissionLog/?repage";
	}

}