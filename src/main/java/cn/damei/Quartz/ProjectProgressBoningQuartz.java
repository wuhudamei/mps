package cn.damei.Quartz;

import cn.damei.entity.modules.BizProjectProgressBoning;
import cn.damei.service.modules.BizProjectProgressBoningService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

/**
 * Created by qww on 2016/12/1.
 */
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
                    if(projectProgressBoning != null){ // 不为空，更新
                        bizProjectProgressBoningService.queryOrderAllNode(boning);
                        boning.setUpdateDate(new Date());
                        bizProjectProgressBoningService.updateByOrderId(boning);
                    }else{ // 为空，新增
                        bizProjectProgressBoningService.queryOrderAllNode(boning);
                        boning.setCreateDate(new Date());
                        boning.setUpdateDate(new Date());
                        bizProjectProgressBoningService.insert(boning);
                    }
                }catch (Exception e){
                    logger.info(e.getMessage());
                   // throw e;
                }
            }
        }
    }

   /* public static void main(String[] arg) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date smdate=sdf.parse("2016-12-12");
        Date bdate=sdf.parse("2016-12-31");
        System.out.println(daysBetween(smdate, bdate));
    }*/
}
