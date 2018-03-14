/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardTaskpackTemp;

import java.util.List;

/**
 * 评价奖励任务包模板DAO接口
 * @author qww
 * @version 2017-02-24
 */
@MyBatisDao
public interface BizEvalRewardTaskpackTempDao extends CrudDao2<BizEvalRewardTaskpackTemp> {

    public List<Integer> queryTaskpackTempIdByEvalRewardCfgId(Integer evalRewardCfgId);

    public List<String> queryEvalRewardTaskpackTempByRewardCfgId(Integer evalRewardCfgId);
}