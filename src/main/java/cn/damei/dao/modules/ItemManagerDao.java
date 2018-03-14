package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ItemManager;

@MyBatisDao
public interface ItemManagerDao extends CrudDao2<ItemManager>{

	Integer findTotalCountByStar(Integer id,String mode);

	List<ItemManager> findListForOrder(ItemManager manager);
	List<ItemManager> findListForOrder1(ItemManager manager);
	List<ItemManager> findListForOrderTradition(ItemManager manager);
	List<ItemManager> findListForOrderTradition1(ItemManager manager);
	
	
	
	
}
