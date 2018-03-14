
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BreakProblem;

import java.util.List;


@MyBatisDao
public interface BreakProblemDao extends CrudDao<BreakProblem> {

    List<BreakProblem> queryBreakProblemListbreakProblem(BreakProblem breakProblem);
}