package cn.damei.Quartz;

import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.BizProjectProgressSummaryDataService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 新订单工程进度大看板定时器
 * Created by hyh on 2017/12/13.
 */
public class BizProjectProgressSummaryDataQuartz {

    @Autowired
    private BizProjectProgressSummaryDataService bizProjectProgressSummaryDataService;

    public void execute(){
        //获取所有需要更新的订单
        List<Order2> list = bizProjectProgressSummaryDataService.queryOrderByCondition2();
        if(CollectionUtils.isNotEmpty(list)){
            for (Order2 order : list){
                try {
                    bizProjectProgressSummaryDataService.updateBizProjectProgressSummaryData(order.getId());
                } catch (Exception e) {
                    bizProjectProgressSummaryDataService.updateErrorStatus(order.getId());
                    e.printStackTrace();
                }
            }
        }
    }
}
