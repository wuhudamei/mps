package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReportRelatedOrder;

/**
 * 返单上报关联订单Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizOrderReportRelatedOrderDao extends CrudDao2<BizOrderReportRelatedOrder>{

}
