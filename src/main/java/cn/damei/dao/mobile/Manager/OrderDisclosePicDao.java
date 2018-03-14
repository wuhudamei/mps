package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderDisclosePic;


@MyBatisDao
public interface OrderDisclosePicDao extends CrudDao2<OrderDisclosePic>{

	boolean batchInsertPic(List<OrderDisclosePic> disclosePicList);

	boolean insertPic(OrderDisclosePic pic);

}
