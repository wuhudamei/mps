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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizQcStarCommissionCnfg;
import cn.damei.service.modules.BizQcStarCommissionCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 质检员星级和提成设置Controller
 * @author wyb
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg")
public class BizQcStarCommissionCnfgController extends BaseController {

	@Autowired
	private BizQcStarCommissionCnfgService bizQcStarCommissionCnfgService;
	
	@ModelAttribute
	public BizQcStarCommissionCnfg get(@RequestParam(required=false) Integer id) {
		BizQcStarCommissionCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizQcStarCommissionCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizQcStarCommissionCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcStarCommissionCnfg bizQcStarCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcStarCommissionCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcStarCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfgList";
	}
	
	@RequiresPermissions("bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(BizQcStarCommissionCnfg bizQcStarCommissionCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcStarCommissionCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcStarCommissionCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		Page<BizQcStarCommissionCnfg> page = bizQcStarCommissionCnfgService.findPage(new Page<BizQcStarCommissionCnfg>(request, response), bizQcStarCommissionCnfg); 
		model.addAttribute("page", page);
		return "modules/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfgList";
	}

	@RequiresPermissions("bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizQcStarCommissionCnfg bizQcStarCommissionCnfg, Model model) {
		model.addAttribute("bizQcStarCommissionCnfg", bizQcStarCommissionCnfg);
		return "modules/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfgForm";
	}

	@RequiresPermissions("bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizQcStarCommissionCnfg bizQcStarCommissionCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcStarCommissionCnfg)){
			return form(bizQcStarCommissionCnfg, model);
		}
		
		double a = bizQcStarCommissionCnfg.getCommissionRateMidway()*0.01;
		double b = bizQcStarCommissionCnfg.getCommissionRateComplete()*0.01;
		bizQcStarCommissionCnfg.setCommissionRateMidwayTwo(a);
		bizQcStarCommissionCnfg.setCommissionRateCompleteTwo(b);
		
		String panduan = "0";
		List<BizQcStarCommissionCnfg> list = bizQcStarCommissionCnfgService.findList(bizQcStarCommissionCnfg);
		if(null != list && list.size()>0){
			if(null != bizQcStarCommissionCnfg.getId()){
				for(BizQcStarCommissionCnfg last : list){
					if(last.getId().equals(bizQcStarCommissionCnfg.getId())){
						panduan = "1";
						bizQcStarCommissionCnfgService.save(bizQcStarCommissionCnfg);
						addMessage(redirectAttributes, "保存质检员星级和提成设置成功");
					}
				}
			}
		}else{
			panduan = "1";
			bizQcStarCommissionCnfgService.save(bizQcStarCommissionCnfg);
			addMessage(redirectAttributes, "保存质检员星级和提成设置成功");
		}
		if(panduan=="0"){
			addMessage(redirectAttributes, "质检员星级和提成设置已存在,保存失败");
		}

		return "redirect:"+Global.getAdminPath()+"/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/list1?repage";
	}
	
	@RequiresPermissions("bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcStarCommissionCnfg bizQcStarCommissionCnfg, RedirectAttributes redirectAttributes) {
		
		if (bizQcStarCommissionCnfg.getIsEnabled().equals("1")) {
			bizQcStarCommissionCnfg.setIsEnabled("0");
		} else {
			bizQcStarCommissionCnfg.setIsEnabled("1");
		}
		
		bizQcStarCommissionCnfgService.delete(bizQcStarCommissionCnfg);
		addMessage(redirectAttributes, "删除质检员星级配置表成功");
		return "redirect:"+Global.getAdminPath()+"/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/list1?repage";
	}

}