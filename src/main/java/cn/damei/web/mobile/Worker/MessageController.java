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
		/*//查询所有的信息
		List<Message> allMessage = messageService.findAllMessage(worker.getId());//所有
		//查询已读的消息
		List<Message> readedMessage = messageService.findReadedMessage(worker.getId());//已读
		//所有的消息
		List<Message> temp = new ArrayList<Message>();
		//去除已读的消息 得到未读的消息
		for (int i = 0; i < allMessage.size(); i++) {	
			for (int j = 0; j < readedMessage.size(); j++) {
			if(allMessage.get(i).getMsgId() == readedMessage.get(j).getMsgId()){
				allMessage.remove(i);
					break;
				}	
			}
		}*/

		//List<Message> unreadeMessage = new ArrayList<Message>();
		
	/*	for (int i = 0; i < allMessage.size(); i++) {
			boolean isreaded = false;
			for (int j = 0; j < readedMessage.size(); j++) {
				if(allMessage.get(i).getMsgId() == readedMessage.get(j).getMsgId()){
					isreaded = true;
					break;
				}
			}
			if(!isreaded){
				unreadeMessage.add(allMessage.get(i));
			}
		}*/
		
		/*//将未读的消息放入集合
		for (Message message : allMessage) {
			temp.add(message);
		}
		//将已读的消息放入集合
		for (Message message : readedMessage) {
			temp.add(message);
		}*/
		Integer intPage = ((page == null || page == 0)?1:page); //页号
		Integer number = ((rows==null || rows == 0)?10:rows); //每页数 
		Integer start = (intPage - 1)*number;   //开始值
		
		/*List<Message> unreadMessage = messageService.findUnreadMessage(worker.getId());*///查询未读的消息
		List<Message> messages = messageService.findMessagePage(start,number,worker.getId());//优化1--查询所有的消息
	/*	model.addAttribute("unreadMessage",unreadMessage);*/
		model.addAttribute("messages",messages);

	/*	int totalCount = temp.size();  //总条数
		int pageCount = totalCount%number == 0 ? totalCount/number : totalCount/number +1;   //总页数
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("temp", temp);
		model.addAttribute("allMessage", allMessage);*/
		
		return "mobile/modules/Worker/chief_index_msg";
	}
	
	
	@RequestMapping(value="loadMore")
	public @ResponseBody List<Message> loadMore(Model model,Integer page,Integer rows,HttpServletRequest request){
		
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		/*//查询所有的信息
		List<Message> allMessage1 = messageService.findAllMessage(worker.getId());//所有
		//查询已读的消息
		List<Message> readedMessage = messageService.findReadedMessage(worker.getId());//已读
		//所有的消息
		List<Message> temp = new ArrayList<Message>();
		//去除已读的消息 得到未读的消息
		for (int i = 0; i < allMessage1.size(); i++) {
			
			for (int j = 0; j < readedMessage.size(); j++) {
				if(allMessage1.get(i).getMsgId() == readedMessage.get(j).getMsgId()){
					allMessage1.remove(i);
				}
			}
		}
		//将未读的消息放入集合
		for (Message message : allMessage1) {
			temp.add(message);
		}
		//将已读的消息放入集合
		for (Message message : readedMessage) {
			temp.add(message);
		}*/
		
		int intPage = ((page == null || page == 0)?1:page); //页号
		int number = ((rows==null || rows == 0)?10:rows); //每页数 
		int start = (intPage - 1)*number;   //开始值

		/*int totalCount = temp.size();  //总条数
		int pageCount = totalCount%number == 0 ? totalCount/number : totalCount/number +1;   //总页数
		List<Message> messagePage = null;  
		if(intPage == pageCount){  
			messagePage = temp.subList(start,totalCount );   
		}else{  
			messagePage = temp.subList(start,start+number);  
		}
		model.addAttribute("allMessage1", allMessage1);*/
		
		List<Message> messagePage = messageService.findMessagePage(start,number,worker.getId());//优化1
		
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
