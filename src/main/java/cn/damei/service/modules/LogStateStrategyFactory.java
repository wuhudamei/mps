package cn.damei.service.modules;

import cn.damei.dao.modules.OrderReportLogStatePolicyInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joseph on 2017/5/15.
 */
public class LogStateStrategyFactory {

    private static LogStateStrategyFactory factory = new LogStateStrategyFactory();

    private LogStateStrategyFactory(){

    }

    private static Map<Integer,OrderReportLogStatePolicyInterface> strategyMap = new HashMap();

    static{

        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_20.value(), new OrderReportLogInvalidImpl());
        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_25.value(), new OrderReportLogDistributeServiceImpl());

        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_26.value(), new OrderReportLogTransferServiceImpl());

        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_30.value(), new OrderReportLogCustomerInStoreImpl());

        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_40.value(), new OrderReportLogInStoreWithBigOrderImpl());
        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_50.value(), new OrderReportLogSignContractImpl());
        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_55.value(), new OrderReportLogReplenishContractImpl());
        strategyMap.put(OrderReportLogStateEnum.ORDER_REPORT_LOG_STATE_90.value(), new OrderReportLogOutOfDateImpl());

    }

    public OrderReportLogStatePolicyInterface creator(String type){

        return strategyMap.get(Integer.valueOf(type));

    }

    public static LogStateStrategyFactory getInstance(){

        return factory;

    }
}
