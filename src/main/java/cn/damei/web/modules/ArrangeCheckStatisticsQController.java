
package cn.damei.web.modules;

import java.util.Date;

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
import cn.damei.entity.modules.ArrangeCheckStatisticsQ;
import cn.damei.service.modules.ArrangeCheckStatisticsQService;


@Controller
@RequestMapping(value = "${adminPath}/arrangecheckstatisticsq/arrangeCheckStatisticsQ")
public class ArrangeCheckStatisticsQController extends BaseController {

	@Autowired
	private ArrangeCheckStatisticsQService arrangeCheckStatisticsQService;

	@ModelAttribute
	public ArrangeCheckStatisticsQ get(@RequestParam(required = false) String id) {
		ArrangeCheckStatisticsQ entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = arrangeCheckStatisticsQService.get(id);
		}
		if (entity == null) {
			entity = new ArrangeCheckStatisticsQ();
			entity.setAcceptCheckDatetimeStart(new Date());
			entity.setAcceptCheckDatetimeEnd(new Date());
		}
		return entity;
	}


	@RequiresPermissions("arrangecheckstatisticsq:arrangeCheckStatisticsQ:view")
	@RequestMapping(value = { "list", "" })
	public String list(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ, HttpServletRequest request, HttpServletResponse response, Model model) {

		arrangeCheckStatisticsQ.setAcceptCheckDatetime(new Date());
		model.addAttribute("arrangeCheckStatisticsQ", arrangeCheckStatisticsQ);
		return "modules/arrangecheckstatisticsq/arrangeCheckStatisticsQList";
	}

	@RequiresPermissions("arrangecheckstatisticsq:arrangeCheckStatisticsQ:view")
	@RequestMapping(value = "listget")
	public String listget(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ArrangeCheckStatisticsQ> page = arrangeCheckStatisticsQService.findPage(new Page<ArrangeCheckStatisticsQ>(request, response), arrangeCheckStatisticsQ);
		model.addAttribute("page", page);
		return "modules/arrangecheckstatisticsq/arrangeCheckStatisticsQList";
	}

	@RequiresPermissions("arrangecheckstatisticsq:arrangeCheckStatisticsQ:view")
	@RequestMapping(value = "form")
	public String form(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ, Model model) {
		model.addAttribute("arrangeCheckStatisticsQ", arrangeCheckStatisticsQ);
		return "modules/arrangecheckstatisticsq/arrangeCheckStatisticsQForm";
	}

	@RequiresPermissions("arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit")
	@RequestMapping(value = "save")
	public String save(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, arrangeCheckStatisticsQ)) {
			return form(arrangeCheckStatisticsQ, model);
		}
		arrangeCheckStatisticsQService.save(arrangeCheckStatisticsQ);
		addMessage(redirectAttributes, "保存约检节点验收统计成功");
		return "redirect:" + Global.getAdminPath() + "/arrangecheckstatisticsq/arrangeCheckStatisticsQ/?repage";
	}

	@RequiresPermissions("arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit")
	@RequestMapping(value = "delete")
	public String delete(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ, RedirectAttributes redirectAttributes) {
		arrangeCheckStatisticsQService.delete(arrangeCheckStatisticsQ);
		addMessage(redirectAttributes, "删除约检节点验收统计成功");
		return "redirect:" + Global.getAdminPath() + "/arrangecheckstatisticsq/arrangeCheckStatisticsQ/?repage";
	}

}