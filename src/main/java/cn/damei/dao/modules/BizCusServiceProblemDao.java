
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCusServiceProblem;


@MyBatisDao
public interface BizCusServiceProblemDao extends CrudDao<BizCusServiceProblem> {

	String findPicsById(Integer id);

	int update(BizCusServiceProblem bizCusServiceProblem);

	void updateYu(BizCusServiceProblem bizCusServiceProblem);
}