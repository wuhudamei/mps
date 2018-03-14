
package cn.damei.service.mobile.Manager;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackRoleScoreDao;
import cn.damei.entity.mobile.Manager.EvalScoreRole;

@Service
@Transactional(readOnly = true)
public class BizEvalTaskpackRoleScoreService extends CrudService2<BizEvalTaskpackRoleScoreDao, EvalScoreRole> {

    @Transactional(readOnly=false)
    public void insert(EvalScoreRole bizEvalScoreRole){
        dao.insert(bizEvalScoreRole);
    }

    public String queryEvalFeedback(Map<String, Object> map){
        return dao.queryEvalFeedback(map);
    }

    public Integer queryCountByMap(Map<String, Object> map){
        return dao.queryCountByMap(map);
    }
}