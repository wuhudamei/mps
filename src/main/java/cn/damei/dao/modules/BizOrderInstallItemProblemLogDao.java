/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;

/**
 * 订单安装项问题日志DAO接口
 * @author 汪文
 * @version 2017-02-20
 */
@MyBatisDao
public interface BizOrderInstallItemProblemLogDao extends CrudDao2<BizOrderInstallItemProblemLog> {

	BizOrderInstallItemProblemLog queryByProblemId(Map<String, Object> map);
	
}