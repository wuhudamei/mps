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
import cn.damei.entity.modules.BizAssessRuleType;
import cn.damei.service.modules.BizAssessRuleTypeService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 考核条例分类Controllers
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/bizAssessRuleType/bizAssessRuleType")
public class BizAssessRuleTypeController extends BaseController {

	@Autowired
	private BizAssessRuleTypeService bizAssessRuleTypeService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@RequestMapping(value = "queryBizAssessRuleType")
	public String queryBizAssessRuleType(BizAssessRuleType bizAssessRuleType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		// 过滤门店
		if (null == bizAssessRuleType.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRuleType.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizAssessRuleType.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRuleType.setProjectMode(be.getProjectMode());
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
						bizAssessRuleType.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizAssessRuleType> page = bizAssessRuleTypeService.findPage(new Page<BizAssessRuleType>(request, response),
				bizAssessRuleType);
		model.addAttribute("page", page);
		return "modules/bizAssessRuleType/bizAssessRuleTypeList";
	}

	@RequestMapping(value = "openBizAssessRuleTypeForm")
	public String openBizAssessRuleTypeForm(BizAssessRuleType bizAssessRuleType, Model model) {
		
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizAssessRuleType.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRuleType.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizAssessRuleType.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRuleType.setProjectMode(be.getProjectMode());
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
						bizAssessRuleType.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		if(bizAssessRuleType.getId() != null){
			bizAssessRuleType = bizAssessRuleTypeService.get(bizAssessRuleType);
		}
		if(bizAssessRuleType.getIsEnabled() == null){
			bizAssessRuleType.setIsEnabled(1);
		}
		if(bizAssessRuleType.getIsMonthInspection() == null){
			bizAssessRuleType.setIsMonthInspection("0");
		}
		model.addAttribute("bizAssessRuleType",bizAssessRuleType);
		return "modules/bizAssessRuleType/bizAssessRuleTypeForm";
	}

	@RequestMapping(value = "saveBizAssessRuleType")
	public String saveBizAssessRuleType(BizAssessRuleType bizAssessRuleType, RedirectAttributes redirectAttributes,Model model) {
		if (!beanValidator(model, bizAssessRuleType)) {
			return openBizAssessRuleTypeForm(bizAssessRuleType, model);
		}
		bizAssessRuleTypeService.save(bizAssessRuleType);
		addMessage(redirectAttributes, "保存成功!");
		return "redirect:" + Global.getAdminPath() + "/bizAssessRuleType/bizAssessRuleType/queryBizAssessRuleType?repage";
	}
	
	@RequestMapping(value = "enable")
	public String enable(BizAssessRuleType bizAssessRuleType, RedirectAttributes redirectAttributes) {
		BizAssessRuleType assessRuleType = bizAssessRuleTypeService.get(bizAssessRuleType);
		assessRuleType.setIsEnabled(bizAssessRuleType.getIsEnabled());
		bizAssessRuleTypeService.save(assessRuleType);
		addMessage(redirectAttributes, DictUtils.getDictLabel(bizAssessRuleType.getIsEnabled() + "", "biz_enable_status", "") + "分类成功");
		return "redirect:" + Global.getAdminPath() + "/bizAssessRuleType/bizAssessRuleType/queryBizAssessRuleType?repage";
	}
	
	/**
	 * 根据门店和工程模式获取条例分类
	 * @return
	 */
	@RequestMapping(value = "queryRuleTypeByParam")
	public @ResponseBody List<BizAssessRuleType> queryRuleTypeByParam(BizAssessRuleType bizAssessRuleType){
		return bizAssessRuleTypeService.queryRuleTypeByParam(bizAssessRuleType);
	}

}
