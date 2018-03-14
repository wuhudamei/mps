package cn.damei.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Manager.BacklogService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizNotice;
import cn.damei.service.modules.BizNoticeService;

public class MsgInterceptor implements HandlerInterceptor{
	@Autowired
	private MessageService messageService;
	@Autowired
	private BizNoticeService bizNoticeService;
	@Autowired
	private BacklogService backlogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Integer id = null;
		String requestURI = request.getRequestURI();
		if(requestURI.contains("/worker")){
			Worker worker =(Worker) request.getSession().getAttribute("worker");
			if(worker!=null&&worker.getId()!=null){
				id = worker.getId();
			}
		}else if(requestURI.contains("/manager")){
			Manager manager =(Manager) request.getSession().getAttribute("manager");
			if(manager!=null&&manager.getId()!=null){
				id = manager.getId();
				Integer managerId = manager.getId();
				int toDoDealNum = backlogService.findTotalCountTodo(managerId);
				request.setAttribute("toDoDealNum", toDoDealNum);
			}
		}else if(requestURI.contains("/pqc")){
			 Inspector inspector  =(Inspector) request.getSession().getAttribute("inspector");
			 if(inspector!=null&&inspector.getId()!=null){
					id = inspector.getId();
				}
		}
		List<Message> unreadMessage = messageService.findUnreadMessage(id);
		int size = unreadMessage.size();
		BizNotice notice = new BizNotice();
		notice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_2);
		notice.setReceiverEmployeeId(id);
		Integer count = bizNoticeService.findAppNoticePageListNum(notice);
		request.setAttribute("unreadMessageNum", size+count);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
	}

}
