
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderFinishProjectBill;


@MyBatisDao
public interface BizOrderFinishProjectBillDao extends CrudDao2<BizOrderFinishProjectBill>{

	BizOrderFinishProjectBill getByOrderID(Integer orderID);

	boolean updateByDate(String realFinishProjectDate, Integer id);

	boolean updateByOrderID(Integer orderID, String addDate, Integer managerID,String status3,
			String orderstatus340ValueRemark, String string);

	boolean updateByOrderIDOrFail(Integer orderID, String checkwords,String status,String remarks, String date1,Integer managerID);

	boolean updateByStatusOrRemarks(String orderFinishProjectBillStatus4, String orderstatus400ValueRemark,
			String date1, Integer integer);

	void updateOrderById(Integer valueOf, String realFinishProjectDate);

}