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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBillItem;
import cn.damei.service.modules.BizMaterialsChoiceChangeBillItemService;
	
/**
 * 选材变更单材料表Controller
 * @author wyb
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem")
public class BizMaterialsChoiceChangeBillItemController extends BaseController {

	@Autowired
	private BizMaterialsChoiceChangeBillItemService bizMaterialsChoiceChangeBillItemService;
	
	@ModelAttribute
	public BizMaterialsChoiceChangeBillItem get(@RequestParam(required=false) Integer id) {
		BizMaterialsChoiceChangeBillItem entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialsChoiceChangeBillItemService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsChoiceChangeBillItem();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsChoiceChangeBillItem> page = bizMaterialsChoiceChangeBillItemService.findPage(new Page<BizMaterialsChoiceChangeBillItem>(request, response), bizMaterialsChoiceChangeBillItem); 
		model.addAttribute("page", page);
		return "modules/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItemList";
	}

	@RequiresPermissions("bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem, Model model) {
		model.addAttribute("bizMaterialsChoiceChangeBillItem", bizMaterialsChoiceChangeBillItem);
		return "modules/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItemForm";
	}

	@RequiresPermissions("bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsChoiceChangeBillItem)){
			return form(bizMaterialsChoiceChangeBillItem, model);
		}
		bizMaterialsChoiceChangeBillItemService.save(bizMaterialsChoiceChangeBillItem);
		addMessage(redirectAttributes, "保存选材变更单材料表成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/?repage";
	}
	
	@RequiresPermissions("bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem, RedirectAttributes redirectAttributes) {
		bizMaterialsChoiceChangeBillItemService.delete(bizMaterialsChoiceChangeBillItem);
		addMessage(redirectAttributes, "删除选材变更单材料表成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/?repage";
	}

}