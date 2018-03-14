/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.damei.entity.modules.BizPmSettleSummaryBill;
import cn.damei.service.modules.BizPmSettleSummaryBillService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 结算汇总单Controller
 * @author qww
 * @version 2016-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/pmsettlesummarybill/bizPmSettleSummaryBill")
public class BizPmSettleSummaryBillController extends BaseController {

	@Autowired
	private BizPmSettleSummaryBillService bizPmSettleSummaryBillService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizPmSettleSummaryBill get(@RequestParam(required=false) Integer id) {
		BizPmSettleSummaryBill entity = null;
		if (id != null){
			entity = bizPmSettleSummaryBillService.get(id);
		}
		if (entity == null){
			entity = new BizPmSettleSummaryBill();
		}
		return entity;
	}

	/**
	 * 项目经理-月度结算单查询表
	 * @param bizPmSettleSummaryBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(bizPmSettleSummaryBill.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(org.apache.commons.lang3.StringUtils.isBlank(storeId)){
				bizPmSettleSummaryBill.setStoreId(null);
			}else{
				bizPmSettleSummaryBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/pmsettlesummarybill/bizPmSettleSummaryBillList";
	}

	/**
	 * 项目经理-月度结算单查询表
	 * @param bizPmSettleSummaryBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:view")
	@RequestMapping(value = {"loadList", ""})
	public String loadList(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(bizPmSettleSummaryBill.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(org.apache.commons.lang3.StringUtils.isBlank(storeId)){
				bizPmSettleSummaryBill.setStoreId(null);
			}else{
				bizPmSettleSummaryBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if(bizPmSettleSummaryBill.getEnginDepartId() == null){
			if(StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(CollectionUtils.isNotEmpty(list)){
					bizPmSettleSummaryBill.setEnginDepartIds(list);
				}else{
					bizPmSettleSummaryBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleSummaryBill.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleSummaryBill.getEnginDepartId());
			bizPmSettleSummaryBill.setEnginDepartIds(list);
		}

		Page<BizPmSettleSummaryBill> page = bizPmSettleSummaryBillService.findSummaryBillPage(new Page<BizPmSettleSummaryBill>(request, response), bizPmSettleSummaryBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleSummaryBill", bizPmSettleSummaryBill);
		return "modules/pmsettlesummarybill/bizPmSettleSummaryBillList";
	}

	/**
	 * 质检员-月度结算单查询表
	 * @param bizPmSettleSummaryBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:view")
	@RequestMapping(value = {"listPbc", ""})
	public String listPbc(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(bizPmSettleSummaryBill.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(org.apache.commons.lang3.StringUtils.isBlank(storeId)){
				bizPmSettleSummaryBill.setStoreId(null);
			}else{
				bizPmSettleSummaryBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/pmsettlesummarybill/bizPmSettleSummaryBillListPbc";
	}

	/**
	 * 质检员-月度结算单查询表
	 * @param bizPmSettleSummaryBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:view")
	@RequestMapping(value = {"loadListPbc", ""})
	public String loadListPbc(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(bizPmSettleSummaryBill.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(org.apache.commons.lang3.StringUtils.isBlank(storeId)){
				bizPmSettleSummaryBill.setStoreId(null);
			}else{
				bizPmSettleSummaryBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if(bizPmSettleSummaryBill.getEnginDepartId() == null){
			if(StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(CollectionUtils.isNotEmpty(list)){
					bizPmSettleSummaryBill.setEnginDepartIds(list);
				}else{
					bizPmSettleSummaryBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleSummaryBill.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleSummaryBill.getEnginDepartId());
			bizPmSettleSummaryBill.setEnginDepartIds(list);
		}

		Page<BizPmSettleSummaryBill> page = bizPmSettleSummaryBillService.findSummaryBillPagePbc(new Page<BizPmSettleSummaryBill>(request, response), bizPmSettleSummaryBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleSummaryBill", bizPmSettleSummaryBill);
		return "modules/pmsettlesummarybill/bizPmSettleSummaryBillListPbc";
	}

	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:view")
	@RequestMapping(value = "form")
	public String form(BizPmSettleSummaryBill bizPmSettleSummaryBill, Model model) {
		model.addAttribute("bizPmSettleSummaryBill", bizPmSettleSummaryBill);
		return "modules/pmsettlesummarybill/bizPmSettleSummaryBillForm";
	}

	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:edit")
	@RequestMapping(value = "save")
	public String save(BizPmSettleSummaryBill bizPmSettleSummaryBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmSettleSummaryBill)){
			return form(bizPmSettleSummaryBill, model);
		}
		bizPmSettleSummaryBillService.save(bizPmSettleSummaryBill);
		addMessage(redirectAttributes, "保存结算汇总单成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlesummarybill/bizPmSettleSummaryBill/?repage";
	}
	
	@RequiresPermissions("pmsettlesummarybill:bizPmSettleSummaryBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmSettleSummaryBill bizPmSettleSummaryBill, RedirectAttributes redirectAttributes) {
		bizPmSettleSummaryBillService.delete(bizPmSettleSummaryBill);
		addMessage(redirectAttributes, "删除结算汇总单成功");
		return "redirect:"+Global.getAdminPath()+"/pmsettlesummarybill/bizPmSettleSummaryBill/?repage";
	}

	@RequestMapping(value="exportExcel")
	public void exportExcel(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletResponse response) throws Exception{
		// 区域
		if(bizPmSettleSummaryBill.getEnginDepartId() == null){
			if(StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(CollectionUtils.isNotEmpty(list)){
					bizPmSettleSummaryBill.setEnginDepartIds(list);
				}else{
					bizPmSettleSummaryBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleSummaryBill.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleSummaryBill.getEnginDepartId());
			bizPmSettleSummaryBill.setEnginDepartIds(list);
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizPmSettleSummaryBillService.exportExcel(bizPmSettleSummaryBill);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("项目经理月度工程结算单"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
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

	@RequestMapping(value="exportExcelPbc")
	public void exportExcelPbc(BizPmSettleSummaryBill bizPmSettleSummaryBill, HttpServletResponse response) throws Exception{
		// 区域
		if(bizPmSettleSummaryBill.getEnginDepartId() == null){
			if(StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(CollectionUtils.isNotEmpty(list)){
					bizPmSettleSummaryBill.setEnginDepartIds(list);
				}else{
					bizPmSettleSummaryBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleSummaryBill.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleSummaryBill.getEnginDepartId());
			bizPmSettleSummaryBill.setEnginDepartIds(list);
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizPmSettleSummaryBillService.exportExcelPbc(bizPmSettleSummaryBill);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("质检员月度工程结算单"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
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
}