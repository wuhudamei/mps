
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
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplit;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailSplitService;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit")
public class BizOrderTaskpackagePaymentDetailSplitController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentDetailSplitService bizOrderTaskpackagePaymentDetailSplitService;
	
	@ModelAttribute
	public BizOrderTaskpackagePaymentDetailSplit get(@RequestParam(required=false) Integer id) {
		BizOrderTaskpackagePaymentDetailSplit entity = null;
		if (id != null){
			entity = bizOrderTaskpackagePaymentDetailSplitService.get(id);
		}
		if (entity == null){
			entity = new BizOrderTaskpackagePaymentDetailSplit();
		}
		return entity;
	}
	
	@RequiresPermissions("ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderTaskpackagePaymentDetailSplit> page = bizOrderTaskpackagePaymentDetailSplitService.findPage(new Page<BizOrderTaskpackagePaymentDetailSplit>(request, response), bizOrderTaskpackagePaymentDetailSplit); 
		model.addAttribute("page", page);
		return "modules/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplitList";
	}

	@RequiresPermissions("ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit, Model model) {
		model.addAttribute("bizOrderTaskpackagePaymentDetailSplit", bizOrderTaskpackagePaymentDetailSplit);
		return "modules/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplitForm";
	}

	@RequiresPermissions("ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePaymentDetailSplit)){
			return form(bizOrderTaskpackagePaymentDetailSplit, model);
		}
		bizOrderTaskpackagePaymentDetailSplitService.save(bizOrderTaskpackagePaymentDetailSplit);
		addMessage(redirectAttributes, "保存付款单明细拆分成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit/?repage";
	}
	
	@RequiresPermissions("ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentDetailSplitService.delete(bizOrderTaskpackagePaymentDetailSplit);
		addMessage(redirectAttributes, "删除付款单明细拆分成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit/?repage";
	}

}