/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.TaskPackDetails;
import cn.damei.entity.modules.WorkerInfo;

/**
 * 任务包详情DAO接口
 * @author 张康健
 * @version 
 */
@MyBatisDao
public interface TaskPackDetailsDao extends CrudDao2<TaskPackDetails> {

	public TaskPackDetails findTaskPackDetailsById(String id);

	public List<WorkerInfo> findWorkerInfoByid(String id);

	
}