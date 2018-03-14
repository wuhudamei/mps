
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.entity.modules.BizOrderComplaintProblem;
import cn.damei.entity.modules.BizOrderComplaintProblemItem;


@MyBatisDao
public interface BizOrderComplaintProblemItemDao extends CrudDao<BizOrderComplaintProblemItem> {

	List<BizComplaintProblemItem> queryOrderComplaintProblemItem(BizOrderComplaintProblem bizOrderComplaintProblem2);

}