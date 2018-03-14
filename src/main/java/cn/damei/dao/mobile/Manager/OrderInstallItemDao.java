package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderInstallItem;

/**
 * 工程安装
 * @author llp
 * 2016/10/28
 */
@MyBatisDao
public interface OrderInstallItemDao extends CrudDao2<OrderInstallItem>{

	List<OrderInstallItem> getByOrderIdList(Integer orderId);

}
