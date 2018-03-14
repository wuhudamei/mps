
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
import cn.damei.entity.modules.SysSequence;
import cn.damei.service.modules.SysSequenceService;


@Controller
@RequestMapping(value = "${adminPath}/sequence/sysSequence")
public class SysSequenceController extends BaseController {

	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public SysSequence get(@RequestParam(required=false) String id) {
		SysSequence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysSequenceService.get(id);
		}
		if (entity == null){
			entity = new SysSequence();
		}
		return entity;
	}
	
	@RequiresPermissions("sequence:sysSequence:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysSequence sysSequence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysSequence> page = sysSequenceService.findPage(new Page<SysSequence>(request, response), sysSequence); 
		model.addAttribute("page", page);
		return "modules/sequence/sysSequenceList";
	}

	@RequiresPermissions("sequence:sysSequence:view")
	@RequestMapping(value = "form")
	public String form(SysSequence sysSequence, Model model) {
		model.addAttribute("sysSequence", sysSequence);
		return "modules/sequence/sysSequenceForm";
	}

	@RequiresPermissions("sequence:sysSequence:edit")
	@RequestMapping(value = "save")
	public String save(SysSequence sysSequence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysSequence)){
			return form(sysSequence, model);
		}
		sysSequenceService.save(sysSequence);
		addMessage(redirectAttributes, "保存编号成功");
		return "redirect:"+Global.getAdminPath()+"/sequence/sysSequence/?repage";
	}
	
	@RequiresPermissions("sequence:sysSequence:edit")
	@RequestMapping(value = "delete")
	public String delete(SysSequence sysSequence, RedirectAttributes redirectAttributes) {
		sysSequenceService.delete(sysSequence);
		addMessage(redirectAttributes, "删除编号成功");
		return "redirect:"+Global.getAdminPath()+"/sequence/sysSequence/?repage";
	}

}