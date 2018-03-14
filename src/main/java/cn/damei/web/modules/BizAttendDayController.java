package cn.damei.web.modules;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAttendDay;
import cn.damei.service.modules.BizAttendDayService;

@Controller
@RequestMapping(value = "${adminPath}/bizAttend/bizAttendDays/")
public class BizAttendDayController extends BaseController {

	@Autowired
	private BizAttendDayService bizAttendDayService;


	/**
	 * 修改考勤
	 * @param id
	 * @param attendType
	 * @return
	 */
	@RequiresPermissions("bizAttendBills:bizAttendBills:edit")
	@ResponseBody
	@RequestMapping(value = "updateVal")
	public String updateVal(Integer id, String attendType) {
		if (id != null) {
			BizAttendDay bizAttendDay = bizAttendDayService.get(id);

			BizAttendDay bizAttendDay2 = new BizAttendDay();
			//类型
			bizAttendDay2.setAttendType(attendType);
			bizAttendDay2.setAttendEmployeeId(bizAttendDay.getAttendEmployeeId());
			bizAttendDay2.setAttendEmployeeRole(bizAttendDay.getAttendEmployeeRole());

			bizAttendDay2.setEarlySignDate(bizAttendDay.getEarlySignDate());
			bizAttendDay2.setEarlySignReeorDistance(bizAttendDay.getEarlySignReeorDistance());
			bizAttendDay2.setId(bizAttendDay.getId());

			bizAttendDay2.setLateSignDate(bizAttendDay.getLateSignDate());
			bizAttendDay2.setLateSignErrorDistance(bizAttendDay.getLateSignErrorDistance());

			bizAttendDay2.setManagerName(bizAttendDay.getManagerName());

			bizAttendDay2.setSignTimes(bizAttendDay.getSignTimes());
			bizAttendDay2.setAttendDate(bizAttendDay.getAttendDate());
			
			bizAttendDayService.save(bizAttendDay2);

			return "1";
		}
		return "0";
	}
	
}
