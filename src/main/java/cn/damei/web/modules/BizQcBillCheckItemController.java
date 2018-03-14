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
import cn.damei.entity.modules.BizQcBillCheckItem;
import cn.damei.service.modules.BizQcBillCheckItemService;

/**
 * 质检检查项查询Controller
 * @author ztw
 * @version 2017-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/bizQcBillCheckItem")
public class BizQcBillCheckItemController extends BaseController {

	@Autowired
	private BizQcBillCheckItemService bizQcBillCheckItemService;
	
	@ModelAttribute
	public BizQcBillCheckItem get(@RequestParam(required=false) String id) {
		BizQcBillCheckItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizQcBillCheckItemService.get(id);
		}
		if (entity == null){
			entity = new BizQcBillCheckItem();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:bizQcBillCheckItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcBillCheckItem bizQcBillCheckItem, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/quality/bizQcBillCheckItemList";
	}
	@RequiresPermissions("quality:bizQcBillCheckItem:view")
	@RequestMapping(value = {"querylist", ""})
	public String querylist(BizQcBillCheckItem bizQcBillCheckItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizQcBillCheckItem> page = bizQcBillCheckItemService.findPage(new Page<BizQcBillCheckItem>(request, response), bizQcBillCheckItem);
		model.addAttribute("page", page);
		return "modules/quality/bizQcBillCheckItemList";
	}

	@RequiresPermissions("quality:bizQcBillCheckItem:view")
	@RequestMapping(value = "form")
	public String form(BizQcBillCheckItem bizQcBillCheckItem, Model model) {
		model.addAttribute("bizQcBillCheckItem", bizQcBillCheckItem);
		return "modules/quality/bizQcBillCheckItemForm";
	}

	@RequiresPermissions("quality:bizQcBillCheckItem:edit")
	@RequestMapping(value = "save")
	public String save(BizQcBillCheckItem bizQcBillCheckItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcBillCheckItem)){
			return form(bizQcBillCheckItem, model);
		}
		bizQcBillCheckItemService.save(bizQcBillCheckItem);
		addMessage(redirectAttributes, "保存质检检查项查询成功");
		return "redirect:"+Global.getAdminPath()+"/quality/bizQcBillCheckItem/?repage";
	}
	
	@RequiresPermissions("quality:bizQcBillCheckItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcBillCheckItem bizQcBillCheckItem, RedirectAttributes redirectAttributes) {
		bizQcBillCheckItemService.delete(bizQcBillCheckItem);
		addMessage(redirectAttributes, "删除质检检查项查询成功");
		return "redirect:"+Global.getAdminPath()+"/quality/bizQcBillCheckItem/?repage";
	}

}