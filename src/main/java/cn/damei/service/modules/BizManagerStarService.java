package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizManagerStarDao;
import cn.damei.entity.modules.BizManagerStar;

@Service
@Transactional(readOnly = true)
public class BizManagerStarService extends CrudService2<BizManagerStarDao, BizManagerStar> {

	
}
