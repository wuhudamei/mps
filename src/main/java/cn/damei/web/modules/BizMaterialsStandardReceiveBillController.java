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
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.service.modules.BizMaterialsStandardReceiveBillService;
import cn.damei.service.modules.BizMaterialsStandardReceiveBillVoService;

/**
 * 标化辅材记录Controller
 * @author 汪文文
 * @version 2016-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/standradmaterialsrecords/bizMaterialsStandardReceiveBill")
public class BizMaterialsStandardReceiveBillController extends BaseController {

	@Autowired
	private BizMaterialsStandardReceiveBillService bizMaterialsStandardReceiveBillService;
	@Autowired
	private BizMaterialsStandardReceiveBillVoService bizMaterialsStandardReceiveBillVoService;
	
	@ModelAttribute
	public BizMaterialsStandardReceiveBill get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardReceiveBill entity = null;
		if (id != null){
			entity = bizMaterialsStandardReceiveBillService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandardReceiveBill();
		}
		return entity;
	}
	
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:view")
	@RequestMapping(value = "list1")
	public String list(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsStandardReceiveBill> page = bizMaterialsStandardReceiveBillService.findPage(new Page<BizMaterialsStandardReceiveBill>(request, response), bizMaterialsStandardReceiveBill); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillList";
	}

	/*@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsStandardReceiveBillVo> page = bizMaterialsStandardReceiveBillVoService.findPage(new Page<BizMaterialsStandardReceiveBillVo>(request, response), bizMaterialsStandardReceiveBill); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillList";
	}*/
	
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill, Model model) {
		model.addAttribute("bizMaterialsStandardReceiveBill", bizMaterialsStandardReceiveBill);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillForm";
	}

	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandardReceiveBill)){
			return form(bizMaterialsStandardReceiveBill, model);
		}
		bizMaterialsStandardReceiveBillService.save(bizMaterialsStandardReceiveBill);
		addMessage(redirectAttributes, "保存标化辅材记录成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsrecords/bizMaterialsStandardReceiveBill/?repage";
	}
	
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardReceiveBillService.delete(bizMaterialsStandardReceiveBill);
		addMessage(redirectAttributes, "删除标化辅材记录成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsrecords/bizMaterialsStandardReceiveBill/?repage";
	}

}