package cn.damei.dao.mobile.Worker;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.entity.mobile.Worker.WorkerTaskpackageVo;

@MyBatisDao
public interface EmployeeGroupDao extends CrudDao2<EmployeeGroup>{

	EmployeeGroup selectEmployeeGroupByGroupId(Integer groupId);
	Integer findCount(WorkerTaskpackageVo vo);
	Integer findSignCount(WorkerTaskpackageVo vo);
}
