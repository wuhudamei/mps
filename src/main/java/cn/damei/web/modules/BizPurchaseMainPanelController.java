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
			//当前登录用户门店
			bizPurchaseMainPanel.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
		if(bizPurchaseMainPanel.getStoreId()!= null && bizPurchaseMainPanel.getStoreId() ==1)
			{
				//总部
			bizPurchaseMainPanel.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();
		if(null !=user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
				bizPurchaseMainPanel.setProjectMode(null);//表示查询所有的采购单
			}else{
				bizPurchaseMainPanel.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}
		}else{
			bizPurchaseMainPanel.setProjectMode(null);//表示查询所有的采购单
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
			//当前登录用户门店
			bizPurchaseMainPanel.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
		if(bizPurchaseMainPanel.getStoreId()!= null && bizPurchaseMainPanel.getStoreId() ==1)
			{
				//总部
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
					bizPurchaseMainPanel.setProjectMode(null);//表示查询所有的采购单
				}else{
					bizPurchaseMainPanel.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizPurchaseMainPanel.setProjectMode(null);//表示查询所有的采购单
			}
		}
		Page<BizPurchaseMainPanel> page = bizPurchaseMainPanelService.findPage(new Page<BizPurchaseMainPanel>(request, response), bizPurchaseMainPanel);
		model.addAttribute("page", page);
		return "modules/purchase/bizPurchaseMainPanelList";
	}
	
	//开关面板
	@RequiresPermissions("purchase:bizPurchaseMainPanel:view")
	@RequestMapping(value = "mainPanelDetails")
	public String mainPanelDetails(Integer id , HttpServletRequest request, HttpServletResponse response, Model model){
		//id 采购单id
		BizPurchaseMainPanel bizPurchaseMainPanel =  bizPurchaseMainPanelService.findById(id);
		//Integer storeId = bizPurchaseVo.getStoreId();
		List<MainPanel> mainPanels = mainPanelService.findListByPurchaseId(id);
		model.addAttribute("bizPurchaseMainPanel", bizPurchaseMainPanel);
		model.addAttribute("mainPanels", mainPanels);
		return "modules/purchase/mainPanelDetails";
	}
	
/*	@RequestMapping(value = "seePhoto")
	public String seePhoto(Integer id ,Model model) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/purchase/photo";
	}*/
	/**
	 * 查看收货单
	 * @param
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
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

/*	@RequestMapping(value = "seeOverPhoto")
	public String seeOverPhoto(Integer id ,Model model) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryOverPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_104);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/purchase/overPhoto";
	}*/
	/**
	 * 查看超定额图片
	 * @param
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
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
