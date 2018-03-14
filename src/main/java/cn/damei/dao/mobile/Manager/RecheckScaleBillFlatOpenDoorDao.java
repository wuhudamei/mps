package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillFlatOpenDoor;


@MyBatisDao
public interface RecheckScaleBillFlatOpenDoorDao extends CrudDao2<RecheckScaleBillFlatOpenDoor>{

	boolean insertDoor(RecheckScaleBillFlatOpenDoor openDoor);

	List<RecheckScaleBillFlatOpenDoor> getByRecheckID(Integer recheckIDs);

}
