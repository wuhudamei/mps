package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAuxiliaryMaterialsVerify;
import cn.damei.entity.modules.BizAuxiliaryMaterialsVerifyInclude;
import cn.damei.service.modules.BizAuxiliaryMaterialsVerifyIncludeService;
import cn.damei.service.modules.BizAuxiliaryMaterialsVerifyService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.service.modules.BizSupplierService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/verify/bizAuxiliaryMaterialsVerify")
public class BizAuxiliaryMaterialsVerifyController extends BaseController {
	@Autowired
	private BizAuxiliaryMaterialsVerifyService bizAuxiliaryMaterialsVerifyService;
	
	@Autowired
	private BizSupplierService bizSupplierService;
	
	@Autowired
	private BizAuxiliaryMaterialsVerifyIncludeService bizAuxiliaryMaterialsVerifyIncludeService;


	@RequestMapping(value = "openApplyVerifyPage")
	public String openApplyVerifyPage(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/bizverify/applyVerify";
	}
	

	@RequestMapping(value = "openEngineeringVerifyPage")
	public String openEngineeringVerifyPage(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify,
			HttpServletRequest request, HttpServletResponse response, Model model){
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/bizverify/engineeringVerify";
	}
	

	@RequestMapping(value = "openWangzhenVerifyPage")
	public String openWangzhenVerifyPage(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify,
			HttpServletRequest request, HttpServletResponse response, Model model){
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/bizverify/wangzhenVerify";
	}


	@RequestMapping(value = "createVerify")
	public @ResponseBody String createVerify(Integer storeId, String startDate, String endDate, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String result = bizAuxiliaryMaterialsVerifyService.createVerify(storeId, startDate, endDate);
		return result;
	}
	

	@RequestMapping(value = "list")
	public String list(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify, HttpServletRequest request,
			HttpServletResponse response, Model model){
		List<BizSupplier> supplierList = bizSupplierService.findListByPhone(UserUtils.getUser().getPhone());
		if(supplierList != null && supplierList.size()>0){
			bizAuxiliaryMaterialsVerify.setSupplierId(supplierList.get(0).getId());
			Page<BizAuxiliaryMaterialsVerify> page = bizAuxiliaryMaterialsVerifyService.findPage(new Page<BizAuxiliaryMaterialsVerify>(request, response), bizAuxiliaryMaterialsVerify);
			model.addAttribute("page", page);
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/bizverify/applyVerify";
	}

	@RequestMapping(value = "engineeringVerifyList")
	public String engineeringVerifyList(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify, HttpServletRequest request,
			HttpServletResponse response, Model model){
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		Page<BizAuxiliaryMaterialsVerify> page = bizAuxiliaryMaterialsVerifyService.findPage(new Page<BizAuxiliaryMaterialsVerify>(request, response), bizAuxiliaryMaterialsVerify);
		model.addAttribute("page", page);
		return "modules/bizverify/engineeringVerify";
	}
	

	@RequestMapping(value = "wangzhenVerifyList")
	public String wangzhenVerifyList(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify, HttpServletRequest request,
			HttpServletResponse response, Model model){
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		Page<BizAuxiliaryMaterialsVerify> page = bizAuxiliaryMaterialsVerifyService.findPage(new Page<BizAuxiliaryMaterialsVerify>(request, response), bizAuxiliaryMaterialsVerify);
		model.addAttribute("page", page);
		return "modules/bizverify/wangzhenVerify";
	}

	@RequestMapping(value = "verifyAuxiliaryDetail")
	public String verifyAuxiliaryDetail(BizAuxiliaryMaterialsVerifyInclude bizAuxiliaryMaterialsVerifyInclude,HttpServletRequest request,
			HttpServletResponse response, Model model){
		String result=null;
		BizAuxiliaryMaterialsVerify verify = bizAuxiliaryMaterialsVerifyService.get(bizAuxiliaryMaterialsVerifyInclude.getAuxiliaryMaterialsVerifyId());
		model.addAttribute("verify",verify);
		Page<BizAuxiliaryMaterialsVerifyInclude> page = bizAuxiliaryMaterialsVerifyIncludeService.findPage(new Page<BizAuxiliaryMaterialsVerifyInclude>(request, response), bizAuxiliaryMaterialsVerifyInclude);
		model.addAttribute("page", page);
		model.addAttribute("groupType",bizAuxiliaryMaterialsVerifyInclude.getGroupType());
		model.addAttribute("auxiliaryMaterialsVerifyId",bizAuxiliaryMaterialsVerifyInclude.getAuxiliaryMaterialsVerifyId());
		if(bizAuxiliaryMaterialsVerifyInclude.getGroupType().equals("1")){
			result = "modules/bizverify/supplierVerifyDetail";
		}else if(bizAuxiliaryMaterialsVerifyInclude.getGroupType().equals("2")){
			result =  "modules/bizverify/engineeringVerifyDetail";
		}else if(bizAuxiliaryMaterialsVerifyInclude.getGroupType().equals("3")){
			result =  "modules/bizverify/wangzhenVerifyDetail";
		}
		return result;
	}
	@RequestMapping(value = "updateVerifyStatus")
	public String updateVerifyStatus(String type,Integer verifyId,String status,String cancelReason,HttpServletRequest request,
			HttpServletResponse response, Model model){
		BizAuxiliaryMaterialsVerify verify = bizAuxiliaryMaterialsVerifyService.get(verifyId);
		verify.setStatus(status);
		if(cancelReason!=null && !cancelReason.equals("")){
			verify.setCancelReason(cancelReason);
		}
        bizAuxiliaryMaterialsVerifyService.updateVerifyStatus(verify);
        String result=null;
        if(type.equals("1")){
        	result="redirect:" + Global.getAdminPath() + "/verify/bizAuxiliaryMaterialsVerify/list";
        }else if(type.equals("2")){
        	result="redirect:" + Global.getAdminPath() + "/verify/bizAuxiliaryMaterialsVerify/engineeringVerifyList";
        }else if(type.equals("3")){
        	result="redirect:" + Global.getAdminPath() + "/verify/bizAuxiliaryMaterialsVerify/wangzhenVerifyList";
        }
        return result;
	}
	
}
