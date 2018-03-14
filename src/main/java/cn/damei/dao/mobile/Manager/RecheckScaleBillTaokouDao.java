package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillTaokou;


@MyBatisDao
public interface RecheckScaleBillTaokouDao extends CrudDao2<RecheckScaleBillTaokou>{

	boolean insertTaokou(RecheckScaleBillTaokou taokou);

	List<RecheckScaleBillTaokou> getByRecheckID(Integer recheckID);


}
