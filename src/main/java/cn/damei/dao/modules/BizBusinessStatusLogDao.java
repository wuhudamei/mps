/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessStatusLog;

/**
 * 业务状态记录表DAO接口
 * @author wyb
 * @version 2017-03-16
 */
@MyBatisDao
public interface BizBusinessStatusLogDao extends CrudDao2<BizBusinessStatusLog> {

	/**
	 * 查询状态日志
	 * @param bizBusinessStatusLog
	 * @return
	 */
	List<BizBusinessStatusLog> findInstallStatusLog(BizBusinessStatusLog bizBusinessStatusLog);
	
	BizBusinessStatusLog queryOrderPmSettleLog(Map<String,Object> param);

	List<BizBusinessStatusLog> findListByVarchar(BizBusinessStatusLog log);

	List<BizBusinessStatusLog> findMyList(BizBusinessStatusLog log);

	/**
	 * 查询5分钟内操作的数量
	 * @param bizBusinessStatusLog
	 * @return
	 */
	Integer findFiveTimeApplyCount(BizBusinessStatusLog bizBusinessStatusLog);
}