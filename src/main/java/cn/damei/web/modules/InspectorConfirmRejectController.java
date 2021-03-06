
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.InspectorConfirmReject;
import cn.damei.service.modules.InspectorConfirmRejectService;


@Controller
@RequestMapping(value = "${adminPath}/modules/settlementPaymentManagement/checkAccept/confirmReject/web/InspectorConfirmReject")
public class InspectorConfirmRejectController extends BaseController {

	@Autowired
	private InspectorConfirmRejectService inspectorConfirmRejectService;

	@ModelAttribute
	public InspectorConfirmReject get(@RequestParam(required = false) Integer id) {
		InspectorConfirmReject entity = null;
		if (StringUtils.isNotBlank(id+"")) {
			entity = inspectorConfirmRejectService.get(id);
		}
		if (entity == null) {
			entity = new InspectorConfirmReject();
		}
		return entity;
	}


	@RequiresPermissions("confirmReject:confirmReject:view")
	@RequestMapping(value = { "/preList", "" })
	public String preList(InspectorConfirmReject inspectorConfirmReject, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		inspectorConfirmRejectService.queryStoreIdAndprojectModeAndEnginDepart(inspectorConfirmReject, model);
		
		return "modules/settlementPaymentManagement/checkAccept/confirmReject/confirmRejectList";
	}


	@RequiresPermissions("confirmReject:confirmReject:view")
	@RequestMapping(value = { "/list", "" })
	public String list(InspectorConfirmReject inspectorConfirmReject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		inspectorConfirmRejectService.queryStoreIdAndprojectModeAndEnginDepart(inspectorConfirmReject, model);
		
		Page<InspectorConfirmReject> page = inspectorConfirmRejectService.findPage(new Page<InspectorConfirmReject>(request, response),inspectorConfirmReject);
		model.addAttribute("page", page);
		
		return "modules/settlementPaymentManagement/checkAccept/confirmReject/confirmRejectList";
	}

	

}