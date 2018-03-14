/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScoreTeamMemberEvaluate;

/**
 * 团队成员评价DAO接口
 * @author liwc
 * @version 2017-04-12
 */
@MyBatisDao
public interface ScoreTeamMemberEvaluateDao extends CrudDao<ScoreTeamMemberEvaluate> {
	
	/**
	 * 根据订单iD验证当天是否对该用户进行评价
	 * @param map
	 * @return
	 */
	public int evaluateValidate(Map<String,Object> map);
}