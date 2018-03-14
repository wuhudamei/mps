package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillFlatOpenDoor;


@MyBatisDao
public interface BizRecheckScaleBillFlatOpenDoorDao extends CrudDao2<BizRecheckScaleBillFlatOpenDoor>{

	List<BizRecheckScaleBillFlatOpenDoor> getByRecheckID(Integer recheckIDs);

}
