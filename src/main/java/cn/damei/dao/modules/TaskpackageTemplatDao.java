package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.TaskpackageTemplat;
@MyBatisDao
public interface TaskpackageTemplatDao extends CrudDao2<TaskpackageTemplat>{

	TaskpackageTemplat findByStoreId(int storeId,int projectMode);
}
