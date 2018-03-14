package cn.damei.service.mobile.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.TaskPackageDao;
import cn.damei.entity.mobile.Manager.PackProcedure;
import cn.damei.entity.mobile.Manager.TaskPackage;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月19日 下午5:00:04 类说明
 */
@Service
@Transactional(readOnly = true)
public class TaskPackageService extends CrudService<TaskPackageDao, TaskPackage> {

	/**
	 * 查询项目经理下的订单下的所有的任务包
	 * 
	 * @return
	 */
	public List<TaskPackage> getAllPackage(TaskPackage pack) {

		return dao.getAllPackage(pack);
	}
	public List<String> selectStateName(Integer managerId){
		
		return dao.selectStateName(managerId);
	}
	public TaskPackage  getPackById(Integer packId){
		
		return dao.getPackById(packId);
	}
	
	/**
	 * 根据 任务包id 查询 工序
	 * @return
	 */
	public List<PackProcedure> findProcedureByPackId(Integer packageId){
		
		return dao.findProcedureByPackId(packageId);
	}
	
	/**
	 * 根据组长id 获取组长手机
	*/
	public String getLeaderPhoneById(String leaderId){
		
		return dao.getLeaderPhoneById(leaderId);
	}
	
	/**
	 * 查询项目经理名下，任务包状态为【已申请完工】的任务包 
	*/
	public List<TaskPackage> queryTaskPackageByNoState(Integer itemManagerId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemManagerId", itemManagerId);
		List<String> list = new ArrayList<String>(); 
		list.add(ConstantUtils.ORDER_TASK_STATUS_80_VALUE);
		list.add(ConstantUtils.ORDER_TASK_STATUS_95_VALUE);
		map.put("list", list);
		return dao.queryTaskPackageByNoState(map);
	}
	
	public List<TaskPackage> queryTaskPackageStateList(String type){
		return dao.queryTaskPackageStateList(type);
	}
	
	/**
	 * 结算单管理列表
	 * @param taskPackageTemplatId
	 * @param stateId
	 * @param orderType
	 * @return
	 */
	public List<TaskPackage> queryOrderPackageList(Integer taskPackageTemplatId, String stateId, Integer orderType, Integer managerId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("managerId", managerId);
		if(taskPackageTemplatId != null){
			map.put("taskPackageTemplatId", taskPackageTemplatId);
		}
		if(StringUtils.isNotBlank(stateId)){
			map.put("stateId", stateId);
		}
		if(orderType != null){
			map.put("orderType", orderType);
		}
		return dao.queryOrderPackageList(map);
	}

	public TaskPackage queryRewardActivity(Integer id){
		return dao.queryRewardActivity(id);
	}
}
