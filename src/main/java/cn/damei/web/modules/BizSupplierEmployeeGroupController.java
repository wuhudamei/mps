/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.BizSupplierEmployeeGroup;
import cn.damei.service.modules.BizSupplierEmployeeGroupService;

/**
 * 主材安装供应商和工人组Controller
 * 
 * @author ztw
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/supplieremgroup/bizSupplierEmployeeGroup")
public class BizSupplierEmployeeGroupController extends BaseController {

	@Autowired
	private BizSupplierEmployeeGroupService bizSupplierEmployeeGroupService;

	@ModelAttribute
	public BizSupplierEmployeeGroup get(@RequestParam(required = false) String id) {
		BizSupplierEmployeeGroup entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizSupplierEmployeeGroupService.get(id);
		}
		if (entity == null) {
			entity = new BizSupplierEmployeeGroup();
		}
		return entity;
	}

	@RequiresPermissions("supplieremgroup:bizSupplierEmployeeGroup:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSupplierEmployeeGroup> page = bizSupplierEmployeeGroupService.findPage(new Page<BizSupplierEmployeeGroup>(request, response), bizSupplierEmployeeGroup);
		model.addAttribute("page", page);
		return "modules/supplieremgroup/bizSupplierEmployeeGroupList";
	}

	@RequiresPermissions("supplieremgroup:bizSupplierEmployeeGroup:view")
	@RequestMapping(value = "form")
	public String form(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, Model model) {
		model.addAttribute("bizSupplierEmployeeGroup", bizSupplierEmployeeGroup);
		return "modules/supplieremgroup/bizSupplierEmployeeGroupForm";
	}

	/**
	 * 修改回显
	 * 
	 * @Title: formUpdate
	 * @Description: TODO
	 * @param @param bizSupplierEmployeeGroup
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "formUpdate")
	public String formUpdate(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, Model model) {
		List<BizSupplierEmployeeGroup> bizSupplierEmployeeGroupList = bizSupplierEmployeeGroupService.findBizSupplierEmployeeGroup(bizSupplierEmployeeGroup);

		model.addAttribute("bizSupplierEmployeeGroup", bizSupplierEmployeeGroupList.get(0));
		bizSupplierEmployeeGroup.setSupplierId(bizSupplierEmployeeGroup.getId());
		List<BizEmployeegroupVO> bizEmployeegroupVOList = bizSupplierEmployeeGroupService.queryEmployeeGroupList(bizSupplierEmployeeGroup);

		model.addAttribute("bizEmgrouprelationList", bizEmployeegroupVOList);

		return "modules/supplieremgroup/bizSupplierEmployeeGroupUpdate";
	}

	/**
	 * 根据供应商id查询 供应商的ID集合
	 * 
	 * @Title: queryEmployeeGroupList
	 * @Description: TODO
	 * @param @param bizSupplierEmployeeGroup
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "queryEmployeeGroupList")
	public String queryEmployeeGroupList(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, Model model) {
		List<BizEmployeegroupVO> bizEmployeegroupVOList = bizSupplierEmployeeGroupService.queryEmployeeGroupList(bizSupplierEmployeeGroup);

		model.addAttribute("bizEmgrouprelationList", bizEmployeegroupVOList);
		return "modules/supplieremgroup/empGroupList";
	}

	@RequiresPermissions("supplieremgroup:bizSupplierEmployeeGroup:edit")
	@RequestMapping(value = "save")
	public String save(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSupplierEmployeeGroup)) {
			return form(bizSupplierEmployeeGroup, model);
		}
		bizSupplierEmployeeGroupService.insert(bizSupplierEmployeeGroup);
		addMessage(redirectAttributes, "保存主材安装供应商和工人组成功");
		return "redirect:" + Global.getAdminPath() + "/supplieremgroup/bizSupplierEmployeeGroup/?repage";
	}

	@RequestMapping(value = "saveUpdate")
	public String saveUpdate(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSupplierEmployeeGroup)) {
			return form(bizSupplierEmployeeGroup, model);
		}

		bizSupplierEmployeeGroup.setSupplierName(bizSupplierEmployeeGroup.getId());
		bizSupplierEmployeeGroup.setDelFlag("1");
		bizSupplierEmployeeGroupService.deleteSupplier(bizSupplierEmployeeGroup); // delfle置为1的方法

		bizSupplierEmployeeGroup.setDelFlag("0");
		bizSupplierEmployeeGroupService.insert(bizSupplierEmployeeGroup);
		addMessage(redirectAttributes, "保存主材安装供应商和工人组成功");
		return "redirect:" + Global.getAdminPath() + "/supplieremgroup/bizSupplierEmployeeGroup/?repage";
	}

	@RequiresPermissions("supplieremgroup:bizSupplierEmployeeGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSupplierEmployeeGroup bizSupplierEmployeeGroup, RedirectAttributes redirectAttributes) {
		bizSupplierEmployeeGroupService.delete(bizSupplierEmployeeGroup);
		addMessage(redirectAttributes, "删除主材安装供应商和工人组成功");
		return "redirect:" + Global.getAdminPath() + "/supplieremgroup/bizSupplierEmployeeGroup/?repage";
	}

}