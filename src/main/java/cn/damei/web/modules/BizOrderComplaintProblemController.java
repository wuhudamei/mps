
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
import cn.damei.entity.modules.BizOrderComplaintProblem;
import cn.damei.service.modules.BizOrderComplaintProblemService;


@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/bizOrderComplaintProblem")
public class BizOrderComplaintProblemController extends BaseController {

	@Autowired
	private BizOrderComplaintProblemService bizOrderComplaintProblemService;

	@ModelAttribute
	public BizOrderComplaintProblem get(@RequestParam(required = false) String id) {
		BizOrderComplaintProblem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizOrderComplaintProblemService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderComplaintProblem();
		}
		return entity;
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderComplaintProblem bizOrderComplaintProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderComplaintProblem> page = bizOrderComplaintProblemService.findPage(new Page<BizOrderComplaintProblem>(request, response), bizOrderComplaintProblem);
		model.addAttribute("page", page);
		return "modules/ordercomplan/bizOrderComplaintProblemList";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblem:view")
	@RequestMapping(value = "form")
	public String form(BizOrderComplaintProblem bizOrderComplaintProblem, Model model) {
		model.addAttribute("bizOrderComplaintProblem", bizOrderComplaintProblem);
		return "modules/ordercomplan/bizOrderComplaintProblemForm";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblem:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderComplaintProblem bizOrderComplaintProblem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderComplaintProblem)) {
			return form(bizOrderComplaintProblem, model);
		}
		bizOrderComplaintProblemService.save(bizOrderComplaintProblem);
		addMessage(redirectAttributes, "保存工程问题成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaintProblem/?repage";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderComplaintProblem bizOrderComplaintProblem, RedirectAttributes redirectAttributes) {
		bizOrderComplaintProblemService.delete(bizOrderComplaintProblem);
		addMessage(redirectAttributes, "删除工程问题成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaintProblem/?repage";
	}

}