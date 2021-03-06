package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderConstructionReport;
import cn.damei.entity.modules.BizOrderConstructionReportService;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.service.modules.BizConstructionScheduleService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizorderconstructionreport/bizOrderConstructionReport")
public class BizOrderConstructionReportController extends BaseController {



	@Autowired
	private BizOrderConstructionReportService bizOrderConstructionReportService;
	@Autowired
	private BizConstructionScheduleService bizConstructionScheduleService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	

	@RequiresPermissions("bizorderconstructionreport:bizOrderConstructionReport:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizOrderConstructionReport bizOrderConstructionReport, Model model, 
			HttpServletRequest request) {


		

		
		User user = UserUtils.getUser();

		if(null == bizOrderConstructionReport.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstructionReport.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderConstructionReport.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstructionReport.setProjectMode(be.getProjectMode());
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
						bizOrderConstructionReport.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/bizconfirmstart/bizConstructionReportList";
	}


	@RequiresPermissions("bizorderconstructionreport:bizOrderConstructionReport:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderConstructionReport bizOrderConstructionReport, Model model, HttpServletResponse response, 
			HttpServletRequest request) {
		User user = UserUtils.getUser();

		if(null == bizOrderConstructionReport.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstructionReport.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderConstructionReport.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstructionReport.setProjectMode(be.getProjectMode());
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
						bizOrderConstructionReport.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		

		List<BizConstructionSchedule> list = bizConstructionScheduleService.getByEnableOrNewHouse(
				bizOrderConstructionReport.getStoreId(),bizOrderConstructionReport.getHouseIsNew());
		
		Page<BizOrderConstructionReport> page = bizOrderConstructionReportService.
				findPage(new Page<BizOrderConstructionReport>(request, response),bizOrderConstructionReport);
		
		
		List<BizOrderConstructionReport> storeList = bizOrderConstructionReportService.getByStoreList(bizOrderConstructionReport.getStoreId());
		
		
		if(StringUtils.isBlank(bizOrderConstructionReport.getProjectMode())){
			model.addAttribute("modeType", "");
		}else if(bizOrderConstructionReport.getProjectMode().equals("1")){
			model.addAttribute("modeType", "1");
		}else if(bizOrderConstructionReport.getProjectMode().equals("2")){
			model.addAttribute("modeType", "2");
		}
		
		model.addAttribute("page", page);
		model.addAttribute("ScheduleList", list);
		model.addAttribute("StoreList", storeList);
		return "modules/bizconfirmstart/bizConstructionReportList";
	}
}
