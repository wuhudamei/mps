package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.Message;

@MyBatisDao
public interface MessageDao extends CrudDao2<Message>{

	List<Message> findAllMessage(Integer employeeId);
	
	List<Message> findReadedMessage(Integer employeeId);

	Message findByMsgId(Integer msgId);

	List<Message> findMessagePage(Integer start, Integer number,Integer employeeId);

	List<Message> findUnreadMessage(Integer id); 
	List<Message> findMessage(Integer id);
	List<Integer>findReadMessage(Integer id);

}
