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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizQcCheckKind;
import cn.damei.service.modules.BizQcCheckKindService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 检查分类Controller
 * @author wyb
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/bizqccheckkind/bizQcCheckKind")
public class BizQcCheckKindController extends BaseController {

	@Autowired
	private BizQcCheckKindService bizQcCheckKindService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizQcCheckKind get(@RequestParam(required=false) Integer id) {
		BizQcCheckKind entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizQcCheckKindService.get(id);
		}
		if (entity == null){
			entity = new BizQcCheckKind();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqccheckkind:bizQcCheckKind:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcCheckKind bizQcCheckKind, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(StringUtils.isBlank(bizQcCheckKind.getStoreId())){
			bizQcCheckKind.setStoreId(user.getStoreId());
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizQcCheckKind.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckKind.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckKind.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/bizqccheckkind/bizQcCheckKindList";
	}
	
	@RequiresPermissions("bizqccheckkind:bizQcCheckKind:view")
	@RequestMapping(value = {"kindList", ""})
	public String kindList(BizQcCheckKind bizQcCheckKind, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(StringUtils.isBlank(bizQcCheckKind.getStoreId())){
			bizQcCheckKind.setStoreId(user.getStoreId());
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizQcCheckKind.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckKind.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckKind.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizQcCheckKind> page = bizQcCheckKindService.findPage(new Page<BizQcCheckKind>(request, response), bizQcCheckKind); 
		model.addAttribute("page", page);
		return "modules/bizqccheckkind/bizQcCheckKindList";
	}

	@RequiresPermissions("bizqccheckkind:bizQcCheckKind:view")
	@RequestMapping(value = "form")
	public String form(BizQcCheckKind bizQcCheckKind, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizQcCheckKind", bizQcCheckKind);
		return "modules/bizqccheckkind/bizQcCheckKindForm";
	}

	@RequiresPermissions("bizqccheckkind:bizQcCheckKind:edit")
	@RequestMapping(value = "save")
	public String save(BizQcCheckKind bizQcCheckKind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcCheckKind)){
			return form(bizQcCheckKind, model);
		}
		bizQcCheckKindService.save(bizQcCheckKind);
		addMessage(redirectAttributes, "保存检查分类成功");
		return "redirect:"+Global.getAdminPath()+"/bizqccheckkind/bizQcCheckKind/kindList?repage";
	}



	@RequiresPermissions("bizqccheckkind:bizQcCheckKind:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcCheckKind bizQcCheckKind, RedirectAttributes redirectAttributes) {
		
		if (bizQcCheckKind.getStatus().equals("1")) {
			bizQcCheckKind.setStatus("0");
		} else {
			bizQcCheckKind.setStatus("1");
		}
		bizQcCheckKindService.delete(bizQcCheckKind);
		addMessage(redirectAttributes, "检查分类状态修改成功");
		return "redirect:"+Global.getAdminPath()+"/bizqccheckkind/bizQcCheckKind/kindList?repage";
	}

}