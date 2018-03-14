/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.WorkerSign;

/**
 * 项目经理签到查询DAO接口
 * @author 梅浩
 * @version 2016-09-26
 */
@MyBatisDao
public interface WorkerSignInfoDao extends CrudDao2<WorkerSign> {
	
}