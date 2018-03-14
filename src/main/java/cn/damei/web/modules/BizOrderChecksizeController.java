/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 上传复尺Controller
 * @author wyb
 * @version 2016-10-20
 */
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

		
		//权限控制
		
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_CSFC);
		bizOrderChecksize.setPhones(orderdPhones);
		String userId = user.getId();
		bizOrderChecksize.setUserId(userId);
		//过滤工程模式
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
		//复尺状态
		if(StringUtils.isBlank(bizOrderChecksize.getChecksizeStatus())){
			bizOrderChecksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_10);
		}
		//门店
		if(null!=storeId && !storeId.equals("")){
			bizOrderChecksize.setStoreId(Integer.valueOf(storeId));
		}
		//工程模式
		if(null!=projectMode && !projectMode.equals("")){
			bizOrderChecksize.setProjectMode(projectMode);
		}
		//订单编号
		if(null!=orderNumber && !orderNumber.equals("")){
			bizOrderChecksize.setOrderNumber(orderNumber);
		}
		//顾客姓名
		if(null!=customerName && !customerName.equals("")){
			bizOrderChecksize.setCustomerName(customerName);
		}
		//项目经理
		if(null!=itemManager && !itemManager.equals("")){
			bizOrderChecksize.setItemManager(itemManager);
		}
		//复尺类型	
		if(null!=checksizeType && !checksizeType.equals("")){
			bizOrderChecksize.setChecksizeType(checksizeType);
		}
		//复尺状态
		if(null!=checksizeStatus && !checksizeStatus.equals("")){
			bizOrderChecksize.setChecksizeStatus(checksizeStatus);
		}
		//合同开工日期 开始
		if(null!=beginContractStartDate && !beginContractStartDate.equals("")){
			bizOrderChecksize.setBeginContractStartDate(DateUtils.parseDate(beginContractStartDate));
		}
		//合同开工日期 结束
		if(null!=endContractStartDate && !endContractStartDate.equals("")){
			bizOrderChecksize.setEndContractStartDate(DateUtils.parseDate(endContractStartDate));
		}
		//提交申请日期 开始
		if(null!=beginCreateDate && !beginCreateDate.equals("")){
			bizOrderChecksize.setBeginCreateDate(DateUtils.parseDate(beginCreateDate));
		}
		//提交申请日期 结束
		if(null!=endCreateDate && !endCreateDate.equals("")){
			bizOrderChecksize.setEndCreateDate(DateUtils.parseDate(endCreateDate));
		}
		//期望复尺日期 开始
		if(null!=beginChecksizeDate && !beginChecksizeDate.equals("")){
			bizOrderChecksize.setBeginChecksizeDate(DateUtils.parseDate(beginChecksizeDate));
		}
		//期望复尺日期 结束
		if(null!=endChecksizeDate && !endChecksizeDate.equals("")){
			bizOrderChecksize.setEndChecksizeDate(DateUtils.parseDate(endChecksizeDate));
		}
		//供应商确认日期 开始
		if(null!=beginSupplierConfirmDate && !beginSupplierConfirmDate.equals("")){
			bizOrderChecksize.setBeginSupplierConfirmDate(DateUtils.parseDate(beginSupplierConfirmDate));
		}
		//供应商确认日期 结束
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
		//过滤门店
		if(null==bizOrderChecksize.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderChecksize.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
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
	//图片
	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:view")
	@RequestMapping(value = "checksizePhoto")
	public String checksizePhoto(Integer orderChecksizeId,BizOrderChecksizePic bizOrderChecksizePic, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//通过复尺表id查询复尺图片
		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(orderChecksizeId);
		if(picList.size()>0){
			String baseUrl = PicRootName.picPrefixName();
			model.addAttribute("picList", picList);
			model.addAttribute("baseUrl", baseUrl);
		}
		
		return "modules/bizorderchecksize/bizOrderChecksizePhoto";
	}
	
	
	/**
	 * 详情
	 * @param orderChecksizeId
	 * @param bizOrderChecksizePic
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:edit")
	@RequestMapping(value = "checksizeDetails")
	public String checksizeDetails(Integer orderChecksizeId,BizOrderChecksizePic bizOrderChecksizePic, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		//通过复尺表id查询详情
		BizOrderChecksizeEntity bizOrderChecksize = bizOrderChecksizeService.selectDetailsByOrderChecksizeId(orderChecksizeId);
		//通过复尺表id查询复尺图片
		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(orderChecksizeId);
		model.addAttribute("bizOrderChecksize", bizOrderChecksize);
		if(picList.size()>0){
			String baseUrl = PicRootName.picPrefixName();
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("picList", picList);
		}
		return "modules/bizorderchecksize/bizOrderChecksizeDetails";
	}
	
	/**
	 * 处理
	 * @param orderChecksizeId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("bizorderchecksize:bizOrderChecksize:edit")
	@RequestMapping(value = "operation")
	public String operation(BizOrderChecksizeEntity orderChecksize, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		//通过复尺表id查询详情
		BizOrderChecksizeEntity bizOrderChecksize = bizOrderChecksizeService.selectDetailsByOrderChecksizeId(orderChecksize.getOrderChecksizeId());
		model.addAttribute("bizOrderChecksize", bizOrderChecksize);
		model.addAttribute("orderChecksize", orderChecksize);
		return "modules/bizorderchecksize/bizOrderChecksizeOperation";
	}

	/**
	 * 处理--保存
	 * @param bizQcCheckKind
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
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
			//=====================================短信start========================================================
			/**
			 * 处理厂家复尺==项目经理
			 */
			//项目经理【美得你】订单（小区名称-楼号-单元号-门牌号-客户姓名-复尺内容），材料部已处理--供应商确认日期（日期），回复内容（回复内容），请您知晓。
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
			//=====================================短信end========================================================
		
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
	/**
	 * 动态加载主材复尺项
	 * @param storeId
	 * @param projectModeValue
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findMainItem")
	@ResponseBody
	public List<BizOrderChecksizeEntity> findMainItem(String storeId, String projectModeValue, Model model){
		List<BizOrderChecksizeEntity> list = bizOrderChecksizeService.findMainItem(storeId,projectModeValue);
		return list;
	}
	
	

}