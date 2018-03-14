package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.InspectSignQueryDao;
import cn.damei.entity.modules.InspectSignQueryEntity;

@Service
@Transactional(readOnly=true)
public class InspectSignQueryService  extends CrudService<InspectSignQueryDao, InspectSignQueryEntity>{

}
