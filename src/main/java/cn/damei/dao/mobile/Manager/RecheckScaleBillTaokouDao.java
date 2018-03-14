package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillTaokou;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 套口
 * 2016-11-15
 * biz_recheck_scale_bill_taokou
 */
@MyBatisDao
public interface RecheckScaleBillTaokouDao extends CrudDao2<RecheckScaleBillTaokou>{

	boolean insertTaokou(RecheckScaleBillTaokou taokou);

	List<RecheckScaleBillTaokou> getByRecheckID(Integer recheckID);


}
