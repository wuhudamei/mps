package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;
@MyBatisDao
public interface OrderStartWorkerDateSetDao extends CrudDao<ConfirmStartOrder>{

	ConfirmStartOrder findDetail(ConfirmStartOrder confirmStartOrder);

}
