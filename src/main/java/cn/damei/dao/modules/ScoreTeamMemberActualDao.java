/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScoreTeamMemberActual;

/**
 * 团队成员当前实际评分DAO接口
 * @author liwc
 * @version 2017-04-12
 */
@MyBatisDao
public interface ScoreTeamMemberActualDao extends CrudDao<ScoreTeamMemberActual> {
	
	
	/**
	 * 更新员工评分以及好评差评数
	 * @param map
	 */
	public void updateEmployee(Map<String,Object> map);
	
	/**
	 * 根据员工姓名/手机查询评分记录
	 * @param map
	 * @return
	 */
	public ScoreTeamMemberActual selectByEmployee(Map<String,Object> map);
}