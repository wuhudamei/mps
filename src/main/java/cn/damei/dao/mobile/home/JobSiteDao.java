package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CustomerOrderVo;

@MyBatisDao
public interface JobSiteDao {

	
	
	public List<CustomerOrderVo> findBroadCastWithOrderLimitByCustomerPhone(CustomerOrderVo orderVo);
}
