package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillPushPullDoor;


@MyBatisDao
public interface BizRecheckScaleBillPushPullDoorDao extends CrudDao2<BizRecheckScaleBillPushPullDoor>{

	List<BizRecheckScaleBillPushPullDoor> getByRecheckID(Integer recheckIDs);


}
