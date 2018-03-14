/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardStar;

import java.util.List;
import java.util.Map;

/**
 * 评价奖励星级DAO接口
 * @author qww
 * @version 2017-02-24
 */
@MyBatisDao
public interface BizEvalRewardStarDao extends CrudDao2<BizEvalRewardStar> {

    public Double queryEvalRewardStarByMap(Map<String, Object> map);

    public List<BizEvalRewardStar> queryEvalRewardStarByEvalRewardCfgId(Integer evalRewardCfgId);
}