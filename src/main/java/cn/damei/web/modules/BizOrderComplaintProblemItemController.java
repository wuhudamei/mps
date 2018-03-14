
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
import cn.damei.entity.modules.BizOrderComplaintProblemItem;
import cn.damei.service.modules.BizOrderComplaintProblemItemService;


@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/bizOrderComplaintProblemItem")
public class BizOrderComplaintProblemItemController extends BaseController {

	@Autowired
	private BizOrderComplaintProblemItemService bizOrderComplaintProblemItemService;
	
	@ModelAttribute
	public BizOrderComplaintProblemItem get(@RequestParam(required=false) String id) {
		BizOrderComplaintProblemItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizOrderComplaintProblemItemService.get(id);
		}
		if (entity == null){
			entity = new BizOrderComplaintProblemItem();
		}
		return entity;
	}
	
	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderComplaintProblemItem bizOrderComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderComplaintProblemItem> page = bizOrderComplaintProblemItemService.findPage(new Page<BizOrderComplaintProblemItem>(request, response), bizOrderComplaintProblemItem); 
		model.addAttribute("page", page);
		return "modules/ordercomplan/bizOrderComplaintProblemItemList";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemItem:view")
	@RequestMapping(value = "form")
	public String form(BizOrderComplaintProblemItem bizOrderComplaintProblemItem, Model model) {
		model.addAttribute("bizOrderComplaintProblemItem", bizOrderComplaintProblemItem);
		return "modules/ordercomplan/bizOrderComplaintProblemItemForm";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemItem:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderComplaintProblemItem bizOrderComplaintProblemItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderComplaintProblemItem)){
			return form(bizOrderComplaintProblemItem, model);
		}
		bizOrderComplaintProblemItemService.save(bizOrderComplaintProblemItem);
		addMessage(redirectAttributes, "保存工程事项和工程问题对照表成功");
		return "redirect:"+Global.getAdminPath()+"/ordercomplan/bizOrderComplaintProblemItem/?repage";
	}
	
	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderComplaintProblemItem bizOrderComplaintProblemItem, RedirectAttributes redirectAttributes) {
		bizOrderComplaintProblemItemService.delete(bizOrderComplaintProblemItem);
		addMessage(redirectAttributes, "删除工程事项和工程问题对照表成功");
		return "redirect:"+Global.getAdminPath()+"/ordercomplan/bizOrderComplaintProblemItem/?repage";
	}

}