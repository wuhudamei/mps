package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.MaterialWarning;
@MyBatisDao
public interface MaterialWarningDao extends CrudDao<MaterialWarning>{

	List<String> findAllDelayOrderId(int i);

	List<String> findCompleteOrderId();

	List<MaterialWarning> findCountOrder(List<String> list);

}
