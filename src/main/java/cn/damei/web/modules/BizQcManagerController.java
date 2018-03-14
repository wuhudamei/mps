/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizQcManager;
import cn.damei.service.modules.BizQcManagerService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 质检管理人员Controller
 * @author wyb
 * @version 2016-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/bizqcmanager/bizQcManager")
public class BizQcManagerController extends BaseController {

	@Autowired
	private BizQcManagerService bizQcManagerService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizQcManager get(@RequestParam(required=false) Integer id) {
		BizQcManager entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizQcManagerService.get(id);
		}
		if (entity == null){
			entity = new BizQcManager();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqcmanager:bizQcManager:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcManager bizQcManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcManager.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcManager.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//过滤工程模式
		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(StringUtils.isBlank(be.getProjectMode())){
				model.addAttribute("gongcheng", true);
			}else{
				if(be.getProjectMode().equals("3")){
					model.addAttribute("gongcheng", true);
				}else{
					bizQcManager.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}
		
		return "modules/bizqcmanager/bizQcManagerList";
	}
	@RequiresPermissions("bizqcmanager:bizQcManager:view")
	@RequestMapping(value = {"managerList", ""})
	public String managerList(BizQcManager bizQcManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizQcManager.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcManager.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//过滤工程模式
		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(StringUtils.isBlank(be.getProjectMode())){
				model.addAttribute("gongcheng", true);
			}else{
				if(be.getProjectMode().equals("3")){
					model.addAttribute("gongcheng", true);
				}else{
					bizQcManager.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}
		
		Page<BizQcManager> page = bizQcManagerService.findPage(new Page<BizQcManager>(request, response), bizQcManager); 
		model.addAttribute("page", page);
		return "modules/bizqcmanager/bizQcManagerList";
	}

	@RequiresPermissions("bizqcmanager:bizQcManager:view")
	@RequestMapping(value = "form")
	public String form(BizQcManager bizQcManager, Model model) {
		model.addAttribute("bizQcManager", bizQcManager);
		return "modules/bizqcmanager/bizQcManagerForm";
	}

	@RequiresPermissions("bizqcmanager:bizQcManager:edit")
	@RequestMapping(value = "save")
	public String save(BizQcManager bizQcManager, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcManager)){
			return form(bizQcManager, model);
		}
		//通过人员id查询门店
		Integer storeId = bizQcManagerService.findStore(bizQcManager.getManagerEmployeeId());
		bizQcManager.setStoreId(storeId);
		bizQcManager.setIsEnabled("1");
		bizQcManager.setGeneratedDatetime(new Date());
		bizQcManagerService.save(bizQcManager);
		addMessage(redirectAttributes, "保存质检管理人员成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcmanager/bizQcManager/managerList?repage";
	}
	
	@RequiresPermissions("bizqcmanager:bizQcManager:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcManager bizQcManager, RedirectAttributes redirectAttributes) {
		bizQcManagerService.delete(bizQcManager);
		addMessage(redirectAttributes, "删除质检管理人员成功");
		return "redirect:"+Global.getAdminPath()+"/bizqcmanager/bizQcManager/managerList?repage";
	}

}