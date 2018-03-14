package cn.damei.dao.mobile.home;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.WeiChatOpenId;

@MyBatisDao
public interface WeiChatOpenIdDao extends CrudDao2<WeiChatOpenId>{

	WeiChatOpenId findByOpenId(String openid);

	void updateByPhone(String phone);
	
}
