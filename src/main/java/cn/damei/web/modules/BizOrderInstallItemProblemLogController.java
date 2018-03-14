
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
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
import cn.damei.service.modules.BizOrderInstallItemProblemLogService;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog")
public class BizOrderInstallItemProblemLogController extends BaseController {

	@Autowired
	private BizOrderInstallItemProblemLogService bizOrderInstallItemProblemLogService;
	
	@ModelAttribute
	public BizOrderInstallItemProblemLog get(@RequestParam(required=false) Integer id) {
		BizOrderInstallItemProblemLog entity = null;
		if (id != null){
			entity = bizOrderInstallItemProblemLogService.get(id);
		}
		if (entity == null){
			entity = new BizOrderInstallItemProblemLog();
		}
		return entity;
	}
	
	@RequiresPermissions("bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderInstallItemProblemLog> page = bizOrderInstallItemProblemLogService.findPage(new Page<BizOrderInstallItemProblemLog>(request, response), bizOrderInstallItemProblemLog); 
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLogList";
	}

	@RequiresPermissions("bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:view")
	@RequestMapping(value = "form")
	public String form(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog, Model model) {
		model.addAttribute("bizOrderInstallItemProblemLog", bizOrderInstallItemProblemLog);
		return "modules/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLogForm";
	}

	@RequiresPermissions("bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderInstallItemProblemLog)){
			return form(bizOrderInstallItemProblemLog, model);
		}
		bizOrderInstallItemProblemLogService.save(bizOrderInstallItemProblemLog);
		addMessage(redirectAttributes, "保存订单安装项问题日志成功");
		return "redirect:"+Global.getAdminPath()+"/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog/?repage";
	}
	
	@RequiresPermissions("bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog, RedirectAttributes redirectAttributes) {
		bizOrderInstallItemProblemLogService.delete(bizOrderInstallItemProblemLog);
		addMessage(redirectAttributes, "删除订单安装项问题日志成功");
		return "redirect:"+Global.getAdminPath()+"/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog/?repage";
	}

}