
package cn.damei.web.modules;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.service.modules.DataAuthorityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizOrderChecksizePic;
import cn.damei.service.modules.BizOrderChecksizeService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizorderchecksize/bizOrderChecksize")
public class BizOrderChecksizeController extends BaseController {

	@Autowired
	private BizOrderChecksizeService bizOrderChecksizeService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private DataAuthorityService dataAuthorityService;
	@ModelAttribute
	public BizOrderChecksizeEntity get(@RequestParam(required=false) Integer id) {
		BizOrderChecksizeEntity entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizOrderChecksizeService.get(id);
		}
		if (entity == null){
			entity = new BizOrderChecksizeEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderChecksizeEntity bizOrderChecksize, HttpServletRequest request,
                       HttpServletResponse response, Model model,
                       @ModelAttribute("storeId")String storeId, @ModelAttribute("projectMode")String projectMode,
                       @ModelAttribute("orderNumber")String orderNumber, @ModelAttribute("customerName")String customerName,
                       @ModelAttribute("itemManager")String itemManager, @ModelAttribute("checksizeType")String checksizeType,
                       @ModelAttribute("checksizeStatus")String checksizeStatus,
                       @ModelAttribute("beginContractStartDate")String beginContractStartDate, @ModelAttribute("endContractStartDate")String endContractStartDate,
                       @ModelAttribute("beginCreateDate")String beginCreateDate, @ModelAttribute("endCreateDate")String endCreateDate,
                       @ModelAttribute("beginChecksizeDate")String beginChecksizeDate, @ModelAttribute("endChecksizeDate")String endChecksizeDate,
                       @ModelAttribute("beginSupplierConfirmDate")String beginSupplierConfirmDate, @ModelAttribute("endSupplierConfirmDate")String endSupplierConfirmDate
			) {
		
		User user = UserUtils.getUser();

		

		
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_CSFC);
		bizOrderChecksize.setPhones(orderdPhones);
		String userId = user.getId();
		bizOrderChecksize.setUserId(userId);

		if(StringUtils.isBlank(bizOrderChecksize.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderChecksize.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderChecksize.setProjectMode(user.getProjectMode());
			}
		}

		if(StringUtils.isBlank(bizOrderChecksize.getChecksizeStatus())){
			bizOrderChecksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_10);
		}

		if(null!=storeId && !storeId.equals("")){
			bizOrderChecksize.setStoreId(Integer.valueOf(storeId));
		}

		if(null!=projectMode && !projectMode.equals("")){
			bizOrderChecksize.setProjectMode(projectMode);
		}

		if(null!=orderNumber && !orderNumber.equals("")){
			bizOrderChecksize.setOrderNumber(orderNumber);
		}

		if(null!=customerName && !customerName.equals("")){
			bizOrderChecksize.setCustomerName(customerName);
		}

		if(null!=itemManager && !itemManager.equals("")){
			bizOrderChecksize.setItemManager(itemManager);
		}

		if(null!=checksizeType && !checksizeType.equals("")){
			bizOrderChecksize.setChecksizeType(checksizeType);
		}

		if(null!=checksizeStatus && !checksizeStatus.equals("")){
			bizOrderChecksize.setChecksizeStatus(checksizeStatus);
		}

		if(null!=beginContractStartDate && !beginContractStartDate.equals("")){
			bizOrderChecksize.setBeginContractStartDate(DateUtils.parseDate(beginContractStartDate));
		}

		if(null!=endContractStartDate && !endContractStartDate.equals("")){
			bizOrderChecksize.setEndContractStartDate(DateUtils.parseDate(endContractStartDate));
		}

		if(null!=beginCreateDate && !beginCreateDate.equals("")){
			bizOrderChecksize.setBeginCreateDate(DateUtils.parseDate(beginCreateDate));
		}

		if(null!=endCreateDate && !endCreateDate.equals("")){
			bizOrderChecksize.setEndCreateDate(DateUtils.parseDate(endCreateDate));
		}

		if(null!=beginChecksizeDate && !beginChecksizeDate.equals("")){
			bizOrderChecksize.setBeginChecksizeDate(DateUtils.parseDate(beginChecksizeDate));
		}

		if(null!=endChecksizeDate && !endChecksizeDate.equals("")){
			bizOrderChecksize.setEndChecksizeDate(DateUtils.parseDate(endChecksizeDate));
		}

		if(null!=beginSupplierConfirmDate && !beginSupplierConfirmDate.equals("")){
			bizOrderChecksize.setBeginSupplierConfirmDate(DateUtils.parseDate(beginSupplierConfirmDate));
		}

		if(null!=endSupplierConfirmDate && !endSupplierConfirmDate.equals("")){
			bizOrderChecksize.setEndSupplierConfirmDate(DateUtils.parseDate(endSupplierConfirmDate));
		}
				
		Page<BizOrderChecksizeEntity> page = bizOrderChecksizeService.findPage(new Page<BizOrderChecksizeEntity>(request, response), bizOrderChecksize);
		model.addAttribute("page", page);
		return "modules/bizorderchecksize/bizOrderChecksizeList";
	}
	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:view")
	@RequestMapping(value = {"checksizeList", ""})
	public String checksizeList(BizOrderChecksizeEntity bizOrderChecksize, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizOrderChecksize.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderChecksize.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderChecksize.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderChecksize.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderChecksize.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<BizOrderChecksizeEntity> page = bizOrderChecksizeService.findPage(new Page<BizOrderChecksizeEntity>(request, response), bizOrderChecksize);
		model.addAttribute("page", page);
		return "modules/bizorderchecksize/bizOrderChecksizeList";
	}

	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:view")
	@RequestMapping(value = "checksizePhoto")
	public String checksizePhoto(Integer orderChecksizeId,BizOrderChecksizePic bizOrderChecksizePic, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(orderChecksizeId);
		if(picList.size()>0){
			String baseUrl = PicRootName.picPrefixName();
			model.addAttribute("picList", picList);
			model.addAttribute("baseUrl", baseUrl);
		}
		
		return "modules/bizorderchecksize/bizOrderChecksizePhoto";
	}
	
	

	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:edit")
	@RequestMapping(value = "checksizeDetails")
	public String checksizeDetails(Integer orderChecksizeId,BizOrderChecksizePic bizOrderChecksizePic, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		

		BizOrderChecksizeEntity bizOrderChecksize = bizOrderChecksizeService.selectDetailsByOrderChecksizeId(orderChecksizeId);

		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(orderChecksizeId);
		model.addAttribute("bizOrderChecksize", bizOrderChecksize);
		if(picList.size()>0){
			String baseUrl = PicRootName.picPrefixName();
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("picList", picList);
		}
		return "modules/bizorderchecksize/bizOrderChecksizeDetails";
	}
	

	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:edit")
	@RequestMapping(value = "operation")
	public String operation(BizOrderChecksizeEntity orderChecksize, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		

		BizOrderChecksizeEntity bizOrderChecksize = bizOrderChecksizeService.selectDetailsByOrderChecksizeId(orderChecksize.getOrderChecksizeId());
		model.addAttribute("bizOrderChecksize", bizOrderChecksize);
		model.addAttribute("orderChecksize", orderChecksize);
		return "modules/bizorderchecksize/bizOrderChecksizeOperation";
	}


	@RequestMapping(value = "save")
	public String save(BizOrderChecksizeEntity bizOrderChecksize, String beginContractStartDate, String endContractStartDate,
                       String beginCreateDate, String endCreateDate, String beginChecksizeDate, String endChecksizeDate,
                       String beginSupplierConfirmDate, String endSupplierConfirmDate,
                       Model model, RedirectAttributes redirectAttributes) {
		
		String checksizeStatus = bizOrderChecksize.getChecksizeStatus();
		
		String message = "";
		boolean flag = bizOrderChecksizeService.updateOrderChecksize(bizOrderChecksize);
		if(flag){
			message = "上报厂家复尺处理成功";
			BizOrderChecksizeEntity change = bizOrderChecksizeService.selectDetailsByOrderChecksizeId(bizOrderChecksize.getOrderChecksizeId());
			String supplierDate = "";
			if(null!=bizOrderChecksize.getSupplierConfirmDate()){
				supplierDate = DateUtils.formatDate(bizOrderChecksize.getSupplierConfirmDate());
			}
			Date date = new Date();



			String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getChecksizeTypeName()+"），材料部已处理--供应商确认日期（"+supplierDate+"），回复内容（"+bizOrderChecksize.getMaterialDepartmentDealReply()+"），请您知晓。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(change.getItemManagerId());
			phone.setReceivePhone(change.getPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(date);
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300201);
			phone.setRelatedBusinessIdInt(bizOrderChecksize.getOrderChecksizeId());
			bizPhoneMsgService.insert(phone);

		
		}else{
			message = "上报厂家复尺处理失败";
		}
		addMessage(redirectAttributes, message);
		
		redirectAttributes.addFlashAttribute("storeId", ((null==bizOrderChecksize.getStoreId())?"":(bizOrderChecksize.getStoreId().toString())));
		redirectAttributes.addFlashAttribute("projectMode", ((null==bizOrderChecksize.getProjectMode()||bizOrderChecksize.getProjectMode().equals(""))?"":(bizOrderChecksize.getProjectMode().toString())));
		redirectAttributes.addFlashAttribute("orderNumber", ((null==bizOrderChecksize.getOrderNumber()||bizOrderChecksize.getOrderNumber().equals(""))?"":(bizOrderChecksize.getOrderNumber().toString())));
		redirectAttributes.addFlashAttribute("customerName", ((null==bizOrderChecksize.getCustomerName()||bizOrderChecksize.getCustomerName().equals(""))?"":(bizOrderChecksize.getCustomerName().toString())));
		redirectAttributes.addFlashAttribute("itemManager", ((null==bizOrderChecksize.getItemManager()||bizOrderChecksize.getItemManager().equals(""))?"":(bizOrderChecksize.getItemManager().toString())));
		redirectAttributes.addFlashAttribute("checksizeType", ((null==bizOrderChecksize.getChecksizeType()||bizOrderChecksize.getChecksizeType().equals(""))?"":(bizOrderChecksize.getChecksizeType().toString())));
		redirectAttributes.addFlashAttribute("checksizeStatus", ((null==checksizeStatus||checksizeStatus.equals(""))?"":(checksizeStatus.toString())));
		redirectAttributes.addFlashAttribute("beginContractStartDate", ((null==beginContractStartDate)?"":(beginContractStartDate.toString())));
		redirectAttributes.addFlashAttribute("endContractStartDate", ((null==endContractStartDate)?"":(endContractStartDate.toString())));
		redirectAttributes.addFlashAttribute("beginCreateDate", ((null==beginCreateDate)?"":(beginCreateDate.toString())));
		redirectAttributes.addFlashAttribute("endCreateDate", ((null==endCreateDate)?"":(endCreateDate.toString())));
		redirectAttributes.addFlashAttribute("beginChecksizeDate", ((null==beginChecksizeDate)?"":(beginChecksizeDate.toString())));
		redirectAttributes.addFlashAttribute("endChecksizeDate", ((null==endChecksizeDate)?"":(endChecksizeDate.toString())));
		redirectAttributes.addFlashAttribute("beginSupplierConfirmDate", ((null==beginSupplierConfirmDate)?"":(beginSupplierConfirmDate.toString())));
		redirectAttributes.addFlashAttribute("endSupplierConfirmDate", ((null==endSupplierConfirmDate)?"":(endSupplierConfirmDate.toString())));
		
		
		return "redirect:"+Global.getAdminPath()+"/bizorderchecksize/bizOrderChecksize/list?repage";
	}

	@RequestMapping(value = "findMainItem")
	@ResponseBody
	public List<BizOrderChecksizeEntity> findMainItem(String storeId, String projectModeValue, Model model){
		List<BizOrderChecksizeEntity> list = bizOrderChecksizeService.findMainItem(storeId,projectModeValue);
		return list;
	}
	
	

}