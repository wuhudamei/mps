package cn.damei.Quartz;

import cn.damei.entity.modules.BizProjectProgressBoning;
import cn.damei.service.modules.BizProjectProgressBoningService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;


public class ProjectProgressBoningQuartz {

    private static Logger logger = LoggerFactory.getLogger(ProjectProgressBoningQuartz.class);

    @Autowired
    public BizProjectProgressBoningService bizProjectProgressBoningService;

    public void execute() {
        List<BizProjectProgressBoning> list = bizProjectProgressBoningService.queryOrderByCondition();
        if(CollectionUtils.isNotEmpty(list)){
            for(BizProjectProgressBoning boning:list){
                try{
                    BizProjectProgressBoning projectProgressBoning = bizProjectProgressBoningService.queryByOrderId(boning.getOrderId());
                    if(projectProgressBoning != null){
                        bizProjectProgressBoningService.queryOrderAllNode(boning);
                        boning.setUpdateDate(new Date());
                        bizProjectProgressBoningService.updateByOrderId(boning);
                    }else{
                        bizProjectProgressBoningService.queryOrderAllNode(boning);
                        boning.setCreateDate(new Date());
                        boning.setUpdateDate(new Date());
                        bizProjectProgressBoningService.insert(boning);
                    }
                }catch (Exception e){
                    logger.info(e.getMessage());

                }
            }
        }
    }


}
