package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalActivityOrderTaskpackRelDao;
import cn.damei.entity.mobile.Manager.BizEvalActivityOrderTaskpackRel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class BizEvalActivityOrderTaskpackRelService extends CrudService2<BizEvalActivityOrderTaskpackRelDao, BizEvalActivityOrderTaskpackRel> {

    @Transactional(readOnly=false)
    public void insert(BizEvalActivityOrderTaskpackRel entity){
        dao.insert(entity);
    }
}