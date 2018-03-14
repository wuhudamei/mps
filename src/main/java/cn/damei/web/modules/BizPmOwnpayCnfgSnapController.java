
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
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.service.modules.BizPmOwnpayCnfgSnapService;


@Controller
@RequestMapping(value = "${adminPath}/ownpaysnap/bizPmOwnpayCnfgSnap")
public class BizPmOwnpayCnfgSnapController extends BaseController {

	@Autowired
	private BizPmOwnpayCnfgSnapService bizPmOwnpayCnfgSnapService;
	
	@ModelAttribute
	public BizPmOwnpayCnfgSnap get(@RequestParam(required=false) Integer id) {
		BizPmOwnpayCnfgSnap entity = null;
		if (id != null){
			entity = bizPmOwnpayCnfgSnapService.get(id);
		}
		if (entity == null){
			entity = new BizPmOwnpayCnfgSnap();
		}
		return entity;
	}
	
	@RequiresPermissions("ownpaysnap:bizPmOwnpayCnfgSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmOwnpayCnfgSnap> page = bizPmOwnpayCnfgSnapService.findPage(new Page<BizPmOwnpayCnfgSnap>(request, response), bizPmOwnpayCnfgSnap); 
		model.addAttribute("page", page);
		return "modules/pmownpaysnap/bizPmOwnpayCnfgSnapList";
	}

	@RequiresPermissions("ownpaysnap:bizPmOwnpayCnfgSnap:view")
	@RequestMapping(value = "form")
	public String form(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap, Model model) {
		model.addAttribute("bizPmOwnpayCnfgSnap", bizPmOwnpayCnfgSnap);
		return "modules/pmownpaysnap/bizPmOwnpayCnfgSnapForm";
	}

	@RequiresPermissions("ownpaysnap:bizPmOwnpayCnfgSnap:edit")
	@RequestMapping(value = "save")
	public String save(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmOwnpayCnfgSnap)){
			return form(bizPmOwnpayCnfgSnap, model);
		}
		bizPmOwnpayCnfgSnapService.save(bizPmOwnpayCnfgSnap);
		addMessage(redirectAttributes, "保存自主支配快照成功");
		return "redirect:"+Global.getAdminPath()+"/ownpaysnap/bizPmOwnpayCnfgSnap/?repage";
	}
	
	@RequiresPermissions("ownpaysnap:bizPmOwnpayCnfgSnap:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap, RedirectAttributes redirectAttributes) {
		bizPmOwnpayCnfgSnapService.delete(bizPmOwnpayCnfgSnap);
		addMessage(redirectAttributes, "删除自主支配快照成功");
		return "redirect:"+Global.getAdminPath()+"/ownpaysnap/bizPmOwnpayCnfgSnap/?repage";
	}

}