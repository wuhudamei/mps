
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ArrangeCheckDoneDetailQuery;

import java.util.List;


@MyBatisDao
public interface ArrangeCheckDoneDetailQueryDao extends CrudDao<ArrangeCheckDoneDetailQuery> {



    List<String> findPqcByBillId(String qcBillId);
}