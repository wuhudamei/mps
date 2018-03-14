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
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfg;
import cn.damei.service.modules.BizQcLongwayCommissionCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 远程费提成金额设置Controller
 * @author wyb
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg")
public class BizQcLongwayCommissionCnfgController extends BaseController {

	@Autowired
	private BizQcLongwayCommissionCnfgService bizQcLongwayCommissionCnfgService;
	
	@ModelAttribute
	public BizQcLongwayCommissionCnfg get(@RequestParam(required=false) Integer id) {
		BizQcLongwayCommissionCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizQcLongwayCommissionCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizQcLongwayCommissionCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcLongwayCommissionCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcLongwayCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		return "modules/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfgList";
	}
	
	@RequiresPermissions("bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(String panduan,BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcLongwayCommissionCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcLongwayCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		
		Page<BizQcLongwayCommissionCnfg> page = bizQcLongwayCommissionCnfgService.findPage(new Page<BizQcLongwayCommissionCnfg>(request, response), bizQcLongwayCommissionCnfg); 
		model.addAttribute("page", page);
		model.addAttribute("panduan", panduan);
		
		return "modules/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfgList";
	}

	@RequiresPermissions("bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg, Model model) {
		model.addAttribute("bizQcLongwayCommissionCnfg", bizQcLongwayCommissionCnfg);
		return "modules/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfgForm";
	}
	

	@RequiresPermissions("bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcLongwayCommissionCnfg)){
			return form(bizQcLongwayCommissionCnfg, model);
		}
		double a = bizQcLongwayCommissionCnfg.getCommissionRateMidway()*0.01;
		double b = bizQcLongwayCommissionCnfg.getCommissionRateComplete()*0.01;
		bizQcLongwayCommissionCnfg.setCommissionRateMidwayTwo(a);
		bizQcLongwayCommissionCnfg.setCommissionRateCompleteTwo(b);
         
         
		bizQcLongwayCommissionCnfg.setIsEnabled(ConstantUtils.IS_ENABLE_1);
		
		String panduan = "0";
		List<BizQcLongwayCommissionCnfg> list = bizQcLongwayCommissionCnfgService.findList(bizQcLongwayCommissionCnfg);
		if(null != list && list.size()>0){
			if(null != bizQcLongwayCommissionCnfg.getId()){
				for(BizQcLongwayCommissionCnfg last : list){
					if(last.getId().equals(bizQcLongwayCommissionCnfg.getId())){
						panduan = "1";
						bizQcLongwayCommissionCnfgService.save(bizQcLongwayCommissionCnfg);
						addMessage(redirectAttributes, "保存远程费提成金额设置成功");
					}
				}
			}
		}else{
			panduan = "1";
			bizQcLongwayCommissionCnfgService.save(bizQcLongwayCommissionCnfg);
			addMessage(redirectAttributes, "保存远程费提成金额设置成功");
		}
		
		if(panduan=="0"){
			addMessage(redirectAttributes, "远程费提成金额已存在,保存失败");
		}	
		
		return "redirect:"+Global.getAdminPath()+"/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/list1?panduan="+panduan+"&repage";
	}
	
	@RequiresPermissions("bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcLongwayCommissionCnfg bizQcLongwayCommissionCnfg, RedirectAttributes redirectAttributes) {
		bizQcLongwayCommissionCnfgService.delete(bizQcLongwayCommissionCnfg);
		addMessage(redirectAttributes, "删除远程费提成金额设置成功");
		return "redirect:"+Global.getAdminPath()+"/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/list1?repage";
	}

}