package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizStarSetting;

@MyBatisDao
public interface BizStarSettingDao extends CrudDao2<BizStarSetting> {
	List<BizStarSetting> findIsCopy(BizStarSetting bizStarSetting);

	List<BizStarSetting> findIsCopyStar(BizStarSetting bizStarSetting);



	List<BizStarSetting> findIsNotCopyStar(Integer storeId, String projectMode,Integer star);
}