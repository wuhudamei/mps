
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;


@MyBatisDao
public interface BizPmGuaranteeMoneyCnfgSnapDao extends CrudDao2<BizPmGuaranteeMoneyCnfgSnap> {

	void save(BizPmGuaranteeMoneyCnfgSnap gmcs);

	BizPmGuaranteeMoneyCnfgSnap findGmc(Integer orderId);
	
}