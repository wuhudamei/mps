package cn.damei.common.utils;

import java.util.List;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.dao.modules.BizComplaintProblemTypeDao;
import cn.damei.entity.modules.BizComplaintProblemType;

/**
 * 查询分类信息
 * 
 * @ClassName: ProjectComplaint
 * @Description: TODO
 * @author ZhangTongWei
 * @date 2017年7月5日 下午12:09:15
 */
public class ProjectComplaint {

	private static BizComplaintProblemTypeDao bizComplaintProblemTypeDao = SpringContextHolder.getBean(BizComplaintProblemTypeDao.class);

	public static List<BizComplaintProblemType> getComplaintProblemTypeList() {

		List<BizComplaintProblemType> allList = bizComplaintProblemTypeDao.queryComTypeAll(null);
		return allList;

	}
}
