package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessRewardPunish;

/**
 * 奖励惩罚Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizBusinessRewardPunishDao extends CrudDao2<BizBusinessRewardPunish>{
	public BizBusinessRewardPunish queryBusinessRewardPunishByParam(Map<String,Object> param);

}
