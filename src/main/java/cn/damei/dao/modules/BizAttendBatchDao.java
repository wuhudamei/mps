/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAttendBatch;

/**
 * 考勤批次DAO接口
 * @author cgh
 */
@MyBatisDao
public interface BizAttendBatchDao extends CrudDao2<BizAttendBatch> {
	
}