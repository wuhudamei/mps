package cn.damei.Quartz;

import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.BizProjectProgressSummaryDataService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BizProjectProgressSummaryDataQuartz {

    @Autowired
    private BizProjectProgressSummaryDataService bizProjectProgressSummaryDataService;

    public void execute(){

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
