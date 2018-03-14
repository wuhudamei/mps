package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderDisclose;

/**
 * 项目经理端
 * 现场交底
 * @author llp
 * 2016/10/17
 */
@MyBatisDao
public interface OrderDiscloseDao extends CrudDao2<OrderDisclose>{

	void insertByDisclose(OrderDisclose dis);

	Integer findByOrderId(Integer orderId);

}
