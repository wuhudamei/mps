/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendCnfg;
import cn.damei.entity.modules.BizPmAttendCnfgStar;

/**
 * 项目经理考勤基础设置DAO接口
 * @author lzm
 * @version 2017-08-02
 */
@MyBatisDao
public interface BizPmAttendCnfgDao extends CrudDao<BizPmAttendCnfg> {
	void saveBizPmAttendCnfgStarList(List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList);
	List<BizPmAttendCnfgStar> findBizPmAttendCnfgStarListByCnfgId(String id);
	void updateBizPmAttendCnfgStarListById(BizPmAttendCnfgStar bizPmAttendCnfgStar);
	void deleteStarById(String id);
	int checkRepeateByStorIdAndMonth(String storId,String month,String id,String projectMode);
	void deleteStarByCnfgId(String pmAttendCnfgId);
	void updateIsEnabledById(String id,String isEnabled);

    void updateSignEnabled(String signId, String isEnabled);
}