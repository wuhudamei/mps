package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.unqualifiedItemsStatisticsDao;
import cn.damei.entity.modules.unqualifiedItemsStatisticsEntity;

@Service
@Transactional(readOnly=true)
public class unqualifiedItemsStatisticsService  extends CrudService<unqualifiedItemsStatisticsDao, unqualifiedItemsStatisticsEntity>{

	
	
	
}
