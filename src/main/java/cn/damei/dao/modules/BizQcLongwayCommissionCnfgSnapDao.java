
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;


@MyBatisDao
public interface BizQcLongwayCommissionCnfgSnapDao extends CrudDao2<BizQcLongwayCommissionCnfgSnap> {

	BizQcLongwayCommissionCnfgSnap queryByMap(Map<String, Object> map);

	BizQcLongwayCommissionCnfgSnap findBqlccsByOrderId(Integer orderId);

	void insert(BizPmSettleCategoryDetail details);
	
}