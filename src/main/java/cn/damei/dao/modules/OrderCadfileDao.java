
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderCadfile;


@MyBatisDao
public interface OrderCadfileDao extends CrudDao2<OrderCadfile> {

	void saveCadfile(OrderCadfile orderCadfile);

	void insertCadfile(OrderCadfile orderCadfile);
	OrderCadfile findDtail(OrderCadfile orderCadfile);
	

}