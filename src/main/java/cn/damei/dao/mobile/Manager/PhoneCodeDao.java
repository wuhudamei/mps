package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PhoneCode;
@MyBatisDao
public interface PhoneCodeDao extends CrudDao2<PhoneCode>{

	PhoneCode findByUsernameAndCode(String username, String code ,String appType);

	PhoneCode findByPhone(String phoneNumber,String appType);

}
