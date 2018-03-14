package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillToilet;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 套口
 * 2016-11-15
 * biz_recheck_scale_bill_taokou
 */
@MyBatisDao
public interface RecheckScaleBillToiletDao extends CrudDao2<RecheckScaleBillToilet>{

	boolean insertToilet(RecheckScaleBillToilet taokou);

	List<RecheckScaleBillToilet> getByRecheckID(Integer recheckIDs);


}
