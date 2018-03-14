package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillPushPullDoor;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 2016-11-15
 * 窗帘	
 * biz_recheck_scale_bill_curtain
 */
@MyBatisDao
public interface RecheckScaleBillPushPullDoorDao extends CrudDao2<RecheckScaleBillPushPullDoor>{

	boolean insertpush(RecheckScaleBillPushPullDoor door);

	List<RecheckScaleBillPushPullDoor> getByRecheckID(Integer recheckIDs);


}
