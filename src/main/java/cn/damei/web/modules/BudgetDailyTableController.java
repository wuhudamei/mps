package cn.damei.web.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BudgetDailyTable;
import cn.damei.service.modules.BudgetDailyTableService;
import cn.damei.service.modules.BizEngineeringDepartmentService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/budgetdailytable/budgetDailyTable")
public class BudgetDailyTableController extends BaseController {

	@Autowired
	private BudgetDailyTableService budgetDailyTableService;
	
	@Autowired
    private BizEngineeringDepartmentService bizEngineeringDepartmentService;
	
	SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequiresPermissions("budgetdailytable:budgetdailytable:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BudgetDailyTable budgetDailyTable, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		

		if(null == budgetDailyTable.getStoreId()){
			if(null != user.getStoreId()){
				budgetDailyTable.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		Date date=new Date();
	    Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);
		date=calendar.getTime(); 
		
		String dateString = sdfYMD.format(date);
		
		try {
			budgetDailyTable.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
			budgetDailyTable.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/budgetdailytable/budgetDailyTable";
	}
	
	@RequiresPermissions("budgetdailytable:budgetdailytable:view")
	@RequestMapping(value = {"list", ""})
	public String list(BudgetDailyTable budgetDailyTable,String employeeId, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		

		if(null == budgetDailyTable.getStoreId()){
			if(null != user.getStoreId()){
				budgetDailyTable.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		List<Integer> ids = null;
		if(employeeId != null && !"".equals(employeeId)){
			ids = bizEngineeringDepartmentService.findByEmployeeId(Integer.parseInt(employeeId));
		} else {
			ids = bizEngineeringDepartmentService.findAll();
		}
		if (ids != null && ids.size() > 0) {
			budgetDailyTable.setIds(ids);
		} else {
			budgetDailyTable.setIds(null);
		}
		
		try {
			if (budgetDailyTable.getEndDate() != null) {
				Date endDate = budgetDailyTable.getEndDate();
				String endDateStr = sdfYMD.format(endDate) + " 23:59:59";
				budgetDailyTable.setEndDate(sdfYMDHMS.parse(endDateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer storeStayCreatePkgOrdCount = budgetDailyTableService.findStayCreatePkgOrdCount(budgetDailyTable);
		Integer storeStayAuditPkgCount = budgetDailyTableService.findStayAuditPkgCount(budgetDailyTable);
		
		List<BudgetDailyTable> list = budgetDailyTableService.findBudgetDailyTBList(budgetDailyTable);
		
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("storeStayCreatePkgOrdCount",storeStayCreatePkgOrdCount);
		model.addAttribute("storeStayAuditPkgCount",storeStayAuditPkgCount);
		model.addAttribute("list",list);
		return "modules/budgetdailytable/budgetDailyTable";
	}
	
}
