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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsStandard;
import cn.damei.service.modules.BizMaterialsStandardService;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 标化辅材Controller
 * @author 汪文文
 * @version 2016-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/standradmaterials/bizMaterialsStandard")
public class BizMaterialsStandardController extends BaseController {

	@Autowired
	private BizMaterialsStandardService bizMaterialsStandardService;
	
	@ModelAttribute
	public BizMaterialsStandard get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandard entity = null;
		if (id != null){
			entity = bizMaterialsStandardService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandard();
		}
		return entity;
	}
	@RequiresPermissions("standradmaterials:bizMaterialsStandard:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizMaterialsStandard bizMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null){
			//当前登录用户门店
			bizMaterialsStandard.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}else{
			//门店是总部的查询所有部门信息
			if(bizMaterialsStandard.getStoreId()!=null && bizMaterialsStandard.getStoreId().equals(1)){
				//总部
				bizMaterialsStandard.setStoreId(null);
			}
		}
		return "modules/managerSettlement/standradmaterials/bizMaterialsStandardList";
	}
	@RequiresPermissions("standradmaterials:bizMaterialsStandard:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandard bizMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null){
			//当前登录用户门店
			bizMaterialsStandard.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}else{
			//门店是总部的查询所有部门信息
			if(bizMaterialsStandard.getStoreId()!=null && bizMaterialsStandard.getStoreId().equals(1)){
				//总部
				bizMaterialsStandard.setStoreId(null);
			}
		}
		//設置标化
		bizMaterialsStandard.setMaterialsLargeType("1");
		Page<BizMaterialsStandard> page = bizMaterialsStandardService.findPage(new Page<BizMaterialsStandard>(request, response), bizMaterialsStandard); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterials/bizMaterialsStandardList";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsStandard:edit")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandard bizMaterialsStandard, Model model) {
		if(null == bizMaterialsStandard.getIsEnabled()){
			bizMaterialsStandard.setIsEnabled("1");
		}
		model.addAttribute("bizMaterialsStandard", bizMaterialsStandard);
		return "modules/managerSettlement/standradmaterials/bizMaterialsStandardForm";
	}

	@RequiresPermissions("standradmaterials:bizMaterialsStandard:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandard bizMaterialsStandard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandard)){
			return form(bizMaterialsStandard, model);
		}
		bizMaterialsStandard.setMaterialsLargeType("1");
		bizMaterialsStandardService.save(bizMaterialsStandard);
		addMessage(redirectAttributes, "保存标化辅材成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsStandard/?repage";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsStandard:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandard bizMaterialsStandard, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardService.delete(bizMaterialsStandard);
		addMessage(redirectAttributes, "删除标化辅材成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsStandard/?repage";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsStandard:edit")
	@RequestMapping(value = "enable")
	public String enable(BizMaterialsStandard bizMaterialsStandard , RedirectAttributes redirectAttributes) {
		Integer isEnable = 1 ^ Integer.parseInt(bizMaterialsStandard.getIsEnabled());
		bizMaterialsStandard.setIsEnabled(isEnable.toString());
		bizMaterialsStandardService.update(bizMaterialsStandard);
		addMessage(redirectAttributes, DictUtils.getDictLabel(isEnable+"", "biz_enable_status", "")+"标化辅材成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsStandard/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "getBizMaterialsStandard")
	public String getBizMaterialsStandard(String materialId){
		BizMaterialsStandard bizMaterialsStandard = bizMaterialsStandardService.get(Integer.parseInt(materialId));
		String a =  JsonMapper.getInstance().toJson(bizMaterialsStandard);
		return a;
	}
}