/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCusServiceProblem;

/**
 * 售后问题反馈详情DAO接口
 * 
 * @author joseph
 * @version 2017-06-23
 */
@MyBatisDao
public interface BizCusServiceProblemDao extends CrudDao<BizCusServiceProblem> {

	String findPicsById(Integer id);

	int update(BizCusServiceProblem bizCusServiceProblem);

	void updateYu(BizCusServiceProblem bizCusServiceProblem);
}