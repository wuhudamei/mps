package cn.damei.Quartz;

import cn.damei.service.modules.BizPmAttendMonthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangkangjian
 * @Description: 记录月末最后一天换项目经理的记录
 * @date 2017/12/22 10:45
 */
public class ManagerAttendQuartz {
    @Autowired
    private BizPmAttendMonthService bizPmAttendMonthService;
    /* 0 0 0 1 * */
    /*每个月一号凌晨执行*/

    /*记录订单这个月项目经理的信息*/
    public void execute(){
        bizPmAttendMonthService.insertOrderManagerId();
    }
}
