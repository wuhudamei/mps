package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Inspector;

@MyBatisDao
public interface InspectorDao extends CrudDao2<Inspector>{

	List<Inspector> findListForOrder(Inspector inspector);
	List<Inspector> findListForOrderTradition(Inspector inspector);
	
}
