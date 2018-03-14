package cn.damei.service.mobile.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.home.JobSiteDao;
import cn.damei.entity.mobile.home.CustomerOrderVo;

@Service
@Transactional(readOnly=true)
public class JobSiteService {
@Autowired
private JobSiteDao dao;
	

public List<CustomerOrderVo> findBroadCastWithOrderLimitByCustomerPhone(CustomerOrderVo orderVo){
	
	
	return dao.findBroadCastWithOrderLimitByCustomerPhone(orderVo);
}
}
