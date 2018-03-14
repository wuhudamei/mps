package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAssessRule;
import cn.damei.service.modules.BizAssessRuleService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizAssessRule/bizAssessRule")
public class BizAssessRuleController extends BaseController  {

	@Autowired
	private BizAssessRuleService bizAssessRuleService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequestMapping(value = "queryBizAssessRule")
	public String queryBizAssessRule(BizAssessRule bizAssessRule, HttpServletRequest request,
			HttpServletResponse response, Model model){
		User user = UserUtils.getUser();


		if (null == bizAssessRule.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRule.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRule.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRule.setProjectMode(be.getProjectMode());
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
						bizAssessRule.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizAssessRule> page = bizAssessRuleService.findPage(new Page<BizAssessRule>(request, response),
				bizAssessRule);
		model.addAttribute("page", page);
		return "modules/bizAssessRule/bizAssessRuleList";
	}
	
	@RequestMapping(value = "openBizAssessRuleForm")
	public String openBizAssessRuleForm(BizAssessRule bizAssessRule, Model model) {
		User user = UserUtils.getUser();

		if (null == bizAssessRule.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRule.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRule.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRule.setProjectMode(be.getProjectMode());
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
						bizAssessRule.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		if(bizAssessRule.getId() != null){
			bizAssessRule = bizAssessRuleService.get(bizAssessRule);
		}
		if(bizAssessRule.getIsEnabled() == null){
			bizAssessRule.setIsEnabled(1);
		}
		model.addAttribute("bizAssessRule",bizAssessRule);
		return "modules/bizAssessRule/bizAssessRuleForm";
	}
	
	@RequestMapping(value = "saveBizAssessRule")
	public String saveBizAssessRule(BizAssessRule bizAssessRule, RedirectAttributes redirectAttributes,Model model) {
		if (!beanValidator(model, bizAssessRule)) {
			return openBizAssessRuleForm(bizAssessRule, model);
		}
		bizAssessRuleService.save(bizAssessRule);
		addMessage(redirectAttributes, "保存成功!");
		return "redirect:" + Global.getAdminPath() + "/bizAssessRule/bizAssessRule/queryBizAssessRule?repage";
	}
	
	@RequestMapping(value = "enable")
	public String enable(BizAssessRule bizAssessRule, RedirectAttributes redirectAttributes) {
		BizAssessRule assessRule = bizAssessRuleService.get(bizAssessRule);
		assessRule.setIsEnabled(bizAssessRule.getIsEnabled());
		bizAssessRuleService.save(assessRule);
		addMessage(redirectAttributes, DictUtils.getDictLabel(bizAssessRule.getIsEnabled() + "", "biz_enable_status", "") + "条例细则成功");
		return "redirect:" + Global.getAdminPath() + "/bizAssessRule/bizAssessRule/queryBizAssessRule?repage";
	}
	
	@RequestMapping(value = "queryAssessRuleByAssessRuleType")
	public @ResponseBody List<BizAssessRule> queryAssessRuleByAssessRuleType(BizAssessRule bizAssessRule){
		return bizAssessRuleService.findList(bizAssessRule);
	}
	
	@RequestMapping(value = "findBizAssessRuleById")
	public @ResponseBody BizAssessRule findBizAssessRuleById(Integer id){
		return bizAssessRuleService.get(id);
	}
}
