package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;
import cn.damei.entity.modules.BizOrderReport;

/**
 * Created by joseph on 2017/5/15.
 */
public class OrderReportLogContext {


    private OrderReportLogStatePolicyInterface strategy;



    public void  saveOrderLog(BizOrderReport orderReport) {

        strategy = LogStateStrategyFactory.getInstance().creator(orderReport.getReportStatus());

         strategy.saveOrderReportLog(orderReport);

    }



    public OrderReportLogStatePolicyInterface getStrategy() {

        return strategy;

    }



    public void setStrategy(OrderReportLogStatePolicyInterface strategy) {

        this.strategy = strategy;

    }
}