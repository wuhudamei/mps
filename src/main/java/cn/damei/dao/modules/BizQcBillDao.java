
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcBill;


@MyBatisDao
public interface BizQcBillDao extends CrudDao<BizQcBill> {
	
	public BizQcBill queryQcBillByParam(Map<String,Object> param);

	public List<BizQcBill> findBizQcBillByOrderId(BizQcBill bizQcBill);
	
	public int checkSettleCheckNodeByOrderId(Integer ordreId);
	
}