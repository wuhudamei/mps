/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.TaskPackDetailsDao;
import cn.damei.entity.modules.TaskPackDetails;
import cn.damei.entity.modules.WorkerInfo;

/**
 * 任务包详情Service
 * @author zkj 
 * @version 2017-3-25
 */
@Service
@Transactional(readOnly = true)
public class TaskpackDetailService extends CrudService2<TaskPackDetailsDao,TaskPackDetails> {

	@Autowired
	private TaskPackDetailsDao dao;
	
	
	public TaskPackDetails findTaskPackDetailsById(String id) {
		// TODO Auto-generated method stub
		return dao.findTaskPackDetailsById(id);
	}


	public List<WorkerInfo> findWorkerInfoByid(String id) {
		// TODO Auto-generated method stub
		return dao.findWorkerInfoByid(id);
	}

	
}