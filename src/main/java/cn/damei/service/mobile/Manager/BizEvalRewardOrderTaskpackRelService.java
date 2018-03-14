package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalRewardOrderTaskpackRelDao;
import cn.damei.entity.mobile.Manager.BizEvalRewardOrderTaskpackRel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class BizEvalRewardOrderTaskpackRelService extends CrudService2<BizEvalRewardOrderTaskpackRelDao, BizEvalRewardOrderTaskpackRel> {

    @Transactional(readOnly=false)
    public void insert(BizEvalRewardOrderTaskpackRel entity){
        dao.insert(entity);
    }
}