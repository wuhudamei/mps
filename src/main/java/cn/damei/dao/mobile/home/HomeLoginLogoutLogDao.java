package cn.damei.dao.mobile.home;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;


@MyBatisDao
public interface HomeLoginLogoutLogDao extends CrudDao2<HomeLoginLogoutLog>  {
	

	Map<String,Object> summaryQueryByCondition(HomeLoginLogoutLog homeLoginLogoutLog);
}
