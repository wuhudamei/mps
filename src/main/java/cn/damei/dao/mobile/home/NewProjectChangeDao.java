package cn.damei.dao.mobile.home;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.entity.mobile.home.BizProjectChangeBill;

@MyBatisDao
public interface NewProjectChangeDao {


	List<BizOrder> findOrderList(String customerPhone);


	BizOrder findProjectChangeBillList(BizOrder bizOrder);


	BizProjectChangeBill projectChangeDetail(Integer projectChangeId);


	void updateChangeBill(BizProjectChangeBill bizProjectChangeBill);


	
	
}
