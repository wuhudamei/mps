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

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.RecheckControl;
import cn.damei.service.modules.RecheckControlService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.common.utils.StringUtils;

/**
 * 复检单监控表Controller
 * @author wyb
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pqc/recheckControl/recheckControl")
public class RecheckControlController extends BaseController {

	@Autowired
	private RecheckControlService recheckControlService;
	
	@ModelAttribute
	public RecheckControl get(@RequestParam(required=false) String id) {
		RecheckControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recheckControlService.get(id);
		}
		if (entity == null){

			entity = new RecheckControl();
		}
		return entity;
	}
	
	@RequiresPermissions("recheckControl:recheckControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecheckControl recheckControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==recheckControl.getStoreId()){
			if(null!=user.getStoreId()){
				recheckControl.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(recheckControl.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				recheckControl.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				recheckControl.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/PQC/recheckControl/recheckControlList";
	}
	@RequiresPermissions("recheckControl:recheckControl:view")
	@RequestMapping(value = {"recheckControlList", ""})
	public String recheckControlList(RecheckControl recheckControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==recheckControl.getStoreId()){
			if(null!=user.getStoreId()){
				recheckControl.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(recheckControl.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				recheckControl.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				recheckControl.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<RecheckControl> page = recheckControlService.findPage(new Page<RecheckControl>(request, response), recheckControl); 
		model.addAttribute("page", page);
		model.addAttribute("recheckControl", recheckControl);
		return "modules/PQC/recheckControl/recheckControlList";
	}
	
	


	
	

}