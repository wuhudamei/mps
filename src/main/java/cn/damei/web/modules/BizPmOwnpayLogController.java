
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
import cn.damei.entity.modules.BizPmOwnpayLog;
import cn.damei.service.modules.BizPmOwnpayLogService;


@Controller
@RequestMapping(value = "${adminPath}/pmownpaylog/bizPmOwnpayLog")
public class BizPmOwnpayLogController extends BaseController {

	@Autowired
	private BizPmOwnpayLogService bizPmOwnpayLogService;
	
	@ModelAttribute
	public BizPmOwnpayLog get(@RequestParam(required=false) Integer id) {
		BizPmOwnpayLog entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPmOwnpayLogService.get(id);
		}
		if (entity == null){
			entity = new BizPmOwnpayLog();
		}
		return entity;
	}
	
	@RequiresPermissions("pmownpaylog:bizPmOwnpayLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmOwnpayLog bizPmOwnpayLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmOwnpayLog> page = bizPmOwnpayLogService.findPage(new Page<BizPmOwnpayLog>(request, response), bizPmOwnpayLog); 
		model.addAttribute("page", page);
		return "modules/pmownpaylog/bizPmOwnpayLogList";
	}

	@RequiresPermissions("pmownpaylog:bizPmOwnpayLog:view")
	@RequestMapping(value = "form")
	public String form(BizPmOwnpayLog bizPmOwnpayLog, Model model) {
		model.addAttribute("bizPmOwnpayLog", bizPmOwnpayLog);
		return "modules/pmownpaylog/bizPmOwnpayLogForm";
	}

	@RequiresPermissions("pmownpaylog:bizPmOwnpayLog:edit")
	@RequestMapping(value = "save")
	public String save(BizPmOwnpayLog bizPmOwnpayLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmOwnpayLog)){
			return form(bizPmOwnpayLog, model);
		}
		bizPmOwnpayLogService.save(bizPmOwnpayLog);
		addMessage(redirectAttributes, "保存自主支配日志表成功");
		return "redirect:"+Global.getAdminPath()+"/pmownpaylog/bizPmOwnpayLog/?repage";
	}
	
	@RequiresPermissions("pmownpaylog:bizPmOwnpayLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmOwnpayLog bizPmOwnpayLog, RedirectAttributes redirectAttributes) {
		bizPmOwnpayLogService.delete(bizPmOwnpayLog);
		addMessage(redirectAttributes, "删除自主支配日志表成功");
		return "redirect:"+Global.getAdminPath()+"/pmownpaylog/bizPmOwnpayLog/?repage";
	}

}