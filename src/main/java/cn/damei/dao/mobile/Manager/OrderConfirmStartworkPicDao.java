
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartworkPic;


@MyBatisDao
public interface OrderConfirmStartworkPicDao extends CrudDao2<OrderConfirmStartworkPic>{

	

	boolean insertConfirmStartworkPic(OrderConfirmStartworkPic workPic);


	boolean saveConfirmStartPicList(List<OrderConfirmStartworkPic> startList);
	
}