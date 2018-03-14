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

			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
			List<Auxiliary> auxiliarys = auxiliaryService.findListByPurchaseId(id, Integer.parseInt(order.getStoreId()));
			List<BizSupplier> bizSuppliers = auxiliaryService.findSuppliersByPurchaseId(id, Integer.parseInt(order.getStoreId()));
			StringBuilder supplierMessage = new StringBuilder();
			if (null != bizSuppliers && bizSuppliers.size() > 0)
			{
				for (int i = 0; i < bizSuppliers.size(); i++)
				{

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
		
		

		if("21".equals(status)){
			

			wallAndFloorService.updateOrderWallFloorTile(orderId);
			

			if(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1.equals(projectMode)){
				User user = UserUtils.getUser();
				Integer empId = null;
				if(null!=user && StringUtils.isNotBlank(user.getEmpId())){
					empId = Integer.valueOf(user.getEmpId());
				}

				wallAndFloorService.saveWallFloorTileRecheck(orderId,empId);
			}
		}
		

		if ("40".equals(status)){
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());























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


	@RequestMapping(value = "main_panel_abandoned_because_ajax")
	public @ResponseBody String mainPanelAbandonedBecauseAjax(String purchaseId, String status, String urgeReply)
	{

		String result = "0";

		if (null == purchaseId || purchaseId.equals(""))
		{
			result = "1";
			return result;
		}

		if (null == status || status.equals(""))
		{
			result = "2";
			return result;
		}

		if (null == urgeReply || urgeReply.equals(""))
		{
			result = "3";
			return result;
		}

		boolean flag = bizPurchaseService.updateMainPanelStatus(purchaseId, status, urgeReply);
		if (!flag)
		{
			result = "4";
			return result;
		}
		return result;
	}
	

	@RequestMapping(value = "settlement_is_exist_ajax")
	public @ResponseBody String settlementIsExistAjax(String orderId){
		
		String result = "0";

		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}

		Integer count = bizPurchaseService.findSettlementIsExist(Integer.valueOf(orderId));
		if(null!=count && count>0){
			result = "2";
			return result;
		}
		
		return result;
	}
	
	
	
}
