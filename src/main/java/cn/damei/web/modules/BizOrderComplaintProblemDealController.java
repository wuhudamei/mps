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
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizOrderComplaintProblemDeal;
import cn.damei.service.modules.BizOrderComplaintProblemDealService;

/**
 * 投诉问题处理Controller
 * @author ztw
 * @version 2017-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/bizOrderComplaintProblemDeal")
public class BizOrderComplaintProblemDealController extends BaseController {

	@Autowired
	private BizOrderComplaintProblemDealService bizOrderComplaintProblemDealService;
	
	@ModelAttribute
	public BizOrderComplaintProblemDeal get(@RequestParam(required=false) String id) {
		BizOrderComplaintProblemDeal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizOrderComplaintProblemDealService.get(id);
		}
		if (entity == null){
			entity = new BizOrderComplaintProblemDeal();
		}
		return entity;
	}
	
	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemDeal:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderComplaintProblemDeal> page = bizOrderComplaintProblemDealService.findPage(new Page<BizOrderComplaintProblemDeal>(request, response), bizOrderComplaintProblemDeal); 
		model.addAttribute("page", page);
		return "modules/ordercomplan/bizOrderComplaintProblemDealList";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemDeal:view")
	@RequestMapping(value = "form")
	public String form(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal, Model model) {
		model.addAttribute("bizOrderComplaintProblemDeal", bizOrderComplaintProblemDeal);
		return "modules/ordercomplan/bizOrderComplaintProblemDealForm";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemDeal:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderComplaintProblemDeal)){
			return form(bizOrderComplaintProblemDeal, model);
		}
		bizOrderComplaintProblemDealService.save(bizOrderComplaintProblemDeal);
		addMessage(redirectAttributes, "保存投诉问题处理成功");
		return "redirect:"+Global.getAdminPath()+"/ordercomplan/bizOrderComplaintProblemDeal/?repage";
	}
	
	@RequiresPermissions("ordercomplan:bizOrderComplaintProblemDeal:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal, RedirectAttributes redirectAttributes) {
		bizOrderComplaintProblemDealService.delete(bizOrderComplaintProblemDeal);
		addMessage(redirectAttributes, "删除投诉问题处理成功");
		return "redirect:"+Global.getAdminPath()+"/ordercomplan/bizOrderComplaintProblemDeal/?repage";
	}

}