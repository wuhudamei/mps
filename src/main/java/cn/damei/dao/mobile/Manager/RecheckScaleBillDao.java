package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBill;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 复尺表
 * biz_recheck_scale_bill
 * 2016-11-15
 */
@MyBatisDao
public interface RecheckScaleBillDao extends CrudDao2<RecheckScaleBill>{

	List<RecheckScaleBill> queryListByOrderID(Integer orderID);

	RecheckScaleBill getByID(Integer recheckIDs);


}
