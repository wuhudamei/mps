package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EvalScoreRole;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface BizEvalTaskpackRoleScoreDao extends CrudDao2<EvalScoreRole>{

    public String queryEvalFeedback(Map<String, Object> map);

    public Integer queryCountByMap(Map<String, Object> map);
}