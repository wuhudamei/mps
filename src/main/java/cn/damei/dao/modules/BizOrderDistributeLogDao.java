
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderDistributeLog;


@MyBatisDao
public interface BizOrderDistributeLogDao extends CrudDao2<BizOrderDistributeLog> {
	
	public List<BizOrderDistributeLog> queryOrderPmDistributeLogByOrderId(Integer orderId);
}