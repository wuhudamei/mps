package cn.damei.common.utils;

import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.service.modules.BizBusinessStatusLogService;

public class SavelogUtils {



	private static BizBusinessStatusLogService bizBusinessStatusLogService = SpringContextHolder.getBean(BizBusinessStatusLogService.class);


	@Transactional(readOnly = false)
	public static Integer saveBusinessStatusLog(Integer empId, Integer id, String businessType, String installPlanStatus, String installPlanStatusName) {

		return bizBusinessStatusLogService.insertBusinessStatusLog(empId, id, businessType, installPlanStatus, installPlanStatusName);
	}
}
