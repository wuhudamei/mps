
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardStar;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizEvalRewardStarDao extends CrudDao2<BizEvalRewardStar> {

    public Double queryEvalRewardStarByMap(Map<String, Object> map);

    public List<BizEvalRewardStar> queryEvalRewardStarByEvalRewardCfgId(Integer evalRewardCfgId);
}