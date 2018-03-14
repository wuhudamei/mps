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
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeRel;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailMergeRelService;

/**
 * 付款单明细合并关系Controller
 * @author qww
 * @version 2016-10-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeRel")
public class BizOrderTaskpackagePaymentDetailMergeRelController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentDetailMergeRelService bizOrderTaskpackagePaymentDetailMergeRelService;
	
	@ModelAttribute
	public BizOrderTaskpackagePaymentDetailMergeRel get(@RequestParam(required=false) Integer id) {
		BizOrderTaskpackagePaymentDetailMergeRel entity = null;
		if (id != null){
			entity = bizOrderTaskpackagePaymentDetailMergeRelService.get(id);
		}
		if (entity == null){
			entity = new BizOrderTaskpackagePaymentDetailMergeRel();
		}
		return entity;
	}
	
	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMergeRel:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderTaskpackagePaymentDetailMergeRel> page = bizOrderTaskpackagePaymentDetailMergeRelService.findPage(new Page<BizOrderTaskpackagePaymentDetailMergeRel>(request, response), bizOrderTaskpackagePaymentDetailMergeRel); 
		model.addAttribute("page", page);
		return "modules/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeRelList";
	}

	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMergeRel:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel, Model model) {
		model.addAttribute("bizOrderTaskpackagePaymentDetailMergeRel", bizOrderTaskpackagePaymentDetailMergeRel);
		return "modules/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeRelForm";
	}

	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMergeRel:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePaymentDetailMergeRel)){
			return form(bizOrderTaskpackagePaymentDetailMergeRel, model);
		}
		bizOrderTaskpackagePaymentDetailMergeRelService.save(bizOrderTaskpackagePaymentDetailMergeRel);
		addMessage(redirectAttributes, "保存付款单明细合并关系成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeRel/?repage";
	}
	
	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMergeRel:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentDetailMergeRelService.delete(bizOrderTaskpackagePaymentDetailMergeRel);
		addMessage(redirectAttributes, "删除付款单明细合并关系成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeRel/?repage";
	}

}