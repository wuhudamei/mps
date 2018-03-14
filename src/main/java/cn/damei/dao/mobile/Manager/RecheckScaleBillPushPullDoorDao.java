package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillPushPullDoor;


@MyBatisDao
public interface RecheckScaleBillPushPullDoorDao extends CrudDao2<RecheckScaleBillPushPullDoor>{

	boolean insertpush(RecheckScaleBillPushPullDoor door);

	List<RecheckScaleBillPushPullDoor> getByRecheckID(Integer recheckIDs);


}
