
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderComplaintProblem;


@MyBatisDao
public interface BizOrderComplaintProblemDao extends CrudDao<BizOrderComplaintProblem> {

	List<BizOrderComplaintProblem> queryOerCoblemList(BizOrderComplaintProblem bizOrderComplaintProblem);

	List<BizOrderComplaintProblem> queryProblemdeal(BizOrderComplaintProblem bizOrderComplaintProblem2);

	BizOrderComplaintProblem queryDealPersonType1(BizOrderComplaintProblem bizOrderComplaintProblem2);

}