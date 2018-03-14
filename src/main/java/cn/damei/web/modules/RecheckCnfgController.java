
package cn.damei.web.modules;

import java.util.List;

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
import cn.damei.entity.modules.RecheckCnfg;
import cn.damei.service.modules.RecheckCnfgService;


@Controller
@RequestMapping(value = "${adminPath}/materialwallfloor/recheckCnfg")
public class RecheckCnfgController extends BaseController {

	@Autowired
	private RecheckCnfgService recheckCnfgService;

	@ModelAttribute
	public RecheckCnfg get(@RequestParam(required = false) String id) {
		RecheckCnfg entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = recheckCnfgService.get(id);
		}
		if (entity == null) {
			entity = new RecheckCnfg();
		}
		return entity;
	}


	@RequiresPermissions("materialwallfloor:recheckCnfg:view")
	@RequestMapping(value = { "list", "" })
	public String list(RecheckCnfg recheckCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecheckCnfg> page = recheckCnfgService.findPage(new Page<RecheckCnfg>(request, response), recheckCnfg);
		List<RecheckCnfg> list = page.getList();
		if (null != list) {
			for (RecheckCnfg recheckCnfg2 : list) {
				recheckCnfg2.setSquareOverMaxRate(((Double.parseDouble(recheckCnfg2.getSquareOverMaxRate()) * 100) + "").substring(0, ((Double.parseDouble(recheckCnfg2.getSquareOverMaxRate()) * 100) + "").length() - 2) + "%");
			}
		}
		model.addAttribute("page", page);
		return "modules/materialwallfloor/recheckCnfgList";
	}

	@RequiresPermissions("materialwallfloor:recheckCnfg:view")
	@RequestMapping(value = "form")
	public String form(RecheckCnfg recheckCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecheckCnfg> page = recheckCnfgService.findPage(new Page<RecheckCnfg>(request, response), recheckCnfg);
		List<RecheckCnfg> list = page.getList();
		if (null != list && list.size() > 0) {
			for (RecheckCnfg recheckCnfg2 : list) {
				recheckCnfg2.setSquareOverMaxRate(((Double.parseDouble(recheckCnfg2.getSquareOverMaxRate()) * 100) + "").substring(0, ((Double.parseDouble(recheckCnfg2.getSquareOverMaxRate()) * 100) + "").length() - 2));
			}
		}
		if (null != page.getList() && page.getList().size() > 0) {
			RecheckCnfg recheckCnfg2 = page.getList().get(0);
			model.addAttribute("recheckCnfg", recheckCnfg2);
		}
		return "modules/materialwallfloor/recheckCnfgForm";
	}

	@RequiresPermissions("materialwallfloor:recheckCnfg:edit")
	@RequestMapping(value = "save")
	public String save(RecheckCnfg recheckCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recheckCnfg)) {
			return form(recheckCnfg, null, null, model);
		}
		if (recheckCnfg.getSquareOverMaxRate().contains("%")) {
			recheckCnfg.setSquareOverMaxRate(recheckCnfg.getSquareOverMaxRate().substring(0, recheckCnfg.getSquareOverMaxRate().length() - 1));
		}
		recheckCnfgService.datalesave(recheckCnfg);
		addMessage(redirectAttributes, "保存复尺配置表成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/recheckCnfg/?repage";
	}

	@RequiresPermissions("materialwallfloor:recheckCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(RecheckCnfg recheckCnfg, RedirectAttributes redirectAttributes) {
		recheckCnfgService.delete(recheckCnfg);
		addMessage(redirectAttributes, "删除复尺配置表成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/recheckCnfg/?repage";
	}

}