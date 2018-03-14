package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PackProcedure;
import cn.damei.entity.mobile.Manager.TaskPackage;


/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月19日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface TaskPackageDao  extends CrudDao<TaskPackage>{

	/**
	 * 查询项目经理下的订单下的所有的任务包
	 * @return
	 */
public List<TaskPackage> getAllPackage(TaskPackage  pack);

public List<String> selectStateName(Integer managerId);
/**
 * 根据id得到任务包对象
 * @param packId
 * @return
 */
public TaskPackage  getPackById(Integer packId);

/**
 * 根据 任务包id 查询 工序
 * @return
 */
public List<PackProcedure> findProcedureByPackId(Integer packId);

/**
 * 根据组长id 获取组长手机
*/
public String getLeaderPhoneById(String leaderId);

	/**
	 * 查询项目经理名下，任务包状态为【已申请完工】的任务包 
	*/
	public List<TaskPackage> queryTaskPackageByNoState(Map<String, Object> map);

	public List<TaskPackage> queryTaskPackageStateList(String type);
	
	/**
	 * 结算单管理列表
	 * @param map
	 * @return
	 */
	public List<TaskPackage> queryOrderPackageList(Map<String, Object> map);
	
	/**
	 * 实际工程量超3%给质检员发短信
	 * @param id
	 * @return
	 */
	public TaskPackage querySmsMessage(Integer id);

	/**
	 * 结算单生成后给工人组长发送短信
	 * @param id
	 * @return
	 */
	public TaskPackage querySmsMessageToGroup(Integer id);

	public TaskPackage queryRewardActivity(Integer id);
	
	public TaskPackage queryOrderTaskPackageByParam(Map<String,Object> param);
}