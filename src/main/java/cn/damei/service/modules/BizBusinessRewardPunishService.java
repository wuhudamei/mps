package cn.damei.service.modules;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizBusinessRewardPunishDao;
import cn.damei.entity.modules.BizBusinessRewardPunish;


@Service
@Transactional(readOnly = true)
public class BizBusinessRewardPunishService extends CrudService2<BizBusinessRewardPunishDao, BizBusinessRewardPunish>{

	@Transactional(readOnly = false)
	public void save(BizBusinessRewardPunish bizBusinessRewardPunish) {
		super.save(bizBusinessRewardPunish);
	}
	
	public BizBusinessRewardPunish queryBusinessRewardPunishByParam(Map<String,Object> param){
		return dao.queryBusinessRewardPunishByParam(param);
	}
}
