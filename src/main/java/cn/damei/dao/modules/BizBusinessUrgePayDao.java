
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.entity.modules.BizBusinessUrgePayEntity;


@MyBatisDao
public interface BizBusinessUrgePayDao extends CrudDao<BizBusinessUrgePayEntity> {
	Backlog getOrderInfo(Integer orderId);
}