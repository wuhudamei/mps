
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.entity.modules.BizOrderComplaintProblem;


@MyBatisDao
public interface BizComplaintProblemItemDao extends CrudDao<BizComplaintProblemItem> {

	List<Map<String, String>> findTypeMapByStoreId(String storeId);

	List<BizComplaintProblemItem> getcomplaintProblemTypeId(BizComplaintProblemItem bizComplaintProblemItem);

	List<BizComplaintProblemItem> getcomplaintProblemId(BizComplaintProblemItem bizComplaintProblemItem);

	List<BizComplaintProblemItem> queryComplaintProblemItem(BizOrderComplaintProblem bizOrderComplaintProblem2);

}