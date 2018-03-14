package cn.damei.web.mobile.Worker;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Worker.Message;
import cn.damei.entity.mobile.Worker.MessageRecord;
import cn.damei.service.mobile.Worker.MessageRecordService;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker")
public class MessageRecordController {
	
	@Autowired 
	private MessageRecordService messageRecordService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="saveMessageRecord")
	public @ResponseBody String saveMessageRecord(Integer msgId , HttpServletRequest request){
		
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		Message message = messageService.findByMsgId(msgId);
		
		List<MessageRecord> records = messageRecordService.selectAllRecord();
		
		boolean flag = true;
		if(records != null && records.size() > 0){
			for (MessageRecord messageRecord : records) {
				if(messageRecord.getMsgId().equals(message.getMsgId()) && messageRecord.getEmployeeId().equals(worker.getId())){
					messageRecord.setRecordTime(new Date());
					messageRecord.setMsgId(worker.getId());
					messageRecordService.update(messageRecord);
					flag = false;
				}
			}
		}
		
		if(flag){
			MessageRecord messageRecord = new MessageRecord();
			messageRecord.setMsgId(message.getMsgId());
			messageRecord.setRecordTime(new Date());
			messageRecord.setEmployeeId(worker.getId());
			messageRecordService.insert(messageRecord);
		}
		return "success";
	}
}
