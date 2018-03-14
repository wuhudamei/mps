package cn.damei.common.utils;

import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.service.modules.BizBusinessStatusLogService;

public class SavelogUtils {

	// @Autowired
	// private static BizBusinessStatusLogService bizBusinessStatusLogService;
	private static BizBusinessStatusLogService bizBusinessStatusLogService = SpringContextHolder.getBean(BizBusinessStatusLogService.class);

	/**
	 * 
	 * @Title: insertBusinessStatusLog
	 * @Description: TODO
	 * @param @param empId 业务人员员工id
	 * @param @param id 业务类型ID
	 * @param @param businessType 业务类型(常量)
	 * @param @param installPlanStatus 业务状态(一般也是常量)
	 * @param @param installPlanStatusName 业务备注
	 * @param @return
	 * @return Integer
	 * @author ZhangTongWei
	 * @throws
	 */
	@Transactional(readOnly = false)
	public static Integer saveBusinessStatusLog(Integer empId, Integer id, String businessType, String installPlanStatus, String installPlanStatusName) {

		return bizBusinessStatusLogService.insertBusinessStatusLog(empId, id, businessType, installPlanStatus, installPlanStatusName);
	}
}
