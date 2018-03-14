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
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizArea;
import cn.damei.entity.modules.BizAreaService;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.ProjectOrderListService;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.entity.modules.Role;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 区域管理Controller
 * 
 * @author qhy
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/bizarea/bizArea")
public class BizAreaController extends BaseController {

	@Autowired
	private BizAreaService bizAreaService;

	@Autowired
	private ProjectOrderListService projectOrderListService;
	@Autowired
	private SysSequenceService sysSequenceService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizArea get(@RequestParam(required = false) String id) {
		BizArea entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizAreaService.get(id);
		}
		if (entity == null) {
			entity = new BizArea();
		}
		return entity;
	}

	@SuppressWarnings("unused")
	@RequiresPermissions("bizarea:bizArea:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizArea bizArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 过虑门店
		if (StringUtils.isBlank(bizArea.getStoreId())) {
			bizArea.setStoreId(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizArea.getProjectMode())) {
			User user = UserUtils.getUser();
			if (StringUtils.isNoneBlank(user.getEmpId())) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())) {
					bizArea.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				} else {
					bizArea.setProjectMode(null);
				}
			} else {
				bizArea.setProjectMode(null);
			}
		}
		User user = UserUtils.getUser(); // 查询当前用户信息
		String storeId = UserUtils.getUser().getStoreId(); // 查询当前用户的门店
		List<BizEmpStore> allStoreList = BizDictUtils.getStoreList(); // 查询当前用户的所在区域
		List<DropModel> engineListWithUserConditions = DropUtils.getEngineListWithUserConditions(); // 查询当前用户的所在区域
		List<Role> roleList = UserUtils.getUser().getRoleList();// 查询当前用户是否为预算员
		String isflag = ""; // 是否是预算员
		BizMaterialsChoiceBill findChoiceBillCount = null;
		if (null != storeId && null != engineListWithUserConditions && null != allStoreList) {
			BizMaterialsChoiceBill bizMaterialsChoiceBill = new BizMaterialsChoiceBill();
			bizMaterialsChoiceBill.setStoreId(Integer.parseInt(storeId));
			bizMaterialsChoiceBill.setEngineDepartId(engineListWithUserConditions.get(0).getValue());
			findChoiceBillCount = projectOrderListService.findChoiceBillCount(bizMaterialsChoiceBill);
			if (null != findChoiceBillCount.getCounts() && !findChoiceBillCount.getCounts().equals("0")) {
				for (Role role : roleList) {
					if ((role.getId().equals("78ff91f745d544e4868e2b35c934ab91")) || role.getId().equals("【预算员】")) {
						findChoiceBillCount.setIsFalg("1");
					} else {
						findChoiceBillCount.setIsFalg("0");
					}
				}
			}
			findChoiceBillCount.setStoreName(allStoreList.get(0).getLabel());
			findChoiceBillCount.setContractNumber(engineListWithUserConditions.get(0).getLabel());
		}
		Page<BizArea> page = bizAreaService.findPage(new Page<BizArea>(request, response), bizArea);
		model.addAttribute("findChoiceBillCount", findChoiceBillCount);
		model.addAttribute("page", page);
		return "modules/bizarea/bizAreaList";
	}

	@RequiresPermissions("bizarea:bizArea:view")
	@RequestMapping(value = "form")
	public String form(BizArea bizArea, Model model) {
		// 过滤门店
		if (StringUtils.isBlank(bizArea.getStoreId())) {
			bizArea.setStoreId(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		User user = UserUtils.getUser();
		if (StringUtils.isNoneBlank(user.getEmpId())) {
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if (ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())) {
				model.addAttribute("projectModeEnable", be.getProjectMode());
			}
		}
		model.addAttribute("bizArea", bizArea);
		return "modules/bizarea/bizAreaForm";
	}

	@RequiresPermissions("bizarea:bizArea:edit")
	@RequestMapping(value = "save")
	public String save(BizArea bizArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizArea)) {
			return form(bizArea, model);
		}
		if (bizArea.getId() == null || "".equals(bizArea.getId())) {
			bizArea.setCode(sysSequenceService.getSequence("QY"));
		}
		bizAreaService.save(bizArea);
		addMessage(redirectAttributes, "保存区域成功");
		return "redirect:" + Global.getAdminPath() + "/bizarea/bizArea/?repage";
	}

	@RequiresPermissions("bizarea:bizArea:edit")
	@RequestMapping(value = "delete")
	public String delete(BizArea bizArea, RedirectAttributes redirectAttributes) {
		bizAreaService.delete(bizArea);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:" + Global.getAdminPath() + "/bizarea/bizArea/?repage";
	}

}