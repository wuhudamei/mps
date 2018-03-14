
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
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.service.modules.BizPmSettleCategoryDetailService;


@Controller
@RequestMapping(value = "${adminPath}/pmsettlecategorydetail/bizPmSettleCategoryDetail")
public class BizPmSettleCategoryDetailController extends BaseController {

	@Autowired
	private BizPmSettleCategoryDetailService bizPmSettleCategoryDetailService;
	
	@ModelAttribute
	public BizPmSettleCategoryDetail get(@RequestParam(required=false) Integer id) {
		BizPmSettleCategoryDetail entity = null;
		if (id != null){
			entity = bizPmSettleCategoryDetailService.get(id);
		}
		if (entity == null){
			entity = new BizPmSettleCategoryDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pmsettlecategorydetail:bizPmSettleCategoryDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmSettleCategoryDetail bizPmSettleCategoryDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmSettleCategoryDetail> page = bizPmSettleCategoryDetailService.findPage(new Page<BizPmSettleCategoryDetail>(request, response), bizPmSettleCategoryDetail); 
		model.addAttribute("page", page);
		return "modules/pmsettlecategorydetail/bizPmSettleCategoryDetailList";
	}

	@RequiresPermissions("pmsettlecategorydetail:bizPmSettleCategoryDetail:view")
	@RequestMapping(value = "form")
	public String form(BizPmSettleCategoryDetail bizPmSettleCategoryDetail, Model model) {
		model.addAttribute("bizPmSettleCategoryDetail", bizPmSettleCategoryDetail);
		return "modules/pmsettlecategorydetail/bizPmSettleCategoryDetailForm";
	}

	@RequiresPermissions("pmsettlecategorydetail:bizPmSettleCategoryDetail:edit")
	@RequestMapping(value = "save")
	public String save(BizPmSettleCategoryDetail bizPmSettleCategoryDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmSettleCategoryDetail)){
			return form(bizPmSettleCategoryDetail, model);
		}
		bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		addMessage(redirectAttributes, "保存结算类目明细成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlecategorydetail/bizPmSettleCategoryDetail/?repage";
	}
	
	@RequiresPermissions("pmsettlecategorydetail:bizPmSettleCategoryDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmSettleCategoryDetail bizPmSettleCategoryDetail, RedirectAttributes redirectAttributes) {
		bizPmSettleCategoryDetailService.delete(bizPmSettleCategoryDetail);
		addMessage(redirectAttributes, "删除结算类目明细成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlecategorydetail/bizPmSettleCategoryDetail/?repage";
	}
}