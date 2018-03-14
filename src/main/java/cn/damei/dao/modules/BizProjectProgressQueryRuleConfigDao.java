package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;

import java.util.List;


@MyBatisDao
public interface BizProjectProgressQueryRuleConfigDao extends CrudDao2<BizProjectProgressQueryRuleConfig> {
    public List<BizProjectProgressQueryRuleConfig> findFirstList();

    public List<BizProjectProgressQueryRuleConfig> findChildList(Integer parendId);

    public void deleteBatch(List<Integer> list);
}
