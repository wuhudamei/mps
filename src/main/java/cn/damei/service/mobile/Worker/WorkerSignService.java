package cn.damei.service.mobile.Worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.dao.mobile.Worker.WorkerSignDao;
import cn.damei.entity.mobile.Worker.SignDetail;
import cn.damei.entity.mobile.Worker.TaskPackSignVo;


@Service
@Transactional(readOnly=true)
public class WorkerSignService extends CrudService2<WorkerSignDao,TaskPackSignVo> {



	public  List<TaskPackSignVo> packByworkerLeaderId(Integer workerLeaderId){
		return dao.packByworkerLeaderId(workerLeaderId);
	}
	
	

	public SignDetail  signDetailByWorkerLeaderId(Integer workerLeaderId,Integer packId){
		
		return dao.signDetailByWorkerLeaderId(workerLeaderId,packId);
	}
	

	public SignDetail  getSignDetailByPackIdLimit(Integer packId){
		return dao.getSignDetailByPackIdLimit(packId);
	}
	
	

	@Transactional(readOnly=false)
	public void  signSuccess(SignDetail  sign){
		dao.signSuccess(sign);
	}
	
	

	public TaskPackSignVo getCustomerInfoByPackId(Integer packId){
		return dao.getCustomerInfoByPackId(packId);
	}
	
	


	public OrderSignVo  getAddressThroughOrder(Integer orderId){
		
		return dao.getAddressThroughOrder(orderId);
	}

	public SignDetail  setManagerInfoForWorker(Integer workerLeaderId){
		return dao.setManagerInfoForWorker(workerLeaderId);
	}

@Transactional(readOnly=false)
public  void 	updatePackStatusById(Integer packId){
	
	dao.updatePackStatusById(packId);
}

public String selectPackNameById(Integer packId){
	return dao.selectPackNameById(packId);
}
}
