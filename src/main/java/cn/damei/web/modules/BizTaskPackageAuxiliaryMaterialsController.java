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
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageAuxiliaryMaterials;
import cn.damei.service.modules.BizTaskPackageAuxiliaryMaterialsService;

/**
 * 任务包辅料对照表管理Controller
 * 
 * @author wangchao
 * @version 2016-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/taskpackage/bizTaskPackageAuxiliaryMaterials")
public class BizTaskPackageAuxiliaryMaterialsController extends BaseController {

	@Autowired
	private BizTaskPackageAuxiliaryMaterialsService bizTaskPackageAuxiliaryMaterialsService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizTaskPackageAuxiliaryMaterials get(@RequestParam(required = false) String id) {
		BizTaskPackageAuxiliaryMaterials entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizTaskPackageAuxiliaryMaterialsService.get(id);
		}
		if (entity == null) {
			entity = new BizTaskPackageAuxiliaryMaterials();
		}
		return entity;
	}

	@RequiresPermissions("taskpackage:bizTaskPackageAuxiliaryMaterials:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (StringUtils.isBlank(bizTaskPackageAuxiliaryMaterials.getStoreId())) {
			bizTaskPackageAuxiliaryMaterials.setStoreId(user.getStoreId());
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizTaskPackageAuxiliaryMaterials.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageAuxiliaryMaterials.setProjectMode(be.getProjectMode());
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
						bizTaskPackageAuxiliaryMaterials.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		Page<BizTaskPackageAuxiliaryMaterials> page = bizTaskPackageAuxiliaryMaterialsService.findPage(
				new Page<BizTaskPackageAuxiliaryMaterials>(request, response), bizTaskPackageAuxiliaryMaterials);
		model.addAttribute("page", page);
		model.addAttribute("bizTaskPackageAuxiliaryMaterials", bizTaskPackageAuxiliaryMaterials);
		return "modules/taskpackage/bizTaskPackageAuxiliaryMaterialsList";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageAuxiliaryMaterials:view")
	@RequestMapping(value = "form")
	public String form(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (StringUtils.isBlank(bizTaskPackageAuxiliaryMaterials.getStoreId())) {
			bizTaskPackageAuxiliaryMaterials.setStoreId(user.getStoreId());
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizTaskPackageAuxiliaryMaterials.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageAuxiliaryMaterials.setProjectMode(be.getProjectMode());
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
						bizTaskPackageAuxiliaryMaterials.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("bizTaskPackageAuxiliaryMaterials", bizTaskPackageAuxiliaryMaterials);
		return "modules/taskpackage/bizTaskPackageAuxiliaryMaterialsForm";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageAuxiliaryMaterials:edit")
	@RequestMapping(value = "save")
	public String save(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTaskPackageAuxiliaryMaterials)) {
			return form(bizTaskPackageAuxiliaryMaterials, model);
		}
		bizTaskPackageAuxiliaryMaterialsService.save(bizTaskPackageAuxiliaryMaterials);
		addMessage(redirectAttributes, "保存任务包辅料对照表成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageAuxiliaryMaterials/?repage";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageAuxiliaryMaterials:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials,
			RedirectAttributes redirectAttributes) {
		bizTaskPackageAuxiliaryMaterialsService.delete(bizTaskPackageAuxiliaryMaterials);
		addMessage(redirectAttributes, "删除任务包辅料对照表成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageAuxiliaryMaterials/?repage";
	}

}