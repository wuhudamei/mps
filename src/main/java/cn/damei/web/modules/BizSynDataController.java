
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
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;


@Controller
@RequestMapping(value = "${adminPath}/bizsyndata/bizSynData")
public class BizSynDataController extends BaseController {

	@Autowired
	private BizSynDataService bizSynDataService;
	
	@ModelAttribute
	public BizSynData get(@RequestParam(required=false) Integer id) {
		BizSynData entity = null;
		if (id != null){
			entity = bizSynDataService.get(id);
		}
		if (entity == null){
			entity = new BizSynData();
		}
		return entity;
	}
	
	@RequiresPermissions("bizsyndata:bizSynData:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSynData bizSynData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSynData> page = bizSynDataService.findPage(new Page<BizSynData>(request, response), bizSynData); 
		model.addAttribute("page", page);
		return "modules/bizsyndata/bizSynDataList";
	}

	@RequiresPermissions("bizsyndata:bizSynData:view")
	@RequestMapping(value = "form")
	public String form(BizSynData bizSynData, Model model) {
		model.addAttribute("bizSynData", bizSynData);
		return "modules/bizsyndata/bizSynDataForm";
	}

	@RequiresPermissions("bizsyndata:bizSynData:edit")
	@RequestMapping(value = "save")
	public String save(BizSynData bizSynData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSynData)){
			return form(bizSynData, model);
		}
		bizSynDataService.save(bizSynData);
		addMessage(redirectAttributes, "保存同步数据成功");
		return "redirect:"+Global.getAdminPath()+"/bizsyndata/bizSynData/?repage";
	}
	
	@RequiresPermissions("bizsyndata:bizSynData:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSynData bizSynData, RedirectAttributes redirectAttributes) {
		bizSynDataService.delete(bizSynData);
		addMessage(redirectAttributes, "删除同步数据成功");
		return "redirect:"+Global.getAdminPath()+"/bizsyndata/bizSynData/?repage";
	}
	
	
	

}