
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.web.BaseController;


@Controller
@RequestMapping(value = "${adminPath}/tag")
public class TagController extends BaseController {
	

	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url"));
		model.addAttribute("extId", request.getParameter("extId"));
		model.addAttribute("checked", request.getParameter("checked"));
		model.addAttribute("selectIds", request.getParameter("selectIds"));
		model.addAttribute("isAll", request.getParameter("isAll"));
		model.addAttribute("module", request.getParameter("module"));
		return "modules/sys/tagTreeselect";
	}
	

	@RequiresPermissions("user")
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "modules/sys/tagIconselect";
	}
	
}
