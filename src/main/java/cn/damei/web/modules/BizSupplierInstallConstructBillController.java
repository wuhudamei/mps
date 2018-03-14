
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
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.service.modules.BizSupplierInstallConstructBillService;


@Controller
@RequestMapping(value = "${adminPath}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill")
public class BizSupplierInstallConstructBillController extends BaseController {

	@Autowired
	private BizSupplierInstallConstructBillService bizSupplierInstallConstructBillService;
	
	@ModelAttribute
	public BizSupplierInstallConstructBill get(@RequestParam(required=false) Integer id) {
		BizSupplierInstallConstructBill entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizSupplierInstallConstructBillService.get(id);
		}
		if (entity == null){
			entity = new BizSupplierInstallConstructBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSupplierInstallConstructBill bizSupplierInstallConstructBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSupplierInstallConstructBill> page = bizSupplierInstallConstructBillService.findPage(new Page<BizSupplierInstallConstructBill>(request, response), bizSupplierInstallConstructBill); 
		model.addAttribute("page", page);
		return "modules/bizsupplierinstallcontructbill/bizSupplierInstallConstructBillList";
	}

	@RequiresPermissions("bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:view")
	@RequestMapping(value = "form")
	public String form(BizSupplierInstallConstructBill bizSupplierInstallConstructBill, Model model) {
		model.addAttribute("bizSupplierInstallConstructBill", bizSupplierInstallConstructBill);
		return "modules/bizsupplierinstallcontructbill/bizSupplierInstallConstructBillForm";
	}

	@RequiresPermissions("bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:edit")
	@RequestMapping(value = "save")
	public String save(BizSupplierInstallConstructBill bizSupplierInstallConstructBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSupplierInstallConstructBill)){
			return form(bizSupplierInstallConstructBill, model);
		}
		bizSupplierInstallConstructBillService.save(bizSupplierInstallConstructBill);
		addMessage(redirectAttributes, "保存供应商安装施工单表成功");
		return "redirect:"+Global.getAdminPath()+"/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/?repage";
	}
	
	@RequiresPermissions("bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSupplierInstallConstructBill bizSupplierInstallConstructBill, RedirectAttributes redirectAttributes) {
		bizSupplierInstallConstructBillService.delete(bizSupplierInstallConstructBill);
		addMessage(redirectAttributes, "删除供应商安装施工单表成功");
		return "redirect:"+Global.getAdminPath()+"/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/?repage";
	}

}