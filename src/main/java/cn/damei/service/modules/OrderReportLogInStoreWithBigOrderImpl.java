package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;

import java.util.Date;


public class OrderReportLogInStoreWithBigOrderImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {
        OrderReportLogEntity  logEntity = new   OrderReportLogEntity();
        logEntity.setOrderReportId(orderReport.getId());
        logEntity.setOperateDateTime(new Date());
        logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
        logEntity.setLogDateTime(new Date());
        logEntity.setRelatedOrderNumber(orderReport.getOrderNumber());
        logEntity.setLogRemarks(orderReport.getSignBillRemarks());
        logEntity.setMessageContent(orderReport.getMessageContent());

        return logEntity;
    }
}
