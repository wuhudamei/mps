/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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
import cn.damei.entity.modules.BizNoticeViewLog;
import cn.damei.service.modules.BizNoticeViewLogService;

/**
 * 消息公告查看日志Controller
 * @author qww
 * @version 2017-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/noticeviewlog/bizNoticeViewLog")
public class BizNoticeViewLogController extends BaseController {

	@Autowired
	private BizNoticeViewLogService bizNoticeViewLogService;
	
	@ModelAttribute
	public BizNoticeViewLog get(@RequestParam(required=false) Integer id) {
		BizNoticeViewLog entity = null;
		if (id != null){
			entity = bizNoticeViewLogService.get(id);
		}
		if (entity == null){
			entity = new BizNoticeViewLog();
		}
		return entity;
	}
	
	@RequiresPermissions("noticeviewlog:bizNoticeViewLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNoticeViewLog bizNoticeViewLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizNoticeViewLog> page = bizNoticeViewLogService.findPage(new Page<BizNoticeViewLog>(request, response), bizNoticeViewLog); 
		model.addAttribute("page", page);
		return "modules/noticeviewlog/bizNoticeViewLogList";
	}

	@RequiresPermissions("noticeviewlog:bizNoticeViewLog:view")
	@RequestMapping(value = "form")
	public String form(BizNoticeViewLog bizNoticeViewLog, Model model) {
		model.addAttribute("bizNoticeViewLog", bizNoticeViewLog);
		return "modules/noticeviewlog/bizNoticeViewLogForm";
	}

	@RequiresPermissions("noticeviewlog:bizNoticeViewLog:edit")
	@RequestMapping(value = "save")
	public String save(BizNoticeViewLog bizNoticeViewLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizNoticeViewLog)){
			return form(bizNoticeViewLog, model);
		}
		bizNoticeViewLogService.save(bizNoticeViewLog);
		addMessage(redirectAttributes, "保存消息公告查看日志成功");
		return "redirect:"+Global.getAdminPath()+"/noticeviewlog/bizNoticeViewLog/?repage";
	}
	
	@RequiresPermissions("noticeviewlog:bizNoticeViewLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizNoticeViewLog bizNoticeViewLog, RedirectAttributes redirectAttributes) {
		bizNoticeViewLogService.delete(bizNoticeViewLog);
		addMessage(redirectAttributes, "删除消息公告查看日志成功");
		return "redirect:"+Global.getAdminPath()+"/noticeviewlog/bizNoticeViewLog/?repage";
	}

}