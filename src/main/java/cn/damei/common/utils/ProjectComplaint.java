package cn.damei.common.utils;

import java.util.List;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.dao.modules.BizComplaintProblemTypeDao;
import cn.damei.entity.modules.BizComplaintProblemType;


public class ProjectComplaint {

	private static BizComplaintProblemTypeDao bizComplaintProblemTypeDao = SpringContextHolder.getBean(BizComplaintProblemTypeDao.class);

	public static List<BizComplaintProblemType> getComplaintProblemTypeList() {

		List<BizComplaintProblemType> allList = bizComplaintProblemTypeDao.queryComTypeAll(null);
		return allList;

	}
}
