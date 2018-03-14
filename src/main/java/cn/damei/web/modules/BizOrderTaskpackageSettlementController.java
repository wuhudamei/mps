/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Arrays;
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
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.entity.modules.SettlementForDetail;
import cn.damei.service.modules.BizOrderTaskpackageSettlementService;
import cn.damei.service.modules.SettlementForDetailService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 结算单Controller
 * 
 * @author 汪文文
 * @version 2016-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackagesettlement/bizOrderTaskpackageSettlement")
public class BizOrderTaskpackageSettlementController extends BaseController {

	@Autowired
	private BizOrderTaskpackageSettlementService bizOrderTaskpackageSettlementService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizOrderTaskpackageSettlement get(@RequestParam(required = false) Integer id) {
		BizOrderTaskpackageSettlement entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizOrderTaskpackageSettlementService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderTaskpackageSettlement();
		}
		return entity;
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<BizOrderTaskpackageSettlement> page = bizOrderTaskpackageSettlementService
				.findPage(new Page<BizOrderTaskpackageSettlement>(request, response), bizOrderTaskpackageSettlement);
		model.addAttribute("page", page);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement, Model model) {
		model.addAttribute("bizOrderTaskpackageSettlement", bizOrderTaskpackageSettlement);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementForm";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackageSettlement)) {
			return form(bizOrderTaskpackageSettlement, model);
		}
		bizOrderTaskpackageSettlementService.save(bizOrderTaskpackageSettlement);
		addMessage(redirectAttributes, "保存结算单成功");
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagesettlement/bizOrderTaskpackageSettlement/?repage";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement,
			RedirectAttributes redirectAttributes) {
		bizOrderTaskpackageSettlementService.delete(bizOrderTaskpackageSettlement);
		addMessage(redirectAttributes, "删除结算单成功");
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagesettlement/bizOrderTaskpackageSettlement/?repage";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:view")
	@RequestMapping(value = "settlementListPage")
	public String settlementListPage(SettlementForDetail settlementForDetail, Model model) {
		User user = UserUtils.getUser();
		// 过滤区域
		if (null == settlementForDetail.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					settlementForDetail.setEnginDepartIds(list);
				} else {
					settlementForDetail.setEnginDepartIds(null);
				}
			} else {
				settlementForDetail.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(settlementForDetail.getEnginDepartId());
			settlementForDetail.setEnginDepartIds(list);
		}

		// 过滤门店
		if (settlementForDetail.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				settlementForDetail.setStoreId(null);
			} else {
				settlementForDetail.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(settlementForDetail.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						settlementForDetail.setProjectMode(be.getProjectMode());
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
						settlementForDetail.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/ordertaskpackagesettlement/settlementListPage";
	}

	@Autowired
	private SettlementForDetailService settlementForDetailService;

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlement:view")
	@RequestMapping(value = "settlementList")
	public String settlementList(SettlementForDetail settlementForDetail, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = UserUtils.getUser();
		// 过滤区域
		if (null == settlementForDetail.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					settlementForDetail.setEnginDepartIds(list);
				} else {
					settlementForDetail.setEnginDepartIds(null);
				}
			} else {
				settlementForDetail.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(settlementForDetail.getEnginDepartId());
			settlementForDetail.setEnginDepartIds(list);
		}

		// 过滤门店
		if (settlementForDetail.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				settlementForDetail.setStoreId(null);
			} else {
				settlementForDetail.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(settlementForDetail.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						settlementForDetail.setProjectMode(be.getProjectMode());
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
						settlementForDetail.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		settlementForDetail.setStatusList(null);
		if (settlementForDetail.getStatus() != null) {
			String[] split = settlementForDetail.getStatus().split(",");
			if (split != null) {
				settlementForDetail.setStatusList(Arrays.asList(split));
			}
		}
		Page<SettlementForDetail> page = settlementForDetailService
				.findPage(new Page<SettlementForDetail>(request, response), settlementForDetail);
		model.addAttribute("page", page);
		model.addAttribute("settlementForDetail", settlementForDetail);
		return "modules/ordertaskpackagesettlement/settlementListPage";
	}
}