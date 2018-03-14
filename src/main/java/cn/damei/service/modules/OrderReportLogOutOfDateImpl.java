package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;

import java.util.Date;


public class OrderReportLogOutOfDateImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {

        OrderReportLogEntity  logEntity = new   OrderReportLogEntity();

       logEntity.setLogDateTime(new Date());
        logEntity.setOrderReportId(orderReport.getId());
        return logEntity;
    }
}
