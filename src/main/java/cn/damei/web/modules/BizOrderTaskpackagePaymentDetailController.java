
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
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailService;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail")
public class BizOrderTaskpackagePaymentDetailController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentDetailService bizOrderTaskpackagePaymentDetailService;
	
	@ModelAttribute
	public BizOrderTaskpackagePaymentDetail get(@RequestParam(required=false) Integer id) {
		BizOrderTaskpackagePaymentDetail entity = null;
		if (id != null){
			entity = bizOrderTaskpackagePaymentDetailService.get(id);
		}
		if (entity == null){
			entity = new BizOrderTaskpackagePaymentDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderTaskpackagePaymentDetail> page = bizOrderTaskpackagePaymentDetailService.findPage(new Page<BizOrderTaskpackagePaymentDetail>(request, response), bizOrderTaskpackagePaymentDetail); 
		model.addAttribute("page", page);
		return "modules/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetailList";
	}

	@RequiresPermissions("ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail, Model model) {
		model.addAttribute("bizOrderTaskpackagePaymentDetail", bizOrderTaskpackagePaymentDetail);
		return "modules/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetailForm";
	}

	@RequiresPermissions("ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePaymentDetail)){
			return form(bizOrderTaskpackagePaymentDetail, model);
		}
		bizOrderTaskpackagePaymentDetailService.save(bizOrderTaskpackagePaymentDetail);
		addMessage(redirectAttributes, "保存付款单明细成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail/?repage";
	}
	
	@RequiresPermissions("ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentDetailService.delete(bizOrderTaskpackagePaymentDetail);
		addMessage(redirectAttributes, "删除付款单明细成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail/?repage";
	}

}