
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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;


@Controller
@RequestMapping(value = "${adminPath}/bizbusinessstatuslog/bizBusinessStatusLog")
public class BizBusinessStatusLogController extends BaseController {

	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	
	@ModelAttribute
	public BizBusinessStatusLog get(@RequestParam(required=false) Integer id) {
		BizBusinessStatusLog entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizBusinessStatusLogService.get(id);
		}
		if (entity == null){
			entity = new BizBusinessStatusLog();
		}
		return entity;
	}
	
	@RequiresPermissions("bizbusinessstatuslog:bizBusinessStatusLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizBusinessStatusLog bizBusinessStatusLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizBusinessStatusLog> page = bizBusinessStatusLogService.findPage(new Page<BizBusinessStatusLog>(request, response), bizBusinessStatusLog); 
		model.addAttribute("page", page);
		return "modules/bizbusinessstatuslog/bizBusinessStatusLogList";
	}

	@RequiresPermissions("bizbusinessstatuslog:bizBusinessStatusLog:view")
	@RequestMapping(value = "form")
	public String form(BizBusinessStatusLog bizBusinessStatusLog, Model model) {
		model.addAttribute("bizBusinessStatusLog", bizBusinessStatusLog);
		return "modules/bizbusinessstatuslog/bizBusinessStatusLogForm";
	}

	@RequiresPermissions("bizbusinessstatuslog:bizBusinessStatusLog:edit")
	@RequestMapping(value = "save")
	public String save(BizBusinessStatusLog bizBusinessStatusLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizBusinessStatusLog)){
			return form(bizBusinessStatusLog, model);
		}
		bizBusinessStatusLogService.save(bizBusinessStatusLog);
		addMessage(redirectAttributes, "保存业务状态记录表成功");
		return "redirect:"+Global.getAdminPath()+"/bizbusinessstatuslog/bizBusinessStatusLog/?repage";
	}
	
	@RequiresPermissions("bizbusinessstatuslog:bizBusinessStatusLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizBusinessStatusLog bizBusinessStatusLog, RedirectAttributes redirectAttributes) {
		bizBusinessStatusLogService.delete(bizBusinessStatusLog);
		addMessage(redirectAttributes, "删除业务状态记录表成功");
		return "redirect:"+Global.getAdminPath()+"/bizbusinessstatuslog/bizBusinessStatusLog/?repage";
	}

}