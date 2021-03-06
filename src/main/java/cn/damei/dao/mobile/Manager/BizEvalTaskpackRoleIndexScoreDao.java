package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EvalScoreRoleIndex;
import cn.damei.entity.mobile.Manager.SettlementEvalStore;


@MyBatisDao
public interface BizEvalTaskpackRoleIndexScoreDao extends CrudDao2<EvalScoreRoleIndex>{

    public void insertBatch(List<EvalScoreRoleIndex> list);

    public List<SettlementEvalStore> querySettlementEval(Map<String, Object> map);

    public Double querySumGotScoreByOrderTaskpackageId(Map<String, Object> map);
}