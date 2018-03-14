package cn.damei.web.mobile.Worker;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Worker.Message;
import cn.damei.entity.mobile.Worker.MessageRecord;
import cn.damei.service.mobile.Worker.MessageRecordService;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	@Autowired 
	private MessageRecordService messageRecordService;
	
	@RequestMapping(value="message")
	public String message(Model model,Integer page,Integer rows,HttpServletRequest request){
		
		Worker worker = (Worker) request.getSession().getAttribute("worker");



		

		

		Integer intPage = ((page == null || page == 0)?1:page);
		Integer number = ((rows==null || rows == 0)?10:rows);
		Integer start = (intPage - 1)*number;
		

		List<Message> messages = messageService.findMessagePage(start,number,worker.getId());

		model.addAttribute("messages",messages);


		
		return "mobile/modules/Worker/chief_index_msg";
	}
	
	
	@RequestMapping(value="loadMore")
	public @ResponseBody List<Message> loadMore(Model model,Integer page,Integer rows,HttpServletRequest request){
		
		Worker worker = (Worker) request.getSession().getAttribute("worker");

		
		int intPage = ((page == null || page == 0)?1:page);
		int number = ((rows==null || rows == 0)?10:rows);
		int start = (intPage - 1)*number;


		
		List<Message> messagePage = messageService.findMessagePage(start,number,worker.getId());
		
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
		return "mobile/modules/Worker/msg_details";
	}
}
