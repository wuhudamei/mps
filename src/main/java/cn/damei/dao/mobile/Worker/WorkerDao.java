package cn.damei.dao.mobile.Worker;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.Worker;


@MyBatisDao
public interface WorkerDao extends CrudDao2<Worker>{

	Worker selectWorkerByPhone(String phone);
	
}
