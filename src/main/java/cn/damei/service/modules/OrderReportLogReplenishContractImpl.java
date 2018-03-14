package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;

import java.util.Date;


public class OrderReportLogReplenishContractImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {

        OrderReportLogEntity  logEntity = new   OrderReportLogEntity();

        logEntity.setOperateDateTime(new Date());
        logEntity.setOperateEmployeeId(UserUtils.getUser().getId());

        logEntity.setRelatedOrderNumber(orderReport.getOrderNumber());
        logEntity.setLogRemarks(orderReport.getSignBillRemarks());
        logEntity.setMessageContent(orderReport.getMessageContent());
        logEntity.setOrderReportId(orderReport.getId());
        return logEntity;
    }
}
