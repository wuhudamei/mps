/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScoreTeamMemberHistory;

/**
 * 团队成员历史评分DAO接口
 * @author liwc
 * @version 2017-04-12
 */
@MyBatisDao
public interface ScoreTeamMemberHistoryDao extends CrudDao<ScoreTeamMemberHistory> {
	
}