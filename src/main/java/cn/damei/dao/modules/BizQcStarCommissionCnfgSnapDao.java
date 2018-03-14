
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;


@MyBatisDao
public interface BizQcStarCommissionCnfgSnapDao extends CrudDao2<BizQcStarCommissionCnfgSnap> {

	BizQcStarCommissionCnfgSnap queryByMap(Map<String, Object> map);

	BizQcStarCommissionCnfgSnap findBqsccsByOrderId(Integer orderId);
	
}