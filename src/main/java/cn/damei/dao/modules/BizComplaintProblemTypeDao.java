
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemType;


@MyBatisDao
public interface BizComplaintProblemTypeDao extends CrudDao<BizComplaintProblemType> {


	List<BizComplaintProblemType> queryComTypeAll(BizComplaintProblemType bizComplaintProblemType);

	BizComplaintProblemType queryComTypeName(BizComplaintProblemType bizComplaintProblemType);

	List<BizComplaintProblemType> queryComTypeid(BizComplaintProblemType bizComplaintProblemType);

	BizComplaintProblemType queryIsordertaskpackag(BizComplaintProblemType bizComplaintProblemType);

	List<Map<String,String>> findPackByStoreId(Map<String, Object> mapp);
}