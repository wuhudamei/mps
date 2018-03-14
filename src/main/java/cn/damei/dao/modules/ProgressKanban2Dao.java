package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProgressKanban2;


@MyBatisDao
public interface ProgressKanban2Dao extends CrudDao2<ProgressKanban2>{

	List<ProgressKanban2> findOrderByStoreId(ProgressKanban2 progressKanban);

}
