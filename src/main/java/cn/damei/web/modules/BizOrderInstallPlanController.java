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

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEnginInstall;
import cn.damei.entity.modules.BizOrderInstallPlan;
import cn.damei.service.modules.BizEnginInstallService;
import cn.damei.service.modules.BizOrderInstallPlanService;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallplan/bizOrderInstallPlan")
public class BizOrderInstallPlanController extends BaseController {

	@Autowired
	private BizOrderInstallPlanService bizOrderInstallPlanService;
	
	@Autowired
	private BizEnginInstallService bizEnginInstallService;

	@ModelAttribute
	public BizOrderInstallPlan get(@RequestParam(required = false) Integer id) {
		BizOrderInstallPlan entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderInstallPlanService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderInstallPlan();
		}
		return entity;
	}

	@RequiresPermissions("bizorderinstallplan:bizOrderInstallPlan:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		return "modules/bizOrderDisclose/discloseList";
	}
	
	@RequiresPermissions("bizorderinstallplan:bizOrderInstallPlan:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<BizOrderInstallPlan> page = bizOrderInstallPlanService.findPage(new Page<BizOrderInstallPlan>(request, response),
				bizOrderInstallPlan);
		
		model.addAttribute("page", page);
		return "modules/bizOrderDisclose/discloseList";
	}
	

	@RequestMapping(value = { "selectByInstallID", "" })
	public String selectByInstallID(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request, HttpServletResponse response,
			Model model,String id,String orderID) {
		logger.info("install主键编号："+id+"\t 订单编号："+orderID);
		BizOrderInstallPlan plan = null;
		BizEnginInstall order = null;
		if(!id.equals("")&& !orderID.equals("")){
			order = bizEnginInstallService.get(Integer.valueOf(orderID));
			plan = bizOrderInstallPlanService.selectByInstallID(Integer.valueOf(id));
		}
		model.addAttribute("order", order);
		model.addAttribute("InstallPlan", plan);
		return "modules/bizEnginInstall/installDetail";
	}
	

	@RequestMapping(value = { "updateByStatus", "" })
	public String updateByStatus(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request, HttpServletResponse response,
			Model model,String id,String orderID,String supplierConfirmRemarks) {
		logger.info("install主键编号："+id+"\t 订单编号："+orderID);
		bizOrderInstallPlanService.updateByIdAndStatus(Integer.valueOf(id),"3",null,supplierConfirmRemarks);
		return "modules/bizEnginInstall/preBizEnginList";
	}
}
