package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderRecheckMonitor;
import cn.damei.service.modules.BizOrderRecheckMonitorService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 复尺未转单监控(20161114-20161120)Controller
 * @author llp
 * @version 2016-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderrecheckmonitor/bizOrderRecheckMonitor")
public class BizOrderRecheckMonitorController extends BaseController{
	@Autowired
	private BizOrderRecheckMonitorService bizOrderRecheckMonitorService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	/**
	 * 准备列出复尺未转单监控列表
	 */
	@RequiresPermissions("bizorderrecheckmonitor:bizOrderRecheckMonitor:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizOrderRecheckMonitor bizOrderRecheckMonitor, Model model, HttpServletRequest request) {
		logger.info("当前登陆人ID："+UserUtils.getUser().getId());
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderRecheckMonitor.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderRecheckMonitor.setStoreId(Integer.valueOf(user.getStoreId()));
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
					bizOrderRecheckMonitor.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}

		return "modules/bizrecheck/bizRecheckMonitorList";
	}
	
	/**
	 * 列出复尺未转单监控列表
	 */
	@RequiresPermissions("bizconstruction:bizConstruction:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderRecheckMonitor bizOrderRecheckMonitor, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderRecheckMonitor.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderRecheckMonitor.setStoreId(Integer.valueOf(user.getStoreId()));
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
					bizOrderRecheckMonitor.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}

		if(null != bizOrderRecheckMonitor.getRecheckType()){
			logger.info("复选框选择的value："+bizOrderRecheckMonitor.getRecheckType());
			String[] list = bizOrderRecheckMonitor.getRecheckType().split(",");
			bizOrderRecheckMonitor.setRecheckTypeList(Arrays.asList(list));
		}
		
		Page<BizOrderRecheckMonitor> page = bizOrderRecheckMonitorService
				.findPage(new Page<BizOrderRecheckMonitor>(request, response), bizOrderRecheckMonitor);

		model.addAttribute("page", page);
		return "modules/bizrecheck/bizRecheckMonitorList";
	}
}
