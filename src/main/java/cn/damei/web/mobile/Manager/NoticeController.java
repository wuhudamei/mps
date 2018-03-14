package cn.damei.web.mobile.Manager;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.entity.mobile.Worker.MessageRecord;
import cn.damei.service.mobile.Worker.MessageRecordService;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.modules.BizNotice;
import cn.damei.service.modules.BizNoticeService;
import cn.damei.entity.modules.BizNoticeViewLog;
import cn.damei.service.modules.BizNoticeViewLogService;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 进度通报
 * biz_node_plan
 * llp
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class NoticeController {
	private static Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private BizNoticeService bizNoticeService;

	@Autowired
	private BizNoticeViewLogService bizNoticeViewLogService;

	@Autowired
	private MessageService messageService;
	
	@Autowired 
	private MessageRecordService messageRecordService;
	
	@RequestMapping(value={"queryMsgPageList",""})
	public String queryMsgPageList() {

		return "mobile/modules/Manager/notice/msg_index_list";
	}

	@RequestMapping(value={"queryNoticePageList",""})
	public String queryNoticePageList(HttpServletRequest request, Model model) {
		Manager manager = SessionUtils.getManagerSession(request);
		BizNotice notice = new BizNotice();
		notice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_2);
		notice.setReceiverEmployeeId(manager.getId());
		Integer count = bizNoticeService.queryAppNoticePageListCount(notice);
		model.addAttribute("managerId", manager.getId());
		model.addAttribute("count", count);
		return "mobile/modules/Manager/notice/notice_index_list";
	}

	@RequestMapping(value={"queryLoadNoticePageList",""})
	public @ResponseBody Page<BizNotice> queryLoadNoticePageList(BizNotice bizNotice, HttpServletRequest request, HttpServletResponse response) {
		bizNotice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_2);
		Page<BizNotice> page = bizNoticeService.findAppNoticePage(new Page<BizNotice>(request, response), bizNotice);
		return page;
	}

	@RequestMapping(value={"queryNoticePageListDetail",""})
	public String queryNoticePageListDetail(Integer id, Model model) {
		BizNotice notice = bizNoticeService.get(id);
		notice.setNoticeContent(StringEscapeUtils.unescapeHtml(notice.getNoticeContent()));
		model.addAttribute("notice", notice);
		return "mobile/modules/Manager/notice/notice_index_list_detail";
	}

	@RequestMapping(value={"saveNoticeLog",""})
	public void saveNoticeLog(HttpServletRequest request, Integer id) {
		try{
			Date date = new Date();
			Manager manager = SessionUtils.getManagerSession(request);
			BizNoticeViewLog log = new BizNoticeViewLog();
			log.setNoticeId(id);
			log.setViewEmployeeId(manager.getId());
			BizNoticeViewLog noticeLog = bizNoticeViewLogService.queryByNoticeIdAndEmployeeId(log);
			if(noticeLog != null){
				noticeLog.setViewDatetime(date);
				bizNoticeViewLogService.update(noticeLog);
			}else{
				noticeLog = new BizNoticeViewLog();
				noticeLog.setNoticeId(id);
				noticeLog.setViewEmployeeId(manager.getId());
				noticeLog.setViewDatetime(date);
				noticeLog.setCreateDate(date);
				noticeLog.setUpdateDate(date);
				bizNoticeViewLogService.insert(noticeLog);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="message")
	public String message(Model model,Integer page,Integer rows,HttpServletRequest request){
		Manager manager = SessionUtils.getManagerSession(request);
		Integer intPage = ((page == null || page == 0)?1:page); //页号
		Integer number = ((rows==null || rows == 0)?10:rows); //每页数 
		Integer start = (intPage - 1)*number;   //开始值
		/*List<Message> unreadMessage = messageService.findUnreadMessage(manager.getId());*///查询未读的消息
		List<Message> messages = messageService.findMessagePage(start,number,manager.getId());//优化1--查询所有的消息
		/*model.addAttribute("unreadMessage",unreadMessage);*/
		model.addAttribute("messages",messages);
		return "mobile/modules/Manager/notice/msg_index_list";
	}
	@RequestMapping(value="loadMore")
	public @ResponseBody List<Message> loadMore(Model model,Integer page,Integer rows,HttpServletRequest request){
		Manager manager = SessionUtils.getManagerSession(request);
		int intPage = ((page == null || page == 0)?1:page); //页号
		int number = ((rows==null || rows == 0)?10:rows); //每页数 
		int start = (intPage - 1)*number;   //开始值
		List<Message> messagePage = messageService.findMessagePage(start,number,manager.getId());//优化1
		return messagePage;
	}
	
	
	@RequestMapping(value="messageDetail")
	public String messageDetail(Integer msgId,Model model){
		Message message = messageService.findByMsgId(msgId);
		MessageRecord messageRecord = messageRecordService.findByMsgIdAndEmployeeId(message.getMsgId(),message.getEmployeeId());
		if(messageRecord != null){
			messageRecord.setRecordTime(new Date());
			messageRecordService.update(messageRecord);
		}else{
			MessageRecord messageRecord1 = new MessageRecord();
			messageRecord1.setMsgId(message.getMsgId());
			messageRecord1.setRecordTime(new Date());
			messageRecord1.setEmployeeId(message.getEmployeeId());
			messageRecordService.insert(messageRecord1);
		}
		model.addAttribute("message",message);
		return "mobile/modules/Manager/notice/msg_details";
	}
	
}
