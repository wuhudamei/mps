package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProgressKanban2;

/**
 * 进度看板
 * @author llp
 * 2016/10/18
 */
@MyBatisDao
public interface ProgressKanban2Dao extends CrudDao2<ProgressKanban2>{

	List<ProgressKanban2> findOrderByStoreId(ProgressKanban2 progressKanban);

}
