package cn.damei.service.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ItemManagerDao;
import cn.damei.entity.modules.ItemManager;

@Service
@Transactional(readOnly = true)
public class ItemManagerService extends CrudService2<ItemManagerDao, ItemManager>{

	public Integer findTotalCountByStar(Integer id,String mode) {
		return dao.findTotalCountByStar(id,mode);
	}

	public List<ItemManager> findListForOrder(ItemManager manager){
		return dao.findListForOrder(manager);
	}
	public List<ItemManager> findListForOrder1(ItemManager manager){
		return dao.findListForOrder1(manager);
	}
	public List<ItemManager> findListForOrderTradition(ItemManager manager){
		return dao.findListForOrderTradition(manager);
	}
	public List<ItemManager> findListForOrderTradition1(ItemManager manager){
		return dao.findListForOrderTradition1(manager);
	}
}
