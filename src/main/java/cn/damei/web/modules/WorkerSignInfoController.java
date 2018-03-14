
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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.WorkerSign;
import cn.damei.service.modules.WorkerSignInfoService;


@Controller
@RequestMapping(value = "${adminPath}/workersign/workerSign")
public class WorkerSignInfoController extends BaseController {

	@Autowired
	private WorkerSignInfoService workerSignService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public WorkerSign get(@RequestParam(required=false) String id) {
		WorkerSign entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workerSignService.get(Integer.valueOf(id));
		}
		if (entity == null){
			entity = new WorkerSign();
		}
		return entity;
	}
	@RequestMapping(value = "login")
	public String test(HttpServletRequest request, HttpServletResponse response){
		return "modules/sys/login";
	}

	
	@RequiresPermissions("workersign:workerSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkerSign WorkerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(StringUtils.isBlank(WorkerSign.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						WorkerSign.setProjectMode(be.getProjectMode());
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
						WorkerSign.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/workersign/workerSignList";
	}
	@RequiresPermissions("workersign:workerSign:view")
	@RequestMapping(value = {"list2"})
	public String list2(WorkerSign WorkerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(null!=WorkerSign.getSignDate1()){
			
			model.addAttribute("signDate1", WorkerSign.getSignDate1());
		}
		if(null!=WorkerSign.getSignDate2()){
			
			
			model.addAttribute("signDate2", WorkerSign.getSignDate2());
		}
		Page<WorkerSign> page = workerSignService.findPage(new Page<WorkerSign>(request, response), WorkerSign); 
	
		User user = UserUtils.getUser();

		if(null == WorkerSign.getStoreId()){
			if(null != user.getStoreId()){
				WorkerSign.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(WorkerSign.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						WorkerSign.setProjectMode(be.getProjectMode());
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
						WorkerSign.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		model.addAttribute("page", page);
		return "modules/workersign/workerSignList";
	}


}