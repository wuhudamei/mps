package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.constantUtils.toDoConstant.UrgeToPayConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.service.mobile.Manager.BacklogService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.ToDoItemEntity;
import cn.damei.service.modules.ToDoItemService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
/**
 * 待办事项
 * @author zkj
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager/backlog")
public class BacklogController {
	@Autowired
	private BacklogService backlogService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private ToDoItemService toDoItemService;
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Model model,HttpServletResponse response){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		Integer managerId = manager.getId();
		/*//任务包
		List<Backlog> backlog = backlogService.findOrderTaskPackageByManagerId(id,ConstantUtils.ORDER_TASKPACKAGE_STATUS_80,ConstantUtils.ORDER_TASKPACKAGE_STATUS_95,ConstantUtils.ORDER_TASKPACKAGE_STATUS_110,ConstantUtils.ORDER_TASKPACKAGE_STATUS_130);
		//材料
		List<Backlog> material = backlogService.findApplyMaterial(id);*/
		//今日待办
		//查出所有的订单
		List<Backlog> backklogList = backlogService.findOrderByManagerId(managerId);
		int totalCount = 0;  //总条数
		for(Backlog backlog:backklogList){
			String orderId = backlog.getOrderId();
			//今日待办
			List<ToDoItemEntity> todayToDoList = backlogService.findTodayTodo(managerId,orderId);
			/*//如果url是空的，就是二期款待办，添加二期款的url
			for(ToDoItemEntity toDoItemEntity:todayToDoList){
				if(null==toDoItemEntity.getToDoItemDealUrl()||(toDoItemEntity.getToDoItemDealUrl()!=null&&toDoItemEntity.getToDoItemDealUrl().equals(""))){
					String dealUrl = "/app/manager/backlog/toDealErQiKuan?id="+toDoItemEntity.getId();
					toDoItemEntity.setToDoItemDealUrl(dealUrl);
					toDoItemService.saveDealUrlById(toDoItemEntity.getId(),dealUrl);
				}
			}*/
			if(todayToDoList!=null){
				backlog.setTodayToDoList(todayToDoList);
				int todayToDoListCount = todayToDoList.size();
				backlog.setTodayToDoListCount(todayToDoListCount);
				totalCount+=todayToDoListCount;
			}
			//其他待办
			List<ToDoItemEntity> otherToDoList = backlogService.findOtherTodo(managerId,orderId);
			/*//如果url是空的，就是二期款待办，添加二期款的url
			for(ToDoItemEntity toDoItemEntity:otherToDoList){
				if(null==toDoItemEntity.getToDoItemDealUrl()||(toDoItemEntity.getToDoItemDealUrl()!=null&&toDoItemEntity.getToDoItemDealUrl().equals(""))){
					String dealUrl = "/app/manager/backlog/toDealErQiKuan?id="+toDoItemEntity.getId();
					toDoItemEntity.setToDoItemDealUrl(dealUrl);
					toDoItemService.saveDealUrlById(toDoItemEntity.getId(),dealUrl);
				}
			}*/
			if(otherToDoList!=null){
				backlog.setOtherToDoList(otherToDoList);
				int otherToDoListCount = otherToDoList.size();
				backlog.setOtherToDoListCount(otherToDoListCount);
				totalCount+=otherToDoListCount;
			}
		}
		model.addAttribute("backklogList", backklogList);
		model.addAttribute("totalCount", totalCount);
		/*model.addAttribute("list", backlog);
		model.addAttribute("material", material);
		model.addAttribute("count", backlog.size()+material.size());*/
		
		return "mobile/modules/Manager/backlog/todo";
	//	return "/mobile/modules/Manager/backlog/backlogList";
	}
	@RequestMapping(value = "toDealErQiKuan")
	public String toDealErQiKuan(String id, Model model){
		Backlog backlog = backlogService.getErQiKuanInfo(id);
		model.addAttribute("backlog",backlog);
		return "mobile/modules/Manager/backlog/todoDetails";
	}
	@RequestMapping(value = "updateItemAndUrgeStatus")
	public String updateItemAndUrgeStatus(String id){
		//改变待办表为已查看，已处理
		toDoItemService.updateViewdOrSolvedByObj(id, "2");
		//改变催缴表为不许催缴
		toDoItemService.updateUrgePayStatusByItemId(id,UrgeToPayConstantUtil.URGE_STATUS_30);
		return "redirect:/a/app/manager/backlog/list";
	}
	
	@RequestMapping(value = "sendMessage")
	public void sendMessage(String id,HttpServletResponse response) throws IOException{
		Backlog backlog = backlogService.getErQiKuanInfoByItemId(id);
		/*String content = "尊敬的业主您好，您家的"+backlog.getRemindTitle()+"验收施工已全部完成并经验收合格。为了不影响您家的施工进度，劳烦您办理二期款缴纳。感谢您对美得你的支持。";
		//短信通知客户
		BizPhoneMsg phone = new BizPhoneMsg();
		phone.setReceivePhone(backlog.getCustomerPhone());
		phone.setMsgContent(content);
		phone.setMsgGenerateDatetime(new Date());
		phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_901001);
		phone.setRelatedBusinessIdInt(backlog.getId());
		bizPhoneMsgService.insert(phone);*/
		//短信通知设计师
		String content2 = "（"+backlog.getCommunityName()+"-"+backlog.getBuildNumber()+"-"
							  +backlog.getBuildUnit()+"-"+backlog.getBuildRoom()+"-"+backlog.getCustomerName()+"）家的"
							  +backlog.getRemindTitle()+"验收工程客户已经验收合格，请及时计算二期款缴款明细，并催促客户及时交纳。【美得你】"
							  ;
		BizPhoneMsg phone2 = new BizPhoneMsg();
		phone2.setReceivePhone(backlog.getDesignerPhone());
		phone2.setMsgContent(content2);
		phone2.setMsgGenerateDatetime(new Date());
		phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_901002);
		phone2.setRelatedBusinessIdInt(backlog.getId());
		bizPhoneMsgService.insert(phone2);
		//改变待办表为已查看，已处理
		toDoItemService.updateViewdOrSolvedByObj(id, "2");
		//改变催缴表为已催缴
		toDoItemService.updateUrgePayStatusByItemId(id,UrgeToPayConstantUtil.URGE_STATUS_20);
		/*JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();*/
	}
	@RequestMapping(value = "updateViewed")
	public String updateViewed(String id,String url,HttpServletRequest request){
		
		String webName = request.getContextPath();
		url = url.replaceFirst(webName, "");
		//url = url.substring(0,url.indexOf("?")+1)+"id="+id;
		toDoItemService.updateViewdOrSolvedByObj(id, "0");
		return  "redirect:"+url;
	}
}
