
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.TaskPackDetails;
import cn.damei.entity.modules.WorkerInfo;


@MyBatisDao
public interface TaskPackDetailsDao extends CrudDao2<TaskPackDetails> {

	public TaskPackDetails findTaskPackDetailsById(String id);

	public List<WorkerInfo> findWorkerInfoByid(String id);

	
}