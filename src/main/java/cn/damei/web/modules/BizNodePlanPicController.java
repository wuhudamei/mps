/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlanPic;
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
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizNodePlanPicService;

/**
 * llpController
 * @author llp
 * @version 2016-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/biznodeplanpic/bizNodePlanPic")
public class BizNodePlanPicController extends BaseController {

	@Autowired
	private BizNodePlanPicService bizNodePlanPicService;
	
	@ModelAttribute
	public BizNodePlanPic get(@RequestParam(required=false) Integer id) {
		BizNodePlanPic entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizNodePlanPicService.get(id);
		}
		if (entity == null){
			entity = new BizNodePlanPic();
		}
		return entity;
	}
	
	@RequiresPermissions("biznodeplanpic:bizNodePlanPic:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNodePlanPic bizNodePlanPic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizNodePlanPic> page = bizNodePlanPicService.findPage(new Page<BizNodePlanPic>(request, response), bizNodePlanPic); 
		model.addAttribute("page", page);
		return "modules/biznodeplanpic/bizNodePlanPicList";
	}

	@RequiresPermissions("biznodeplanpic:bizNodePlanPic:view")
	@RequestMapping(value = "form")
	public String form(BizNodePlanPic bizNodePlanPic, Model model) {
		model.addAttribute("bizNodePlanPic", bizNodePlanPic);
		return "modules/biznodeplanpic/bizNodePlanPicForm";
	}

	@RequiresPermissions("biznodeplanpic:bizNodePlanPic:edit")
	@RequestMapping(value = "save")
	public String save(BizNodePlanPic bizNodePlanPic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizNodePlanPic)){
			return form(bizNodePlanPic, model);
		}
		bizNodePlanPicService.save(bizNodePlanPic);
		addMessage(redirectAttributes, "保存BizNodePlanPic成功");
		return "redirect:"+Global.getAdminPath()+"/biznodeplanpic/bizNodePlanPic/?repage";
	}
	
	@RequiresPermissions("biznodeplanpic:bizNodePlanPic:edit")
	@RequestMapping(value = "delete")
	public String delete(BizNodePlanPic bizNodePlanPic, RedirectAttributes redirectAttributes) {
		bizNodePlanPicService.delete(bizNodePlanPic);
		addMessage(redirectAttributes, "删除BizNodePlanPic成功");
		return "redirect:"+Global.getAdminPath()+"/biznodeplanpic/bizNodePlanPic/?repage";
	}
	
	@RequestMapping(value = {"showPhoto", ""})
	public String showPhoto(BizNodePlanPic bizNodePlanPic, Model model, HttpServletRequest request) throws IOException {
		Integer nodeId = Integer.valueOf(request.getParameter("id"));
		String orderId = request.getParameter("orderId");
		logger.info("nodeId=====" + nodeId + "\t orderID==" + orderId);
		
		List<BizNodePlanPic> npList = bizNodePlanPicService.getByNodePlanId(nodeId);
		for(BizNodePlanPic p : npList){
			logger.info(""+p.getPicUrl());
		}
		
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		model.addAttribute("orderId" , orderId);
		model.addAttribute("npList", npList);
		return "modules/biznodeplanpic/showPic";
	}
}