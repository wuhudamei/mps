
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfg;


@MyBatisDao
public interface BizPmGuaranteeMoneyCnfgDao extends CrudDao2<BizPmGuaranteeMoneyCnfg> {

	BizPmGuaranteeMoneyCnfg queryByStoreIdAndProjectModel(Map<String, Object> map);
	
}