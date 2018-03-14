package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillRoomCabinet;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 浴室柜复尺
 * 2016-11-15
 * BIZ_RECHECK_SCALE_BILL_BATHROOM_CABINET
 */
@MyBatisDao
public interface RecheckScaleBillRoomCabinetDao extends CrudDao2<RecheckScaleBillRoomCabinet>{

	boolean insertCabinet(RecheckScaleBillRoomCabinet cabinet);

	List<RecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs);


}
