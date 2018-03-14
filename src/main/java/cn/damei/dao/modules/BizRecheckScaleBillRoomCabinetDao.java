package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillRoomCabinet;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 浴室柜复尺
 * 2016-11-15
 * BIZ_RECHECK_SCALE_BILL_BATHROOM_CABINET
 */
@MyBatisDao
public interface BizRecheckScaleBillRoomCabinetDao extends CrudDao2<BizRecheckScaleBillRoomCabinet>{

	List<BizRecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs);


}
