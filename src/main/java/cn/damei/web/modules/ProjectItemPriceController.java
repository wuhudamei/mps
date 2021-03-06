
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
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizSynDateSendAndReceiveService;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.service.modules.ProjectItemPriceService;


@Controller
@RequestMapping(value = "${adminPath}/projectitemprice/projectItemPrice")
public class ProjectItemPriceController extends BaseController {

	@Autowired
	private ProjectItemPriceService projectItemPriceService;
	@Autowired
	private BizSynDateSendAndReceiveService bizSynDateSendAndReceiveService;
	
	@ModelAttribute
	public ProjectItemPrice get(@RequestParam(required=false) String id) {
		ProjectItemPrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectItemPriceService.get(id);
		}
		if (entity == null){
			entity = new ProjectItemPrice();
		}
		return entity;
	}
	

	@RequiresPermissions("projectitemprice:projectItemPrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectItemPrice projectItemPrice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectItemPrice> page = projectItemPriceService.findPage(new Page<ProjectItemPrice>(request, response), projectItemPrice); 
		model.addAttribute("page", page);
		model.addAttribute("itemId", projectItemPrice.getProjectIntemId());
		return "modules/projectitemprice/projectItemPriceList";
	}


	@RequiresPermissions("projectitemprice:projectItemPrice:view")
	@RequestMapping(value = "form")
	public String form(ProjectItemPrice projectItemPrice, Model model) {
		model.addAttribute("projectItemPrice", projectItemPrice);
		return "modules/projectitemprice/projectItemPriceForm";
	}


	@RequiresPermissions("projectitemprice:projectItemPrice:edit")
	@RequestMapping(value = "save")
	public String save(ProjectItemPrice projectItemPrice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectItemPrice)){
			return form(projectItemPrice, model);
		}
		projectItemPriceService.save(projectItemPrice);


		bizSynDateSendAndReceiveService.sendProjectItemPrice(projectItemPrice);
		addMessage(redirectAttributes, "保存施工项价格成功");
		return "redirect:"+Global.getAdminPath()+"/projectitemprice/projectItemPrice/?repage&projectIntemId="+projectItemPrice.getProjectIntemId();
	}


	@RequestMapping(value="/checkedData")
	@ResponseBody
	public boolean checkedData(ProjectItemPrice projectItemPrice, Model model, RedirectAttributes redirectAttributes){
		return projectItemPriceService.checkedData(projectItemPrice);
	}
	
	@RequiresPermissions("projectitemprice:projectItemPrice:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectItemPrice projectItemPrice, RedirectAttributes redirectAttributes) {
		projectItemPriceService.delete(projectItemPrice);
		addMessage(redirectAttributes, "删除施工项价格成功");
		return "redirect:"+Global.getAdminPath()+"/projectitemprice/projectItemPrice/?repage&projectIntemId="+projectItemPrice.getProjectIntemId();
	}
	
	
	@RequestMapping(value="findMaxVersion")
	public @ResponseBody  String findMaxVersion(String storeId,String projectItemId){
		ProjectItemPrice price = new ProjectItemPrice();
		
		price.setStoreId(Integer.parseInt(storeId));
		price.setProjectIntemId(Integer.parseInt(projectItemId));
			
		Integer maxVersion = projectItemPriceService.setMaxVersion(price);
		if(null==maxVersion ||maxVersion<1){
		return "1";
			
		}else{
		return String.valueOf(maxVersion+1);
			
		}
		
		
		
	}
	
	

}