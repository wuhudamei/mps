package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillTaokou;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 套口
 * 2016-11-15
 * biz_recheck_scale_bill_taokou
 */
@MyBatisDao
public interface BizRecheckScaleBillTaokouDao extends CrudDao2<BizRecheckScaleBillTaokou>{

	List<BizRecheckScaleBillTaokou> getByRecheckID(Integer recheckID);

}
