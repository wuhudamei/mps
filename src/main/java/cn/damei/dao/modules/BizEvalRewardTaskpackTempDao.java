
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalRewardTaskpackTemp;

import java.util.List;


@MyBatisDao
public interface BizEvalRewardTaskpackTempDao extends CrudDao2<BizEvalRewardTaskpackTemp> {

    public List<Integer> queryTaskpackTempIdByEvalRewardCfgId(Integer evalRewardCfgId);

    public List<String> queryEvalRewardTaskpackTempByRewardCfgId(Integer evalRewardCfgId);
}