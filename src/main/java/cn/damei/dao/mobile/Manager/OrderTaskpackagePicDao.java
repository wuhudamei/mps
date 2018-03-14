package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackagePic;

/**
 * 项目经理端
 * 现场交底
 * @author qww
 * 2016/10/17
 */
@MyBatisDao
public interface OrderTaskpackagePicDao extends CrudDao2<OrderTaskpackagePic>{

	public List<OrderTaskpackagePic> queryOrderTaskpackagePicByOrderTaskpackageId(Integer orderTaskpackageId);
	
}
