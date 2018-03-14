/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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


/** 
* @ClassName: ProjectIntemController 
* @Description: 施工项
* @author zkj  
* @date 2017年11月6日 下午4:44:49 
* @version V1.0 
*/
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
	/** 
	* @Description: 施工项列表查询
	* @param @param projectIntem
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return
	* @author zkj 
	* @date 2017年11月3日 上午10:35:48 
	*/
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

	/** 
	* @Description: 保存施工项操作
	* @param @param projectIntem
	* @param @param model
	* @param @param redirectAttributes
	* @param @return
	* @author zkj 
	* @date 2017年11月3日 上午10:21:23 
	*/
	@RequiresPermissions("projectitem:projectIntem:edit")
	@RequestMapping(value = "save")
	public String save(ProjectIntem projectIntem, Model model, RedirectAttributes redirectAttributes) {
		//校验实体类
		/*if (!beanValidator(model, projectIntem)) {
			return form(projectIntem, model);
		}*/

		//生成编码
		if(StringUtils.isBlank(projectIntem.getId())){
			projectIntem.setProjectIntemCode(projectItemCode());
		}
		projectIntemService.save(projectIntem);
		//推送数据到订单流转系统ConstantUtils.BUSINESS_TYPE_702

//		boolean insertBizSynData = bizSynDateSendAndReceiveService.insertBizSynData("702", ConstantUtils.DATA_DIRECTION_2, ConstantUtils.SYN_STATUS_4);

		bizSynDateSendAndReceiveService.sendProjectItem(projectIntem);
//		logger.info("施工项推送数据到订单流转系统【时间："+new Date()+"状态："+insertBizSynData+"】");
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

	/**
	 * 施工项编号 SG加6位流水号
	 * 
	 * @return
	 */
	public String projectItemCode() {
		// 以ZJ开头

		StringBuilder builder = new StringBuilder();
		// num
		ReCheckCode code1 = projectIntemService.getCode();
		if (null == code1) {
			projectIntemService.saveCode();
			code1 = projectIntemService.getCode();
		}
		builder.append(code1.getBussinessType());
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
		projectIntemService.updateCode(code1);
		String code = String.valueOf(code1.getLastSeiralnum());
		// 判断长度
		if (code.length() == 1) {

			builder.append("00000").append(code);

		} else if (code.length() == 2) {
			// 拼接施工项编号
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
		// 返回施工项编号
		return builder.toString();

	}

}