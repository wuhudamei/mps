/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizConstructionSchedule;

/**
 * 工程进度节点DAO接口
 * @author 魏建勇
 * @version 2016-09-05
 */
@MyBatisDao
public interface BizConstructionScheduleDao extends CrudDao<BizConstructionSchedule> {

	List<BizConstructionSchedule> getConsScheduleByIsOldHouseAndStoreId(String storeId, String houseIsNew, String projectMode);

	List<BizConstructionSchedule> getByStoreIdList(String storeId);

	List<BizConstructionSchedule> getByStoreIdAndDelflag(Integer stroeID, String isOldHouse);

	/**获取新房的节点
	 * @param stroeID 
	 * @param isOldHouse **/
	List<BizConstructionSchedule> getByEnableOrNewHouse(String stroeID, String isOldHouse);

	/******获取老房的节点***********/
	List<BizConstructionSchedule> getByEnableOrOldHouse();
	
}