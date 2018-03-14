
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
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.service.modules.BizPmSettleCategorySummaryService;


@Controller
@RequestMapping(value = "${adminPath}/pmsettlecategorysummary/bizPmSettleCategorySummary")
public class BizPmSettleCategorySummaryController extends BaseController {

	@Autowired
	private BizPmSettleCategorySummaryService bizPmSettleCategorySummaryService;
	
	@ModelAttribute
	public BizPmSettleCategorySummary get(@RequestParam(required=false) Integer id) {
		BizPmSettleCategorySummary entity = null;
		if (id != null){
			entity = bizPmSettleCategorySummaryService.get(id);
		}
		if (entity == null){
			entity = new BizPmSettleCategorySummary();
		}
		return entity;
	}
	
	@RequiresPermissions("pmsettlecategorysummary:bizPmSettleCategorySummary:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmSettleCategorySummary bizPmSettleCategorySummary, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmSettleCategorySummary> page = bizPmSettleCategorySummaryService.findPage(new Page<BizPmSettleCategorySummary>(request, response), bizPmSettleCategorySummary); 
		model.addAttribute("page", page);
		return "modules/pmsettlecategorysummary/bizPmSettleCategorySummaryList";
	}

	@RequiresPermissions("pmsettlecategorysummary:bizPmSettleCategorySummary:view")
	@RequestMapping(value = "form")
	public String form(BizPmSettleCategorySummary bizPmSettleCategorySummary, Model model) {
		model.addAttribute("bizPmSettleCategorySummary", bizPmSettleCategorySummary);
		return "modules/pmsettlecategorysummary/bizPmSettleCategorySummaryForm";
	}

	@RequiresPermissions("pmsettlecategorysummary:bizPmSettleCategorySummary:edit")
	@RequestMapping(value = "save")
	public String save(BizPmSettleCategorySummary bizPmSettleCategorySummary, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmSettleCategorySummary)){
			return form(bizPmSettleCategorySummary, model);
		}
		bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);
		addMessage(redirectAttributes, "保存结算类目汇总成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlecategorysummary/bizPmSettleCategorySummary/?repage";
	}
	
	@RequiresPermissions("pmsettlecategorysummary:bizPmSettleCategorySummary:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmSettleCategorySummary bizPmSettleCategorySummary, RedirectAttributes redirectAttributes) {
		bizPmSettleCategorySummaryService.delete(bizPmSettleCategorySummary);
		addMessage(redirectAttributes, "删除结算类目汇总成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlecategorysummary/bizPmSettleCategorySummary/?repage";
	}

}