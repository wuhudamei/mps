package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.MaterialScheduleQueryDao;
import cn.damei.entity.modules.MaterialScheduleQueryEntity;

@Service
@Transactional(readOnly=true)
public class MaterialScheduleQueryService extends CrudService2<MaterialScheduleQueryDao,MaterialScheduleQueryEntity> {

	
	
}
