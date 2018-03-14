package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderInstallDetail;
import cn.damei.service.modules.BizOrderInstallDetailService;
import cn.damei.service.modules.BizEmployeeService2;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstalldetail/bizOrderInstallDetail")
public class BizOrderInstallDetailController extends BaseController {

	@Autowired
	private BizOrderInstallDetailService bizOrderInstallDetailService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizOrderInstallDetail get(@RequestParam(required = false) Integer id) {
		BizOrderInstallDetail entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderInstallDetailService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderInstallDetail();
		}
		return entity;
	}

	@RequiresPermissions("bizorderinstalldetail:bizOrderInstallDetail:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallDetail bizOrderInstallDetail, Model model) {
        bizOrderInstallDetailService.projectModeAndStore(bizOrderInstallDetail, model, this);

		return "modules/bizEnginInstall/bizInstallDetailList";
	}


	@RequiresPermissions("bizorderinstalldetail:bizOrderInstallDetail:view")
	@RequestMapping(value = "list")
	public String list(BizOrderInstallDetail bizOrderInstallDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        bizOrderInstallDetailService.projectModeAndStore(bizOrderInstallDetail, model, this);
		if (null != bizOrderInstallDetail.getInstallStatus()) {
			String[] list = bizOrderInstallDetail.getInstallStatus().split(",");
			bizOrderInstallDetail.setInstallList(Arrays.asList(list));
		}
		Page<BizOrderInstallDetail> page = bizOrderInstallDetailService.findPage(new Page<BizOrderInstallDetail>(request, response), bizOrderInstallDetail);
		model.addAttribute("page", page);
		return "modules/bizEnginInstall/bizInstallDetailList";
	}

    @RequiresPermissions("bizorderinstalldetail:bizOrderInstallDetail:view")
	@RequestMapping(value = "preListInstall")
	public String preListInstall(BizOrderInstallDetail bizOrderInstallDetail, Model model) {
        bizOrderInstallDetailService.projectModeAndStore(bizOrderInstallDetail, model, this);
		return "modules/bizEnginInstall/InstallDetailList_install1";
	}

	@RequiresPermissions("bizorderinstalldetail:bizOrderInstallDetail:view")
	@RequestMapping(value = "listInstall")
	public String listInstall(BizOrderInstallDetail bizOrderInstallDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        bizOrderInstallDetailService.projectModeAndStore(bizOrderInstallDetail, model, this);

		if (null != bizOrderInstallDetail.getInstallStatus()) {
			logger.info("复选框选择的value：" + bizOrderInstallDetail.getInstallStatus());
			String[] list = bizOrderInstallDetail.getInstallStatus().split(",");
			bizOrderInstallDetail.setInstallList(Arrays.asList(list));
		}
		Page<BizOrderInstallDetail> page = bizOrderInstallDetailService.findPagezlist(new Page<BizOrderInstallDetail>(request, response), bizOrderInstallDetail);
		model.addAttribute("page", page);
		return "modules/bizEnginInstall/InstallDetailList_install1";
	}
}
