package cn.damei.service.mobile.home;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.home.WeiChatOpenIdDao;
import cn.damei.entity.mobile.home.WeiChatOpenId;

@Service
@Transactional(readOnly=true)
public class WeiChatOpenIdService extends CrudService2<WeiChatOpenIdDao, WeiChatOpenId>{

	public WeiChatOpenId findByOpendId(String openid) {

		return dao.findByOpenId(openid);
	}

	public void updateByPhone(String phone) {
		
		dao.updateByPhone(phone);
	}
	
}
