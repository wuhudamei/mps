package cn.damei.dao.mobile.home;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;

/**
 * 
 * <dl>
 * <dd>Description: 业主APP登录日志</dd>
 * <dd>Company: 大城若谷信息技术有限公司</dd>
 * <dd>@date：2017年8月31日 下午8:02:14</dd>
 * <dd>@author：Li wancai</dd>
 * </dl>
 */
@MyBatisDao
public interface HomeLoginLogoutLogDao extends CrudDao2<HomeLoginLogoutLog>  {
	
	/**
	 * 汇总统计
	 * @param homeLoginLogoutLog
	 * @return
	 */
	Map<String,Object> summaryQueryByCondition(HomeLoginLogoutLog homeLoginLogoutLog);
}
