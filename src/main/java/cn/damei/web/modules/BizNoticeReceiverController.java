
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
import cn.damei.entity.modules.BizNoticeReceiver;
import cn.damei.service.modules.BizNoticeReceiverService;


@Controller
@RequestMapping(value = "${adminPath}/noticereceiver/bizNoticeReceiver")
public class BizNoticeReceiverController extends BaseController {

	@Autowired
	private BizNoticeReceiverService bizNoticeReceiverService;
	
	@ModelAttribute
	public BizNoticeReceiver get(@RequestParam(required=false) Integer id) {
		BizNoticeReceiver entity = null;
		if (id != null){
			entity = bizNoticeReceiverService.get(id);
		}
		if (entity == null){
			entity = new BizNoticeReceiver();
		}
		return entity;
	}
	
	@RequiresPermissions("noticereceiver:bizNoticeReceiver:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNoticeReceiver bizNoticeReceiver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizNoticeReceiver> page = bizNoticeReceiverService.findPage(new Page<BizNoticeReceiver>(request, response), bizNoticeReceiver); 
		model.addAttribute("page", page);
		return "modules/noticereceiver/bizNoticeReceiverList";
	}

	@RequiresPermissions("noticereceiver:bizNoticeReceiver:view")
	@RequestMapping(value = "form")
	public String form(BizNoticeReceiver bizNoticeReceiver, Model model) {
		model.addAttribute("bizNoticeReceiver", bizNoticeReceiver);
		return "modules/noticereceiver/bizNoticeReceiverForm";
	}

	@RequiresPermissions("noticereceiver:bizNoticeReceiver:edit")
	@RequestMapping(value = "save")
	public String save(BizNoticeReceiver bizNoticeReceiver, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizNoticeReceiver)){
			return form(bizNoticeReceiver, model);
		}
		bizNoticeReceiverService.save(bizNoticeReceiver);
		addMessage(redirectAttributes, "保存消息公告接收人成功");
		return "redirect:"+Global.getAdminPath()+"/noticereceiver/bizNoticeReceiver/?repage";
	}
	
	@RequiresPermissions("noticereceiver:bizNoticeReceiver:edit")
	@RequestMapping(value = "delete")
	public String delete(BizNoticeReceiver bizNoticeReceiver, RedirectAttributes redirectAttributes) {
		bizNoticeReceiverService.delete(bizNoticeReceiver);
		addMessage(redirectAttributes, "删除消息公告接收人成功");
		return "redirect:"+Global.getAdminPath()+"/noticereceiver/bizNoticeReceiver/?repage";
	}

}