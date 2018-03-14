package cn.damei.service.mobile.home;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.home.HomeLoginLogoutLogDao;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;

/**
 * 
 * <dl>
 * <dd>Description: 业主app登陆日志操作service</dd>
 * <dd>Company: 大城若谷信息技术有限公司</dd>
 * <dd>@date：2017年8月31日 下午8:27:32</dd>
 * <dd>@author：Li wancai</dd>
 * </dl>
 */
@Service
public class HomeLoginLogoutLogService extends CrudService2<HomeLoginLogoutLogDao,HomeLoginLogoutLog>{
	
	
	public List<HomeLoginLogoutLog> findList(HomeLoginLogoutLog homeLoginLogoutLog) {
		return super.findList(homeLoginLogoutLog);
	}
	
	public Page<HomeLoginLogoutLog> findPage(Page<HomeLoginLogoutLog> page, HomeLoginLogoutLog homeLoginLogoutLog) {
		return super.findPage(page, homeLoginLogoutLog);
	}
	
	/**
	 * 根据查询条件汇总实际人数、微信端操作人数以及APP端操作人数
	 * @param homeLoginLogoutLog
	 * @return map
	 */
	public Map<String,Object> summaryQueryByCondition(HomeLoginLogoutLog homeLoginLogoutLog){
		return this.dao.summaryQueryByCondition(homeLoginLogoutLog);
	}
}
