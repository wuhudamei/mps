package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.TaskpackageTemplatDao;
import cn.damei.entity.modules.TaskpackageTemplat;

@Service
@Transactional(readOnly=true)
public class TaskpackageTemplatService extends CrudService2<TaskpackageTemplatDao, TaskpackageTemplat>{

	public TaskpackageTemplat findByStoreId(int storeId,int projectMode) {
		
		return dao.findByStoreId(storeId,projectMode);
	}
	
}
