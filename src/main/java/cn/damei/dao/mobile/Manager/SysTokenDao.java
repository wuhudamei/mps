package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.SysToken;

@MyBatisDao
public interface SysTokenDao extends CrudDao2<SysToken>{

	SysToken findByTokenId(String tokenid,String appType);
	
	SysToken findByTokenIdForIndex(String tokenid,String appType);

	SysToken finByPhone(String phoneNumber,String appType);

	void deleteByPhone(String phone,String appType);
	
}
