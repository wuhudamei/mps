package cn.damei.dao.modules;

import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;

/**
 * Created by joseph on 2017/5/15.
 */
public interface OrderReportLogStatePolicyInterface{


    OrderReportLogEntity saveOrderReportLog(BizOrderReport orderReport);
}
