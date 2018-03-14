package cn.damei.service.modules;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ApplyCheckEarlyWarningDetailDao;
import cn.damei.entity.modules.ApplyCheckEarlyWarningQueryDetailEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by joseph on 2017/4/7.
 */
@Service
@Transactional(readOnly = false)
public class ApplyCheckEarlyWarningQueryDetailService extends CrudService<ApplyCheckEarlyWarningDetailDao,ApplyCheckEarlyWarningQueryDetailEntity >{





}
