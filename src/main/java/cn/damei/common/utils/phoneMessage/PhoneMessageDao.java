package cn.damei.common.utils.phoneMessage;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface PhoneMessageDao{

	
	
	
	 void saveMessageContent(PhoneMessageEntity entity);
	/**
	 * 根据门店,短信组类型 ,查询该员工的手机号, name和id
	 * @param storeId
	 * @param dictValue
	 * @return MessageEmployeePhoneAndId
	 */
	 List<MessageEmployeePhoneAndId> getEmployeePhoneAndId(Integer storeId,String dictValue);

	/**
	 * 根据类型和businessId 查询是否已经插入
	 * @param entity
	 * @return
	 */
	  Integer checkIsExistByTypeAndBusinessId(PhoneMessageEntity entity);
	/**
	 * 批量 根据类型和businessId 查询是否已经插入
	 * @param list
	 * @return handId(主键) count(是否存在)
	 */
	  List<Map<String,Integer>> batchCheckIsExist(List<PhoneMessageEntity> list);


	/**
	 * 批量保存短信信息
	 */

	void batchSavePhoneInfo(List<PhoneMessageEntity>list);

}
