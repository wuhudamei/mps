package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MyOrder;

@MyBatisDao
public interface MyOrderDao extends CrudDao2<MyOrder>{
	
	public int findCount(Integer itemManagerId);

	public int findBuildingCount(Integer itemManagerId);
}
