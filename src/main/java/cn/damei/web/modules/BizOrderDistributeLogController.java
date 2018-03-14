
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderDistributeLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizOrderDistributeLogService;


@Controller
@RequestMapping(value = "${adminPath}/bizorderdistributelog/bizOrderDistributeLog")
public class BizOrderDistributeLogController extends BaseController {

	@Autowired
	private BizOrderDistributeLogService bizOrderDistributeLogService;
	
	@ModelAttribute
	public BizOrderDistributeLog get(@RequestParam(required=false) Integer id) {
		BizOrderDistributeLog entity = null;
		if (id != null){
			entity = bizOrderDistributeLogService.get(id);
		}
		if (entity == null){
			entity = new BizOrderDistributeLog();
		}
		return entity;
	}
	
	@RequiresPermissions("bizorderdistributelog:bizOrderDistributeLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderDistributeLog bizOrderDistributeLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderDistributeLog> page = bizOrderDistributeLogService.findPage(new Page<BizOrderDistributeLog>(request, response), bizOrderDistributeLog); 
		model.addAttribute("page", page);
		return "modules/bizorderdistributelog/bizOrderDistributeLogList";
	}

	@RequiresPermissions("bizorderdistributelog:bizOrderDistributeLog:view")
	@RequestMapping(value = "form")
	public String form(BizOrderDistributeLog bizOrderDistributeLog, Model model) {
		model.addAttribute("bizOrderDistributeLog", bizOrderDistributeLog);
		return "modules/bizorderdistributelog/bizOrderDistributeLogForm";
	}

	@RequiresPermissions("bizorderdistributelog:bizOrderDistributeLog:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderDistributeLog bizOrderDistributeLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderDistributeLog)){
			return form(bizOrderDistributeLog, model);
		}
		bizOrderDistributeLogService.save(bizOrderDistributeLog);
		addMessage(redirectAttributes, "保存订单分配记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizorderdistributelog/bizOrderDistributeLog/?repage";
	}
	
	@RequiresPermissions("bizorderdistributelog:bizOrderDistributeLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderDistributeLog bizOrderDistributeLog, RedirectAttributes redirectAttributes) {
		bizOrderDistributeLogService.delete(bizOrderDistributeLog);
		addMessage(redirectAttributes, "删除订单分配记录成功");
		return "redirect:"+Global.getAdminPath()+"/bizorderdistributelog/bizOrderDistributeLog/?repage";
	}
	
	@RequestMapping(value = "queryOrderPmDistributeLogByOrderId")
	public @ResponseBody List<BizOrderDistributeLog> queryOrderPmDistributeLogByOrderId(Integer orderId){
		return bizOrderDistributeLogService.queryOrderPmDistributeLogByOrderId(orderId);
	}

}