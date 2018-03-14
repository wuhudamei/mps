package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Worker.SignDetail;
import cn.damei.entity.mobile.Worker.TaskPackSignVo;


@MyBatisDao
public interface WorkerSignDao  extends CrudDao2<TaskPackSignVo>{


	public  List<TaskPackSignVo> packByworkerLeaderId(Integer workerLeaderId);
	
	

	public SignDetail signDetailByWorkerLeaderId(Integer workerLeaderId,Integer packId);
	

	public SignDetail  getSignDetailByPackIdLimit(Integer packId);
	
	

	public void  signSuccess(SignDetail  sign);
	
	

	public TaskPackSignVo getCustomerInfoByPackId(Integer packId);
	
	

	public OrderSignVo  getAddressThroughOrder(Integer orderId);
	

	public SignDetail  setManagerInfoForWorker(Integer workerLeaderId);
	
	

public  void 	updatePackStatusById(Integer packId);


public String selectPackNameById(Integer packId);
	
}
