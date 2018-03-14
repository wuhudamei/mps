/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.List;

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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPmStarCommissionCnfg;
import cn.damei.service.modules.BizPmStarCommissionCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 项目经理星级和提成设置Controller
 * @author wyb
 * @version 2016-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg")
public class BizPmStarCommissionCnfgController extends BaseController {

	@Autowired
	private BizPmStarCommissionCnfgService bizPmStarCommissionCnfgService;
	
	@ModelAttribute
	public BizPmStarCommissionCnfg get(@RequestParam(required=false) Integer id) {
		BizPmStarCommissionCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPmStarCommissionCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizPmStarCommissionCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmStarCommissionCnfg bizPmStarCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(null!=bizPmStarCommissionCnfg.getStoreId()){
			String store = user.getStoreId();
			if(StringUtils.isNoneBlank(store)){
				bizPmStarCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		return "modules/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfgList";
	}
	@RequiresPermissions("bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(BizPmStarCommissionCnfg bizPmStarCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(null!=bizPmStarCommissionCnfg.getStoreId()){
			String store = user.getStoreId();
			if(StringUtils.isNoneBlank(store)){
				bizPmStarCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		Page<BizPmStarCommissionCnfg> page = bizPmStarCommissionCnfgService.findPage(new Page<BizPmStarCommissionCnfg>(request, response), bizPmStarCommissionCnfg); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfgList";
	}

	@RequiresPermissions("bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizPmStarCommissionCnfg bizPmStarCommissionCnfg, Model model) {
		model.addAttribute("bizPmStarCommissionCnfg", bizPmStarCommissionCnfg);
		return "modules/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfgForm";
	}

	@RequiresPermissions("bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizPmStarCommissionCnfg bizPmStarCommissionCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmStarCommissionCnfg)){
			return form(bizPmStarCommissionCnfg, model);
		}
		 double a = bizPmStarCommissionCnfg.getCommissionRateMidway()*0.01;
		 double b = bizPmStarCommissionCnfg.getCommissionRateComplete()*0.01;
         bizPmStarCommissionCnfg.setCommissionRateMidwayTwo(a);
         bizPmStarCommissionCnfg.setCommissionRateCompleteTwo(b);
         
		//修改
		if(null!=bizPmStarCommissionCnfg.getId()){
			List<BizPmStarCommissionCnfg> list = bizPmStarCommissionCnfgService.findList(bizPmStarCommissionCnfg);
			if(null!=list && list.size()>0){
				for(BizPmStarCommissionCnfg last:list){
					if(last.getId().equals(bizPmStarCommissionCnfg.getId())){
						bizPmStarCommissionCnfgService.save(bizPmStarCommissionCnfg);
						addMessage(redirectAttributes, "保存项目经理星级和提成设置成功");
					}else{
						addMessage(redirectAttributes, "项目经理星级和提成已经存在，保存失败");
					}
				}
			}else{
				bizPmStarCommissionCnfgService.save(bizPmStarCommissionCnfg);
				addMessage(redirectAttributes, "保存项目经理星级和提成设置成功");
			}
		}else{
			//新增
			List<BizPmStarCommissionCnfg> list = bizPmStarCommissionCnfgService.findList(bizPmStarCommissionCnfg);
			if(null!=list && list.size()>0){
				addMessage(redirectAttributes, "项目经理星级和提成已经存在,保存失败");
			}else{
				bizPmStarCommissionCnfgService.save(bizPmStarCommissionCnfg);
				addMessage(redirectAttributes, "保存项目经理星级和提成设置成功");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/list2?repage";
	}
	
	@RequiresPermissions("bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmStarCommissionCnfg bizPmStarCommissionCnfg, RedirectAttributes redirectAttributes) {
		
		if (bizPmStarCommissionCnfg.getIsEnabled().equals("1")) {
			bizPmStarCommissionCnfg.setIsEnabled("0");
		} else {
			bizPmStarCommissionCnfg.setIsEnabled("1");
		}
		
		bizPmStarCommissionCnfgService.delete(bizPmStarCommissionCnfg);
		
		if(bizPmStarCommissionCnfg.getIsEnabled().equals("0")){
			addMessage(redirectAttributes, "停用成功");
		}else{
			addMessage(redirectAttributes, "启用成功");
		}
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/list2?repage";
	}

}