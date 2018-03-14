package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderInstallAcceptanceDetail;
import cn.damei.service.modules.BizOrderInstallAcceptanceDetailService;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallacceptdetail/bizOrderInstallAcceptDetail")
public class BizOrderInstallAcceptanceDetailController extends BaseController {

	@Autowired
	private BizOrderInstallAcceptanceDetailService bizOrderInstallDetailService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;

	@Autowired
	private DataAuthorityService dataAuthorityService;
	@ModelAttribute
	public BizOrderInstallAcceptanceDetail get(@RequestParam(required = false) Integer id) {
		BizOrderInstallAcceptanceDetail entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderInstallDetailService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderInstallAcceptanceDetail();
		}
		return entity;
	}

	@RequiresPermissions("bizorderinstallacceptdetail:bizOrderInstallAcceptDetail:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderInstallAcceptanceDetail bizOrderInstallAcceptanceDetail, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if(null == bizOrderInstallAcceptanceDetail.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderInstallAcceptanceDetail.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(StringUtils.isBlank(be.getProjectMode())){
				model.addAttribute("gongcheng", true);
			}else{
				if(be.getProjectMode().equals("3")){
					model.addAttribute("gongcheng", true);
				}else{
					bizOrderInstallAcceptanceDetail.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}

		return "modules/bizEnginInstall/bizInstallAcceptList";
	}
	
	@RequiresPermissions("bizorderinstallacceptdetail:bizOrderInstallAcceptDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderInstallAcceptanceDetail bizOrderInstallAcceptanceDetail, HttpServletRequest request, 
			HttpServletResponse response, Model model) throws IOException {
		User user = UserUtils.getUser();

		
		
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizOrderInstallAcceptanceDetail.setPhones(orderdPhones);
		
		String userId = user.getId();
		bizOrderInstallAcceptanceDetail.setUserId(userId);
		
		

		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(StringUtils.isBlank(be.getProjectMode())){
				model.addAttribute("gongcheng", true);
			}else{
				if(be.getProjectMode().equals("3")){
					model.addAttribute("gongcheng", true);
				}else{
					bizOrderInstallAcceptanceDetail.setProjectMode(be.getProjectMode());
				}
			}
		}else{
			model.addAttribute("gongcheng", true);
		}

		Page<BizOrderInstallAcceptanceDetail> page = bizOrderInstallDetailService.findPage(new 
				Page<BizOrderInstallAcceptanceDetail>(request, response), bizOrderInstallAcceptanceDetail);
		
		model.addAttribute("page", page);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "modules/bizEnginInstall/bizInstallAcceptList";
	}
}
