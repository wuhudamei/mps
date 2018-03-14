package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillFlatOpenDoor;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 2016-11-15
 * 窗帘	
 * biz_recheck_scale_bill_curtain
 */
@MyBatisDao
public interface RecheckScaleBillFlatOpenDoorDao extends CrudDao2<RecheckScaleBillFlatOpenDoor>{

	boolean insertDoor(RecheckScaleBillFlatOpenDoor openDoor);

	List<RecheckScaleBillFlatOpenDoor> getByRecheckID(Integer recheckIDs);

}
