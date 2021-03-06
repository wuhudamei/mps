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



		List<Backlog> backklogList = backlogService.findOrderByManagerId(managerId);
		int totalCount = 0;
		for(Backlog backlog:backklogList){
			String orderId = backlog.getOrderId();

			List<ToDoItemEntity> todayToDoList = backlogService.findTodayTodo(managerId,orderId);

			if(todayToDoList!=null){
				backlog.setTodayToDoList(todayToDoList);
				int todayToDoListCount = todayToDoList.size();
				backlog.setTodayToDoListCount(todayToDoListCount);
				totalCount+=todayToDoListCount;
			}

			List<ToDoItemEntity> otherToDoList = backlogService.findOtherTodo(managerId,orderId);

			if(otherToDoList!=null){
				backlog.setOtherToDoList(otherToDoList);
				int otherToDoListCount = otherToDoList.size();
				backlog.setOtherToDoListCount(otherToDoListCount);
				totalCount+=otherToDoListCount;
			}
		}
		model.addAttribute("backklogList", backklogList);
		model.addAttribute("totalCount", totalCount);

		
		return "mobile/modules/Manager/backlog/todo";

	}
	@RequestMapping(value = "toDealErQiKuan")
	public String toDealErQiKuan(String id, Model model){
		Backlog backlog = backlogService.getErQiKuanInfo(id);
		model.addAttribute("backlog",backlog);
		return "mobile/modules/Manager/backlog/todoDetails";
	}
	@RequestMapping(value = "updateItemAndUrgeStatus")
	public String updateItemAndUrgeStatus(String id){

		toDoItemService.updateViewdOrSolvedByObj(id, "2");

		toDoItemService.updateUrgePayStatusByItemId(id,UrgeToPayConstantUtil.URGE_STATUS_30);
		return "redirect:/a/app/manager/backlog/list";
	}
	
	@RequestMapping(value = "sendMessage")
	public void sendMessage(String id,HttpServletResponse response) throws IOException{
		Backlog backlog = backlogService.getErQiKuanInfoByItemId(id);


		String content2 = "（"+backlog.getCommunityName()+"-"+backlog.getBuildNumber()+"-"
							  +backlog.getBuildUnit()+"-"+backlog.getBuildRoom()+"-"+backlog.getCustomerName()+"）家的"
							  +backlog.getRemindTitle()+"验收工程客户已经验收合格，请及时计算二期款缴款明细，并催促客户及时交纳。【大美装饰管理平台】"
							  ;
		BizPhoneMsg phone2 = new BizPhoneMsg();
		phone2.setReceivePhone(backlog.getDesignerPhone());
		phone2.setMsgContent(content2);
		phone2.setMsgGenerateDatetime(new Date());
		phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_901002);
		phone2.setRelatedBusinessIdInt(backlog.getId());
		bizPhoneMsgService.insert(phone2);

		toDoItemService.updateViewdOrSolvedByObj(id, "2");

		toDoItemService.updateUrgePayStatusByItemId(id,UrgeToPayConstantUtil.URGE_STATUS_20);

	}
	@RequestMapping(value = "updateViewed")
	public String updateViewed(String id,String url,HttpServletRequest request){
		
		String webName = request.getContextPath();
		url = url.replaceFirst(webName, "");

		toDoItemService.updateViewdOrSolvedByObj(id, "0");
		return  "redirect:"+url;
	}
}
