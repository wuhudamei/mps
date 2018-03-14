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
import cn.damei.entity.modules.BizEmployeeBankcardRelatedIdcard;
import cn.damei.service.modules.BizEmployeeBankcardRelatedIdcardService;

/**
 * 员工身份证关联Controller
 * @author qww
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard")
public class BizEmployeeBankcardRelatedIdcardController extends BaseController {

	@Autowired
	private BizEmployeeBankcardRelatedIdcardService bizEmployeeBankcardRelatedIdcardService;
	
	@ModelAttribute
	public BizEmployeeBankcardRelatedIdcard get(@RequestParam(required=false) Integer id) {
		BizEmployeeBankcardRelatedIdcard entity = null;
		if (id != null){
			entity = bizEmployeeBankcardRelatedIdcardService.get(id);
		}
		if (entity == null){
			entity = new BizEmployeeBankcardRelatedIdcard();
		}
		return entity;
	}
	
	@RequiresPermissions("employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizEmployeeBankcardRelatedIdcard> page = bizEmployeeBankcardRelatedIdcardService.findPage(new Page<BizEmployeeBankcardRelatedIdcard>(request, response), bizEmployeeBankcardRelatedIdcard); 
		model.addAttribute("page", page);
		return "modules/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcardList";
	}

	@RequiresPermissions("employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:view")
	@RequestMapping(value = "form")
	public String form(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard, Model model) {
		model.addAttribute("bizEmployeeBankcardRelatedIdcard", bizEmployeeBankcardRelatedIdcard);
		return "modules/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcardForm";
	}

	@RequiresPermissions("employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit")
	@RequestMapping(value = "save")
	public String save(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployeeBankcardRelatedIdcard)){
			return form(bizEmployeeBankcardRelatedIdcard, model);
		}
		bizEmployeeBankcardRelatedIdcardService.save(bizEmployeeBankcardRelatedIdcard);
		addMessage(redirectAttributes, "保存员工身份证关联成功");
		return "redirect:"+Global.getAdminPath()+"/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/?repage";
	}
	
	@RequiresPermissions("employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard, RedirectAttributes redirectAttributes) {
		bizEmployeeBankcardRelatedIdcardService.delete(bizEmployeeBankcardRelatedIdcard);
		addMessage(redirectAttributes, "删除员工身份证关联成功");
		return "redirect:"+Global.getAdminPath()+"/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/?repage";
	}

}