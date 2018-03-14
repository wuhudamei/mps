package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.PmGuaranteeMoneyLogDao;
import cn.damei.entity.mobile.Manager.PmGuaranteeMoneyLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional(readOnly=true)
public class PmGuaranteeMoneyLogService extends  CrudService2<PmGuaranteeMoneyLogDao,PmGuaranteeMoneyLog> {

    public List<PmGuaranteeMoneyLog> queryPmGuaranteeMoneyLog(Integer pmEmployeeId){
        return dao.queryPmGuaranteeMoneyLog(pmEmployeeId);
    }
}
