package cn.damei.common.utils.phoneMessage;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface PhoneMessageDao{

	
	
	
	 void saveMessageContent(PhoneMessageEntity entity);

	 List<MessageEmployeePhoneAndId> getEmployeePhoneAndId(Integer storeId,String dictValue);


	  Integer checkIsExistByTypeAndBusinessId(PhoneMessageEntity entity);

	  List<Map<String,Integer>> batchCheckIsExist(List<PhoneMessageEntity> list);




	void batchSavePhoneInfo(List<PhoneMessageEntity>list);

}
