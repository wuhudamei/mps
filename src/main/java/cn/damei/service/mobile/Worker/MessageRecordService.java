package cn.damei.service.mobile.Worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.MessageRecordDao;
import cn.damei.entity.mobile.Worker.MessageRecord;

@Service
@Transactional(readOnly = false)
public class MessageRecordService extends CrudService2<MessageRecordDao,MessageRecord>{

	public List<MessageRecord> selectAllRecord() {
		return dao.selectAllRecord();
	}

	public int insert(MessageRecord messageRecord) {
		return dao.insert(messageRecord);
	}

	public int update(MessageRecord messageRecord) {
		return dao.update(messageRecord);
	}

	public MessageRecord findByMsgIdAndEmployeeId(Integer msgId, Integer employeeId) {
		// TODO Auto-generated method stub
		return dao.findByMsgIdAndEmployeeId(msgId,employeeId);
	}

	
	
}
