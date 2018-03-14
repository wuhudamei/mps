/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.service.modules.BizSynDateSendAndReceiveService;
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
import cn.damei.entity.modules.ProjectItemType;
import cn.damei.service.modules.ProjectItemTypeService;
import cn.damei.common.utils.UserUtils;

/**
* @Description: 施工项类型
* @Author zhangkangjian
* @param
* @return
* @Date 2017/11/17 17:51
*/
@Controller
@RequestMapping(value = "${adminPath}/projectitemtype/projectItemType")
public class ProjectItemTypeController extends BaseController {

	@Autowired
	private ProjectItemTypeService projectItemTypeService;
	@Autowired
	private BizSynDateSendAndReceiveService bizSynDateSendAndReceiveService;
	
	@ModelAttribute
	public ProjectItemType get(@RequestParam(required=false) String id) {
		ProjectItemType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectItemTypeService.get(id);
		}
		if (entity == null){
			entity = new ProjectItemType();
		}
		return entity;
	}
	
	@RequiresPermissions("projectitemtype:projectItemType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectItemType projectItemType, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/projectitemtype/projectItemTypeList";
	}
	@RequiresPermissions("projectitemtype:projectItemType:view")
	@RequestMapping(value = {"list2"})
	public String list2(ProjectItemType projectItemType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectItemType> page = projectItemTypeService.findPage(new Page<ProjectItemType>(request, response), projectItemType); 
		model.addAttribute("page", page);
		return "modules/projectitemtype/projectItemTypeList";
	}

	@RequiresPermissions("projectitemtype:projectItemType:view")
	@RequestMapping(value = "form")
	public String form(ProjectItemType projectItemType, Model model) {
		model.addAttribute("projectItemType", projectItemType);
		return "modules/projectitemtype/projectItemTypeForm";
	}

	@RequiresPermissions("projectitemtype:projectItemType:edit")
	@RequestMapping(value = "save")
	public String save(ProjectItemType projectItemType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectItemType)){
			return form(projectItemType, model);
		}
		projectItemType.setUpdateBy(UserUtils.getUser());
		projectItemTypeService.save(projectItemType);
		bizSynDateSendAndReceiveService.sendProjectItemType(projectItemType);
		addMessage(redirectAttributes, "保存施工项成功");
		return "redirect:"+Global.getAdminPath()+"/projectitemtype/projectItemType/?repage";
	}
	
	@RequiresPermissions("projectitemtype:projectItemType:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectItemType projectItemType, RedirectAttributes redirectAttributes) {
		projectItemTypeService.delete(projectItemType);
		
		if(projectItemType.getStatus().equals("1")){
			addMessage(redirectAttributes, "启用施工项成功");
		}else{
			addMessage(redirectAttributes, "停用施工项成功");
		}
	
		return "redirect:"+Global.getAdminPath()+"/projectitemtype/projectItemType/?repage";
	}

}