package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Worker.SignDetail;
import cn.damei.entity.mobile.Worker.TaskPackSignVo;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月22日 下午6:31:10 
* 工人组长签到dao
*/
@MyBatisDao
public interface WorkerSignDao  extends CrudDao2<TaskPackSignVo>{

	/**
	 * 根据工人组长id查询任务包集合
	 * @param itemManagerId
	 * @return
	 */
	public  List<TaskPackSignVo> packByworkerLeaderId(Integer workerLeaderId);
	
	
	/**
	 * 根据工人id查询签到详情
	 * @param orderId
	 * @return
	 */
	public SignDetail signDetailByWorkerLeaderId(Integer workerLeaderId,Integer packId);
	
	/**
	 * 根据相关任务包id 查询签到详情 limit 1
	 * @param orderId
	 * @return
	 */
	public SignDetail  getSignDetailByPackIdLimit(Integer packId);
	
	
	/**
	 * 持久化签到数据
	 */
	public void  signSuccess(SignDetail  sign);
	
	
	/**
	 * 根据任务包id查询顾客信息: customerName, customerMessage
	 * @param orderId
	 * @return
	 */
	public TaskPackSignVo getCustomerInfoByPackId(Integer packId);
	
	
	/**
	 * 查询任务包相关的订单的经纬度
	 * @param orderId
	 * @return
	 */
	public OrderSignVo  getAddressThroughOrder(Integer orderId);
	
	/**
	 * 根据组长id查询经理id 和name 设置给工人签到
	 * @param workerLeaderId
	 * @return
	 */
	public SignDetail  setManagerInfoForWorker(Integer workerLeaderId);
	
	
	/**
	 * 根据任务包id更新任务包状态,改为施工中
	 * @param packId
	 */
public  void 	updatePackStatusById(Integer packId);

/**
 * 查询任务包名称
 * @param packId
 * @return
 */
public String selectPackNameById(Integer packId);
	
}
