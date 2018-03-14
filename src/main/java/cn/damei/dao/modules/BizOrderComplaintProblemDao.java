/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderComplaintProblem;

/**
 * 工程问题DAO接口
 * 
 * @author ztw
 * @version 2017-07-06
 */
@MyBatisDao
public interface BizOrderComplaintProblemDao extends CrudDao<BizOrderComplaintProblem> {

	List<BizOrderComplaintProblem> queryOerCoblemList(BizOrderComplaintProblem bizOrderComplaintProblem);

	List<BizOrderComplaintProblem> queryProblemdeal(BizOrderComplaintProblem bizOrderComplaintProblem2);

	BizOrderComplaintProblem queryDealPersonType1(BizOrderComplaintProblem bizOrderComplaintProblem2);

}