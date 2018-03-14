package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderDisclosePic;

/**
 * 项目经理端
 * 现场交底
 * @author llp
 * 2016/10/17
 */
@MyBatisDao
public interface OrderDisclosePicDao extends CrudDao2<OrderDisclosePic>{

	boolean batchInsertPic(List<OrderDisclosePic> disclosePicList);

	boolean insertPic(OrderDisclosePic pic);

}
