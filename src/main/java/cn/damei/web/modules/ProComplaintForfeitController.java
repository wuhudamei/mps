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
import cn.damei.entity.modules.ProComplaintForfeit;
import cn.damei.service.modules.ProComplaintForfeitService;

/**
 * 客诉罚款Controller
 * 
 * @author ZTW
 * @version 2017-10-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/proComplaintForfeit")
public class ProComplaintForfeitController extends BaseController {

	@Autowired
	private ProComplaintForfeitService proComplaintForfeitService;

	@ModelAttribute
	public ProComplaintForfeit get(@RequestParam(required = false) String id) {
		ProComplaintForfeit entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = proComplaintForfeitService.get(id);
		}
		if (entity == null) {
			entity = new ProComplaintForfeit();
		}
		return entity;
	}

	@RequiresPermissions("ordercomplan:proComplaintForfeit:view")
	@RequestMapping(value = { "/list", "" })
	public String list(ProComplaintForfeit proComplaintForfeit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProComplaintForfeit> page = proComplaintForfeitService.findPage(new Page<ProComplaintForfeit>(request, response), proComplaintForfeit);
		model.addAttribute("page", page);
		return "modules/ordercomplan/proComplaintForfeitList";
	}

	@RequiresPermissions("ordercomplan:proComplaintForfeit:view")
	@RequestMapping(value = "/form")
	public String form(ProComplaintForfeit proComplaintForfeit, Model model) {
		model.addAttribute("proComplaintForfeit", proComplaintForfeit);
		return "modules/ordercomplan/proComplaintForfeitForm";
	}

	/**
	 * 同意
	 * 
	 * @Title: refuse
	 * @Description: TODO TODO
	 * @param @param proComplaintForfeit
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "/agree")
	public String agree(ProComplaintForfeit proComplaintForfeit, Model model, RedirectAttributes redirectAttributes) {
		proComplaintForfeitService.updateAgreeForfeit(proComplaintForfeit);
		model.addAttribute("proComplaintForfeit", proComplaintForfeit);
		addMessage(redirectAttributes, "同意客诉罚款成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/proComplaintForfeit/?repage";
	}

	/**
	 * 拒绝
	 * 
	 * @Title: refuse
	 * @Description: TODO
	 * @param @param proComplaintForfeit
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "/refuse")
	public String refuse(ProComplaintForfeit proComplaintForfeit, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("proComplaintForfeit", proComplaintForfeit);
		proComplaintForfeitService.updateRefuseForfeit(proComplaintForfeit);
		addMessage(redirectAttributes, "拒绝客诉罚款成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/proComplaintForfeit/?repage";
	}



}