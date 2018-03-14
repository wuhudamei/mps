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
import cn.damei.entity.modules.BizMainMaterials;
import cn.damei.service.modules.BizMainMaterialsService;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.common.utils.UserUtils;

/**
 * 主材管理Controller
 * @author qww
 * @version 2016-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/mainmaterials/bizMainMaterials")
public class BizMainMaterialsController extends BaseController {

	@Autowired
	private BizMainMaterialsService bizMainMaterialsService;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizMainMaterials get(@RequestParam(required=false) Integer id) {
		BizMainMaterials entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMainMaterialsService.get(id);
		}
		if (entity == null){
			entity = new BizMainMaterials();
		}
		return entity;
	}
	
	@RequiresPermissions("mainmaterials:bizMainMaterials:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMainMaterials bizMainMaterials, Model model) {
		//过滤门店
		if(bizMainMaterials.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizMainMaterials.setStoreId(null);
			}else{
				bizMainMaterials.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/mainmaterials/bizMainMaterialsList";
	}

	@RequiresPermissions("mainmaterials:bizMainMaterials:view")
	@RequestMapping(value = {"mainMaterialsList", ""})
	public String mainMaterialsList(BizMainMaterials bizMainMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(bizMainMaterials.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizMainMaterials.setStoreId(null);
			}else{
				bizMainMaterials.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizMainMaterials> page = bizMainMaterialsService.findPage(new Page<BizMainMaterials>(request, response), bizMainMaterials);
		model.addAttribute("page", page);
		return "modules/mainmaterials/bizMainMaterialsList";
	}

	@RequiresPermissions("mainmaterials:bizMainMaterials:view")
	@RequestMapping(value = "form")
	public String form(BizMainMaterials bizMainMaterials, Model model) {
		if(bizMainMaterials.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizMainMaterials.setStoreId(null);
			}else{
				bizMainMaterials.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		model.addAttribute("bizMainMaterials", bizMainMaterials);
		return "modules/mainmaterials/bizMainMaterialsForm";
	}

	@RequiresPermissions("mainmaterials:bizMainMaterials:edit")
	@RequestMapping(value = "save")
	public String save(BizMainMaterials bizMainMaterials, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMainMaterials)){
			return form(bizMainMaterials, model);
		}
		
		if (bizMainMaterials.getId() == null) {
			bizMainMaterials.setIsNewRecord(true);
			bizMainMaterials.setMainMaterialsNo(sysSequenceService.getSequence("ZC"));
        }

		bizMainMaterialsService.save(bizMainMaterials);
		addMessage(redirectAttributes, "保存主材管理成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterials/bizMainMaterials/mainMaterialsList?repage";
	}
	
	@RequiresPermissions("mainmaterials:bizMainMaterials:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMainMaterials bizMainMaterials, RedirectAttributes redirectAttributes) {
		bizMainMaterialsService.delete(bizMainMaterials);
		addMessage(redirectAttributes, "删除主材管理成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterials/bizMainMaterials/mainMaterialsList?repage";
	}

	@RequiresPermissions("mainmaterials:bizMainMaterials:edit")
	@RequestMapping(value = "enable")
	public String enable(BizMainMaterials bizMainMaterials, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizMainMaterials.getStatus());
		bizMainMaterials.setStatus(status+"");
		// 修改启用/停用状态
		bizMainMaterialsService.save(bizMainMaterials);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterials/bizMainMaterials/mainMaterialsList?repage";
	}
}