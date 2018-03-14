package cn.damei.service.mobile.Worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.dao.mobile.Worker.WorkerSignDao;
import cn.damei.entity.mobile.Worker.SignDetail;
import cn.damei.entity.mobile.Worker.TaskPackSignVo;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月22日 下午7:32:55 
* 类说明 
*/
@Service
@Transactional(readOnly=true)
public class WorkerSignService extends CrudService2<WorkerSignDao,TaskPackSignVo> {


	/**
	 * 根据工人组长id查询任务包集合
	 * @param workerLeaderId
	 * @return
	 */
	public  List<TaskPackSignVo> packByworkerLeaderId(Integer workerLeaderId){
		return dao.packByworkerLeaderId(workerLeaderId);
	}
	
	
	/**
	 * 根据工人id查询签到详情
	 * @param workerLeaderId
	 * @return
	 */
	public SignDetail  signDetailByWorkerLeaderId(Integer workerLeaderId,Integer packId){
		
		return dao.signDetailByWorkerLeaderId(workerLeaderId,packId);
	}
	
	/**
	 * 根据相关任务包id 查询签到详情 limit 1`
	 * @param packId
	 * @return
	 */
	public SignDetail  getSignDetailByPackIdLimit(Integer packId){
		return dao.getSignDetailByPackIdLimit(packId);
	}
	
	
	/**
	 * 成功签到,保存数据到签到表
	 */
	@Transactional(readOnly=false)
	public void  signSuccess(SignDetail  sign){
		dao.signSuccess(sign);
	}
	
	
	/**
	 * 根据任务包id查询顾客信息: customerName, customerMessage
	 * @param packId
	 * @return
	 */
	public TaskPackSignVo getCustomerInfoByPackId(Integer packId){
		return dao.getCustomerInfoByPackId(packId);
	}
	
	

	/**
	 * 查询任务包相关的订单的经纬度
	 * @param orderId
	 * @return
	 */
	public OrderSignVo  getAddressThroughOrder(Integer orderId){
		
		return dao.getAddressThroughOrder(orderId);
	}
	/**
	 * 根据组长id查询经理id 和name 设置给工人签到
	 * @param workerLeaderId
	 * @return
	 */
	public SignDetail  setManagerInfoForWorker(Integer workerLeaderId){
		return dao.setManagerInfoForWorker(workerLeaderId);
	}
	/**
	 * 根据任务包id更新任务包状态,改为施工中
	 * @param packId
	 */
@Transactional(readOnly=false)
public  void 	updatePackStatusById(Integer packId){
	
	dao.updatePackStatusById(packId);
}
/**
 * 查询任务包名称
 * @param packId
 * @return
 */
public String selectPackNameById(Integer packId){
	return dao.selectPackNameById(packId);
}
}
