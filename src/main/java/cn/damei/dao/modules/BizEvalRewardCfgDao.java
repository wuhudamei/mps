/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardCfg;

import java.util.Map;

/**
 * 评价奖励设置DAO接口
 * @author qww
 * @version 2017-02-24
 */
@MyBatisDao
public interface BizEvalRewardCfgDao extends CrudDao2<BizEvalRewardCfg> {

    public Integer queryCountByCondition(Map<String, Object> map);

    public Integer queryIdByMap(Map<String, Object> map);
}