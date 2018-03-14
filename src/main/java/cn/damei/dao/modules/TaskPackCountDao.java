package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.TaskPackCount;

@MyBatisDao
public interface TaskPackCountDao extends CrudDao<TaskPackCount>{

	List<TaskPackCount> findCount(TaskPackCount taskPackCount);

    List<TaskPackCount> findOrderTaskpack(TaskPackCount entity);

    List<TaskPackCount> findLogDatetime(Map<String,Object> map);
}
