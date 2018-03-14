
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.CompletedOrder;


@MyBatisDao
public interface CompletedOrderDao extends CrudDao2<CompletedOrder>{

	List<CompletedOrder> queryList(Integer itemManagerID);

	boolean updateByStatus(String orderstatusValue, String orderstatusRemark, String orderID);

	CompletedOrder getByID(Integer id);
}