package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.NodePlanDao;
import cn.damei.entity.mobile.Manager.NodePlan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NoticeService extends CrudService2<NodePlanDao, NodePlan>{

}
