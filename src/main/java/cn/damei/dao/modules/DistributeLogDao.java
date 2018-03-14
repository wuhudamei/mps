package cn.damei.dao.modules;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DistributeLogEntity;

@MyBatisDao
public interface DistributeLogDao {

	public  void saveDistributeLog(DistributeLogEntity distributeEntity);
}
