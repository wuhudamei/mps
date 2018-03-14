package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.service.modules.BizEmployeegroupVoService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.service.modules.BizTaskPackageTemplatService;

@RequestMapping("${adminPath}/empgroup/bizEmployeegroup/")
@Controller
public class BizEmployeeGroupVoController {

	@Autowired
	private BizEmployeegroupVoService bizEmployeegroupVoService;
	
	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequiresPermissions("empgroup:bizEmployeegroup:view")
	@RequestMapping(value = "toFindFreeLeader")
	public String tofreeLeadl(@ModelAttribute BizEmployeegroupVO bizEmployeegroupVO,Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (bizEmployeegroupVO.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizEmployeegroupVO.setStoreId(null);
			} else {
				bizEmployeegroupVO.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizEmployeegroupVO.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEmployeegroupVO.setProjectMode(be.getProjectMode());
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
						bizEmployeegroupVO.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/empgroup/bizEmployeeGroupLeader";
	}

	@RequiresPermissions("empgroup:bizEmployeegroup:view")
	@RequestMapping(value = "findFreeLeader")
	public String freeLeadl(@ModelAttribute BizEmployeegroupVO bizEmployeegroupVO, String taskPackageId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (bizEmployeegroupVO.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizEmployeegroupVO.setStoreId(null);
			} else {
				bizEmployeegroupVO.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizEmployeegroupVO.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEmployeegroupVO.setProjectMode(be.getProjectMode());
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
						bizEmployeegroupVO.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		Page<BizEmployeegroupVO> page = bizEmployeegroupVoService
				.findFreeLeader(new Page<BizEmployeegroupVO>(request, response), bizEmployeegroupVO);
		
		BizTaskPackageTemplat packageTemplat = bizTaskPackageTemplatService.get(taskPackageId);
		model.addAttribute("packageName", packageTemplat.getTemplatName());
		model.addAttribute("page", page);
		
		return "modules/empgroup/bizEmployeeGroupLeader";
	}

	
}
