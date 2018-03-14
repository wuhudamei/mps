package cn.damei.dao.modules;

import java.util.Date;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBill;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 复尺表
 * biz_recheck_scale_bill
 * 2016-11-15
 */
@MyBatisDao
public interface BizRecheckScaleBillDao extends CrudDao2<BizRecheckScaleBill>{

	void updateByRecheckStatus(String status, String remarks, Date addDate, Integer recheckID);

}
