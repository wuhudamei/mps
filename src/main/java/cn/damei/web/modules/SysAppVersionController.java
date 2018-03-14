
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.StringUtils;
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
import cn.damei.entity.modules.SysAppVersion;
import cn.damei.service.modules.SysAppVersionService;


@Controller
@RequestMapping(value = "${adminPath}/sysappversion/sysAppVersion")
public class SysAppVersionController extends BaseController {

	@Autowired
	private SysAppVersionService sysAppVersionService;
	
	@ModelAttribute
	public SysAppVersion get(@RequestParam(required=false) Integer id) {
		SysAppVersion entity = null;
		if (id != null){
			entity = sysAppVersionService.get(id);
		}
		if (entity == null){
			entity = new SysAppVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("sysappversion:sysAppVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysAppVersion sysAppVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysAppVersion> page = sysAppVersionService.findPage(new Page<SysAppVersion>(request, response), sysAppVersion); 
		model.addAttribute("page", page);
		model.addAttribute("sysAppVersion", sysAppVersion);
		return "modules/sysappversion/sysAppVersionList";
	}

	@RequiresPermissions("sysappversion:sysAppVersion:view")
	@RequestMapping(value = "form")
	public String form(SysAppVersion sysAppVersion, Model model) {
		model.addAttribute("sysAppVersion", sysAppVersion);
		return "modules/sysappversion/sysAppVersionForm";
	}

	@RequiresPermissions("sysappversion:sysAppVersion:edit")
	@RequestMapping(value = "save")
	public String save(SysAppVersion sysAppVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAppVersion)){
			return form(sysAppVersion, model);
		}

		sysAppVersionService.save(sysAppVersion);
		addMessage(redirectAttributes, "保存手机app版本成功");
		return "redirect:"+Global.getAdminPath()+"/sysappversion/sysAppVersion/?repage";
	}
	
	@RequiresPermissions("sysappversion:sysAppVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(SysAppVersion sysAppVersion, RedirectAttributes redirectAttributes) {
		sysAppVersionService.delete(sysAppVersion);
		addMessage(redirectAttributes, "删除手机app版本成功");
		return "redirect:"+Global.getAdminPath()+"/sysappversion/sysAppVersion/?repage";
	}


	@RequestMapping(value = "queryMaxVersion")
	public @ResponseBody String queryMaxVersion(String appType) {
		String version = sysAppVersionService.queryMaxVersion(appType);
		if(StringUtils.isBlank(version)){
			return "";
		}
		return version;
	}


	@RequestMapping(value = "checkVersionIsExits")
	public @ResponseBody String checkVersionIsExits(SysAppVersion sysAppVersion) {
		return sysAppVersionService.checkVersionIsExits(sysAppVersion);
	}
}