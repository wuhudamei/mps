
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTodoItemType;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizTodoItemTypeDao extends CrudDao<BizTodoItemType> {


    List<Map<String,String>> findIdByBusinessType(String relatedBusinessType);

    List<Map<String,String>> findRelatedBusinessTypeByStoreIdProjectMode(String storeId,String projectMode);
	
}