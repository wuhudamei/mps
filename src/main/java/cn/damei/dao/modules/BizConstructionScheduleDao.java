
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizConstructionSchedule;


@MyBatisDao
public interface BizConstructionScheduleDao extends CrudDao<BizConstructionSchedule> {

	List<BizConstructionSchedule> getConsScheduleByIsOldHouseAndStoreId(String storeId, String houseIsNew, String projectMode);

	List<BizConstructionSchedule> getByStoreIdList(String storeId);

	List<BizConstructionSchedule> getByStoreIdAndDelflag(Integer stroeID, String isOldHouse);


	List<BizConstructionSchedule> getByEnableOrNewHouse(String stroeID, String isOldHouse);


	List<BizConstructionSchedule> getByEnableOrOldHouse();
	
}