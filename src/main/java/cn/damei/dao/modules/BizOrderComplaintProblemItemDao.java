/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.entity.modules.BizOrderComplaintProblem;
import cn.damei.entity.modules.BizOrderComplaintProblemItem;

/**
 * 工程事项和工程问题对照表DAO接口
 * 
 * @author ztw
 * @version 2017-07-06
 */
@MyBatisDao
public interface BizOrderComplaintProblemItemDao extends CrudDao<BizOrderComplaintProblemItem> {

	List<BizComplaintProblemItem> queryOrderComplaintProblemItem(BizOrderComplaintProblem bizOrderComplaintProblem2);

}