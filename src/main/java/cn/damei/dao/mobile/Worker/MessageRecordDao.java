package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.MessageRecord;

@MyBatisDao
public interface MessageRecordDao extends CrudDao2<MessageRecord>{

	List<MessageRecord> selectAllRecord();

	MessageRecord findByMsgIdAndEmployeeId(Integer msgId, Integer employeeId);
	
}
