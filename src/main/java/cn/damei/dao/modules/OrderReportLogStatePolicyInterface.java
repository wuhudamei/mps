package cn.damei.dao.modules;

import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;


public interface OrderReportLogStatePolicyInterface{


    OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport);
}
