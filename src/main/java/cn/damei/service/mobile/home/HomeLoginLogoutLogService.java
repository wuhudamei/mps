package cn.damei.service.mobile.home;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.home.HomeLoginLogoutLogDao;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;


@Service
public class HomeLoginLogoutLogService extends CrudService2<HomeLoginLogoutLogDao,HomeLoginLogoutLog>{
	
	
	public List<HomeLoginLogoutLog> findList(HomeLoginLogoutLog homeLoginLogoutLog) {
		return super.findList(homeLoginLogoutLog);
	}
	
	public Page<HomeLoginLogoutLog> findPage(Page<HomeLoginLogoutLog> page, HomeLoginLogoutLog homeLoginLogoutLog) {
		return super.findPage(page, homeLoginLogoutLog);
	}
	

	public Map<String,Object> summaryQueryByCondition(HomeLoginLogoutLog homeLoginLogoutLog){
		return this.dao.summaryQueryByCondition(homeLoginLogoutLog);
	}
}
