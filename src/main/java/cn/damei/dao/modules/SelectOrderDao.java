
package cn.damei.dao.modules;




import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.SelectOrder;


@MyBatisDao
public interface SelectOrderDao extends CrudDao2<SelectOrder> {
	public List<ItemManagerMap> findManagerMoreCount(Map<String,Object> map);
	public List<ItemManagerMap> findManagerMoreCount1(List<SelectOrder> list);

	public List<SelectOrder> findListMap(SelectOrder selectOrder);

}