package cn.damei.web.modules;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.WallAndFloorService;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizPurchase;
import cn.damei.service.modules.AuxiliaryService;
import cn.damei.service.modules.BizPurchaseService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 采购单状态修改
 * 
 * @author wang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase1/purchase1")
public class PurchaseController extends BaseController
{

	@Autowired
	private BizPurchaseService bizPurchaseService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private AuxiliaryService auxiliaryService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private WallAndFloorService wallAndFloorService;
	@Autowired
	private MessageService messageService;

	@RequiresPermissions("purchase:bizPurchase:edit")
	@RequestMapping(value = "reviseAuxiliaryStatus")
	public String reviseAuxiliaryStatus(Integer id, String status)
	{
		BizPurchase bizPurchase = bizPurchaseService.findByPurchaseId(id);
		bizPurchase.setStatus(status);
		bizPurchase.setTransferSupplierDatetime(new Date());
		bizPurchaseService.updateStatus(bizPurchase);
		if ("40".equals(status))
		{
			// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），供应商（姓名-手机号），申请辅料供应商已转单，请登录APP查看详情。--短信项目经理
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
			List<Auxiliary> auxiliarys = auxiliaryService.findListByPurchaseId(id, Integer.parseInt(order.getStoreId()));
			List<BizSupplier> bizSuppliers = auxiliaryService.findSuppliersByPurchaseId(id, Integer.parseInt(order.getStoreId()));
			StringBuilder supplierMessage = new StringBuilder();
			if (null != bizSuppliers && bizSuppliers.size() > 0)
			{
				for (int i = 0; i < bizSuppliers.size(); i++)
				{
					// 根据辅材的id查询供应商
					if (i == auxiliarys.size() - 1)
					{
						supplierMessage.append(bizSuppliers.get(i).getContacts()).append("-").append(bizSuppliers.get(i).getContactsPhone());
					} else
					{
						supplierMessage.append(bizSuppliers.get(i).getContacts()).append("-").append(bizSuppliers.get(i).getContactsPhone()).append(",");
					}
				}
			}
			String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + ",供应商（" + supplierMessage.toString() + "），申请辅料供应商已转单，请登录APP查看详情。";
			BizPhoneMsg message = new BizPhoneMsg();
			message.setMsgContent(content);
			message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			message.setReceiveEmployeeId(order.getItemManagerId());
			message.setReceivePhone(manager.getPhone());
			message.setMsgGenerateDatetime(new Date());
			message.setRelatedBusinessIdInt(bizPurchase.getId());
			message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_700201);
			bizPhoneMsgService.insert(message);

			Message message1 = new Message();
			message1.setMsgTitle("申请辅料供应商已转单");
			message1.setMsgTime(new Date());
			message1.setMsgContent(content);
			message1.setMsgType(MessagePushType.MSG_TYPE_1);
			message1.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_104001001);
			message1.setEmployeeId(order.getItemManagerId());
			message1.setBusiIdInt(bizPurchase.getId());
			messageService.insert(message1);

		}
		return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchase/list";
	}

	@RequiresPermissions("purchase:bizPurchase:edit")
	@RequestMapping(value = "reviseMainTileStatus")
	public String reviseMainTileStatus(Integer id, String status,Integer orderId,String projectMode)
	{
		BizPurchase bizPurchase = bizPurchaseService.findByPurchaseId(id);
		bizPurchase.setStatus(status);
		bizPurchase.setTransferSupplierDatetime(new Date());
		bizPurchaseService.updateStatus(bizPurchase);
		
		
		//废弃
		if("21".equals(status)){
			
			//更新订单墙地砖表（已申请商品数量）
			wallAndFloorService.updateOrderWallFloorTile(orderId);
			
			//如果订单工程模式为产业(是否生成复尺表)
			if(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1.equals(projectMode)){
				User user = UserUtils.getUser();
				Integer empId = null;
				if(null!=user && StringUtils.isNotBlank(user.getEmpId())){
					empId = Integer.valueOf(user.getEmpId());
				}
				// 如果订单工程模式为产业(是否生成复尺表)
				wallAndFloorService.saveWallFloorTileRecheck(orderId,empId);
			}
		}
		
		//转给供应商
		if ("40".equals(status)){
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
			// BizMessagegroup bizMessagegroup =
			// bizMessagegroupService.getByStoreId(order.getStoreId(),"4");
			// List<Integer> list = new ArrayList<Integer>();
			// List<BizEmployee2> employeelist = null;
			// StringBuilder supplierMessage = new StringBuilder();
			// if(null != bizMessagegroup ){
			// String[] str = bizMessagegroup.getEmployees().split(",");
			// for(String id1: str){
			// list.add(Integer.valueOf(id1));
			// }
			// employeelist = bizEmployeeService2.getById(list);
			// if(employeelist != null && employeelist.size()>0){
			// for (int i=0;i<employeelist.size();i++) {
			// if(i == employeelist.size()-1){
			// supplierMessage.append(employeelist.get(i).getRealname()).append("-").append(employeelist.get(i).getPhone());
			// }else{
			// supplierMessage.append(employeelist.get(i).getRealname()).append("-").append(employeelist.get(i).getPhone()).append(",");
			// }
			// }
			// }
			// }
			// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），材料调度员（姓名-手机号），申请墙地砖材料调度员已转单，请登录APP查看详情。
			/*
			 * String content =
			 * "订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"
			 * +order
			 * .getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName
			 * ()+"-"+order.getCustomerPhone()+",材料调度员（"
			 * +supplierMessage.toString()+"），申请墙地砖材料调度员已转单，请登录APP查看详情。";
			 */
			String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "），申请墙地砖材料调度员已转单，请登录APP查看详情。";
			BizPhoneMsg message = new BizPhoneMsg();
			message.setMsgContent(content);
			message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			message.setReceiveEmployeeId(order.getItemManagerId());
			message.setReceivePhone(manager.getPhone());
			message.setMsgGenerateDatetime(new Date());
			message.setRelatedBusinessIdInt(bizPurchase.getId());
			message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_800201);
			bizPhoneMsgService.insert(message);

			Message message1 = new Message();
			message1.setMsgTitle("申请墙地砖材料调度员已转单");
			message1.setMsgTime(new Date());
			message1.setMsgContent(content);
			message1.setMsgType(MessagePushType.MSG_TYPE_1);
			message1.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_104001002);
			message1.setEmployeeId(order.getItemManagerId());
			message1.setBusiIdInt(bizPurchase.getId());
			messageService.insert(message1);
		}
		return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchaseMainTile/tileList";
	}

	@RequiresPermissions("purchase:bizPurchase:edit")
	@RequestMapping(value = "reviseMainPanelStatus")
	public String reviseMainPanelStatus(Integer id, String status)
	{
		BizPurchase bizPurchase = bizPurchaseService.findByPurchaseId(id);
		bizPurchase.setStatus(status);
		bizPurchase.setTransferSupplierDatetime(new Date());
		bizPurchaseService.updateStatus(bizPurchase);
		if ("40".equals(status))
		{
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
			// BizMessagegroup bizMessagegroup =
			// bizMessagegroupService.getByStoreId(order.getStoreId(),"8");
			// List<Integer> list = new ArrayList<Integer>();
			// List<BizEmployee2> employeelist = null;
			// StringBuilder supplierMessage = new StringBuilder();
			// if(null != bizMessagegroup ){
			// String[] str = bizMessagegroup.getEmployees().split(",");
			// for(String id1: str){
			// list.add(Integer.valueOf(id1));
			// }
			// employeelist = bizEmployeeService2.getById(list);
			// if(employeelist != null && employeelist.size()>0){
			// for (int i=0;i<employeelist.size();i++) {
			// if(i == employeelist.size()-1){
			// supplierMessage.append(employeelist.get(i).getRealname()).append("-").append(employeelist.get(i).getPhone());
			// }else{
			// supplierMessage.append(employeelist.get(i).getRealname()).append("-").append(employeelist.get(i).getPhone()).append(",");
			// }
			// }
			// }
			// }
			// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），材料调度员（姓名-手机号），申请开关面板材料调度员已转单，请登录APP查看详情。
			/*
			 * String content =
			 * "订单（"+order.getCommunityName()+"-"+order.getBuildNumber
			 * ()+"-"+order
			 * .getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName
			 * (
			 * )+"-"+order.getCustomerPhone()+",材料调度员（"+supplierMessage.toString
			 * ()+"），申请开关面板材料调度员已转单，请登录APP查看详情。";
			 */
			String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "），申请开关面板材料调度员已转单，请登录APP查看详情。";
			BizPhoneMsg message = new BizPhoneMsg();
			message.setMsgContent(content);
			message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			message.setReceiveEmployeeId(order.getItemManagerId());
			message.setReceivePhone(manager.getPhone());
			message.setMsgGenerateDatetime(new Date());
			message.setRelatedBusinessIdInt(bizPurchase.getId());
			message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_900201);
			bizPhoneMsgService.insert(message);

			Message message1 = new Message();
			message1.setMsgTitle("申请开关面板材料调度员已转单");
			message1.setMsgTime(new Date());
			message1.setMsgContent(content);
			message1.setMsgType(MessagePushType.MSG_TYPE_1);
			message1.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_104001003);
			message1.setEmployeeId(order.getItemManagerId());
			message1.setBusiIdInt(bizPurchase.getId());
			messageService.insert(message1);
		}
		return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchaseMainPanel/panelList";
	}

	@RequestMapping(value = "reviseSandStatus")
	public String reviseSandStatus(Integer id, String status)
	{
		BizPurchase bizPurchase = bizPurchaseService.findByPurchaseId(id);
		bizPurchase.setStatus(status);
		bizPurchase.setTransferSupplierDatetime(new Date());
		bizPurchaseService.updateStatus(bizPurchase);
		return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchase/sand/listPage";
	}

	/**
	 * 送货
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequiresPermissions("purchase:bizPurchase:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(Integer id, String status)
	{
		BizPurchase bizPurchase = bizPurchaseService.findByPurchaseId(id);
		bizPurchase.setStatus(status);
		bizPurchase.preUpdate();
		bizPurchaseService.updateStatus(bizPurchase);
		return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchase/sendAuxiliaryList";
	}

	/**
	 * 开关面板--废弃
	 * 
	 * @param purchaseId
	 * @param status
	 * @param urgeReply
	 * @return
	 */
	@RequestMapping(value = "main_panel_abandoned_because_ajax")
	public @ResponseBody String mainPanelAbandonedBecauseAjax(String purchaseId, String status, String urgeReply)
	{

		String result = "0";
		// 1.采购单编号为空
		if (null == purchaseId || purchaseId.equals(""))
		{
			result = "1";
			return result;
		}
		// 2.状态为空
		if (null == status || status.equals(""))
		{
			result = "2";
			return result;
		}
		// 3.废弃原因为空
		if (null == urgeReply || urgeReply.equals(""))
		{
			result = "3";
			return result;
		}
		// 4.保存废弃状态
		boolean flag = bizPurchaseService.updateMainPanelStatus(purchaseId, status, urgeReply);
		if (!flag)
		{
			result = "4";
			return result;
		}
		return result;
	}
	
	/**
	 * 如果是准产业，点击【转给供应商】时，判断项目经理的中期结算单是否已存在
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "settlement_is_exist_ajax")
	public @ResponseBody String settlementIsExistAjax(String orderId){
		
		String result = "0";
		//1.订单id是否为空
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		//2.判断项目经理的中期结算单是否已存在
		Integer count = bizPurchaseService.findSettlementIsExist(Integer.valueOf(orderId));
		if(null!=count && count>0){
			result = "2";
			return result;
		}
		
		return result;
	}
	
	
	
}
