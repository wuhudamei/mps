package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;

import java.util.Date;

/**
 * Created by joseph on 2017/5/15.
 *
 * 客户进店未签单
 */
public class OrderReportLogCustomerInStoreImpl implements OrderReportLogStatePolicyInterface{


    @Override
    public OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport) {

        OrderReportLogEntity logEntity =new OrderReportLogEntity();
        logEntity.setOperateDateTime(new Date());
        logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
        logEntity.setLogDateTime(orderReport.getInstoreDatetime());
        logEntity.setLogRemarks(orderReport.getInstoreRemarks());
        logEntity.setOrderReportId(orderReport.getId());
        return logEntity;



    }
}
