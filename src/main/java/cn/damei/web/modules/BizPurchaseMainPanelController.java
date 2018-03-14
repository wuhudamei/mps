package cn.damei.web.modules;

import java.io.IOException;
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

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizPurchaseMainPanel;
import cn.damei.entity.modules.MainPanel;
import cn.damei.service.modules.BizPurchaseMainPanelService;
import cn.damei.service.modules.MainPanelService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "${adminPath}/purchase/bizPurchaseMainPanel")
public class BizPurchaseMainPanelController extends BaseController {
	
	@Autowired
	private BizPurchaseMainPanelService bizPurchaseMainPanelService;
	@Autowired
	private MainPanelService mainPanelService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizPurchaseMainPanel get(@RequestParam(required=false) Integer id) {
		BizPurchaseMainPanel entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPurchaseMainPanelService.get(id);
		}
		if (entity == null){
			entity = new BizPurchaseMainPanel();
		}
		return entity;
	}
	@RequiresPermissions("purchase:bizPurchaseMainPanel:view")
	@RequestMapping(value= "panelListPage")
	public String panelListPage(BizPurchaseMainPanel bizPurchaseMainPanel,HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{

			bizPurchaseMainPanel.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{

		if(bizPurchaseMainPanel.getStoreId()!= null && bizPurchaseMainPanel.getStoreId() ==1)
			{

			bizPurchaseMainPanel.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();
		if(null !=user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
				bizPurchaseMainPanel.setProjectMode(null);
			}else{
				bizPurchaseMainPanel.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}
		}else{
			bizPurchaseMainPanel.setProjectMode(null);
		}
		if(null == bizPurchaseMainPanel.getStatus()|| "".equals(bizPurchaseMainPanel.getStatus())){
			bizPurchaseMainPanel.setStatus("10");
		}
		return "modules/purchase/bizPurchaseMainPanelList";
	}
	@RequiresPermissions("purchase:bizPurchaseMainPanel:view")
	@RequestMapping(value= {"panelList",""})
	public String panelList(BizPurchaseMainPanel bizPurchaseMainPanel,HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{

			bizPurchaseMainPanel.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{

		if(bizPurchaseMainPanel.getStoreId()!= null && bizPurchaseMainPanel.getStoreId() ==1)
			{

			bizPurchaseMainPanel.setStoreId(null);
			}
		}
		if(null == bizPurchaseMainPanel.getStatus()){
			bizPurchaseMainPanel.setStatus("10");
		}
		bizPurchaseMainPanel.setPurchaseType(ConstantUtils.SWITCH_PANEL_NUMBER);
		if(bizPurchaseMainPanel.getProjectMode() == null || "".equals(bizPurchaseMainPanel.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					bizPurchaseMainPanel.setProjectMode(null);
				}else{
					bizPurchaseMainPanel.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizPurchaseMainPanel.setProjectMode(null);
			}
		}
		Page<BizPurchaseMainPanel> page = bizPurchaseMainPanelService.findPage(new Page<BizPurchaseMainPanel>(request, response), bizPurchaseMainPanel);
		model.addAttribute("page", page);
		return "modules/purchase/bizPurchaseMainPanelList";
	}
	

	@RequiresPermissions("purchase:bizPurchaseMainPanel:view")
	@RequestMapping(value = "mainPanelDetails")
	public String mainPanelDetails(Integer id , HttpServletRequest request, HttpServletResponse response, Model model){

		BizPurchaseMainPanel bizPurchaseMainPanel =  bizPurchaseMainPanelService.findById(id);

		List<MainPanel> mainPanels = mainPanelService.findListByPurchaseId(id);
		model.addAttribute("bizPurchaseMainPanel", bizPurchaseMainPanel);
		model.addAttribute("mainPanels", mainPanels);
		return "modules/purchase/mainPanelDetails";
	}
	


	@RequestMapping(value = "seePhoto")
	@ResponseBody
	public Map<Object, Object> seePhoto(Integer id, Model model, HttpServletRequest request) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}



	@RequestMapping(value = "seeOverPhoto")
	@ResponseBody
	public Map<Object, Object> seeOverPhoto(Integer id, Model model, HttpServletRequest request) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryOverPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_104);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}
}
