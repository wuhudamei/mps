package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PackProcedure;
import cn.damei.entity.mobile.Manager.TaskPackage;



@MyBatisDao
public interface TaskPackageDao  extends CrudDao<TaskPackage>{


public List<TaskPackage> getAllPackage(TaskPackage  pack);

public List<String> selectStateName(Integer managerId);

public TaskPackage  getPackById(Integer packId);


public List<PackProcedure> findProcedureByPackId(Integer packId);


public String getLeaderPhoneById(String leaderId);


	public List<TaskPackage> queryTaskPackageByNoState(Map<String, Object> map);

	public List<TaskPackage> queryTaskPackageStateList(String type);
	

	public List<TaskPackage> queryOrderPackageList(Map<String, Object> map);
	

	public TaskPackage querySmsMessage(Integer id);


	public TaskPackage querySmsMessageToGroup(Integer id);

	public TaskPackage queryRewardActivity(Integer id);
	
	public TaskPackage queryOrderTaskPackageByParam(Map<String,Object> param);
}