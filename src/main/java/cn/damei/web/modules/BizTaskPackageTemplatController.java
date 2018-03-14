
package cn.damei.web.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizTaskPackageTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageTemplatBugetAmount;
import cn.damei.entity.modules.BizTaskPackageTemplatRelProcedure;
import cn.damei.service.modules.BizTaskPackageTemplatBugetAmountService;
import cn.damei.service.modules.BizTaskPackageTemplatService;


@Controller
@RequestMapping(value = "${adminPath}/taskpackage/bizTaskPackageTemplat")
public class BizTaskPackageTemplatController extends BaseController {

	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	@Autowired
	private BizTaskPackageTemplatRelDao bizTaskPackageTemplatRelDao;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizTaskPackageTemplatBugetAmountService bizTaskPackageTemplatBugetAmountService;

	@ModelAttribute
	public BizTaskPackageTemplat get(@RequestParam(required = false) String id) {
		BizTaskPackageTemplat entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizTaskPackageTemplatService.get(id);
		}
		if (entity == null) {
			entity = new BizTaskPackageTemplat();
		}
		return entity;
	}

	@RequiresPermissions("taskpackage:bizTaskPackageTemplat:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizTaskPackageTemplat bizTaskPackageTemplat, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizTaskPackageTemplat.getStoreId())) {
			bizTaskPackageTemplat.setStoreId(user.getStoreId());
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizTaskPackageTemplat.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizTaskPackageTemplat.setProjectMode(be.getProjectMode());
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
						bizTaskPackageTemplat.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizTaskPackageTemplat> param = new Page<BizTaskPackageTemplat>(request, response);
		param.setOrderBy(bizTaskPackageTemplat.getFrontSort());

		Page<BizTaskPackageTemplat> page = bizTaskPackageTemplatService.findPage(param, bizTaskPackageTemplat);
		model.addAttribute("page", page);
		return "modules/taskpackage/bizTaskPackageTemplatList";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageTemplat:view")
	@RequestMapping(value = "form")
	public String form(BizTaskPackageTemplat bizTaskPackageTemplat, Model model) {
		if (StringUtils.isBlank(bizTaskPackageTemplat.getStoreId())) {
			bizTaskPackageTemplat.setStoreId(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		if (StringUtils.isBlank(bizTaskPackageTemplat.getIsQualityGuarantee())) {
			bizTaskPackageTemplat.setIsQualityGuarantee("0");
		}
		if (StringUtils.isBlank(bizTaskPackageTemplat.getSettleStyle())) {
			bizTaskPackageTemplat.setSettleStyle("1");
		}
		if(bizTaskPackageTemplat.getProjectMode() == null || bizTaskPackageTemplat.getProjectMode().equals("")){
			bizTaskPackageTemplat.setProjectMode(UserUtils.getUser().getProjectMode());
		}
		if (StringUtils.isNotBlank(bizTaskPackageTemplat.getId())) {


			List<BizTaskPackageTemplatRelProcedure> list = bizTaskPackageTemplatRelDao
					.findByTaskPackageRelProcedureTemplateId(bizTaskPackageTemplat.getId());
			bizTaskPackageTemplat.setBizTaskPackageTemplatRefs(list);
			StringBuffer sb = new StringBuffer();
			for (BizTaskPackageTemplatRelProcedure bizTaskPackageTemplatRel : list) {
				sb.append(bizTaskPackageTemplatRel.getProcedureNo());
				sb.append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
				bizTaskPackageTemplat.setProcedureSelect(sb.toString());
			}
		}
		model.addAttribute("bizTaskPackageTemplat", bizTaskPackageTemplat);
		return "modules/taskpackage/bizTaskPackageTemplatForm";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageTemplat:edit")
	@RequestMapping(value = "save")
	public String save(BizTaskPackageTemplat bizTaskPackageTemplat, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTaskPackageTemplat)) {
			return form(bizTaskPackageTemplat, model);
		}
		if (bizTaskPackageTemplat.getId() == null || "".equals(bizTaskPackageTemplat.getId())) {
			bizTaskPackageTemplat.setNo(sysSequenceService.getSequence("RT"));
		}
		if ("0".equals(bizTaskPackageTemplat.getIsQualityGuarantee())) {
			bizTaskPackageTemplat.setQualityGuaranteeRate("0");
		}
		double b = 0d;
		if(null != bizTaskPackageTemplat.getApplyBudgetRatio()){
			b = bizTaskPackageTemplat.getApplyBudgetRatio() * 0.01;
		}
		bizTaskPackageTemplat.setApplyBudgetRatioDouble(b);
         
		bizTaskPackageTemplatService.save(bizTaskPackageTemplat);
		addMessage(redirectAttributes, "保存任务包模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageTemplat/?repage";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageTemplat:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTaskPackageTemplat bizTaskPackageTemplat, RedirectAttributes redirectAttributes) {
		bizTaskPackageTemplatService.delete(bizTaskPackageTemplat);
		addMessage(redirectAttributes, "删除任务包模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageTemplat/?repage";
	}

	@RequiresPermissions("taskpackage:bizTaskPackageTemplat:edit")
	@RequestMapping(value = "enable")
	public String enable(BizTaskPackageTemplat bizTaskPackageTemplat, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizTaskPackageTemplat.getStatus());
		bizTaskPackageTemplat.setStatus(status + "");
		bizTaskPackageTemplatService.update(bizTaskPackageTemplat);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status + "", "biz_enable_status", "") + "任务包模板成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageTemplat/?repage";
	}

    @ResponseBody
    @RequestMapping(value = "taskListByNowStoreId")
    public String getTaskListByNowStoreId(BizEmployee bizEmployee, Model model, HttpServletRequest request) {

        List<DropModel> list2 = bizTaskPackageTemplatService.getTaskList(bizEmployee);
   
        return JsonMapper.getInstance().toJson(list2);
    }

	@ResponseBody
	@RequestMapping(value = "taskListByStoreIdAndProjectMode")
	public List<DropModel> getTaskListByStoreIdAndProjectMode(String storeId,String projectMode) {
		return bizTaskPackageTemplatService.queryTaskPackageByStoreIdAndProjectMode(storeId,projectMode);
	}
	
	@RequestMapping(value = "queryTemplatBudgetAmountList")
	public String queryTemplatBudgetAmountList(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount, HttpServletRequest request,
			HttpServletResponse response, Model model){
		Page<BizTaskPackageTemplatBugetAmount> page = bizTaskPackageTemplatBugetAmountService.findPage(new Page<BizTaskPackageTemplatBugetAmount>(request, response), bizTaskPackageTemplatBugetAmount);
		model.addAttribute("page", page);
		return "modules/taskpackage/templatBudgetAmountList";
	}
	
	@RequestMapping(value = "openTemplatBudgetAmountForm")
	public String openTemplatBudgetAmountForm(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount, HttpServletRequest request,
			HttpServletResponse response, Model model){
		if(bizTaskPackageTemplatBugetAmount.getId() == null){
			BizTaskPackageTemplat template = bizTaskPackageTemplatService.get(String.valueOf(bizTaskPackageTemplatBugetAmount.getTaskpackageTemplatId()));
			bizTaskPackageTemplatBugetAmount.setStoreId(template.getStoreId());
			bizTaskPackageTemplatBugetAmount.setProjectMode(template.getProjectMode());
			bizTaskPackageTemplatBugetAmount.setTemplatName(template.getTemplatName());
			bizTaskPackageTemplatBugetAmount.setIsEnabled("1");
		}else{
			bizTaskPackageTemplatBugetAmount = bizTaskPackageTemplatBugetAmountService.get(bizTaskPackageTemplatBugetAmount.getId());
		}
		model.addAttribute("bizTaskPackageTemplatBugetAmount",bizTaskPackageTemplatBugetAmount);
		return "modules/taskpackage/templatBudgetAmountForm";
	}
	
	@RequestMapping(value = "checkDate")
	public @ResponseBody Map<String,Object> checkDate(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount){
		Map<String,Object> map =new HashMap<String,Object>();
		String result="1";
		BizTaskPackageTemplatBugetAmount templatBugetAmount = bizTaskPackageTemplatBugetAmountService.checkDate(bizTaskPackageTemplatBugetAmount);
		if(templatBugetAmount != null){
			result = "2";
		}
		map.put("result", result);
		map.put("templatBugetAmount", templatBugetAmount);
		return map;
	}
	
	@RequestMapping(value = "saveTemplatBugetAmount")
	public String saveTemplatBugetAmount(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount,RedirectAttributes redirectAttributes){
		bizTaskPackageTemplatBugetAmountService.save(bizTaskPackageTemplatBugetAmount);
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageTemplat/queryTemplatBudgetAmountList?taskpackageTemplatId="+bizTaskPackageTemplatBugetAmount.getTaskpackageTemplatId()+"&repage";
	}
	
	@RequestMapping(value = "enableTemplatBugetAmount")
	public String enableTemplatBugetAmount(BizTaskPackageTemplatBugetAmount bizTaskPackageTemplatBugetAmount, RedirectAttributes redirectAttributes) {
		String status = bizTaskPackageTemplatBugetAmount.getIsEnabled();
		bizTaskPackageTemplatBugetAmount = bizTaskPackageTemplatBugetAmountService.get(bizTaskPackageTemplatBugetAmount.getId());
		bizTaskPackageTemplatBugetAmount.setIsEnabled(status + "");
		bizTaskPackageTemplatBugetAmountService.save(bizTaskPackageTemplatBugetAmount);
		addMessage(redirectAttributes, DictUtils.getDictLabel(status + "", "biz_enable_status", "") + "任务包模板预算金额上限成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackage/bizTaskPackageTemplat/queryTemplatBudgetAmountList?taskpackageTemplatId="+bizTaskPackageTemplatBugetAmount.getTaskpackageTemplatId()+"&repage";
	}
	

	@ResponseBody
    @RequestMapping(value = "check_emp_work_type_ajax")
    public String checkEmpWorkTypeAjax(BizTaskPackageTemplat bizTaskPackageTemplat, Model model, HttpServletRequest request) {
		
		String result = "0";
				
        Integer count = bizTaskPackageTemplatService.checkEmpWorkType(bizTaskPackageTemplat);
   
        if(null!=count && count>0){
        	result = "1";
        }
        
        return result;
    }
}