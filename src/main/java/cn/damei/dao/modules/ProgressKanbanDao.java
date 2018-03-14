package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProgressKanban;


@MyBatisDao
public interface ProgressKanbanDao extends CrudDao2<ProgressKanban>{

	List<ProgressKanban> findOrderByStoreId(Integer storeID);

}
