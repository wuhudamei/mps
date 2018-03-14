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
import cn.damei.entity.modules.BizMaterialCategory;
import cn.damei.service.modules.BizMaterialCategoryService;
import cn.damei.service.modules.SysSequenceService;
/*import cn.damei.entity.modules.BizSupplier;*/
import cn.damei.common.utils.DictUtils;

/**
 * 材料类别管理Controller
 * @author lc
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/materialcategory/bizMaterialCategory")
public class BizMaterialCategoryController extends BaseController {

	@Autowired
	private BizMaterialCategoryService bizMaterialCategoryService;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizMaterialCategory get(@RequestParam(required=false) String id) {
		BizMaterialCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizMaterialCategoryService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("materialcategory:bizMaterialCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialCategory bizMaterialCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialCategory> page = bizMaterialCategoryService.findPage(new Page<BizMaterialCategory>(request, response), bizMaterialCategory); 
		model.addAttribute("page", page);
		return "modules/materialcategory/bizMaterialCategoryList";
	}

	@RequiresPermissions("materialcategory:bizMaterialCategory:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialCategory bizMaterialCategory, Model model) {
		model.addAttribute("bizMaterialCategory", bizMaterialCategory);
		return "modules/materialcategory/bizMaterialCategoryForm";
	}

	@RequiresPermissions("materialcategory:bizMaterialCategory:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialCategory bizMaterialCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialCategory)){
			return form(bizMaterialCategory, model);
		}
		if(bizMaterialCategory.getId() == null || "".equals(bizMaterialCategory.getId())){
			bizMaterialCategory.setCategoryNo(sysSequenceService.getSequence("CL"));    
        }
		bizMaterialCategoryService.save(bizMaterialCategory);
		addMessage(redirectAttributes, "保存材料类别成功");
		return "redirect:"+Global.getAdminPath()+"/materialcategory/bizMaterialCategory/?repage";
	}
	
	@RequiresPermissions("materialcategory:bizMaterialCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialCategory bizMaterialCategory, RedirectAttributes redirectAttributes) {
		bizMaterialCategoryService.delete(bizMaterialCategory);
		addMessage(redirectAttributes, "删除材料类别成功");
		return "redirect:"+Global.getAdminPath()+"/materialcategory/bizMaterialCategory/?repage";
	}
	

	/**
	 * 停用/启用
	 * @param bizTaskPackageType
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("materialcategory:bizMaterialCategory:edit")
	@RequestMapping(value = "enable")
	public String enable(BizMaterialCategory bizMaterialCategory, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizMaterialCategory.getStatus());
		bizMaterialCategory.setStatus(status+"");
		bizMaterialCategoryService.save(bizMaterialCategory);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status+"", "biz_enable_status", "")+"操作成功");
		return "redirect:"+Global.getAdminPath()+"/materialcategory/bizMaterialCategory/??repage";
	}

}