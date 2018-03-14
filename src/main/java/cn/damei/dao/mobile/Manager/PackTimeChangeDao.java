package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.TaskPackage;


@MyBatisDao
public interface PackTimeChangeDao  extends CrudDao<TaskPackage>{

	
	public List<TaskPackage> selectPackByManagerId(TaskPackage pack);
	public TaskPackage packDetailByPackId(Integer packId);
	public void changePackTime(TaskPackage pack);
	public String selectDiaoDuYuan();
	public String selectDiaoDuYuanPhone(Integer employeeId);
}
