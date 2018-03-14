package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackagePic;


@MyBatisDao
public interface OrderTaskpackagePicDao extends CrudDao2<OrderTaskpackagePic>{

	public List<OrderTaskpackagePic> queryOrderTaskpackagePicByOrderTaskpackageId(Integer orderTaskpackageId);
	
}
