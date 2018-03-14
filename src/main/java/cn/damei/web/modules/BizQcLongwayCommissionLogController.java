
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
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.service.modules.BizQcLongwayCommissionLogService;


@Controller
@RequestMapping(value = "${adminPath}/bizqclongwaycommissionlog/bizQcLongwayCommissionLog")
public class BizQcLongwayCommissionLogController extends BaseController {

	@Autowired
	private BizQcLongwayCommissionLogService bizQcLongwayCommissionLogService;
	
	@ModelAttribute
	public BizQcLongwayCommissionLog get(@RequestParam(required=false) Integer id) {
		BizQcLongwayCommissionLog entity = null;
		if (id != null){
			entity = bizQcLongwayCommissionLogService.get(id);
		}
		if (entity == null){
			entity = new BizQcLongwayCommissionLog();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqclongwaycommissionlog:bizQcLongwayCommissionLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcLongwayCommissionLog bizQcLongwayCommissionLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizQcLongwayCommissionLog> page = bizQcLongwayCommissionLogService.findPage(new Page<BizQcLongwayCommissionLog>(request, response), bizQcLongwayCommissionLog); 
		model.addAttribute("page", page);
		return "modules/bizqclongwaycommissionlog/bizQcLongwayCommissionLogList";
	}

	@RequiresPermissions("bizqclongwaycommissionlog:bizQcLongwayCommissionLog:view")
	@RequestMapping(value = "form")
	public String form(BizQcLongwayCommissionLog bizQcLongwayCommissionLog, Model model) {
		model.addAttribute("bizQcLongwayCommissionLog", bizQcLongwayCommissionLog);
		return "modules/bizqclongwaycommissionlog/bizQcLongwayCommissionLogForm";
	}

	@RequiresPermissions("bizqclongwaycommissionlog:bizQcLongwayCommissionLog:edit")
	@RequestMapping(value = "save")
	public String save(BizQcLongwayCommissionLog bizQcLongwayCommissionLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcLongwayCommissionLog)){
			return form(bizQcLongwayCommissionLog, model);
		}
		bizQcLongwayCommissionLogService.save(bizQcLongwayCommissionLog);
		addMessage(redirectAttributes, "保存质检员远程费记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizqclongwaycommissionlog/bizQcLongwayCommissionLog/?repage";
	}
	
	@RequiresPermissions("bizqclongwaycommissionlog:bizQcLongwayCommissionLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcLongwayCommissionLog bizQcLongwayCommissionLog, RedirectAttributes redirectAttributes) {
		bizQcLongwayCommissionLogService.delete(bizQcLongwayCommissionLog);
		addMessage(redirectAttributes, "删除质检员远程费记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizqclongwaycommissionlog/bizQcLongwayCommissionLog/?repage";
	}

}