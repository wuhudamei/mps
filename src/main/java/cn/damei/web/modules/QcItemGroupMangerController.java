
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
import cn.damei.entity.modules.QcItemGroupManger;
import cn.damei.service.modules.QcItemGroupMangerService;


@Controller
@RequestMapping(value = "${adminPath}/qualitygroupmanger/qcItemGroupManger")
public class QcItemGroupMangerController extends BaseController {

	@Autowired
	private QcItemGroupMangerService qcItemGroupMangerService;
	
	@ModelAttribute
	public QcItemGroupManger get(@RequestParam(required=false) String id) {
		QcItemGroupManger entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qcItemGroupMangerService.get(id);
		}
		if (entity == null){
			entity = new QcItemGroupManger();
		}
		return entity;
	}


	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = {"list", ""})
	public String list(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/qualitygroupmanger/qcItemGroupMangerList";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/listall")
	public String listall(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.findPage(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		return "modules/qualitygroupmanger/qcItemGroupMangerList";
	}


	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "listManager")
	public String listManager(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/qualitygroupmanger/qcItemMangerList";
	}


	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/listManagerall")
	public String listManagerall(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.findManagerPage(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		return "modules/qualitygroupmanger/qcItemMangerList";
	}


	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/queryQcItemMangerIllegalDetails")
	public String queryQcItemMangerIllegalDetails(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.queryQcItemMangerIllegalDetails(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		model.addAttribute("qcItemGroupManger", qcItemGroupManger);
		return "modules/qualitygroupmanger/qcItemMangerIllegalDetails";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/queryQcItemMangerPunishDetails")
	public String queryQcItemMangerPunishDetails(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.queryQcItemMangerPunishDetails(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		model.addAttribute("qcItemGroupManger", qcItemGroupManger);
		return "modules/qualitygroupmanger/qcItemMangerPunishDetails";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/queryQcItemGroupIllegalDetails")
	public String queryQcItemGroupIllegalDetails(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.queryQcItemGroupIllegalDetails(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		model.addAttribute("qcItemGroupManger", qcItemGroupManger);
		return "modules/qualitygroupmanger/qcItemGroupIllegalDetails";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "/queryQcItemGroupPunishDetails")
	public String queryQcItemGroupPunishDetails(QcItemGroupManger qcItemGroupManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QcItemGroupManger> page = qcItemGroupMangerService.queryQcItemGroupPunishDetails(new Page<QcItemGroupManger>(request, response), qcItemGroupManger);
		model.addAttribute("page", page);
		model.addAttribute("qcItemGroupManger", qcItemGroupManger);
		return "modules/qualitygroupmanger/qcItemGroupPunishDetails";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:view")
	@RequestMapping(value = "form")
	public String form(QcItemGroupManger qcItemGroupManger, Model model) {
		model.addAttribute("qcItemGroupManger", qcItemGroupManger);
		return "modules/qualitygroupmanger/qcItemGroupMangerForm";
	}

	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:edit")
	@RequestMapping(value = "save")
	public String save(QcItemGroupManger qcItemGroupManger, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qcItemGroupManger)){
			return form(qcItemGroupManger, model);
		}
		qcItemGroupMangerService.save(qcItemGroupManger);
		addMessage(redirectAttributes, "保存检查项工人组和项目经理成功");
		return "redirect:"+Global.getAdminPath()+"/qualitygroupmanger/qcItemGroupManger/?repage";
	}
	
	@RequiresPermissions("qualitygroupmanger:qcItemGroupManger:edit")
	@RequestMapping(value = "delete")
	public String delete(QcItemGroupManger qcItemGroupManger, RedirectAttributes redirectAttributes) {
		qcItemGroupMangerService.delete(qcItemGroupManger);
		addMessage(redirectAttributes, "删除检查项工人组和项目经理成功");
		return "redirect:"+Global.getAdminPath()+"/qualitygroupmanger/qcItemGroupManger/?repage";
	}

}