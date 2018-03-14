package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EvalScoreRole;


@MyBatisDao
public interface BizEvalTaskpackRoleScoreDao extends CrudDao2<EvalScoreRole>{

    public String queryEvalFeedback(Map<String, Object> map);

    public Integer queryCountByMap(Map<String, Object> map);
}