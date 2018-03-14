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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 短信组Controller
 * @author 汪文文
 * @version 2016-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/messagegroup/bizMessagegroup")
public class BizMessagegroupController extends BaseController {

	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	
	@ModelAttribute
	public BizMessagegroup get(@RequestParam(required=false) String id) {
		BizMessagegroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizMessagegroupService.get(id);
		}
		if (entity == null){
			entity = new BizMessagegroup();
		}
		return entity;
	}
	
	@RequiresPermissions("messagegroup:bizMessagegroup:view")
	@RequestMapping(value ="listPage")
	public String listPage(BizMessagegroup bizMessagegroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null){
			//当前登录用户门店
			bizMessagegroup.setStoreId(UserUtils.getUser().getStoreId());
		}
		return "modules/messagegroup/bizMessagegroupList";
	}
	@RequiresPermissions("messagegroup:bizMessagegroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMessagegroup bizMessagegroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			bizMessagegroup.setStoreId(UserUtils.getUser().getStoreId());
		}
		Page<BizMessagegroup> page = bizMessagegroupService.findPage(new Page<BizMessagegroup>(request, response), bizMessagegroup); 
		model.addAttribute("page", page);
		return "modules/messagegroup/bizMessagegroupList";
	}

	@RequiresPermissions("messagegroup:bizMessagegroup:view")
	@RequestMapping(value = "form")
	public String form(BizMessagegroup bizMessagegroup, Model model) {
		if(StringUtils.isBlank(bizMessagegroup.getStoreId())){
			if (UserUtils.getUser().getStoreId() != null) {
				// 当前登录用户门店
				bizMessagegroup.setStoreId(UserUtils.getUser().getStoreId());
			} else {
				// 门店是总部的查询所有部门信息
				/*if (bizEmployeegroup.getStoreId() != null
						&& bizEmployeegroup.getStoreId().equals("1")) {
					// 总部
					bizEmployeegroup.setStoreId(null);
				}*/
				//总部时默认为北京门店
				if(bizMessagegroup.getStoreId() == null || bizMessagegroup.getStoreId().equals("1")){
					bizMessagegroup.setStoreId("2");
				}
			}
		}
		model.addAttribute("bizMessagegroup", bizMessagegroup);
		return "modules/messagegroup/bizMessagegroupForm";
	}

	@RequiresPermissions("messagegroup:bizMessagegroup:edit")
	@RequestMapping(value = "save")
	public String save(BizMessagegroup bizMessagegroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMessagegroup)){
			return form(bizMessagegroup, model);
		}
		bizMessagegroupService.save(bizMessagegroup);
		addMessage(redirectAttributes, "保存短信组成功");
		return "redirect:"+Global.getAdminPath()+"/messagegroup/bizMessagegroup/?repage";
	}
	
	@RequiresPermissions("messagegroup:bizMessagegroup:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMessagegroup bizMessagegroup, RedirectAttributes redirectAttributes) {
		bizMessagegroupService.delete(bizMessagegroup);
		addMessage(redirectAttributes, "删除短信组成功");
		return "redirect:"+Global.getAdminPath()+"/messagegroup/bizMessagegroup/?repage";
	}

	@RequiresPermissions("messagegroup:bizMessagegroup:edit")
	@RequestMapping(value = "enable")
	public String enable(BizMessagegroup bizMessagegroup , RedirectAttributes redirectAttributes) {
		int isEnable = 1 ^ Integer.parseInt(bizMessagegroup.getIsEnable());
		bizMessagegroup.setIsEnable(isEnable+"");
		bizMessagegroupService.update(bizMessagegroup);
		addMessage(redirectAttributes, DictUtils.getDictLabel(isEnable+"", "biz_enable_status", "")+"短信组成功");
		return "redirect:"+Global.getAdminPath()+"/messagegroup/bizMessagegroup/?repage";
	}
	
	
}