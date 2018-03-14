package cn.damei.Quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.entity.modules.BizAttendDay;
import cn.damei.service.modules.BizAttendDayService;

/**
 * 执行上一月的考勤记录
 * @author chenguanhua
 *
 */
public class QuarzBizAttend {
	
	@Autowired
	private BizAttendDayService bizAttendDayService;
	
	
	public void execute() throws Exception{
		List<BizAttendDay> list = bizAttendDayService.pageList();
		
		bizAttendDayService.insertAttend(list);
		
		bizAttendDayService.pageList2();
		
		bizAttendDayService.pageList3();
	}
}
