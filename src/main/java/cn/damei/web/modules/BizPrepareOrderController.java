/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateFormatUtils;
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
import cn.damei.common.constantUtils.EmployeeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.MD5Utils;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.service.modules.BizMaterialsChoiceBillItemService;
import cn.damei.entity.modules.BizPrepareOrder;
import cn.damei.entity.modules.OrdertaskingCount;
import cn.damei.service.modules.BizPrepareOrderService;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 预备订单表Controller
 * 
 * @author wyb
 * @version 2017-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/bizprepareorder/bizPrepareOrder")
public class BizPrepareOrderController extends BaseController
{

	@Autowired
	private BizPrepareOrderService bizPrepareOrderService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private BizSynDataService bizSynDataService;

	@ModelAttribute
	public BizPrepareOrder get(@RequestParam(required = false) Integer id)
	{
		BizPrepareOrder entity = null;
		if (StringUtils.isNotBlank(id + ""))
		{
			entity = bizPrepareOrderService.get(id);
		}
		if (entity == null)
		{
			entity = new BizPrepareOrder();
		}
		return entity;
	}

	@RequiresPermissions("bizprepareorder:bizPrepareOrder:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizPrepareOrder bizPrepareOrder, HttpServletRequest request, HttpServletResponse response, Model model){

		System.err.println("1");
		return "modules/bizprepareorder/bizPrepareOrderList";
	}

	@RequiresPermissions("bizprepareorder:bizPrepareOrder:view")
	@RequestMapping(value = { "list1", "" })
	public String list1(BizPrepareOrder bizPrepareOrder, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		bizPrepareOrder.setProjectMode(ConstantUtils.PROJECT_MODE_1);
		User user = UserUtils.getUser();
		BizPrepareOrder temp = new BizPrepareOrder();
		if(user.getStoreId()!=null){
			temp.setStoreId(Integer.parseInt(user.getStoreId()));
		}
		temp.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_10);
		long count = bizPrepareOrderService.findPrepareOrderCount(temp);
		Page<BizPrepareOrder> page = bizPrepareOrderService.findPage(new Page<BizPrepareOrder>(request, response), bizPrepareOrder);
		
		//待接收预备订单的数量
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "modules/bizprepareorder/bizPrepareOrderList";
	}

	/**
	 * 预备订单详情
	 * 
	 * @param bizPrepareOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:view")
	@RequestMapping(value = "details")
	public String details(BizPrepareOrder bizPrepareOrder, Model model)
	{
		model.addAttribute("bizPrepareOrder", bizPrepareOrder);
		return "modules/bizprepareorder/bizPrepareOrderDetails";
	}

	/**
	 * 预备订单接收失败--修改
	 * 
	 * @param bizPrepareOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:view")
	@RequestMapping(value = "form")
	public String form(BizPrepareOrder bizPrepareOrder, Model model)
	{
		//设计师列表
		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_5);
		//审计员列表
		List<BizEmployee> auditorList = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_8);
		
		model.addAttribute("empList",employeeListByType);
		model.addAttribute("auditorList",auditorList);
		model.addAttribute("bizPrepareOrder", bizPrepareOrder);
		return "modules/bizprepareorder/bizPrepareOrderForm";
	}

	/**
	 * 预备订单接收失败--修改保存
	 * 
	 * @param bizPrepareOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:edit")
	@RequestMapping(value = "save")
	public String save(BizPrepareOrder bizPrepareOrder, Model model, RedirectAttributes redirectAttributes)
	{
		if (!beanValidator(model, bizPrepareOrder))
		{
			return form(bizPrepareOrder, model);
		}

		bizPrepareOrderService.save(bizPrepareOrder);
		addMessage(redirectAttributes, "修改预备订单成功");
		return "redirect:" + Global.getAdminPath() + "/bizprepareorder/bizPrepareOrder/list1?repage";
	}

	/**
	 * 接收
	 * 
	 * @param bizPrepareOrder
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:edit")
	@RequestMapping(value = "receive")
	public String receive(BizPrepareOrder bizPrepareOrder, Model model, RedirectAttributes redirectAttributes)
	{

		Date date = new Date();
		User user = UserUtils.getUser();
		// 1.校验数据是否正确
		String messageVerification = bizPrepareOrderService.verificationPrepareOrder(bizPrepareOrder);

		String message = "失败";

		if (null != messageVerification && messageVerification.equals("预备订单校验"))
		{
			// 2.接收预备订单到订单表
			message = bizPrepareOrderService.receiveOrder(bizPrepareOrder);
		} else if (messageVerification.equals("预备订单接收失败，订单编号在订单列表中已包含！"))
		{
			message = messageVerification;
		} else
		{
			message = "接收失败，请完善该订单数据，再操作此功能";
		}

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		if( StringUtils.isNotBlank(message) && ("预备订单,接收成功").equals(message) && StringUtils.isNotBlank(messageVerification) && ("预备订单校验").equals(messageVerification) ){
			// 订单接收成功
			bizBusinessStatusLog.setBusinessRemarks(message);
			bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PREPARE_ORDER_STATUS_30);
			bizPrepareOrder.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_30);
		}else{
			// 订单接收失败
			bizBusinessStatusLog.setBusinessRemarks(message);
			bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PREPARE_ORDER_STATUS_15);
			bizPrepareOrder.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_15);
		}

		// 3.保存业务状态日志
		bizBusinessStatusLog.setBusinessType(ConstantUtils.BUSINESS_TYPE_101);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(bizPrepareOrder.getId());
		bizBusinessStatusLog.setStatusDatetime(date);
		bizBusinessStatusLog.setCreateDate(date);
		if (StringUtils.isNotBlank(user.getId()))
		{
			bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(user.getId()));
			bizBusinessStatusLog.setCreateBy(user);
			bizBusinessStatusLog.setUpdateBy(user);
		}
		bizBusinessStatusLog.setUpdateDate(date);
		bizBusinessStatusLog.setRemarks("");
		bizBusinessStatusLog.setDelFlag("0");
		bizBusinessStatusLogService.save(bizBusinessStatusLog);

		// 4.更新预备订单状态
		bizPrepareOrder.setUpdateBy(user);
		bizPrepareOrder.setUpdateDate(date);
		bizPrepareOrderService.save(bizPrepareOrder);
		Integer id = bizPrepareOrder.getId();
		System.out.println(id);
		addMessage(redirectAttributes, message);
		return "redirect:" + Global.getAdminPath() + "/bizprepareorder/bizPrepareOrder/list1?repage";
	}

	/**
	 * 拒绝
	 * 
	 * @param bizPrepareOrder
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:edit")
	@RequestMapping(value = "refuse")
	public String refuse(BizPrepareOrder bizPrepareOrder, String reason,String reasonType,String reasonTypeName, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, Exception
	{

		Date date = new Date();
		User user = UserUtils.getUser();
		if (StringUtils.isBlank(reasonType) || (reasonType.equals("2") && StringUtils.isBlank(reason))){
			addMessage(redirectAttributes, "拒绝原因不可为空");
			return "redirect:" + Global.getAdminPath() + "/bizprepareorder/bizPrepareOrder/list1?repage";
		}

		// 1.更新预备订单状态
		bizPrepareOrder.setRefuseReasonType(reasonType);
		bizPrepareOrder.setRefuseReason(reason);
		bizPrepareOrder.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_20);
		bizPrepareOrder.setUpdateBy(user);
		bizPrepareOrder.setUpdateDate(date);
		bizPrepareOrderService.save(bizPrepareOrder);

		// 2.保存业务状态日志
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(ConstantUtils.BUSINESS_TYPE_101);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(bizPrepareOrder.getId());
		bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PREPARE_ORDER_STATUS_20);
		bizBusinessStatusLog.setBusinessRemarks(reason);
		bizBusinessStatusLog.setStatusDatetime(date);
		bizBusinessStatusLog.setCreateDate(date);
		if (StringUtils.isNotBlank(user.getId()))
		{
			bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(user.getId()));
			bizBusinessStatusLog.setCreateBy(user);
			bizBusinessStatusLog.setUpdateBy(user);
		}
		bizBusinessStatusLog.setUpdateDate(date);
		bizBusinessStatusLog.setRemarks(reasonType);
		bizBusinessStatusLog.setDelFlag("0");
		bizBusinessStatusLogService.save(bizBusinessStatusLog);

		// 3.保存同步数据表
		// 向biz_syn_data表中保存数据 --- 预备订单拒绝
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("orderId", bizPrepareOrder.getOrderNumber());
		jsonMap.put("time", DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
		String remarks = reasonTypeName;
		if(StringUtils.isNotBlank(reason)){
			remarks = remarks+"-"+reason;
		}
		jsonMap.put("remarks", remarks);
		jsonMap.put("status", ConstantUtils.PREPARE_ORDER_STATUS_20);
		String key = MD5Utils.MD5Secret(jsonMap);
		jsonMap.put("key", key);
		BizSynData bizSynData = new BizSynData();
		bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
		bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_101);
		bizSynData.setBusinessOnlyMarkInt(bizPrepareOrder.getId());
		bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
		bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
		bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
		bizSynData.preInsert();
		bizSynDataService.save(bizSynData);

		addMessage(redirectAttributes, "预备订单拒绝成功");
		return "redirect:" + Global.getAdminPath() + "/bizprepareorder/bizPrepareOrder/list1?repage";
	}

	
	@Autowired
	private BizMaterialsChoiceBillItemService bizMaterialsChoiceBillItemService;
	@Autowired
	private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;
	/**
	 * 订单的选材清单详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "order_materials_choice_bill_detail")
	public String orderMaterialsChoiceBillDetail(BizPrepareOrder bizPrepareOrder, Model model) {
		
		//1.订单详情
		//2.选材清单
		/*List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
		if(null!=bizPrepareOrder.getOrderNumber() && !bizPrepareOrder.getOrderNumber().equals("")){
			BizMaterialsChoiceBill bizMaterialsChoiceBillItem = new BizMaterialsChoiceBill();
			bizMaterialsChoiceBillItem.setOrderNumber(bizPrepareOrder.getOrderNumber());
			List<BizMaterialsChoiceBill> findList = bizMaterialsChoiceBillService.findList(bizMaterialsChoiceBillItem);
							
		}*/
		//根据订单编号查询选材单号
		Integer id = bizPrepareOrderService.findMaterialsChoiceBillId(bizPrepareOrder.getOrderNumber());
		
		if(id !=null){
			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
			bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(id);
			List<BizMaterialsChoiceBillItem> materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
			model.addAttribute("materialsChoiceList", materialsChoiceList);
		}
		
		
		
		model.addAttribute("bizMaterialsChoiceBill", bizPrepareOrder);
		
		
		return "modules/bizmaterialschoicebill/orderMaterialsChoiceBillDetails";
	}
	/**
	 * 接单统计
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "ordertaskingCount")
	public String ordertaskingCount(OrdertaskingCount ordertaskingCount,BizPrepareOrder bizPrepareOrder,Model model) throws ParseException{
		if(ordertaskingCount.getBeginCreateDate() !=null){
			/*Date endCreateDate = ordertaskingCount.getEndCreateDate();
			Calendar calendar  = new GregorianCalendar(); 
			calendar.setTime(endCreateDate);
			calendar.add(calendar.DATE,1);
			Date dateadd=calendar.getTime();
			ordertaskingCount.setEndCreateDate(dateadd);*/
			List<OrdertaskingCount> list= bizPrepareOrderService.ordertaskingCount(ordertaskingCount);
          	model.addAttribute("list", list);
		}
		
		if(ordertaskingCount.getBeginCreateDate()==null || ordertaskingCount.getEndCreateDate() ==null){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
			String format = sdf.format(date);
			date = sdf.parse(format);
			Calendar calendar  = new GregorianCalendar(); 
			calendar.setTime(date);
			calendar.add(calendar.DATE,-1);
			Date dateadd=calendar.getTime();
			ordertaskingCount.setBeginCreateDate(dateadd);
			ordertaskingCount.setEndCreateDate(dateadd);
		}
		
		User user = UserUtils.getUser();
		BizPrepareOrder temp = new BizPrepareOrder();
		if(StringUtils.isNotBlank(ordertaskingCount.getStoreId())){
			temp.setStoreId(Integer.valueOf(ordertaskingCount.getStoreId()));
		}
		if(user.getStoreId()!=null){
			temp.setStoreId(Integer.parseInt(user.getStoreId()));
		}
		temp.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_10);
		long count = bizPrepareOrderService.findPrepareOrderCount(temp);
		model.addAttribute("count", count);
		model.addAttribute("ordertaskingCount", ordertaskingCount);
		return "/modules/bizprepareorder/ordertaskingCount";
	}
	
	
	

	/**
	 * 获取所有设计师的姓名电话
	 * @param bizPrepareOrder
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprepareorder:bizPrepareOrder:view")
	@RequestMapping(value = "checkForm")
	public @ResponseBody List<BizEmployee> checkForm(String empName,String empPhone, Model model) {
		Map<String,Object> resultMap = new HashMap<>();
		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType("5");
		resultMap.put("empList", resultMap);
		model.addAttribute("empList", employeeListByType);
		return employeeListByType;
	}
	
	

}