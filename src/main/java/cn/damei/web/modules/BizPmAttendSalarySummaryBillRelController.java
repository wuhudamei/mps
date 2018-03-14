/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPmAttendSalaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;
import cn.damei.service.modules.BizPmAttendSalaryBillService;
import cn.damei.service.modules.BizPmAttendSalarySummaryBillRelService;

/**
 * 工资单批次审批Controller
 * @author wl
 * @version 2017-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/attend/bizPmAttendSalarySummaryBillRel")
public class BizPmAttendSalarySummaryBillRelController extends BaseController {

	@Autowired
	private BizPmAttendSalarySummaryBillRelService bizPmAttendSalarySummaryBillRelService;
	@Autowired
	private BizPmAttendSalaryBillService bizPmAttendSalaryBillService;
	
	@ModelAttribute
	public BizPmAttendSalarySummaryBillRel get(@RequestParam(required=false) String id) {
		BizPmAttendSalarySummaryBillRel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizPmAttendSalarySummaryBillRelService.get(id);
		}
		if (entity == null){
			entity = new BizPmAttendSalarySummaryBillRel();
		}
		return entity;
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		bizPmAttendSalarySummaryBillRel.setAttendMonth(mon);
		model.addAttribute("bizPmAttendSalarySummaryBillRel", bizPmAttendSalarySummaryBillRel);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalarySummaryBillRelList";
	}
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:view")
	@RequestMapping(value = {"findSalarySummaryBillRel", ""})
	public String findSalarySummaryBillRel(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		Page<BizPmAttendSalarySummaryBillRel> summaryBillList = bizPmAttendSalarySummaryBillRelService.getSummaryBillList(new Page<BizPmAttendSalarySummaryBillRel>(request, response),bizPmAttendSalarySummaryBillRel);
		model.addAttribute("bizPmAttendSalarySummaryBillRel", bizPmAttendSalarySummaryBillRel);
		model.addAttribute("page", summaryBillList);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalarySummaryBillRelList";
	}

	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:view")
	@RequestMapping(value = "form")
	public String form(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, Model model, HttpServletRequest request, HttpServletResponse response,String flag) {
		BizPmAttendSalaryBill bizPmAttendSalaryBill = new BizPmAttendSalaryBill();
		bizPmAttendSalaryBill.setPmAttendSalarySummaryBillId(bizPmAttendSalarySummaryBillRel.getPmAttendSalarySummaryBillId());
		Page<BizPmAttendSalaryBill> findSalaryBillAuditingList = bizPmAttendSalaryBillService.findSalaryBillAuditingDetail(new Page<BizPmAttendSalaryBill>(request, response),bizPmAttendSalaryBill);
		
		model.addAttribute("bizPmAttendSalarySummaryBillRel", bizPmAttendSalarySummaryBillRel);
		model.addAttribute("page", findSalaryBillAuditingList);
		model.addAttribute("flag",flag);
		return "modules/attend/bizPmAttendSalarySummaryBillRelForm";
	}

	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:edit")
	@RequestMapping(value = "save")
	public String save(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, bizPmAttendSalarySummaryBillRel)){
			return form(bizPmAttendSalarySummaryBillRel, model);
		}*/
		bizPmAttendSalarySummaryBillRelService.save(bizPmAttendSalarySummaryBillRel);
		addMessage(redirectAttributes, "保存工资单批次审批成功");
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalarySummaryBillRel/?repage";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, RedirectAttributes redirectAttributes) {
		bizPmAttendSalarySummaryBillRelService.delete(bizPmAttendSalarySummaryBillRel);
		addMessage(redirectAttributes, "删除工资单批次审批成功");
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalarySummaryBillRel/?repage";
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel,BizPmAttendSalaryBill bizPmAttendSalaryBill, RedirectAttributes redirectAttributes) {
		bizPmAttendSalarySummaryBillRelService.updateStatus(bizPmAttendSalarySummaryBillRel);
		if("20".equals(bizPmAttendSalarySummaryBillRel.getStatus())){
			bizPmAttendSalaryBill.setStatus("60");
			bizPmAttendSalaryBillService.updateStatusBySummaryId(bizPmAttendSalaryBill);
		}else if("30".equals(bizPmAttendSalarySummaryBillRel.getStatus())){
			bizPmAttendSalaryBill.setStatus("90");
			bizPmAttendSalaryBillService.updateStatusBySummaryId(bizPmAttendSalaryBill);
			bizPmAttendSalaryBillService.deletePmAttendSalaryBill(bizPmAttendSalaryBill);
			bizPmAttendSalaryBillService.deletePmAttendSalaryBillRel(bizPmAttendSalaryBill);
		}
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalarySummaryBillRel/findSalarySummaryBillRel?storeId="+bizPmAttendSalarySummaryBillRel.getStoreId()+"&attendMonth="+bizPmAttendSalarySummaryBillRel.getAttendMonth();
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:view")
	@RequestMapping(value = {"findList", ""})
	public String findList(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		bizPmAttendSalarySummaryBillRel.setAttendMonth(mon);
		model.addAttribute("bizPmAttendSalarySummaryBillRel", bizPmAttendSalarySummaryBillRel);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalarySummaryBillRel";
	}
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBillRel:view")
	@RequestMapping(value = {"findSalarySummaryBillRelList", ""})
	public String findSalarySummaryBillRelList(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		Page<BizPmAttendSalarySummaryBillRel> summaryBillList = bizPmAttendSalarySummaryBillRelService.getSummaryBillDetail(new Page<BizPmAttendSalarySummaryBillRel>(request, response),bizPmAttendSalarySummaryBillRel);
		model.addAttribute("bizPmAttendSalarySummaryBillRel", bizPmAttendSalarySummaryBillRel);
		model.addAttribute("page", summaryBillList);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendSalarySummaryBillRel";
	}

}