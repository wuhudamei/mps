
package cn.damei.service.mobile.Manager;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackScoreDao;
import cn.damei.entity.mobile.Manager.EvalScore;

@Service
@Transactional(readOnly = false)
public class BizEvalTaskpackScoreService extends CrudService2<BizEvalTaskpackScoreDao, EvalScore> {

    @Transactional(readOnly=false)
    public void insert(EvalScore bizEvalScore){
        dao.insert(bizEvalScore);
    }

    public Integer queryByCondition(Map<String, Object> map){
        return dao.queryByCondition(map);
    }

    public List<EvalScore> queryEvalTaskpackScoreByMap(Map<String, Object> map){
        return dao.queryEvalTaskpackScoreByMap(map);
    }

    @Transactional(readOnly=false)
    public void update(EvalScore bizEvalScore){
        dao.update(bizEvalScore);
    }

    public Integer queryCountByMap(Map<String, Object> map){
        return dao.queryCountByMap(map);
    }
    
    public EvalScore queryEvalScoreByBusinessId(Map<String, Object> map){
    	return dao.queryEvalScoreByBusinessId(map);
    }

	public List<EvalScore> queryEvalRoleOvertimeByMap(Map<String, Object> map) {

		return dao.queryEvalRoleOvertimeByMap(map);
	}
	@Transactional(readOnly=false)
	public void updateEvalRole(EvalScore bizEvalTaskpackScore) {

		dao.updateEvalRole(bizEvalTaskpackScore);
	}

	public List<EvalScore> findEvalScoreByEvalStatus() {

		return dao.findEvalScoreByEvalStatus();
	}

	public List<EvalScore> findEvalRoleByEvalScoreId(Integer id) {

		return dao.findEvalRoleByEvalScoreId(id);
	}
}