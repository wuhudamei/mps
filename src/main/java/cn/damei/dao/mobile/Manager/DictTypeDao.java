
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.DictType;


@MyBatisDao
public interface DictTypeDao extends CrudDao<DictType> {

	List<DictType> queryListByType(String key);

}
