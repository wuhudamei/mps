package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;

import java.util.Date;

/**
 * Created by joseph on 2017/5/15.
 * 已签施工合同
 */
public class OrderReportLogSignContractImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {

        OrderReportLogEntity  logEntity = new   OrderReportLogEntity();

        logEntity.setOperateDateTime(new Date());
        logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
        logEntity.setLogDateTime(new Date());
        logEntity.setRelatedOrderNumber(orderReport.getOrderNumber());
        logEntity.setOrderReportId(orderReport.getId());
        logEntity.setMessageContent(orderReport.getMessageContent());

        return logEntity;
    }
}
