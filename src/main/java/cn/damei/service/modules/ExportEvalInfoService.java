package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ExportEvalInfoDao;
import cn.damei.entity.modules.ExportEvalInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ExportEvalInfoService extends CrudService2<ExportEvalInfoDao, ExportEvalInfo> {
}
