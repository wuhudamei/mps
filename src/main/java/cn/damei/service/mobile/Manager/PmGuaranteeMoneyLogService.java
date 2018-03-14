package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.PmGuaranteeMoneyLogDao;
import cn.damei.entity.mobile.Manager.PmGuaranteeMoneyLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 梅浩   qww@zzhyun.cn: 
* @version 创建时间：2016年9月28日 上午10:14:21 
* 类说明 
*/

@Service
@Transactional(readOnly=true)
public class PmGuaranteeMoneyLogService extends  CrudService2<PmGuaranteeMoneyLogDao,PmGuaranteeMoneyLog> {

    public List<PmGuaranteeMoneyLog> queryPmGuaranteeMoneyLog(Integer pmEmployeeId){
        return dao.queryPmGuaranteeMoneyLog(pmEmployeeId);
    }
}
