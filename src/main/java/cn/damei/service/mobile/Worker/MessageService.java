package cn.damei.service.mobile.Worker;


import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.MessageDao;
import cn.damei.entity.mobile.Worker.Message;

@Service
@Transactional(readOnly = false)
public class MessageService extends CrudService2<MessageDao, Message>{

	public List<Message> findAllMessage(Integer employeeId) {
		
		return dao.findAllMessage(employeeId);
	}

	public List<Message> findReadedMessage(Integer employeeId) {
		
		return dao.findReadedMessage(employeeId);
	}
	
	public int createMessage(Message message){
		return dao.insert(message);
	}

	public Message findByMsgId(Integer msgId) {
		
		return dao.findByMsgId(msgId);
	}

	public List<Message> findMessagePage(Integer start, Integer number,Integer employeedId ) {

		return dao.findMessagePage(start,number,employeedId);
	}


	public List<Message> findUnreadMessage(Integer id) {
List<Message> allMessage= dao.findMessage(id);
List<Integer> list= dao.findReadMessage(id);

for(Integer  l :list){
	Iterator iterator = allMessage.iterator();
	while(iterator.hasNext()){

		Message message =(Message) iterator.next();

		if(message.getMsgId().equals(l)){

			iterator.remove();
			break;
		}
	}



}


		return allMessage;
	}

	public void insert(Message message) {
		dao.insert(message);
	}
}
