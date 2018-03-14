package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizStarSetting;
/**
 * 评价指标设置DAO接口
 * @author ws
 * @version 2017-09-05
 */
@MyBatisDao
public interface BizStarSettingDao extends CrudDao2<BizStarSetting> {
	List<BizStarSetting> findIsCopy(BizStarSetting bizStarSetting);

	List<BizStarSetting> findIsCopyStar(BizStarSetting bizStarSetting);

	//List<BizStarSetting> findIsNotCopyStar(BizStarSetting bizStarSetting);

	List<BizStarSetting> findIsNotCopyStar(Integer storeId, String projectMode,Integer star);
}