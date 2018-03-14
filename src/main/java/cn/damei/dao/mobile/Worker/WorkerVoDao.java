package cn.damei.dao.mobile.Worker;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.WorkerVo;


@MyBatisDao
public interface WorkerVoDao extends CrudDao2<WorkerVo>{

	WorkerVo selectWorkerByPhone(String phone);
	
}
