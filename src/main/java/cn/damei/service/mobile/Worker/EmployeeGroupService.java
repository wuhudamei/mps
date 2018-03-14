package cn.damei.service.mobile.Worker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.EmployeeGroupDao;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.entity.mobile.Worker.WorkerTaskpackageVo;

@Service
@Transactional(readOnly = true)
public class EmployeeGroupService extends CrudService2<EmployeeGroupDao,EmployeeGroup>{

	public EmployeeGroup selectEmployeeGroupByGroupId(Integer groupId) {
		
		return dao.selectEmployeeGroupByGroupId(groupId);
	}

	
	public Integer findCount(WorkerTaskpackageVo vo){
		
		
		return dao.findCount(vo);
	}


	public Integer findSignCount(WorkerTaskpackageVo vo) {
		return dao.findSignCount(vo);
	}
}
