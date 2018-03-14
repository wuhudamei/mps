
package cn.damei.service.mobile.Manager;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackRoleIndexScoreDao;
import cn.damei.entity.mobile.Manager.EvalScoreRoleIndex;
import cn.damei.entity.mobile.Manager.SettlementEvalStore;

@Service
@Transactional(readOnly = true)
public class BizEvalTaskpackRoleIndexScoreService extends CrudService2<BizEvalTaskpackRoleIndexScoreDao, EvalScoreRoleIndex> {

    @Transactional(readOnly=false)
    public void insertBatch(List<EvalScoreRoleIndex> list){
        dao.insertBatch(list);
    }

    public List<SettlementEvalStore> querySettlementEval(Map<String, Object> map){
        return dao.querySettlementEval(map);
    }

    public Double querySumGotScoreByOrderTaskpackageId(Map<String, Object> map){
        return dao.querySumGotScoreByOrderTaskpackageId(map);
    }
}