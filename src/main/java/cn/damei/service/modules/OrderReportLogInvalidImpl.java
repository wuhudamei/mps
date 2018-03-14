package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;

import java.util.Date;


public class OrderReportLogInvalidImpl implements OrderReportLogStatePolicyInterface {


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {


     OrderReportLogEntity  logEntity = new   OrderReportLogEntity();
        logEntity.setOrderReportId(orderReport.getId());
     logEntity.setOperateDateTime(new Date());
     return logEntity;
    }
}
