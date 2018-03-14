
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.service.modules.BizSynDateSendAndReceiveService;
import cn.damei.entity.modules.ProjectIntem;
import cn.damei.service.modules.ProjectIntemService;
import cn.damei.entity.modules.ProjectItemType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;



@Controller
@RequestMapping(value = "${adminPath}/projectitem/projectIntem")
public class ProjectIntemController extends BaseController {

	@Autowired
	private ProjectIntemService projectIntemService;
	@Autowired
	private BizSynDateSendAndReceiveService bizSynDateSendAndReceiveService;

	@ModelAttribute
	public ProjectIntem get(@RequestParam(required = false) String id) {
		ProjectIntem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = projectIntemService.get(id);
		}
		if (entity == null) {
			entity = new ProjectIntem();
		}
		return entity;
	}

	@RequiresPermissions("projectitem:projectIntem:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProjectIntem projectIntem, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
		return "modules/projectitem/projectIntemList";
	}

	@RequiresPermissions("projectitem:projectIntem:view")
	@RequestMapping(value = { "list2"})
	public String list2(ProjectIntem projectIntem, HttpServletRequest request, HttpServletResponse response,
                        Model model) {
		Page<ProjectIntem> page = projectIntemService.findPage(new Page<ProjectIntem>(request, response), projectIntem);
		List<ProjectItemType> list = projectIntemService.findProjectItemTypeList();
		model.addAttribute("projectItemTypeList", list);
		model.addAttribute("page", page);
		return "modules/projectitem/projectIntemList";
	}

	@RequiresPermissions("projectitem:projectIntem:view")
	@RequestMapping(value = "form")
	public String form(ProjectIntem projectIntem, Model model) {
		List<ProjectItemType> list = projectIntemService.findProjectItemTypeList();
		if(null!=projectIntem.getProjectItemId()){
		Iterator<ProjectItemType> iterator = list.iterator();
			while (iterator.hasNext()) {
				ProjectItemType projectItemType = iterator.next();
				if (projectIntem.getProjectIntemTypeId().equals(projectItemType.getProjectItemTypeId())) {
					iterator.remove();
				}
			}
		}
		model.addAttribute("projectItemTypeList", list);
		model.addAttribute("projectIntem", projectIntem);
		return "modules/projectitem/projectIntemForm";
	}


	@RequiresPermissions("projectitem:projectIntem:edit")
	@RequestMapping(value = "save")
	public String save(ProjectIntem projectIntem, Model model, RedirectAttributes redirectAttributes) {




		if(StringUtils.isBlank(projectIntem.getId())){
			projectIntem.setProjectIntemCode(projectItemCode());
		}
		projectIntemService.save(projectIntem);




		bizSynDateSendAndReceiveService.sendProjectItem(projectIntem);

		addMessage(redirectAttributes, "保存施工项成功");
		return "redirect:" + Global.getAdminPath() + "/projectitem/projectIntem/?repage";
	}

	@RequiresPermissions("projectitem:projectIntem:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectIntem projectIntem, RedirectAttributes redirectAttributes) {
		projectIntemService.delete(projectIntem);

		if (String.valueOf(projectIntem.getStatus()).equals("1")) {
			addMessage(redirectAttributes, "启用施工项成功");
		} else {
			addMessage(redirectAttributes, "停用施工项成功");
		}
		return "redirect:" + Global.getAdminPath() + "/projectitem/projectIntem/?repage";
	}


	public String projectItemCode() {


		StringBuilder builder = new StringBuilder();

		ReCheckCode code1 = projectIntemService.getCode();
		if (null == code1) {
			projectIntemService.saveCode();
			code1 = projectIntemService.getCode();
		}
		builder.append(code1.getBussinessType());
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
		projectIntemService.updateCode(code1);
		String code = String.valueOf(code1.getLastSeiralnum());

		if (code.length() == 1) {

			builder.append("00000").append(code);

		} else if (code.length() == 2) {

			builder.append("0000").append(code);
		} else if (code.length() == 3) {
			builder.append("000").append(code);
		} else if (code.length() == 4) {
			builder.append("00").append(code);
		} else if (code.length() == 5) {
			builder.append("0").append(code);
		} else {
			builder.append(code);
		}

		return builder.toString();

	}

}