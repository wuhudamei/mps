package cn.damei.dao.mobile.home;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.entity.mobile.home.BizProjectChangeBill;
/**
 * 施工变更单
 * @author Administrator
 *
 */
@MyBatisDao
public interface NewProjectChangeDao {

	/**
	 * 查询订单列表
	 * @param customerPhone
	 * @return
	 */
	List<BizOrder> findOrderList(String customerPhone);

	/**
	 * 查询施工变更单列表
	 * @param bizProjectChangeBill
	 * @return
	 */
	BizOrder findProjectChangeBillList(BizOrder bizOrder);

	/**
	 * 变更单详情
	 * @param projectChangeId
	 * @return
	 */
	BizProjectChangeBill projectChangeDetail(Integer projectChangeId);

	/**
	 * 客户审核
	 * @param bizProjectChangeBill
	 */
	void updateChangeBill(BizProjectChangeBill bizProjectChangeBill);


	
	
}
