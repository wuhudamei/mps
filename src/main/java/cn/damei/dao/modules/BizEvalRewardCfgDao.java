
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardCfg;

import java.util.Map;


@MyBatisDao
public interface BizEvalRewardCfgDao extends CrudDao2<BizEvalRewardCfg> {

    public Integer queryCountByCondition(Map<String, Object> map);

    public Integer queryIdByMap(Map<String, Object> map);
}