
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAttendBill;


@MyBatisDao
public interface BizAttendBillDao extends CrudDao2<BizAttendBill> {
	

	public List<BizAttendBill> findNoBillList(BizAttendBill bizAttendBill);
	

	public List<BizAttendBill> findBillList(BizAttendBill bizAttendBill);
	

	public void updateStatus(@Param("ids")String[] ids,@Param("status") String status,@Param("attendBatchId")String attendBatchId);
	

	public List<BizAttendBill> findBizAttendBillListByBatchId(@Param("attendBatchId")Integer attendBatchId);
	

	public void updateBatchIds(List<BizAttendBill> list);
}