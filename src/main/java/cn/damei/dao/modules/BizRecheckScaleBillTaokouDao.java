package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillTaokou;


@MyBatisDao
public interface BizRecheckScaleBillTaokouDao extends CrudDao2<BizRecheckScaleBillTaokou>{

	List<BizRecheckScaleBillTaokou> getByRecheckID(Integer recheckID);

}
