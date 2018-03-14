package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillRoomCabinet;


@MyBatisDao
public interface BizRecheckScaleBillRoomCabinetDao extends CrudDao2<BizRecheckScaleBillRoomCabinet>{

	List<BizRecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs);


}
