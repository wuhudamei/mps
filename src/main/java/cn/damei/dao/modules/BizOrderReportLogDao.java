package cn.damei.dao.modules;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderReportLogEntity;

import java.util.Map;



@MyBatisDao
public interface BizOrderReportLogDao {


    void saveSendLog(OrderReportLogEntity logEntity);
    void saveInstoreLog(OrderReportLogEntity logEntity);
    void saveSignLog(OrderReportLogEntity logEntity);
    void saveLogRelatedOrderNumber(Map<String,Object> relatedOrderMap);
}
