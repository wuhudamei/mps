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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplat;
import cn.damei.service.modules.BizTaskPackageWorkPlanTemplatService;

/**
 * 任务包派工计划模板Controller
 * 
 * @author chy
 * @version 2016-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/taskpackage/bizTaskPackageWorkPlanTemplat")
public class BizTaskPackageWorkPlanTemplatController extends BaseController {

	@Autowired
	private BizTaskPackageWorkPlanTemplatService bizTaskPackageWorkPlanTemplatService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizTaskPackageWorkPlanTemplat get(@RequestParam(required = false) String id) {
		BizTaskPackageWorkPlanTemplat entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizTaskPackageWorkPlanTemplatService.get(id);
		}
		if (entity == null) {
			entity = new BizTaskPackageWorkPlanTemplat();
		}
		return entity;
	}

	@RequiresPermissions("taskpackage:bizTaskPackageWorkPlanTemplat:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 过滤门店
		if (StringUtils.isBlank(bizTaskPackageWorkPlanTemplat.getStoreId())) {
			bizTaskPackageWorkPlanTemplat.setStoreId(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		User user = UserUtils.getUser();

		// 过滤工程模式
		if (StringUtils.isBlank(bizTaskPackageWorkPlanTemplat.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageWorkPlanTemplat.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageWorkPlanTemplat.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizTaskPackageWorkPlanTemplat> page = bizTaskPackageWorkPlanTemplatService
				.findPage(new Page<BizTaskPackageWorkPlanTemplat>(request, response), bizTaskPackageWorkPlanTemplat);
		model.addAttribute("page", page);
		model.addAttribute("storeId", user.getOffice().getId());
		model.addAttribute("storeName", user.getOffice().getName());
		return "modules/taskpackage/bizTaskPackageWorkPlanTemplatList";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageWorkPlanTemplat:view")
	@RequestMapping(value = "form")
	public String form(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat, Model model) {
		if (StringUtils.isBlank(bizTaskPackageWorkPlanTemplat.getStoreId())) {
			bizTaskPackageWorkPlanTemplat.setStoreId(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		User user = UserUtils.getUser();

		// 过滤工程模式
		if (StringUtils.isBlank(bizTaskPackageWorkPlanTemplat.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageWorkPlanTemplat.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageWorkPlanTemplat.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("storeId", user.getOffice().getId());
		model.addAttribute("storeName", user.getOffice().getName());
		model.addAttribute("bizTaskPackageWorkPlanTemplat", bizTaskPackageWorkPlanTemplat);
		return "modules/taskpackage/bizTaskPackageWorkPlanTemplatForm";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageWorkPlanTemplat:edit")
	@RequestMapping(value = "save")
	public String save(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTaskPackageWorkPlanTemplat)) {
			return form(bizTaskPackageWorkPlanTemplat, model);
		}
		bizTaskPackageWorkPlanTemplatService.save(bizTaskPackageWorkPlanTemplat);
		addMessage(redirectAttributes, "保存任务包派工计划模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageWorkPlanTemplat/?repage";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageWorkPlanTemplat:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat,
			RedirectAttributes redirectAttributes) {
		bizTaskPackageWorkPlanTemplatService.delete(bizTaskPackageWorkPlanTemplat);
		addMessage(redirectAttributes, "删除任务包派工计划模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageWorkPlanTemplat/?repage";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageWorkPlanTemplat:edit")
	@RequestMapping(value = "enable")
	public String enable(BizTaskPackageWorkPlanTemplat bizTaskPackageWorkPlanTemplat,
			RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizTaskPackageWorkPlanTemplat.getStatus());
		bizTaskPackageWorkPlanTemplat.setStatus(status + "");
		bizTaskPackageWorkPlanTemplatService.update(bizTaskPackageWorkPlanTemplat);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status + "", "biz_enable_status", "") + "任务包模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageWorkPlanTemplat/?repage";
	}

}