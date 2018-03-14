package cn.damei.web.modules;


import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ExchangeOrderDetails;
import cn.damei.service.modules.ExchangeOrderDetailsService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping(value="${adminPath}/exchangeOrderDetails/exchangeOrderDetails/")
@Controller
public class ExchangeOrderDetailsController extends BaseController{
	
	@Autowired
	private BizEmployeeService bizEmployeeService;
	@Autowired
	private ExchangeOrderDetailsService exchangeOrderDetailsService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	/**
	 * 工人组长换单列表
	 * @param bizEmployee
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("worker:bizWorkerExchange:view")
	@RequestMapping(value = "worker_list")
	public String workerList(@ModelAttribute BizEmployee bizEmployee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (null != bizEmployee.getStartExchanegeDate()) {
			model.addAttribute("startExchanegeDate", bizEmployee.getStartExchanegeDate());
		}
		if (null != bizEmployee.getEndExchanegeDate()) {
			model.addAttribute("endExchanegeDate", bizEmployee.getEndExchanegeDate());
		}
		
		User user = UserUtils.getUser();
		if (bizEmployee.getStoreid() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizEmployee.setStoreid(null);
			} else {
				bizEmployee.setStoreid(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizEmployee.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEmployee.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEmployee.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		bizEmployee.setEmpType(2);
		Page<BizEmployee> page = bizEmployeeService.findLeadListPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		return "modules/exchangeOrderDetails/bizWorkerExchangeList";
	}
	
	/**
	 * 被换详情
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequiresPermissions("worker:bizWorkerExchange:view")
	@RequestMapping(value="details")
	public String workerDetails(Integer id,String startExChangeDate,String endExChangeDate,HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException{
		ExchangeOrderDetails exchangeOrderDetails = new ExchangeOrderDetails();
		exchangeOrderDetails.setOldEmployeeId(id);
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		exchangeOrderDetails.setOldEmployeeId(id);
		if(stringToDate(startExChangeDate)){
			exchangeOrderDetails.setStartExChangeDate(sdf.parse(startExChangeDate));
		}
		if(stringToDate(endExChangeDate)){
			exchangeOrderDetails.setEndExChangeDate(sdf.parse(endExChangeDate));
		}
		Page<ExchangeOrderDetails> page = exchangeOrderDetailsService.selectDetailsPageById(new Page<ExchangeOrderDetails>(request, response),exchangeOrderDetails);
		
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "modules/exchangeOrderDetails/bizWorkerExchangeListDetails";
	}
	
	/**
	 * 项目经理换单查询
	 * @param bizEmployee
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manager:bizManagerExchange:view")
	@RequestMapping(value="manager_list")
	public String managerList(BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response,
							Model model){
		return "modules/exchangeOrderDetails/bizManagerExchangeList";
	}
    @RequiresPermissions("manager:bizManagerExchange:view")
    @RequestMapping(value="findmanagerlist")
    public String preManagerList(BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response,
                              Model model){
        if (null != bizEmployee.getStartExchanegeDate()) {
            model.addAttribute("startExchanegeDate", bizEmployee.getStartExchanegeDate());
        }
        if (null != bizEmployee.getEndExchanegeDate()) {
            model.addAttribute("endExchanegeDate", bizEmployee.getEndExchanegeDate());
        }
        bizEmployee.setEmpType(3);
        Page<BizEmployee> page = bizEmployeeService.findExCahangeManagerList(new Page<BizEmployee>(request, response), bizEmployee);

        model.addAttribute("page", page);
        return "modules/exchangeOrderDetails/bizManagerExchangeList";
    }
	
	/**
	 * 项目经理换单详情
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequiresPermissions("manager:bizManagerExchange:view")
	@RequestMapping(value="managerDetails")
	public String manageDetails(Integer id,String startExChangeDate,String endExChangeDate,HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		ExchangeOrderDetails exchangeOrderDetails = new ExchangeOrderDetails();
		exchangeOrderDetails.setOldEmployeeId(id);
		if(stringToDate(startExChangeDate)){
			exchangeOrderDetails.setStartExChangeDate(sdf.parse(startExChangeDate));
		}
		if(stringToDate(endExChangeDate)){
			exchangeOrderDetails.setEndExChangeDate(sdf.parse(endExChangeDate));
		}
		Page<ExchangeOrderDetails> page = exchangeOrderDetailsService.selectManagerDetailsPageById(new Page<ExchangeOrderDetails>(request, response),exchangeOrderDetails);
		
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "modules/exchangeOrderDetails/bizManagerExchangeListDetails";
	}
	//判断字符串是否能转为日期
	public static boolean stringToDate(String s){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		try {
			sdf.parse(s);
		} catch (ParseException e) {
			return false; 
		}
		return true;
	}
	/**
	 * 质检员换单查询
	 * @param bizEmployee
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("inspector:bizInspectorExchange:view")
	@RequestMapping(value="inspector_list")
	public String inspectorList(@ModelAttribute BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response,
							Model model){
		return "modules/exchangeOrderDetails/bizInspectorExchangeList";
	}
    @RequiresPermissions("inspector:bizInspectorExchange:view")
    @RequestMapping(value="findinspectorlist")
    public String findInspectorList(@ModelAttribute BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response,
                                Model model){
        bizEmployee.setEmpType(1);
        Page<BizEmployee> page = bizEmployeeService.findExCahangeInspectorList(new Page<BizEmployee>(request, response), bizEmployee);

        model.addAttribute("page", page);
        return "modules/exchangeOrderDetails/bizInspectorExchangeList";
    }
	
	/**
	 * 质检员换单详情
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("inspector:bizInspectorExchange:view")
	@RequestMapping(value="inspectorDetails")
	public String inspectorDetails(Integer id,HttpServletRequest request, HttpServletResponse response,Model model){
		
		ExchangeOrderDetails exchangeOrderDetails = new ExchangeOrderDetails();
		exchangeOrderDetails.setOldEmployeeId(id);
		Page<ExchangeOrderDetails> page = exchangeOrderDetailsService.selectInspectorDetailsPageById(new Page<ExchangeOrderDetails>(request, response), exchangeOrderDetails);
		
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "modules/exchangeOrderDetails/bizInspectorExchangeListDetails";
	}
	
	@RequestMapping(value = "exportManagerExchangeToExcel")
	public void exportManagerExchangeToExcel(BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response) {
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		HSSFWorkbook excel = exchangeOrderDetailsService.exportManagerExchangeToExcel(bizEmployee);
		ServletOutputStream out = null;
		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("项目经理被换明细-" + now).getBytes(), "ISO8859-1");

			response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
			out = response.getOutputStream();
			
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	@RequestMapping(value = "exportWorkerExchangeToExcel")
	public void exportWorkerExchangeToExcel(BizEmployee bizEmployee,HttpServletRequest request, HttpServletResponse response) {
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());


		HSSFWorkbook excel = exchangeOrderDetailsService.exportWorkerExchangeToExcel(bizEmployee);


		ServletOutputStream out = null;

		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("工人被换明细-" + now).getBytes(), "ISO8859-1");

			response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
			out = response.getOutputStream();
			
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
}

