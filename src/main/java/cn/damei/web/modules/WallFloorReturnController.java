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
import cn.damei.entity.modules.WallFloorReturn;
import cn.damei.service.modules.WallFloorReturnService;

/**
 * 墙地砖退货Controller
 * 
 * @author 张同维
 * @version 2017-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/materialwallfloor/wallFloorReturn")
public class WallFloorReturnController extends BaseController {

	@Autowired
	private WallFloorReturnService wallFloorReturnService;

	@ModelAttribute
	public WallFloorReturn get(@RequestParam(required = false) String id) {
		WallFloorReturn entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = wallFloorReturnService.get(id);
		}
		if (entity == null) {
			entity = new WallFloorReturn();
		}
		return entity;
	}

	@RequiresPermissions("materialwallfloor:wallFloorReturn:view")
	@RequestMapping(value = { "list", "" })
	public String list(WallFloorReturn wallFloorReturn, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<WallFloorReturn> page = wallFloorReturnService.findPage(new Page<WallFloorReturn>(request, response), wallFloorReturn);
		model.addAttribute("page", page);
		return "modules/materialwallfloor/wallFloorReturnList";
	}

	@RequiresPermissions("materialwallfloor:wallFloorReturn:view")
	@RequestMapping(value = "form")
	public String form(WallFloorReturn wallFloorReturn, Model model) {
		model.addAttribute("wallFloorReturn", wallFloorReturn);
		return "modules/materialwallfloor/wallFloorReturnForm";
	}

	@RequiresPermissions("materialwallfloor:wallFloorReturn:edit")
	@RequestMapping(value = "save")
	public String save(WallFloorReturn wallFloorReturn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wallFloorReturn)) {
			return form(wallFloorReturn, model);
		}
		wallFloorReturnService.save(wallFloorReturn);
		addMessage(redirectAttributes, "保存墙地砖退货成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallFloorReturn/?repage";
	}

	@RequiresPermissions("materialwallfloor:wallFloorReturn:edit")
	@RequestMapping(value = "delete")
	public String delete(WallFloorReturn wallFloorReturn, RedirectAttributes redirectAttributes) {
		wallFloorReturnService.delete(wallFloorReturn);
		addMessage(redirectAttributes, "删除墙地砖退货成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallFloorReturn/?repage";
	}

}