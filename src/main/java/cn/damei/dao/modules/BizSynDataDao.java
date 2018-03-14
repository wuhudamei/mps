
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSynData;


@MyBatisDao
public interface BizSynDataDao extends CrudDao2<BizSynData> {
	
	public List<BizSynData> findPrePmSettleFinList(BizSynData bizSynData);
	
}