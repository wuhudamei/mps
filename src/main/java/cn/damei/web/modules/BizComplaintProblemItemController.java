
package cn.damei.web.modules;

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
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.service.modules.BizComplaintProblemItemService;


@Controller
@RequestMapping(value = "${adminPath}/bizcomplaintproblemitem/bizComplaintProblemItem")
public class BizComplaintProblemItemController extends BaseController {

	@Autowired
	private BizComplaintProblemItemService bizComplaintProblemItemService;

	@ModelAttribute
	public BizComplaintProblemItem get(@RequestParam(required = false) String id) {
		BizComplaintProblemItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizComplaintProblemItemService.get(id);
		}
		if (entity == null) {
			entity = new BizComplaintProblemItem();
		}
		return entity;
	}

	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:view")
	@RequestMapping(value = { "/list", "" })
	public String list(BizComplaintProblemItem bizComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizComplaintProblemItem> page = bizComplaintProblemItemService.findPage(new Page<BizComplaintProblemItem>(request, response), bizComplaintProblemItem);
		model.addAttribute("page", page);
		model.addAttribute("entity", bizComplaintProblemItem);
		return "modules/bizcomplaintproblemitem/bizComplaintProblemItemList";
	}


	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:view")
	@RequestMapping(value = "/list1")
	public String list1(BizComplaintProblemItem bizComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizComplaintProblemItem> page = bizComplaintProblemItemService.findPage(new Page<BizComplaintProblemItem>(request, response), bizComplaintProblemItem);
		model.addAttribute("page", page);
		model.addAttribute("entity", bizComplaintProblemItem);
		return "modules/bizcomplaintproblemitem/bizComplaintProblemItemDetailsList";
	}

	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:view")
	@RequestMapping(value = "form")
	public String form(BizComplaintProblemItem bizComplaintProblemItem, Model model) {
		model.addAttribute("bizComplaintProblemItem", bizComplaintProblemItem);
		return "modules/bizcomplaintproblemitem/bizComplaintProblemItemForm";
	}

	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:edit")
	@RequestMapping(value = "save")
	public String save(BizComplaintProblemItem bizComplaintProblemItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizComplaintProblemItem)) {
			return form(bizComplaintProblemItem, model);
		}
		bizComplaintProblemItemService.save(bizComplaintProblemItem);
		addMessage(redirectAttributes, "保存投诉分类项成功");
		return "redirect:" + Global.getAdminPath() + "/bizcomplaintproblemitem/bizComplaintProblemItem/?repage";
	}

	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizComplaintProblemItem bizComplaintProblemItem, RedirectAttributes redirectAttributes) {
		bizComplaintProblemItemService.delete(bizComplaintProblemItem);
		addMessage(redirectAttributes, "设置成功");
		return "redirect:" + Global.getAdminPath() + "/bizcomplaintproblemitem/bizComplaintProblemItem/?repage";
	}

	@RequiresPermissions("bizcomplaintproblemitem:bizComplaintProblemItem:view")
	@RequestMapping(value = "findTypeMapByStoreId")
	@ResponseBody
	public List<Map<String, String>> delete(String storeId) {
		List<Map<String, String>> list = bizComplaintProblemItemService.findTypeMapByStoreId(storeId);

		return list;
	}

}