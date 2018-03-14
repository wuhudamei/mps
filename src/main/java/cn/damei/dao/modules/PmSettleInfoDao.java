package cn.damei.dao.modules;

import java.util.List;

import cn.damei.entity.modules.PmSettleInfo;
import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface PmSettleInfoDao {

	public List<PmSettleInfo> queryPmSettleInfoByOrderNumber(String orderNum);
}
