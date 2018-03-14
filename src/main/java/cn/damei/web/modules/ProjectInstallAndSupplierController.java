
package cn.damei.web.modules;

import java.util.List;

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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ProjectInstallAndSupplier;
import cn.damei.service.modules.ProjectInstallAndSupplierService;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.service.modules.BizProjectInstallItemService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.service.modules.BizSupplierService;


@Controller
@RequestMapping(value = "${adminPath}/installandsupplier/projectInstallAndSupplier")
public class ProjectInstallAndSupplierController extends BaseController {

	@Autowired
	private ProjectInstallAndSupplierService projectInstallAndSupplierService;
	@Autowired
	private BizProjectInstallItemService bizProjectInstallItemService;
	@Autowired
	private BizSupplierService bizSupplierService;

	@ModelAttribute
	public ProjectInstallAndSupplier get(@RequestParam(required = false) String id) {
		ProjectInstallAndSupplier entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = projectInstallAndSupplierService.get(id);
		}
		if (entity == null) {
			entity = new ProjectInstallAndSupplier();
		}
		return entity;
	}

	@RequiresPermissions("installandsupplier:projectInstallAndSupplier:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProjectInstallAndSupplier projectInstallAndSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/installandsupplier/projectInstallAndSupplierList";
	}

	@RequestMapping(value = "list1")
	public String list1(ProjectInstallAndSupplier projectInstallAndSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectInstallAndSupplier> page = projectInstallAndSupplierService.findPageListall(new Page<ProjectInstallAndSupplier>(request, response), projectInstallAndSupplier);

		model.addAttribute("page", page);
		BizSupplier entity = new BizSupplier();
		entity.setId(projectInstallAndSupplier.getInstallItemName1());
		BizSupplier bizSupplier = bizSupplierService.get(entity);

		model.addAttribute("bizSupplier", bizSupplier);
		return "modules/installandsupplier/projectInstallAndSupplierList";
	}

	@RequestMapping(value = "querySupplierListBack")
	public String querySupplierListBack(ProjectInstallAndSupplier projectInstallAndSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectInstallAndSupplier> page = projectInstallAndSupplierService.findPageListBack(new Page<ProjectInstallAndSupplier>(request, response), projectInstallAndSupplier);
		List<BizSupplier> bizSupplierList = page.getList().get(0).getBizSupplierList();
		model.addAttribute("page", page);
		model.addAttribute("bizSupplierList", bizSupplierList);
		return "modules/installandsupplier/supplierList";
	}


	@RequestMapping(value = "querySupplierList")
	public String querySupplierList(ProjectInstallAndSupplier projectInstallAndSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizSupplier> bizSupplierList = projectInstallAndSupplierService.findPageList(projectInstallAndSupplier);
		model.addAttribute("bizSupplierList", bizSupplierList);
		return "modules/installandsupplier/supplierList";
	}

	@RequiresPermissions("installandsupplier:projectInstallAndSupplier:view")
	@RequestMapping(value = "form")
	public String form(ProjectInstallAndSupplier projectInstallAndSupplier, Model model) {
		model.addAttribute("projectInstallAndSupplier", projectInstallAndSupplier);
		return "modules/installandsupplier/projectInstallAndSupplierForm";
	}

	@RequestMapping(value = "formUpdata")
	public String formUpdata(ProjectInstallAndSupplier projectInstallAndSupplier, Model model) {
		List<ProjectInstallAndSupplier> projectInstallAndSupplierList = projectInstallAndSupplierService.formUpdata(projectInstallAndSupplier);
		ProjectInstallAndSupplier projectInstallAndSupplier2 = projectInstallAndSupplierList.get(0);
		List<BizSupplier> findPageList = projectInstallAndSupplierService.findPageList(projectInstallAndSupplier);
		model.addAttribute("projectInstallAndSupplier", projectInstallAndSupplier2);
		model.addAttribute("supplierList", findPageList);

		return "modules/installandsupplier/projectInstallAndSupplierUpdate";
	}


	@RequiresPermissions("installandsupplier:projectInstallAndSupplier:edit")
	@RequestMapping(value = "save")
	public String save(ProjectInstallAndSupplier projectInstallAndSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectInstallAndSupplier)) {
			return form(projectInstallAndSupplier, model);
		}

		projectInstallAndSupplierService.insert(projectInstallAndSupplier);

		addMessage(redirectAttributes, "保存主材安装供应商设置成功");
		return "redirect:" + Global.getAdminPath() + "/installandsupplier/projectInstallAndSupplier/?repage";
	}

	@RequestMapping(value = "saveUpdate")
	public String saveUpdate(ProjectInstallAndSupplier projectInstallAndSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectInstallAndSupplier)) {
			return form(projectInstallAndSupplier, model);
		}

		projectInstallAndSupplier.setProjectInstallItemId(projectInstallAndSupplier.getId());
		projectInstallAndSupplier.setDelFlag("1");
		projectInstallAndSupplierService.deleteSupplier(projectInstallAndSupplier);
		projectInstallAndSupplier.setDelFlag("0");
		projectInstallAndSupplierService.insert(projectInstallAndSupplier);

		addMessage(redirectAttributes, "保存主材安装供应商设置成功");
		return "redirect:" + Global.getAdminPath() + "/installandsupplier/projectInstallAndSupplier/?repage";
	}

	@RequiresPermissions("installandsupplier:projectInstallAndSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectInstallAndSupplier projectInstallAndSupplier, RedirectAttributes redirectAttributes) {
		BizProjectInstallItem bizProjectInstallItem = new BizProjectInstallItem();
		bizProjectInstallItem.setId(projectInstallAndSupplier.getId());
		bizProjectInstallItem.setIsOn(projectInstallAndSupplier.getIsOn());
		bizProjectInstallItemService.isON(bizProjectInstallItem);

		addMessage(redirectAttributes, "主材安装供应商设置成功");
		return "redirect:" + Global.getAdminPath() + "/installandsupplier/projectInstallAndSupplier/list?repage";
	}

}