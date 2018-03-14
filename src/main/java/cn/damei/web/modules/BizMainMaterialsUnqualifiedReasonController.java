
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.service.modules.BizMainMaterialsUnqualifiedReasonService;
import cn.damei.entity.modules.BizProjectInstallItem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason")
public class BizMainMaterialsUnqualifiedReasonController extends BaseController {

	@Autowired
	private BizMainMaterialsUnqualifiedReasonService bizMainMaterialsUnqualifiedReasonService;

	@ModelAttribute
	public BizMainMaterialsUnqualifiedReason get(@RequestParam(required=false) Integer id) {
		BizMainMaterialsUnqualifiedReason entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMainMaterialsUnqualifiedReasonService.get(id);
		}
		if (entity == null){
			entity = new BizMainMaterialsUnqualifiedReason();
		}
		return entity;
	}


	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, Model model) {
		bizMainMaterialsUnqualifiedReasonService.storeIdAndProjectMode(bizMainMaterialsUnqualifiedReason,model);
		return "modules/bizmainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReasonList";
	}


	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizMainMaterialsUnqualifiedReasonService.storeIdAndProjectMode(bizMainMaterialsUnqualifiedReason,model);
		Page<BizMainMaterialsUnqualifiedReason> page = bizMainMaterialsUnqualifiedReasonService.findPage(new Page<BizMainMaterialsUnqualifiedReason>(request, response), bizMainMaterialsUnqualifiedReason);
        model.addAttribute("page", page);
		return "modules/bizmainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReasonList";
	}

	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:view")
	@RequestMapping(value = "form")
	public String form(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, Model model) {
		model.addAttribute("bizMainMaterialsUnqualifiedReason", bizMainMaterialsUnqualifiedReason);
		return "modules/bizmainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReasonForm";
	}

	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit")
	@RequestMapping(value = "save")
	public String save(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMainMaterialsUnqualifiedReason)){
			return form(bizMainMaterialsUnqualifiedReason, model);
		}
		bizMainMaterialsUnqualifiedReasonService.save(bizMainMaterialsUnqualifiedReason);
		addMessage(redirectAttributes, "保存主材安装项验收不合格原因配置成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/list?repage";
	}


	@RequestMapping(value = "/queryInstallItemList")
	public @ResponseBody List<BizProjectInstallItem> queryInstallItemList(String storeId,String projectMode,String installMode) {
		return bizMainMaterialsUnqualifiedReasonService.queryInstallItemList(storeId,projectMode,installMode);
	}


	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit")
	@RequestMapping(value = "/enable")
	public @ResponseBody String enable(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		return bizMainMaterialsUnqualifiedReasonService.updateRreasonEnable(bizMainMaterialsUnqualifiedReason);
	}


	@RequiresPermissions("mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit")
	@RequestMapping(value = "/delete")
	public @ResponseBody String delete(Integer id) {
		return bizMainMaterialsUnqualifiedReasonService.updateRreasonDelete(id);
	}

}