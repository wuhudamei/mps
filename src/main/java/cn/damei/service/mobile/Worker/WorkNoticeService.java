package cn.damei.service.mobile.Worker;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.NodePlanDao;
import cn.damei.entity.mobile.Manager.NodePlan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkNoticeService extends CrudService2<NodePlanDao, NodePlan>{

}
