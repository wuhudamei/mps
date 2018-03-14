/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPmAttendMonth;
import cn.damei.entity.modules.BizPmAttendSalaryBill;
import cn.damei.service.modules.BizPmAttendMonthService;
import cn.damei.service.modules.BizPmAttendSalaryBillService;
import cn.damei.service.modules.SysSequenceService;

/**
 * 考勤月度工资单Controller
 * @author wl
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/attend/bizPmAttendSalaryBill")
public class BizPmAttendSalaryBillController extends BaseController {

	@Autowired
	private BizPmAttendSalaryBillService bizPmAttendSalaryBillService;
	
	@Autowired
	private BizPmAttendMonthService bizPmAttendMonthService;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizPmAttendSalaryBill get(@RequestParam(required=false) String id) {
		BizPmAttendSalaryBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizPmAttendSalaryBillService.get(id);
		}
		if (entity == null){
			entity = new BizPmAttendSalaryBill();
		}
		return entity;
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmAttendSalaryBill> page = bizPmAttendSalaryBillService.findPage(new Page<BizPmAttendSalaryBill>(request, response), bizPmAttendSalaryBill); 
		model.addAttribute("page", page);
		return "modules/attend/bizPmAttendSalaryBillList";
	}

	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "form")
	public String form(BizPmAttendSalaryBill bizPmAttendSalaryBill, Model model,BizPmAttendMonth bizPmAttendMonth,HttpServletRequest request, HttpServletResponse response) {
		List<BizPmAttendMonth> bizMonthSalary = bizPmAttendMonthService.getBizMonthSalary(new Page<BizPmAttendMonth>(request, response),bizPmAttendMonth);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		if(bizMonthSalary.size()>0){
			model.addAttribute("bizMonthSalary", bizMonthSalary.get(0));
		}
		return "modules/attend/bizPmAttendSalaryBillForm";
	}

	@RequiresPermissions("attend:bizPmAttendSalaryBill:edit")
	@RequestMapping(value = "save")
	public String save(BizPmAttendSalaryBill bizPmAttendSalaryBill, Model model, RedirectAttributes redirectAttributes,BizPmAttendMonth bizPmAttendMonth,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, bizPmAttendSalaryBill)){
			return form(bizPmAttendSalaryBill,model, bizPmAttendMonth, request,  response);
		}
		BizPmAttendSalaryBill findAttendSalaryList = bizPmAttendSalaryBillService.findAttendSalaryList(bizPmAttendSalaryBill);
		if(null == findAttendSalaryList){
			String sequence = sysSequenceService.getSequence(BizAttendBillConstantUtil.GZ_NO);
			//考勤单号
			String gzNo = sequence.substring(0,2);
			//顺序码
			String No = sequence.substring(2);
			//时间
			String date = BizAttendBillConstantUtil.getDate(new Date());
			//考勤单编号
			bizPmAttendSalaryBill.setPmAttendSalaryBillCode(gzNo+date+No);
			bizPmAttendMonthService.updatePmAttendMonthStatus(bizPmAttendMonth);
			bizPmAttendSalaryBillService.save(bizPmAttendSalaryBill);
			addMessage(redirectAttributes, "保存考勤月度工资单成功");
			return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendMonth/findPmAttendList?storeId="+bizPmAttendMonth.getStoreId()+"&itemManagerId="+bizPmAttendMonth.getItemManagerId()+"&enginDepartId="+bizPmAttendMonth.getEnginDepartId()+"&attendMonth="+bizPmAttendMonth.getAttendMonth();
		}else{
			addMessage(redirectAttributes, "重复提交数据，请刷新后再试。");
			return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendMonth/bizPmAttendList";
		}
		
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmAttendSalaryBill bizPmAttendSalaryBill, RedirectAttributes redirectAttributes) {
		bizPmAttendSalaryBillService.delete(bizPmAttendSalaryBill);
		addMessage(redirectAttributes, "删除考勤月度工资单成功");
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/?repage";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "salaryBillAuditing")
	public String salaryBillAuditing(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		bizPmAttendSalaryBill.setAttendMonth(mon);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditing";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "findSalaryBillAuditing")
	public String findSalaryBillAuditing(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		List<BizPmAttendSalaryBill> findSalaryBillAuditingList = bizPmAttendSalaryBillService.findSalaryBillAuditingList(bizPmAttendSalaryBill);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		//model.addAttribute("page", findSalaryBillAuditingList);
		model.addAttribute("list", findSalaryBillAuditingList);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditing";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model,BizPmAttendMonth bizPmAttendMonth,String salaryBillIds,String pmAttendMonthId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		bizPmAttendMonth.setAttendMonth(mon);
		bizPmAttendSalaryBillService.updateStatusByIds(bizPmAttendSalaryBill);
		bizPmAttendMonthService.updatePmAttendMonthStatus(bizPmAttendMonth);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/findSalaryBillAuditing?storeId="+bizPmAttendMonth.getStoreId()+"&enginDepartId="+bizPmAttendMonth.getEnginDepartId();
	}
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "deleteStatus")
	public String deleteStatus(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model,BizPmAttendMonth bizPmAttendMonth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		
		bizPmAttendSalaryBillService.delete(bizPmAttendSalaryBill);
		bizPmAttendMonthService.updatePmAttendMonthStatus(bizPmAttendMonth);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/findSalaryBillAuditing";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "salaryBillAuditingBatch")
	public String salaryBillAuditingBatch(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		bizPmAttendSalaryBill.setAttendMonth(mon);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		return "modules/attend/bizPmAttendSalaryAuditingBatch";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "findSalaryBillAuditingBatch")
	public String findSalaryBillAuditingBatch(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList = bizPmAttendSalaryBillService.findSalaryBillAuditingBatchList(new Page<BizPmAttendSalaryBill>(request, response),bizPmAttendSalaryBill);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("page", findSalaryBillAuditingBatchList);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditingBatch";
	}
	
	@RequestMapping(value = "createBatch")
	public String createBatch(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditingBatch";
	}
	
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "salaryBillAuditingList")
	public String salaryBillAuditingList(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		bizPmAttendSalaryBill.setAttendMonth(mon);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditingList";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "findSalaryBillAuditingList")
	public String findSalaryBillAuditingList(BizPmAttendSalaryBill bizPmAttendSalaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		Page<BizPmAttendSalaryBill> findSalaryBillAuditingList = bizPmAttendSalaryBillService.findSalaryBillAuditingDetailList(new Page<BizPmAttendSalaryBill>(request, response),bizPmAttendSalaryBill);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("page", findSalaryBillAuditingList);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalaryAuditingList";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalaryBill:view")
	@RequestMapping(value = "bizPmAttendSalaryBillDetail")
	public String bizPmAttendSalaryBillDetail(BizPmAttendSalaryBill bizPmAttendSalaryBill, Model model,BizPmAttendMonth bizPmAttendMonth,HttpServletRequest request, HttpServletResponse response,String flag) {
		BizPmAttendMonth bizMonthSalary = bizPmAttendMonthService.bizPmAttendSalaryBillDetail(bizPmAttendMonth);
		model.addAttribute("bizPmAttendSalaryBill", bizPmAttendSalaryBill);
		model.addAttribute("bizMonthSalary", bizMonthSalary);
		model.addAttribute("flag", flag);
		return "modules/attend/bizPmAttendSalaryBillList";
	}

}