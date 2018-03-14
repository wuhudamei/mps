package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProgressKanban3;

/**
 * 进度看板
 * @author llp
 * 2016/10/18
 */
@MyBatisDao
public interface ProgressKanban3Dao extends CrudDao2<ProgressKanban3>{

	List<ProgressKanban3> findOrderByStoreId(ProgressKanban3 progressKanban);

}
