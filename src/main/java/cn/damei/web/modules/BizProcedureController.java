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
import cn.damei.entity.modules.BizProcedure;
import cn.damei.service.modules.BizProcedureService;
import cn.damei.service.modules.SysSequenceService;

/**
 * 工序管理Controller
 * @author 魏建勇
 * @version 2016-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/procedure/bizProcedure")
public class BizProcedureController extends BaseController {

	@Autowired
	private BizProcedureService bizProcedureService;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizProcedure get(@RequestParam(required=false) String id) {
		BizProcedure entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProcedureService.get(id);
		}
		if (entity == null){
			entity = new BizProcedure();
		}
		return entity;
	}
	
	@RequiresPermissions("procedure:bizProcedure:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProcedure bizProcedure, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizProcedure> page = bizProcedureService.findPage(new Page<BizProcedure>(request, response), bizProcedure); 
		model.addAttribute("page", page);
		return "modules/procedure/bizProcedureList";
	}

	@RequiresPermissions("procedure:bizProcedure:view") 
	@RequestMapping(value = "form")
	public String form(BizProcedure bizProcedure, Model model) {
	    if(bizProcedure !=null && !(bizProcedure.getId() != null && !"".equals(bizProcedure.getId()))){
	        bizProcedure.setIsOtherFlag("0");
	        bizProcedure.setIsEnable("1");
	    }
		model.addAttribute("bizProcedure", bizProcedure);
		return "modules/procedure/bizProcedureForm";
	}

	@RequiresPermissions("procedure:bizProcedure:edit")
	@RequestMapping(value = "save")
	public String save(BizProcedure bizProcedure, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProcedure)){
			return form(bizProcedure, model);
		}
		if(bizProcedure.getId() == null || "".equals(bizProcedure.getId())){
		    bizProcedure.setProcedureNo(sysSequenceService.getSequence("GX"));   
		    bizProcedureService.save(bizProcedure);
		    return "redirect:"+Global.getAdminPath()+"/procedureprice/bizProcedurePrice/?procedureNo="+bizProcedure.getProcedureNo()+"&repage";
        }
		bizProcedureService.save(bizProcedure);
		addMessage(redirectAttributes, "保存工序成功");
		return "redirect:"+Global.getAdminPath()+"/procedure/bizProcedure/?repage";
	}
	
	@RequiresPermissions("procedure:bizProcedure:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProcedure bizProcedure, RedirectAttributes redirectAttributes) {
		bizProcedureService.delete(bizProcedure);
		addMessage(redirectAttributes, "停用工序成功");
		return "redirect:"+Global.getAdminPath()+"/procedure/bizProcedure/?repage";
	}

}