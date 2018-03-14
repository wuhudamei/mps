package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillRoomCabinet;


@MyBatisDao
public interface RecheckScaleBillRoomCabinetDao extends CrudDao2<RecheckScaleBillRoomCabinet>{

	boolean insertCabinet(RecheckScaleBillRoomCabinet cabinet);

	List<RecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs);


}
