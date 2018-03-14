package cn.damei.service.mobile.Worker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.WorkerDao;
import cn.damei.entity.mobile.Worker.Worker;

@Service
@Transactional(readOnly = true)


public class WorkerService extends CrudService2<WorkerDao, Worker>{

	public Worker selectWorkerByPhone(String phone) {
		return dao.selectWorkerByPhone(phone);
	}
	
	public Worker get(Integer id){
		
		return super.get(id);
	}

}


