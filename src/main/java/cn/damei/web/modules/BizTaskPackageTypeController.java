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
import cn.damei.common.utils.DictUtils;
import cn.damei.entity.modules.BizTaskPackageType;
import cn.damei.service.modules.BizTaskPackageTypeService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2016-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/taskpackage/bizTaskPackageType")
public class BizTaskPackageTypeController extends BaseController {

	@Autowired
	private BizTaskPackageTypeService bizTaskPackageTypeService;
	
	@ModelAttribute
	public BizTaskPackageType get(@RequestParam(required=false) String id) {
		BizTaskPackageType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTaskPackageTypeService.get(id);
		}
		if (entity == null){
			entity = new BizTaskPackageType();
		}
		return entity;
	}
	
	@RequiresPermissions("taskpackage:bizTaskPackageType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTaskPackageType bizTaskPackageType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTaskPackageType> page = bizTaskPackageTypeService.findPage(new Page<BizTaskPackageType>(request, response), bizTaskPackageType); 
		model.addAttribute("page", page);
		return "modules/taskpackage/bizTaskPackageTypeList";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageType:view")
	@RequestMapping(value = "form")
	public String form(BizTaskPackageType bizTaskPackageType, Model model) {
		if(StringUtils.isBlank(bizTaskPackageType.getStatus())){
			bizTaskPackageType.setStatus("1");
		}
		model.addAttribute("bizTaskPackageType", bizTaskPackageType);
		return "modules/taskpackage/bizTaskPackageTypeForm";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageType:edit")
	@RequestMapping(value = "save")
	public String save(BizTaskPackageType bizTaskPackageType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTaskPackageType)){
			return form(bizTaskPackageType, model);
		}
		bizTaskPackageTypeService.save(bizTaskPackageType);
		addMessage(redirectAttributes, "保存任务包模板成功");
		return "redirect:"+Global.getAdminPath()+"/taskpackage/bizTaskPackageType/?repage";
	}
	
	@RequiresPermissions("taskpackage:bizTaskPackageType:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTaskPackageType bizTaskPackageType, RedirectAttributes redirectAttributes) {
		bizTaskPackageTypeService.delete(bizTaskPackageType);
		addMessage(redirectAttributes, "删除任务包模板成功");
		return "redirect:"+Global.getAdminPath()+"/taskpackage/bizTaskPackageType/?repage";
	}
	
	/**
	 * 停用/启用
	 * @param bizTaskPackageType
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taskpackage:bizTaskPackageType:edit")
	@RequestMapping(value = "enable")
	public String enable(BizTaskPackageType bizTaskPackageType, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizTaskPackageType.getStatus());
		bizTaskPackageType.setStatus(status+"");
		bizTaskPackageTypeService.save(bizTaskPackageType);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status+"", "biz_enable_status", "")+"任务包模板成功");
		return "redirect:"+Global.getAdminPath()+"/taskpackage/bizTaskPackageType/?repage";
	}

}