package cn.damei.Quartz;

import cn.damei.service.modules.BizPmAttendMonthService;
import org.springframework.beans.factory.annotation.Autowired;


public class ManagerAttendQuartz {
    @Autowired
    private BizPmAttendMonthService bizPmAttendMonthService;




    public void execute(){
        bizPmAttendMonthService.insertOrderManagerId();
    }
}
