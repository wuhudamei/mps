package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;

import java.util.Date;

/**
 * Created by joseph on 2017/5/15.
 * 转派客服
 */
public class OrderReportLogTransferServiceImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {

        OrderReportLogEntity logEntity =new OrderReportLogEntity();
        logEntity.setOrderReportId(orderReport.getId());
        logEntity.setOperateDateTime(new Date());
        logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
        logEntity.setServiceName(orderReport.getServiceName());
        logEntity.setServicePhone(orderReport.getServicePhone());
        return logEntity;
    }
}
