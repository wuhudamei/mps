
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
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;


@Controller
@RequestMapping(value = "${adminPath}/phonemsg/bizPhoneMsg")
public class BizPhoneMsgController extends BaseController {

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	@ModelAttribute
	public BizPhoneMsg get(@RequestParam(required=false) Integer id) {
		BizPhoneMsg entity = null;
		if (id != null){
			entity = bizPhoneMsgService.get(id);
		}
		if (entity == null){
			entity = new BizPhoneMsg();
		}
		return entity;
	}
	
	@RequiresPermissions("phonemsg:bizPhoneMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPhoneMsg bizPhoneMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPhoneMsg> page = bizPhoneMsgService.findPage(new Page<BizPhoneMsg>(request, response), bizPhoneMsg); 
		model.addAttribute("page", page);
		return "modules/phonemsg/bizPhoneMsgList";
	}

	@RequiresPermissions("phonemsg:bizPhoneMsg:view")
	@RequestMapping(value = "form")
	public String form(BizPhoneMsg bizPhoneMsg, Model model) {
		model.addAttribute("bizPhoneMsg", bizPhoneMsg);
		return "modules/phonemsg/bizPhoneMsgForm";
	}

	@RequiresPermissions("phonemsg:bizPhoneMsg:edit")
	@RequestMapping(value = "save")
	public String save(BizPhoneMsg bizPhoneMsg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPhoneMsg)){
			return form(bizPhoneMsg, model);
		}
		bizPhoneMsgService.save(bizPhoneMsg);
		addMessage(redirectAttributes, "保存短信成功");
		return "redirect:"+Global.getAdminPath()+"/phonemsg/bizPhoneMsg/?repage";
	}
	
	@RequiresPermissions("phonemsg:bizPhoneMsg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPhoneMsg bizPhoneMsg, RedirectAttributes redirectAttributes) {
		bizPhoneMsgService.delete(bizPhoneMsg);
		addMessage(redirectAttributes, "删除短信成功");
		return "redirect:"+Global.getAdminPath()+"/phonemsg/bizPhoneMsg/?repage";
	}

}