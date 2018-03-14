
package cn.damei.web.modules;

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
import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPmAttendSalaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;
import cn.damei.service.modules.BizPmAttendSalaryBillService;
import cn.damei.service.modules.BizPmAttendSalarySummaryBillService;
import cn.damei.service.modules.SysSequenceService;


@Controller
@RequestMapping(value = "${adminPath}/attend/bizPmAttendSalarySummaryBill")
public class BizPmAttendSalarySummaryBillController extends BaseController {

	@Autowired
	private BizPmAttendSalarySummaryBillService bizPmAttendSalarySummaryBillService;
	
	@Autowired
	private BizPmAttendSalaryBillService bizPmAttendSalaryBillService;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizPmAttendSalarySummaryBill get(@RequestParam(required=false) String id) {
		BizPmAttendSalarySummaryBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizPmAttendSalarySummaryBillService.get(id);
		}
		if (entity == null){
			entity = new BizPmAttendSalarySummaryBill();
		}
		return entity;
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmAttendSalarySummaryBill> page = bizPmAttendSalarySummaryBillService.findPage(new Page<BizPmAttendSalarySummaryBill>(request, response), bizPmAttendSalarySummaryBill); 
		model.addAttribute("page", page);
		return "modules/attend/bizPmAttendSalarySummaryBillList";
	}

	@RequiresPermissions("attend:bizPmAttendSalarySummaryBill:view")
	@RequestMapping(value = "form")
	public String form(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill, Model model) {
		model.addAttribute("bizPmAttendSalarySummaryBill", bizPmAttendSalarySummaryBill);
		return "modules/attend/bizPmAttendSalarySummaryBillForm";
	}

	@RequiresPermissions("attend:bizPmAttendSalarySummaryBill:edit")
	@RequestMapping(value = "save")
	public String save(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill,BizPmAttendSalaryBill bizPmAttendSalaryBill,String salaryBillIds,double money, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, bizPmAttendSalarySummaryBill)){
			return form(bizPmAttendSalarySummaryBill, model);
		}
		Date date = new Date();
		bizPmAttendSalarySummaryBill.setGeneratedDatetime(date);
		bizPmAttendSalarySummaryBill.setStatus("10");
		String sequence = sysSequenceService.getSequence(BizAttendBillConstantUtil.GZPC_NO);

		String gzpcNo = sequence.substring(0,4);

		String No = sequence.substring(2);

		String date1 = BizAttendBillConstantUtil.getDate(new Date());

		String[] split = salaryBillIds.split(",");
		Boolean flag = true;
		for(int i=0; i<split.length;i++){
			bizPmAttendSalaryBill.setSalaryBillId(split[i]);
			BizPmAttendSalaryBill findSalaryBill = bizPmAttendSalaryBillService.findSalaryBill(bizPmAttendSalaryBill);
			if(findSalaryBill != null){
				flag = false;
				break;
			}
		}
		if(flag){
			bizPmAttendSalarySummaryBill.setPmAttendSalarySummaryBillCode(gzpcNo+date1+No);
			bizPmAttendSalarySummaryBillService.save(bizPmAttendSalarySummaryBill);
			BizPmAttendSalarySummaryBillRel bobo = new BizPmAttendSalarySummaryBillRel();
			bizPmAttendSalaryBill.setPmAttendSalarySummaryBillId(bizPmAttendSalarySummaryBill.getId());
			bobo.setPmAttendSalarySummaryBillId(bizPmAttendSalarySummaryBill.getId());
			for(int i=0; i<split.length;i++){
				bizPmAttendSalaryBill.setSalaryBillId(split[i]);
				bobo.setPmAttendSalaryBillId(split[i]);
				bizPmAttendSalaryBillService.updateStatus(bizPmAttendSalaryBill);
				bizPmAttendSalarySummaryBillService.insertBillRel(bobo);
			}
			addMessage(redirectAttributes, "保存月度工资单批次成功");
			if(null!=bizPmAttendSalaryBill.getStoreId()&&null!=bizPmAttendSalaryBill.getEnginDepartId()){
				return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/findSalaryBillAuditingBatch?storeId="+bizPmAttendSalaryBill.getStoreId()+"&enginDepartId="+bizPmAttendSalaryBill.getEnginDepartId();
			}else{
				return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/salaryBillAuditingBatch?repage";
			}
		}else{
			addMessage(redirectAttributes, "有重复数据，请重新刷新页面查询后再试。");
			return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalaryBill/salaryBillAuditingBatch";
		}
	}
	
	@RequiresPermissions("attend:bizPmAttendSalarySummaryBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill, RedirectAttributes redirectAttributes) {
		bizPmAttendSalarySummaryBillService.delete(bizPmAttendSalarySummaryBill);
		addMessage(redirectAttributes, "删除月度工资单批次成功");
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendSalarySummaryBill/?repage";
	}

}