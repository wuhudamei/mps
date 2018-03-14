/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizStar;
import cn.damei.service.modules.BizStarService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 星级承接量Controller
 * @author wyb
 * @version 2016-09-05
 */
@Controller
@RequestMapping(value = "${adminPath}/star/bizStar")
public class BizStarController extends BaseController {

	@Autowired
	private BizStarService bizStarService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizStar get(@RequestParam(required=false) String id) {
		BizStar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizStarService.get(id);
		}
		if (entity == null){
			entity = new BizStar();
		}
		return entity;
	}
	
	
	@RequiresPermissions("star:bizStar:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizStar bizStar, HttpServletRequest request, HttpServletResponse response, Model model) {
			
			User user = UserUtils.getUser();
			//过滤门店
			if(StringUtils.isBlank(bizStar.getStoreId())){
				bizStar.setStoreId(user.getStoreId());
			}
			if(StringUtils.isBlank(user.getStoreId())){
				model.addAttribute("storeDropEnable", true);
			}
			//过滤工程模式
			if(StringUtils.isBlank(bizStar.getProjectMode())){
				if(null != user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(StringUtils.isBlank(be.getProjectMode())){
						model.addAttribute("gongcheng", true);
					}else{
						if(be.getProjectMode().equals("3")){
							model.addAttribute("gongcheng", true);
						}else{
							bizStar.setProjectMode(be.getProjectMode());
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
							bizStar.setProjectMode(be.getProjectMode());
						}
					}
				}else{
					model.addAttribute("gongcheng", true);
				}
			}
		return "modules/star/bizStarList";
	}
	@RequiresPermissions("star:bizStar:view")
	@RequestMapping(value = {"starList", ""})
	public String starList(BizStar bizStar, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(StringUtils.isBlank(bizStar.getStoreId())){
			bizStar.setStoreId(user.getStoreId());
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizStar.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizStar.setProjectMode(be.getProjectMode());
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
						bizStar.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizStar> page = bizStarService.findPage(new Page<BizStar>(request, response), bizStar); 
		model.addAttribute("page", page);
		return "modules/star/bizStarList";
	}

	@RequiresPermissions("star:bizStar:view")
	@RequestMapping(value = "form")
	public String form(BizStar bizStar, Model model) {
		
		//过滤工程模式
		User user = UserUtils.getUser();
		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			model.addAttribute("readOnly", be.getProjectMode());
		}
		model.addAttribute("bizStar", bizStar);
		return "modules/star/bizStarForm";
	}
	
	@RequestMapping(value = "validateForm")
	@ResponseBody
	public Map<String,Object> validateForm(BizStar bizStar, Model model,String projectMode) {
		bizStar.setProjectMode(projectMode);
		List<BizStar> findListByBizStar = bizEmployeeService2.findListByBizStar(bizStar);
		Map<String,Object> map = new HashMap<>();
		if(findListByBizStar == null || findListByBizStar.size() <=0){
			map.put("flag",true);
		}else{
			map.put("flag",false);
		}
		return map;
	}
	
	@RequiresPermissions("star:bizStar:view")
	@RequestMapping(value = "fedit")
	public String fedit(BizStar bizStar, Model model) {
		//过滤工程模式
		User user = UserUtils.getUser();
		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			model.addAttribute("readOnly", be.getProjectMode());
		}
		model.addAttribute("bizStar", bizStar);
		return "modules/star/bizStarFormEdit";
	}

	@RequiresPermissions("star:bizStar:edit")
	@RequestMapping(value = "save")
	public String save(BizStar bizStar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizStar)){
			return form(bizStar, model);
		}
		//修改
		if(null!=bizStar.getId() && bizStar.getId()!="" && bizStar.getId().length()>0){
			List<BizStar> star = bizStarService.findList(bizStar);
			if(null!=star && star.size()>0){
				for(BizStar last:star){
					if(last.getId().equals(bizStar.getId())){
						bizStarService.save(bizStar);
						addMessage(redirectAttributes, "保存星级承接量成功");
					}else{
						addMessage(redirectAttributes, "星级承接量已经存在，保存失败");
					}
				}
			}else{
				bizStarService.save(bizStar);
				addMessage(redirectAttributes, "保存星级承接量成功");
			}
		}else{
			//添加
			List<BizStar> star = bizStarService.findList(bizStar);
			if(null!=star && star.size()>0){
				addMessage(redirectAttributes, "星级承接量已经存在，保存失败");
			}else{
				bizStarService.save(bizStar);
				addMessage(redirectAttributes, "保存星级承接量成功");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/star/bizStar/starList?repage";
	}
	
	@RequiresPermissions("star:bizStar:edit")
	@RequestMapping(value = "delete")
	public String delete(BizStar bizStar, RedirectAttributes redirectAttributes) {
		bizStarService.delete(bizStar);
		addMessage(redirectAttributes, "删除星级承接量成功");
		return "redirect:"+Global.getAdminPath()+"/star/bizStar/starList?repage";
	}

}