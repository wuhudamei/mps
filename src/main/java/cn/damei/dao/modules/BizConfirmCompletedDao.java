package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizConfirmCompleted;


@MyBatisDao
public interface BizConfirmCompletedDao extends CrudDao2<BizConfirmCompleted>{

	boolean updateByOrderStatusOrCompleted(String orderstatus,String orderstatusRemark, Integer id);

	BizConfirmCompleted getByID(Integer id);

}
