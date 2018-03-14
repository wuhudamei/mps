package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderInstallDateCompared;
import cn.damei.service.modules.BizOrderInstallDateComparedService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 *	@author llp
 *	说明： 到场日期对比表
 *	@version 20161124-20161204
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderinstalldatecompared/bizOrderInstallDateCompared")
public class BizOrderInstallDateComparedController extends BaseController {

	@Autowired
	private BizOrderInstallDateComparedService bizOrderInstallDateComparedService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizOrderInstallDateCompared get(@RequestParam(required = false) Integer id) {
		BizOrderInstallDateCompared entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderInstallDateComparedService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderInstallDateCompared();
		}
		return entity;
	}

	@RequiresPermissions("bizorderinstalldatecompared:bizOrderInstallDateCompared:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallDateCompared bizOrderInstallDateCompared,Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderInstallDateCompared.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderInstallDateCompared.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
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
					bizOrderInstallDateCompared.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}

		return "modules/bizEnginInstall/bizInstallDateComparedList";
	}
	
	@RequiresPermissions("bizorderinstalldatecompared:bizOrderInstallDateCompared:view")
	@RequestMapping(value = "list")
	public String list(BizOrderInstallDateCompared bizOrderInstallDateCompared, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderInstallDateCompared.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderInstallDateCompared.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
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
					bizOrderInstallDateCompared.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}
		
		if(null != bizOrderInstallDateCompared.getInstallStatus()){
			logger.info("复选框选择的value："+bizOrderInstallDateCompared.getInstallStatus());
			String[] list = bizOrderInstallDateCompared.getInstallStatus().split(",");
			bizOrderInstallDateCompared.setInstallList(Arrays.asList(list));
		}
		Page<BizOrderInstallDateCompared> page = bizOrderInstallDateComparedService.findPage(new 
				Page<BizOrderInstallDateCompared>(request, response), bizOrderInstallDateCompared);
		
		model.addAttribute("page", page);
		return "modules/bizEnginInstall/bizInstallDateComparedList";
	}
}
