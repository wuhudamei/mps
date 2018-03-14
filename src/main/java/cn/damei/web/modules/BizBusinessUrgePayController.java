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
import cn.damei.entity.modules.BizBusinessUrgePayEntity;
import cn.damei.service.modules.BizBusinessUrgePayService;

/**
 * 业务催缴Controller
 * @author lzm
 * @version 2017-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/businessurgepay/bizBusinessUrgePay")
public class BizBusinessUrgePayController extends BaseController {

	@Autowired
	private BizBusinessUrgePayService bizBusinessUrgePayService;
	
	@ModelAttribute
	public BizBusinessUrgePayEntity get(@RequestParam(required=false) String id) {
		BizBusinessUrgePayEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizBusinessUrgePayService.get(id);
		}
		if (entity == null){
			entity = new BizBusinessUrgePayEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("businessurgepay:bizBusinessUrgePay:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizBusinessUrgePayEntity bizBusinessUrgePay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizBusinessUrgePayEntity> page = bizBusinessUrgePayService.findPage(new Page<BizBusinessUrgePayEntity>(request, response), bizBusinessUrgePay); 
		model.addAttribute("page", page);
		return "modules/businessurgepay/bizBusinessUrgePayList";
	}

	@RequiresPermissions("businessurgepay:bizBusinessUrgePay:view")
	@RequestMapping(value = "form")
	public String form(BizBusinessUrgePayEntity bizBusinessUrgePay, Model model) {
		model.addAttribute("bizBusinessUrgePay", bizBusinessUrgePay);
		return "modules/businessurgepay/bizBusinessUrgePayForm";
	}

	@RequiresPermissions("businessurgepay:bizBusinessUrgePay:edit")
	@RequestMapping(value = "save")
	public String save(BizBusinessUrgePayEntity bizBusinessUrgePay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizBusinessUrgePay)){
			return form(bizBusinessUrgePay, model);
		}
		bizBusinessUrgePayService.save(bizBusinessUrgePay);
		addMessage(redirectAttributes, "保存催缴成功");
		return "redirect:"+Global.getAdminPath()+"/businessurgepay/bizBusinessUrgePay/?repage";
	}
	
	@RequiresPermissions("businessurgepay:bizBusinessUrgePay:edit")
	@RequestMapping(value = "delete")
	public String delete(BizBusinessUrgePayEntity bizBusinessUrgePay, RedirectAttributes redirectAttributes) {
		bizBusinessUrgePayService.delete(bizBusinessUrgePay);
		addMessage(redirectAttributes, "删除催缴成功");
		return "redirect:"+Global.getAdminPath()+"/businessurgepay/bizBusinessUrgePay/?repage";
	}

}