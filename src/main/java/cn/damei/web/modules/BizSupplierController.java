/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.service.modules.BizSupplierService;
import cn.damei.common.utils.DictUtils;

/**
 * 供应商管理Controller
 * 
 * @author lc
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/bizSupplier")
public class BizSupplierController extends BaseController {

	@Autowired
	private BizSupplierService bizSupplierService;

	@Autowired
	private SysSequenceService sysSequenceService;

	@ModelAttribute
	public BizSupplier get(@RequestParam(required = false) String id) {
		BizSupplier entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizSupplierService.get(id);
		}
		if (entity == null) {
			entity = new BizSupplier();
		}
		return entity;
	}

	@RequiresPermissions("supplier:bizSupplier:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizSupplier bizSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSupplier> page = bizSupplierService.findPage(new Page<BizSupplier>(request, response), bizSupplier);
		model.addAttribute("page", page);
		return "modules/supplier/bizSupplierList";
	}

	@RequiresPermissions("supplier:bizSupplier:view")
	@RequestMapping(value = "form")
	public String form(BizSupplier bizSupplier, Model model) {
		model.addAttribute("bizSupplier", bizSupplier);
		return "modules/supplier/bizSupplierForm";
	}

	@RequiresPermissions("supplier:bizSupplier:edit")
	@RequestMapping(value = "save")
	public String save(BizSupplier bizSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSupplier)) {
			return form(bizSupplier, model);
		}
		if (bizSupplier.getId() == null || "".equals(bizSupplier.getId())) {
			bizSupplier.setSupplierNo(sysSequenceService.getSequence("GY"));
		}
		bizSupplierService.save(bizSupplier);
		addMessage(redirectAttributes, "保存供应商成功");
		return "redirect:" + Global.getAdminPath() + "/supplier/bizSupplier/?repage";
	}

	@RequiresPermissions("supplier:bizSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSupplier bizSupplier, RedirectAttributes redirectAttributes) {
		bizSupplierService.delete(bizSupplier);
		addMessage(redirectAttributes, "删除供应商成功");
		return "redirect:" + Global.getAdminPath() + "/supplier/bizSupplier/?repage";
	}

	/**
	 * 停用/启用
	 * 
	 * @param bizTaskPackageType
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("supplier:bizSupplier:edit")
	@RequestMapping(value = "enable")
	public String enable(BizSupplier bizSupplier, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizSupplier.getStatus());
		bizSupplier.setStatus(status + "");
		bizSupplierService.save(bizSupplier);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status + "", "biz_enable_status", "") + "操作成功");
		return "redirect:" + Global.getAdminPath() + "/supplier/bizSupplier/?repage";
	}

	/**
	 * 根据供应商业务类型查询供应商信息
	 * 
	 * @Title: ajaxgetSupplier
	 * @Description: TODO
	 * @param @param bizSupplier
	 * @param @param redirectAttributes
	 * @param @return
	 * @return Map<String,Object>
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "ajaxgetSupplier")
	@ResponseBody
	public Map<String, Object> ajaxgetSupplier(BizSupplier bizSupplier, RedirectAttributes redirectAttributes) {
		List<BizSupplier> SupplierList = bizSupplierService.queryajaxgetSupplier(bizSupplier);

		Map<String, Object> MapList = new HashMap<String, Object>();
		MapList.put("resultMap", SupplierList);
		return MapList;

	}

	/**
	 * 根据ID查询供应商信息
	 * 
	 * @Title: ajaxgetSupplierId
	 * @Description: TODO
	 * @param @param bizSupplier
	 * @param @param redirectAttributes
	 * @param @return
	 * @return Map<String,Object>
	 * @author ZhangTongWei
	 * @throws
	 */

	@RequestMapping(value = "ajaxgetSupplierId")
	@ResponseBody
	public Map<String, Object> ajaxgetSupplierId(BizSupplier bizSupplier, RedirectAttributes redirectAttributes) {
		List<BizSupplier> supplierList = new ArrayList<BizSupplier>();
		BizSupplier supplierbean = bizSupplierService.get(bizSupplier);
		supplierList.add(supplierbean);
		Map<String, Object> MapList = new HashMap<String, Object>();
		MapList.put("resultMap", supplierList);
		return MapList;

	}
}