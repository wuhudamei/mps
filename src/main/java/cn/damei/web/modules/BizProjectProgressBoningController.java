
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizProjectProgressBoning;
import cn.damei.service.modules.BizProjectProgressBoningService;
import cn.damei.service.modules.BizProjectProgressSummaryDataService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "${adminPath}/projectprogressboning/bizProjectProgressBoning")
public class BizProjectProgressBoningController extends BaseController {

	@Autowired
	private BizProjectProgressBoningService bizProjectProgressBoningService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizProjectProgressSummaryDataService bizProjectProgressSummaryDataService;

	@ModelAttribute
	public BizProjectProgressBoning get(@RequestParam(required = false) Integer id) {
		BizProjectProgressBoning entity = null;
		if (id != null) {
			entity = bizProjectProgressBoningService.get(id);
		}
		if (entity == null) {
			entity = new BizProjectProgressBoning();
		}
		return entity;
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoning:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizProjectProgressBoning bizProjectProgressBoning, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		if (bizProjectProgressBoning.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizProjectProgressBoning.setStoreId(null);
			} else {
				bizProjectProgressBoning.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizProjectProgressBoning.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}


		if (bizProjectProgressBoning.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizProjectProgressBoning.setEnginDepartIds(list);
				} else {
					bizProjectProgressBoning.setEnginDepartIds(null);
				}
			} else {
				bizProjectProgressBoning.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizProjectProgressBoning.getEnginDepartId());
			bizProjectProgressBoning.setEnginDepartIds(list);
		}
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizProjectProgressBoning.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizProjectProgressBoning.setProjectMode(be.getProjectMode());
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
						bizProjectProgressBoning.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		if(bizProjectProgressBoning.getIsScrap() == null ){
			bizProjectProgressBoning.setIsScrap(0);
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizProjectProgressBoning", bizProjectProgressBoning);
		return "modules/projectprogressboning/bizProjectProgressBoningList";
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoning:view")
	@RequestMapping(value = { "loadList", "" })
	public String loadList(BizProjectProgressBoning bizProjectProgressBoning, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		if (bizProjectProgressBoning.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizProjectProgressBoning.setStoreId(null);
			} else {
				bizProjectProgressBoning.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizProjectProgressBoning.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}


		if (bizProjectProgressBoning.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizProjectProgressBoning.setEnginDepartIds(list);
				} else {
					bizProjectProgressBoning.setEnginDepartIds(null);
				}
			} else {
				bizProjectProgressBoning.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizProjectProgressBoning.getEnginDepartId());
			bizProjectProgressBoning.setEnginDepartIds(list);
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizProjectProgressBoning.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizProjectProgressBoning.setProjectMode(be.getProjectMode());
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
						bizProjectProgressBoning.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizProjectProgressBoning> page = bizProjectProgressBoningService
				.findPage(new Page<BizProjectProgressBoning>(request, response), bizProjectProgressBoning);
		model.addAttribute("page", page);
		model.addAttribute("bizProjectProgressBoning", bizProjectProgressBoning);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/projectprogressboning/bizProjectProgressBoningList";
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoning:view")
	@RequestMapping(value = { "list1", "" })
	public String list1(BizProjectProgressBoning bizProjectProgressBoning, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		User user = UserUtils.getUser();
		String projectMode = user.getProjectMode();
		if (projectMode == null || projectMode.equals("3")) {
			if (bizProjectProgressBoning.getProjectMode() != null) {
				bizProjectProgressBoning.setProjectMode(bizProjectProgressBoning.getProjectMode());
			}
		} else {
			bizProjectProgressBoning.setProjectMode(projectMode);
			model.addAttribute("projectModeEnable", true);
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizProjectProgressBoning", bizProjectProgressBoning);
		return "modules/projectprogressboning/bizProjectProgressBoningList1";
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoning:view")
	@RequestMapping(value = { "loadList1", "" })
	public String loadList1(BizProjectProgressBoning bizProjectProgressBoning, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (bizProjectProgressBoning.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizProjectProgressBoning.setStoreId(null);
			} else {
				bizProjectProgressBoning.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		User user = UserUtils.getUser();
		String projectMode = user.getProjectMode();
		if (projectMode.equals("3")) {
			bizProjectProgressBoning.setProjectMode(bizProjectProgressBoning.getProjectMode());
			if ("3".equals(bizProjectProgressBoning.getProjectMode())) {
				bizProjectProgressBoning.setProjectMode(null);
			}
		} else {
			bizProjectProgressBoning.setProjectMode(projectMode);
			model.addAttribute("projectModeEnable", true);
		}

		if (bizProjectProgressBoning.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizProjectProgressBoning.setEnginDepartIds(list);
				} else {
					bizProjectProgressBoning.setEnginDepartIds(null);
				}
			} else {
				bizProjectProgressBoning.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizProjectProgressBoning.getEnginDepartId());
			bizProjectProgressBoning.setEnginDepartIds(list);
		}

		Page<BizProjectProgressBoning> page = bizProjectProgressBoningService
				.findPage1(new Page<BizProjectProgressBoning>(request, response), bizProjectProgressBoning);
		model.addAttribute("page", page);
		model.addAttribute("bizProjectProgressBoning", bizProjectProgressBoning);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/projectprogressboning/bizProjectProgressBoningList1";
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoning:edit")
	@RequestMapping(value = { "editOrderNode", "" })
	public String editOrderNode(Integer orderId, RedirectAttributes redirectAttributes) {
		bizProjectProgressBoningService.editOrderNode(orderId);
		addMessage(redirectAttributes, "更新订单节点成功");

		return "redirect:" + Global.getAdminPath() + "/projectprogressboning/bizProjectProgressBoning/loadList?repage";
	}

	@RequestMapping(value = "exportExcel")
	public void exportExcel(BizProjectProgressBoning bizProjectProgressBoning, HttpServletResponse response)
			throws Exception {

		if (bizProjectProgressBoning.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizProjectProgressBoning.setStoreId(null);
			} else {
				bizProjectProgressBoning.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (!StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			bizProjectProgressBoning.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}


		if (bizProjectProgressBoning.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizProjectProgressBoning.setEnginDepartIds(list);
				} else {
					bizProjectProgressBoning.setEnginDepartIds(null);
				}
			} else {
				bizProjectProgressBoning.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizProjectProgressBoning.getEnginDepartId());
			bizProjectProgressBoning.setEnginDepartIds(list);
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizProjectProgressBoningService.exportExcel(bizProjectProgressBoning);
		ServletOutputStream out = null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("工程进度大看板" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
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

	@RequestMapping(value = { "openBizProjectProgressBoningExplain", "" })
	public String openBizProjectProgressBoningExplain(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return "modules/projectprogressboning/bizProjectProgressBoningExplain";
	}
}