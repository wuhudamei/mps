
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;


@MyBatisDao
public interface BizPmStarCommissionCnfgSnapDao extends CrudDao2<BizPmStarCommissionCnfgSnap> {

	List<BizPmStarCommissionCnfgSnap> queryByMap(Map<String, Object> map);

	BizPmStarCommissionCnfgSnap findSccs(Integer orderId);
	
}