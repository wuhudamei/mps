package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.SeiralnumDao;
import cn.damei.entity.modules.Seiralnum;

@Service
@Transactional(readOnly = true)
public class SeiralnumService extends CrudService<SeiralnumDao, Seiralnum>{

}
